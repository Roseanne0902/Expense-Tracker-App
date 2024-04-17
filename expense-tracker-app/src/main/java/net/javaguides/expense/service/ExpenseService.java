package net.javaguides.expense.service;

import net.javaguides.expense.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto);
    ExpenseDto findExpenseById(Long id);
    List<ExpenseDto> getAllExpense();
    ExpenseDto updateExpense(Long id, ExpenseDto expenseDto);
    void deleteExpense(Long id);
}
