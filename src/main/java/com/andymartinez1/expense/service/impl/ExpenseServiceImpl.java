package com.andymartinez1.expense.service.impl;

import org.springframework.stereotype.Service;

import com.andymartinez1.expense.dto.ExpenseDto;
import com.andymartinez1.expense.entity.Expense;
import com.andymartinez1.expense.mapper.ExpenseMapper;
import com.andymartinez1.expense.repository.ExpenseRepository;
import com.andymartinez1.expense.service.ExpenseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseRepository expenseRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.mapToExpense(expenseDto);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }
}
