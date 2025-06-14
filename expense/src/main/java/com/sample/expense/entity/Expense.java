package com.sample.expense.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TBL_EXPENSE")
@SequenceGenerator(name = "GEN_SEQ_EXPENSE", sequenceName = "SEQ_EXPENSE", allocationSize = 10)
public class Expense {

    @Id
    @GeneratedValue(generator = "GEN_SEQ_EXPENSE", strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "CATEGORY_ID_TBL_EXPENSE_TBL_CATEGORY"))
    private Category category;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "description")
    private String description;
    @Column(name = "EXPENSE_TIME")
    private LocalDateTime expenseTime;
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;
    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;
}
