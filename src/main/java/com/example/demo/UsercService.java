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
	
	public List<Userc> selectById(int id,String password) {
		return mapper.selectById(id,password);
	}
	
	public void insert(Userc Userc) {
		mapper.insert(Userc);
	}
	
	public void update(Userc Userc) {
		mapper.update(Userc);
	}
	
	public void delete(int id) {
		mapper.delete(id);
	}

	
}
