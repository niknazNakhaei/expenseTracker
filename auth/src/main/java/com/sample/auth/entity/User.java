package com.sample.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_USER")
@SequenceGenerator(name = "SEQ_GEN_USER", sequenceName = "SEQ_USER", allocationSize = 10)
public class User {
    @Id
    @GeneratedValue(generator = "SEQ_GEN_USER", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ACTIVE")
    private Boolean active;
}
