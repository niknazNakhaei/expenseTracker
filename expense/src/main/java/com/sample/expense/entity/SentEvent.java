package com.sample.expense.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="TBL_SENT_EVENT")
@SequenceGenerator(name = "SEQ_GEN_SENT_EVENT", sequenceName = "SEQ_SENT_EVENT", allocationSize = 10)
@NoArgsConstructor
@AllArgsConstructor
public class SentEvent {

    @Id
    @GeneratedValue(generator = "SEQ_GEN_SENT_EVENT", strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "CATEGORY_ID_TBL_CATEGORY_TBL_SENT_EVENT"))
    private Category category;
    @Column(name = "EXPENSE_TIME")
    private LocalDateTime expenseTime;
    @Column(name = "PROCESSED")
    private Boolean processed;
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;
    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;
}
