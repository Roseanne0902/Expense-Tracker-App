package net.javaguides.expense.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private BigDecimal amount;

    @Column(nullable=false)
    private LocalDate expenseDate;

    //Many to one relationship, many expenses belong to one category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) //foreign key on expense table
    private Category category;
}
