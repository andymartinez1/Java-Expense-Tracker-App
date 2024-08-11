package com.andymartinez1.expense.repository;

import com.andymartinez1.expense.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
