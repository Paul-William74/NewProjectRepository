package com.Ecommerce.demo.Controller.Review;
import com.Ecommerce.demo.Service.Review.QuestionsAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewQuestionAnswerController {

    private final QuestionsAnswerService questionsAnswerService;


    @PostMapping("/post-question/{customer_id}/{product_id}/{question}")
    public ResponseEntity<?> postQuestion(
            @PathVariable final Long customer_id,
            @PathVariable final Long product_id,
            @PathVariable final String question) {
        return this.questionsAnswerService.postQuestion(customer_id, product_id, question);
    }

    @PutMapping("/edit-question/{customer_id}/{question_id}/{editedQuestion}")
    public ResponseEntity<?> editQuestion(
            @PathVariable final Long customer_id,
            @PathVariable final Long question_id,
            @PathVariable final String editedQuestion) {
        return this.questionsAnswerService.editQuestion(customer_id, question_id, editedQuestion);
    }

    @DeleteMapping("/remove-question/{customer_id}/{question_id}")
    public ResponseEntity<?> deleteQuestion(
            @PathVariable final Long customer_id,
            @PathVariable final Long question_id) {
        return this.questionsAnswerService.removeQuestion(customer_id, question_id);
    }

    @PostMapping("/colab-question/{customer_id}/{question_id}")
    public ResponseEntity<?> colabOnAQuestion(
            @PathVariable final Long customer_id,
            @PathVariable final Long question_id) {
        return this.questionsAnswerService.colabToQuestion(customer_id,question_id);
    }


    @PostMapping("/post-answer/{admin_id}/{question_id}/{response}")
    public ResponseEntity<?> postAnswer(
            @PathVariable final Long admin_id,
            @PathVariable final Long question_id,
            @PathVariable final String response) {
        return this.questionsAnswerService.postAnswer(admin_id, question_id, response);
    }
}
