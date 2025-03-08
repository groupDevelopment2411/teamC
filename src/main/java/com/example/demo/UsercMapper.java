
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
	List<Userc> selectById(int id, String password);

	@Insert("INSERT INTO usercs VALUES(#{id}, #{name}, #{password},#{age},#{start DATE},#{end DATE})")
	void insert(Userc userc);

	@Update("UPDATE usercs SET name= #{name}, password = #{password} WHERE id = #{id}")
	void update(Userc userc);

	@Delete("DELETE FROM usercs WHERE id = #{id}")
	void delete(int id);

}