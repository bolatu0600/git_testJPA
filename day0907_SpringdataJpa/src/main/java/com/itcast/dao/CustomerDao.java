package com.itcast.dao;

import com.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Long>
        , JpaSpecificationExecutor<Customer> {

    @Query(value = "from Customer where custName = ?")
    public Customer findJpql(String custName);

    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying
    public void updateJpql(Long id, String custName);


    public Customer findByCustName(String custName);

    List<Customer> findByCustNameLike(String custName);
}
