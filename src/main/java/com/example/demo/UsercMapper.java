
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
	
	    @Select("""
	        SELECT * FROM userc
	        WHERE (#{id} IS NULL OR id = #{id})
	          AND (#{name} IS NULL OR name = #{name})
	          AND (#{password} IS NULL OR password = #{password})
	    """)
	    List<Userc> search(@Param("id") Integer id,
	                       @Param("name") String name,
	                       @Param("password") String password);

	    @Delete("DELETE FROM userc WHERE id = #{id}")
	    int deleteById(@Param("id") int id);
	

		
	@Select
	("SELECT * FROM userc WHERE id  = #{id}")

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

