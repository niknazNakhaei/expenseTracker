package com.sample.expense.repository;

import com.sample.expense.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryDao extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long categoryId);
}
