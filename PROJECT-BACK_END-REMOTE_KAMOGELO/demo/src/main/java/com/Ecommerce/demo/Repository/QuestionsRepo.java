package com.Ecommerce.demo.Repository;

import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Review.Question;
import com.Ecommerce.demo.Model.User.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionsRepo extends JpaRepository<Question, Long> {

    Optional<Question> findByCustomerIdAndProductId(Long customer_id, Long product_id);
    List<Question> findByProductId(Long product_id);
}
