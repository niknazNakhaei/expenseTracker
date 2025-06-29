package com.sample.expense.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="TBL_MONTHLY_REPORT")
@SequenceGenerator(name = "SEQ_GEN_MNT_RPT", sequenceName = "SEQ_MNT_RPT", allocationSize = 10)
public class MonthlyReport {

    @Id
    @GeneratedValue(generator = "SEQ_GEN_MNT_RPT", strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "CATEGORY_ID_TBL_MNT_RPT_TBL_CATEGORY"))
    private Category category;
    @Column(name = "CUMULATIVE_AMOUNT")
    private BigDecimal cumulativeAmount;
    @Column(name = "ALERT")
    private String alert;
    @Column(name = "FROM_DATE")
    private LocalDate fromDate;
    @Column(name = "TO_DATE")
    private LocalDate toDate;
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;
    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;
}
