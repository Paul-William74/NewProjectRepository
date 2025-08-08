package com.Ecommerce.demo.Repository;
import com.Ecommerce.demo.Model.Review.Answer;
import com.Ecommerce.demo.Model.Review.Question;
import com.Ecommerce.demo.Model.User.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswersRepo extends JpaRepository<Answer,Long> {

    Optional<Answer> findByAdminIdAndQuestionId(Admin admin, Question question);
    Optional<Answer> findByQuestionId(Long question_id);
    void deleteAllByQuestionId(Long question_id);

}
