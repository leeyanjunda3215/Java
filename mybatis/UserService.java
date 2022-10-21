package service;

import entity.User;
import mapper.MyMapper;
import mapper.util;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {
    SqlSessionFactory factory = util.getSessionFactory();

    public List<User> selectALL(){
        //获取SqlSession
        SqlSession session = factory.openSession(true);
        //获取Mapper
        MyMapper mapper = session.getMapper(MyMapper.class);
        //调用方法
        List<User> users = mapper.selectAllstudent();
        //释放资源
        session.close();

        return users;
    }
}
