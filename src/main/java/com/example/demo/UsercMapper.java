
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
	("SELECT * FROM userc WHERE id LIKE CONCAT('%', #{id}, '%')")

	List<Userc> searchUsercById(@Param("id") int id) ;
	
	
	@Delete({
	    "<script>",
	    "DELETE FROM userc WHERE id IN ",
	    "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
	    "#{id}",
	    "</foreach>",
	    "</script>"
	})
	void deleteUsercsByIds(@Param("ids") List<Integer> ids);

}

