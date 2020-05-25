package com.example.demo.dao;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {
//    @Select("select * from user where id=#{id}")
//    public List<User> userByid(int id);

    @Select("select * from user where id=#{id}")
    public User userByid(@Param("id") int id);
}
