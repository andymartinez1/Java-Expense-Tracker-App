package com.andymartinez1.expense.service.impl;

import com.andymartinez1.expense.dto.ExpenseDto;
import com.andymartinez1.expense.entity.Category;
import com.andymartinez1.expense.entity.Expense;
import com.andymartinez1.expense.exceptions.ResourceNotFoundException;
import com.andymartinez1.expense.mapper.ExpenseMapper;
import com.andymartinez1.expense.repository.CategoryRepository;
import com.andymartinez1.expense.repository.ExpenseRepository;
import com.andymartinez1.expense.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.mapToExpense(expenseDto);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));
        return ExpenseMapper.mapToExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream().map((expense) -> ExpenseMapper
                .mapToExpenseDto(expense)).collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        expense.setAmount(expenseDto.amount());
        expense.setExpenseDate(expenseDto.expenseDate());
        if (expenseDto.categoryDto() != null) {
            Category category = categoryRepository.findById(expenseDto.categoryDto().id())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Expense not found with id: " + expenseDto.categoryDto().id()));

            expense.setCategory(category);
        }
        Expense updatedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));
        expenseRepository.delete(expense);
    }
}