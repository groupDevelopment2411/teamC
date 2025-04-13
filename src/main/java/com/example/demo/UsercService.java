package com.example.demo;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class UsercService {
	
	public void createUserc(@Valid Userc userc) {}
	@Autowired
	private UsercMapper mapper;
	public List<Userc> getAllUserc;

	public List<Userc> selectAll() {
		return mapper.selectAll();
	}

	public void delete(int ids) {
		mapper.delete(ids);
	}

	public List<Userc> findUsercByIdAndPassword(String numId, String password) {
		return mapper.findUsercByIdAndPassword(numId, password);}

	public List<Userc> searchUsercById(int id) {
		 return mapper.searchUsercById(id);}
	

public void deleteUsercsByIds(List<Integer> id) {
    mapper.deleteUsercsByIds(id);
}

public List<Userc> getAllUsercs() {
	// TODO 自動生成されたメソッド・スタブ
	return null;
}

public List<Userc> findUsersByIds(List<Integer> selectedIds) {
	// TODO 自動生成されたメソッド・スタブ
	return null;
}

public Userc findById(int id) {
	// TODO 自動生成されたメソッド・スタブ
	return null;
}

public List<Userc> selectByKeyword(String userc) {
	// TODO 自動生成されたメソッド・スタブ
	return null;
}

}


