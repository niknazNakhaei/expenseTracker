package com.sample.expense.entity;

import com.sample.expense.entity.enumeration.CategoryType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TBL_CATEGORY")
@SequenceGenerator(name = "GEN_SEQ_CATEGORY", sequenceName = "SEQ_CATEGORY", allocationSize = 10)
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(generator = "GEN_SEQ_CATEGORY", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;
}
