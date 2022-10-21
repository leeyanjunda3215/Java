package mapper;


import entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MyMapper {
//    @Insert("insert into user(nickname,context,ctime) values(#{nickname},#{context},#{ctime})")
//    int addContext(User user);
//
//    @Select("select * from user")
//    List<User> selectALL();
//
//    @Select("select * from user where id=#{id}")
//    User getUserById(int id);
//
//
//    @Select("select * from userBean where name=#{name}")
//    Boolean getUserByname(String name);
//
//    @Insert("insert into userBean(name,password,email) values(#{name},#{password},#{email})")
//    int addUserBean(UserBean userBean);
//int ，表示执行insert后，影响数据库的行数

    @Select("select * from xsb")
    List<User> selectAllstudent();

    @Insert("insert into xsb(name,snumber,sex) values(#{name},#{snumber},#{sex})")
    int addUser(User user);
}
