
package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsercMapper {

	@Select("SELECT * FROM userc")
	List<Userc> selectAll();

	@Select("SELECT * FROM userc WHERE id = #{numId} AND password =#{password}")
	List<Userc> findUsercByIdAndPassword(String numId, String password);
	

	@Delete("DELETE FROM userc WHERE id = #{id}")
	void delete(int id);

		
	@Select
	("SELECT * FROM userc WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Userc> searchUsercByName(@Param("name") String name) ;
}