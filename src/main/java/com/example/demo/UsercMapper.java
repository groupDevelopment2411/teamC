
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

	@Select("SELECT * FROM userc WHERE id = #{id} AND password =#{password}")
	List<Userc> findUsercByIdAndPassword(@Param("id")String numId,@Param("password") String password);

	@Delete("DELETE FROM userczs WHERE id = #{id}")
	void delete(int id);

}