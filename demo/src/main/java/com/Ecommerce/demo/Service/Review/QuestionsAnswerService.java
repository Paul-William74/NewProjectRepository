package com.Ecommerce.demo.Service.Review;
import com.Ecommerce.demo.Components.Publisher.NotificationHandler;
import com.Ecommerce.demo.Exception.User.AdminNotFoundException;
import com.Ecommerce.demo.Exception.User.CustomerNotFoundException;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Review.Answer;
import com.Ecommerce.demo.Model.Review.CollaborationQuestion;
import com.Ecommerce.demo.Model.Review.Question;
import com.Ecommerce.demo.Model.User.Admin;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Repository.AnswersRepo;
import com.Ecommerce.demo.Repository.CollaborationQuestionRepo;
import com.Ecommerce.demo.Repository.QuestionsRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionsAnswerService extends BaseService {

    private final QuestionsRepo questionsRepo;
    private final AnswersRepo answersRepo;
    private final CollaborationQuestionRepo collaborationQuestionRepo;
    private final NotificationHandler notificationHandler;



    public ResponseEntity<?> postQuestion(Long customer_id, Long product_id, String questionAsked) {

        Customer customer = findCustomer(customer_id); //find the customer
        Product product = findProduct(product_id); //find the product

        try {

            Question question = new Question(questionAsked, product, customer); //create the question
            this.questionsRepo.save(question); //save the question the user is asking
            return ResponseEntity.ok("Question Posted");
        }catch (DataIntegrityViolationException exception) {
            return ResponseEntity.badRequest().body("Question Has already Been Posted");
        }


    }

    public ResponseEntity<?> editQuestion(Long customer_id, Long question_id, String editedQuestion) {

        Customer customer = findCustomer(customer_id); //to make sure they are the one doing this action
        Optional<Question> optionalQuestion = this.questionsRepo.findById(question_id);

        if(optionalQuestion.isEmpty())
            return ResponseEntity.badRequest().body("Question Does not Exist");

        Question question = optionalQuestion.get();
        if(!question.getQuestionAsked().equals(editedQuestion))
            question.setQuestionAsked(editedQuestion);

        this.questionsRepo.save(question); //save that edited/new Question
        return ResponseEntity.ok("Question Edited: " + question.getQuestionAsked());
    }

    @Transactional
    public ResponseEntity<?> removeQuestion(Long customer_id, Long question_id) {

        Customer customer = findCustomer(customer_id); //to make sure they are the one doing this action

        Optional<Question> optionalQuestion = this.questionsRepo.findById(question_id);
        if(optionalQuestion.isEmpty())
            return ResponseEntity.badRequest().body("Question Does not Exist");

        this.answersRepo.deleteAllByQuestionId(question_id);
        this.collaborationQuestionRepo.deleteAllByQuestionId(optionalQuestion.get().getId());//delete all the collaborations
        this.questionsRepo.delete(optionalQuestion.get());
        return ResponseEntity.ok("Question Deleted");
    }

    public ResponseEntity<?> colabToQuestion(Long customer_id, Long question_id) {

        Customer customer;
        try {
            customer = findCustomer(customer_id);
        }catch (CustomerNotFoundException ex) {
            return new ResponseEntity<>("Customer Does Not Exist" , HttpStatus.UNAUTHORIZED);
        }

        Optional<Question> optionalQuestion = this.questionsRepo.findById(question_id);
        if(optionalQuestion.isEmpty())
            return ResponseEntity.badRequest().body("Question Does Not Exist");

        //create a new collaboration on the question
        CollaborationQuestion collaborationQuestion = new CollaborationQuestion(optionalQuestion.get(), customer);

        try {
            this.collaborationQuestionRepo.save(collaborationQuestion);
        }catch (DataIntegrityViolationException exception) {
            return ResponseEntity.badRequest().body("Collaboration Already Exists");
        }
        return new ResponseEntity<>("Collaboration on " + optionalQuestion.get().getQuestionAsked()  +
                " by " + customer.getFirstName(), HttpStatus.OK);


    }

    @Transactional
    public ResponseEntity<?> postAnswer(Long admin_id, Long question_id, String responseAnswer) {

        Admin admin;
        try {
            admin = findAdmin(admin_id);
        }catch (AdminNotFoundException e) {
            return new ResponseEntity<>("Admin Does Not Exist", HttpStatus.UNAUTHORIZED);
        }

        Optional<Question> questionOptional = this.questionsRepo.findById(question_id);
        if(questionOptional.isEmpty())
            return ResponseEntity.badRequest().body("Question Does Not Exist");

        Question question =  questionOptional.get(); //get the posted question
        question.setHasBeenAnswered(true); //set the question to be answered

        //generate an answer for the question that was posted
        Answer answer = new Answer(responseAnswer, admin, question);
        this.answersRepo.save(answer);//save the answer to the database
        this.questionsRepo.save(question); //save the updated question

        //get all the collaborators for that question
        List<Customer> customers = new java.util.ArrayList<>(this.collaborationQuestionRepo.findByQuestionId(question.getId())
                .stream()
                .map(CollaborationQuestion::getCustomer)
                .toList()); //gets the collaboration objects for that question then gets all the customers from that list

        customers.add(question.getCustomer()); //add the person whom asked as well
        this.notificationHandler.publishNotificationToCollaborators(customers, answer.getQuestion().getProduct().getName()); //let them know that the response is available

        return ResponseEntity.ok("Answer Posted Successfully to: " + question.getCustomer().getFirstName());
    }

    public ResponseEntity<?> editAnswer(Long admin_id, Long question_id, String editedResponse) {

        Admin admin;
        try {
            admin = findAdmin(admin_id);
        }catch (AdminNotFoundException e) {
            return new ResponseEntity<>("Admin Does Not Exist", HttpStatus.UNAUTHORIZED);
        }

        Optional<Question> questionOptional = this.questionsRepo.findById(question_id);
        if(questionOptional.isEmpty())
            return ResponseEntity.badRequest().body("Question Does Not Exist");

        Question question =  questionOptional.get(); //get the posted question
        Optional<Answer> optionalAnswer = this.answersRepo.findByAdminIdAndQuestionId(admin, question); //look for the Answer to that question
        if(optionalAnswer.isEmpty())
            return ResponseEntity.badRequest().body("Answer Does not Exist");


        Answer answer = optionalAnswer.get(); //get the answer
        answer.setResponseAnswer(editedResponse); //set the new response
        this.answersRepo.save(answer); //save that answer with its new changes
        return ResponseEntity.ok("Answer Was Successfully Edited");
    }

}
