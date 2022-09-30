package com.project.Assesment.dao;

import com.project.Assesment.entity.Activity;
import com.project.Assesment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("""
            SELECT act
            FROM Activity AS act
            WHERE act.activityId = :activityId
                AND act.status = 'Pending'
                AND act.customer.account.username = :username
            """)

//    @Query("""
//            SELECT his
//            FROM Activity AS his
//                LEFT JOIN his.activity AS act
//            WHERE act.activityId = :activityId
//                AND act.status = 'Pending'
//                AND act.customer.account.username = :username
//            """)
    Activity getActivitySuccess(@Param("activityId") Long activityId,
                                @Param("username") String username);
}
