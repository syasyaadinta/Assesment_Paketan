package com.project.Assesment.dao;

import com.project.Assesment.entity.Customer;
import com.project.Assesment.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
