package com.example.demo;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Userc {
    @NotNull(message = "IDを入力してください")
    private Integer id;

    @NotNull(message = "パスワードを入力してください")
    private String password;
    private String confirmPassword;
   
    private String name;
    private int age;
    private LocalDate startDate;
    private LocalDate endDate;
    

    public Userc() {}

    public Userc(Integer id, String name, String password, int age, LocalDate startDate, LocalDate endDate, String confirmPassword) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.age = age;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
