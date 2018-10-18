package com.alvism.springsecurity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String email;

}