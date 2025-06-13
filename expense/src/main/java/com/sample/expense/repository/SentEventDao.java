package com.sample.expense.repository;

import com.sample.expense.entity.SentEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentEventDao extends JpaRepository<SentEvent, Long> {
}
