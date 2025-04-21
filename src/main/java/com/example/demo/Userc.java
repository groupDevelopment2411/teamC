package com.example.demo;

import lombok.Data;

@Data
public class Userc {

	private Integer id;
	private String password;
	private String name;

	public Userc() {
	}

	public Userc(Integer id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;

	}
}
