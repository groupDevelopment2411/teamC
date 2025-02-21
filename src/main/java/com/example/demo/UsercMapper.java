
package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UsercMapper {
	@Select("SELECT * FROM usercs")
	List<Userc> selectAll();
	
	@Select("SELECT * FROM usercs WHERE id = #{id}")
	List<Userc> selectById(int id);
	
	@Insert("INSERT INTO usercs VALUES(#{id}, #{name}, #{password})")
	void insert(Userc member);
	
	@Update("UPDATE usercs SET name= #{name}, password = #{password} WHERE id = #{id}")
	void update(Userc member);
	
	@Delete("DELETE FROM usercs WHERE id = #{id}")
	void delete(int id) ;
}

