package com.service;

import com.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    /**
     * 保存用户
     * @param user
     * @return
     */
    public boolean save(User user);

    /**
     *修改用户
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     *按照id删除用户
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     *根据id查找用户
     * @param id
     * @return
     */
    public User getById(Integer id);

    /**
     *查询所有用户
     * @return
     */
    public List<User> getAll();
}
