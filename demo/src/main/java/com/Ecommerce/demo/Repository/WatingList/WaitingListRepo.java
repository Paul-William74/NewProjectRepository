package com.Ecommerce.demo.Repository.WatingList;

import com.Ecommerce.demo.Model.WaitingList.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WaitingListRepo extends JpaRepository<WaitingList,Long> {
}
