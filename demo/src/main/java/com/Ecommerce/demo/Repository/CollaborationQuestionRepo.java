package com.Ecommerce.demo.Repository;

import com.Ecommerce.demo.Model.Review.CollaborationQuestion;
import com.Ecommerce.demo.Model.Review.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaborationQuestionRepo extends JpaRepository<CollaborationQuestion,Long> {
    List<CollaborationQuestion> findByQuestionId(Long question_id);
    void deleteAllByQuestionId(Long question_id);
}
