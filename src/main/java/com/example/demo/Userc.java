package com.example.demo;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
@Data
public class Userc {
	@NotNull(message = "Id cannot be null")
	private int id;
	private String name;
	private String password;
	
	public Userc() {};
	
	public Userc(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
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


