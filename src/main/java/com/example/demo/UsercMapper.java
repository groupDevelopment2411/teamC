
package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsercMapper {


	@Select("SELECT * FROM userc")
	List<Userc> selectAll();

	@Select("SELECT  FROM userc id=#{id},password=#{password}")
	List<Userc> selectByIdAndPassword(int id,String password);


	@Delete("DELETE FROM userc WHERE id = #{id}")
	void delete(int id);

}