package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {

    @Insert("insert into User (name,password,email,role) values(#{name},#{password},#{email},#{role})")
    public void save(User user);

    @Update("update User set name=#{name}, password=#{password}, email=#{email}, role=#{role} where id=#{id}")
    public void update(User user);

    @Delete("delete from User where id=#{id}")
    public void delete(Integer id);

    @Select("select * from User where id=#{id}")
    public User getById(Integer id);

    @Select("select * from User ")
    public List<User> getAll();

}
