package com.sample.expense.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="TBL_SENT_EVENT")
@SequenceGenerator(name = "SEQ_GEN_SENT_EVENT", sequenceName = "SEQ_SENT_EVENT", allocationSize = 10)
public class SentEvent {

    @Id
    @GeneratedValue(generator = "SEQ_GEN_SENT_EVENT", strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "CATEGORY_ID_TBL_CATEGORY_TBL_SENT_EVENT"))
    private Category category;
    @Column(name = "PROCESSED")
    private Boolean processed;
    @Column(name = "CREATION_TIME")
    private LocalDateTime creationTime;
    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;
}
