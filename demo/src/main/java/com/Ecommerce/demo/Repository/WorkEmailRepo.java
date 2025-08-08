package com.Ecommerce.demo.Repository;

import com.Ecommerce.demo.Model.WorkEmail.WorkEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkEmailRepo extends JpaRepository<WorkEmail,Long> {
    Optional<WorkEmail> findByEmailIgnoreCase(String email);
}
