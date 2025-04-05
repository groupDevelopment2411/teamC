package com.example.demo;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Userc {
	 @NotNull(message = "IDを入力してください")
     private Integer id; // 👈 int → Integer に変更

     @NotNull(message = "パスワードを入力してください")
     private String password;

     private String name;
    
    // デフォルトコンストラクタ
    public Userc() {}

    // パラメータ付きコンストラクタ
    public Userc(Integer id, String name, String password) { // int → Integer に変更
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // ゲッターとセッター
    public Integer getId() { // int → Integer に変更
        return this.id;
    }

    public void setId(Integer id) { // int → Integer に変更
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    
       
    }

}

