package net.javaguides.expense.repository;

import net.javaguides.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // spring jpa provides implementation for this interface
    // CRUD method to perform CRUD database operation on expense Entity
    // Spring Data Jpa provides transaction for all the CRUD method
}
