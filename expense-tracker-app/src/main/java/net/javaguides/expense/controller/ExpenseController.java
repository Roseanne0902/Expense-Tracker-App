package net.javaguides.expense.controller;

import net.javaguides.expense.dto.ExpenseDto;
import net.javaguides.expense.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService=expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> addExpense (@RequestBody ExpenseDto expenseDto){
        ExpenseDto savedExpense = expenseService.createExpense(expenseDto);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExpenseDto> findById (@PathVariable Long id){
        // ExpenseDto savedExpense = expenseService.findExpenseById(id);
        return ResponseEntity.ok(expenseService.findExpenseById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAll (){
        return ResponseEntity.ok(expenseService.getAllExpense());
    }

    @PutMapping("{id}/update")
    public ResponseEntity<ExpenseDto> updateExpense (@PathVariable Long id,
                                                     @RequestBody ExpenseDto expenseDto){
        return ResponseEntity.ok(expenseService.updateExpense(id, expenseDto));
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteExpense (@PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("the expense is successfully deleted");
    }
}
