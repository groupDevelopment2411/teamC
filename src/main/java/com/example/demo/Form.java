package com.example.demo;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

class Form implements Serializable{

	private static final long serialVersionUID =1L;
	
	@NotNull
	private int id;
	

	@NotNull
	
	private String password;}