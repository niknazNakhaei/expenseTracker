package com.sample.expense.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TBL_CATEGORY")
@SequenceGenerator(name = "GEN_SEQ_CATEGORY", sequenceName = "SEQ_CATEGORY", allocationSize = 10)
public class Category {

    @Id
    @GeneratedValue(generator = "GEN_SEQ_CATEGORY", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "USER_ID")
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", foreignKey = @ForeignKey(name = "PARENT_ID_TBL_CATEGORY_TBL_CATEGORY"))
    private Category parent;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;
}
