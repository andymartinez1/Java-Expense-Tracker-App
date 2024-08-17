package com.andymartinez1.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.andymartinez1.expense.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
