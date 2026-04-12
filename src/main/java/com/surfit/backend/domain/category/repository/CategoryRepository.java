package com.surfit.backend.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surfit.backend.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
