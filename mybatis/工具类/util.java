package mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class util {

    private static SqlSessionFactory sqlSessionFactory;

//SqlSessionFactory也只能执行一次
    //静态代码块会随者类的加载自动执行，且只执行一次。
static {
    try {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static SqlSessionFactory getSessionFactory(){
        return sqlSessionFactory;
    };

}
