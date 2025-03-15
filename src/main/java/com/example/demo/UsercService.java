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

	public List<Userc> selectByIdAndPassword(int id,String password) {
		return mapper.selectByIdAndPassword(id,password);
	}

	public void delete(int id) {
		mapper.delete(id);
	}

}
