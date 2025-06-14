package com.sample.expense.repository;

import com.sample.expense.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CategoryDao extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    Optional<Category> findById(Long categoryId);
}
