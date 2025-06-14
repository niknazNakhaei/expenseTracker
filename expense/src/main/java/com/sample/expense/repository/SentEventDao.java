package com.sample.expense.repository;

import com.sample.expense.entity.SentEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SentEventDao extends JpaRepository<SentEvent, Long> {

    Optional<SentEvent> findByCategory_idAndExpenseTimeBetween(Long categoryId, LocalDateTime fromDate, LocalDateTime toDate);
}
