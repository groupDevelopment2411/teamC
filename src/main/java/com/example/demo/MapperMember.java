
package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MapperMember {
	@Select("SELECT * FROM member")
	List<Member> selectAll();
}
