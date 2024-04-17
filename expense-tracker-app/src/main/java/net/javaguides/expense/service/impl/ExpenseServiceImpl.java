package net.javaguides.expense.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.expense.dto.ExpenseDto;
import net.javaguides.expense.entity.Category;
import net.javaguides.expense.entity.Expense;
import net.javaguides.expense.exceptions.ResourceNotFoundException;
import net.javaguides.expense.mapper.ExpenseMapper;
import net.javaguides.expense.repository.CategoryRepository;
import net.javaguides.expense.repository.ExpenseRepository;
import net.javaguides.expense.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseRepository expenseRepository;
//    public  ExpenseServiceImpl(ExpenseRepository expenseRepository){
//        this.expenseRepository = expenseRepository;
//    }

    private CategoryRepository categoryRepository;
//    public ExpenseServiceImpl(CategoryRepository categoryRepository){
//        this.categoryRepository = categoryRepository;
//    }
    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.MapToExpense(expenseDto);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.MapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto findExpenseById(Long id) {
        Expense expense = expenseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The expense not exist ƯITH id "+id));
        return ExpenseMapper.MapToExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpense() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream().map(expense->ExpenseMapper.MapToExpenseDto(expense)).collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("cant find the expense ưith id"+id));
        expense.setExpenseDate(expenseDto.expenseDate());
        if (expenseDto.categoryDto()!=null){
            Category category = categoryRepository.findById(expenseDto.categoryDto().id())
                    .orElseThrow(()->new ResourceNotFoundException("cant find the category with id"+expenseDto.categoryDto().id()));
            expense.setCategory(category);
        }
        expense.setAmount(expenseDto.amount());
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.MapToExpenseDto(savedExpense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()->new ResourceNotFoundException("cannot find the expense with id"+expenseId));
        expenseRepository.delete(expense);
    }
}
