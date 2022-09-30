package com.project.Assesment.dao;

import com.project.Assesment.entity.Account;
import com.project.Assesment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("""
            SELECT cs
            FROM Customer AS cs
                JOIN cs.account AS acc
            WHERE acc.username = :username
            """)
    Customer findByUsername(@Param("username") String username);
}
