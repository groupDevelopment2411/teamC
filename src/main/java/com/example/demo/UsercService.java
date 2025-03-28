package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsercService {
	@Autowired
	private UsercMapper mapper;

	public List<Userc> selectAll() {
		return mapper.selectAll();
	}
	
	public void delete(int id) {
		mapper.delete(id);
	}

	public List<Userc> findUsercByIdAndPassword(String numId, String password){
		return mapper.findUsercByIdAndPassword(numId,password);}

	public List<Userc> selectByName(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.selectByName(name);
	}
	}

