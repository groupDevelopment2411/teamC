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

	public List<Userc> selectById(int id) {
		return mapper.selectById(id);
	}

	public void delete(int id) {
		mapper.delete(id);
	}

}
