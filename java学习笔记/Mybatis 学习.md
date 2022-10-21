# Mybatis

Mybatis是持久层框架，用于简化JDBC的开发。

持久层：负责将数据保存到数据库的那一层代码

三层架构：表现层，业务层，持久层

# Mybatis-JavaWeb的深入学习

为注解开发学习

## 1.导入pom坐标

```xml
		  <dependency>
	           <groupId>org.mybatis</groupId>
	           <artifactId>mybatis</artifactId>
	           <version>3.4.5</version>
	       </dependency>
	       <dependency>
	           <groupId>mysql</groupId>
	           <artifactId>mysql-connector-java</artifactId>
	           <version>5.1.43</version>
	       </dependency>

```

## 2.创建mybatis的配置文件

只需更改数据库，mapper的路径

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/javalx"/>
                <property name="username" value="root"/>
                <property name="password" value="liyanjun"/>
            </dataSource>
        </environment>
    </environments>

  <mappers>
    <mapper resource="org/mybatis/example/BlogMapper.xml"/>
  </mappers>
</configuration>
```

### mapper映射表

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.mybatis.example.BlogMapper">
  <select id="selectBlog" resultType="Blog">
    select * from Blog where id = #{id}
  </select>
 </mapper>
```



## 3.读取xml配置文件,执行sql

```java
    public static void main(String[] args) throws IOException {
        
        //1.加载mybatis的核心配置文件，获取SqlSessionFactory,只需读取一次
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象，用他来执行Sql
        SqlSession session = sqlSessionFactory.openSession();

        //3.执行sql             名称空间.id(sql语句的id)
        List<User> Users = session.selectList("test.selectAll");
        
         /**
         * 如果使用映射表
         *
         * <mapper namespace="test">
         *      <select id="selectAll"  resultType="entity.User">
         *              select * from xsb;
         *      </select>
         * </mapper>
         *
         */
        
        //4.释放资源
        session.close();
    }
```

##  4.mapper代理开发

就是利用接口代理对象

```java
//1.创建mapper接口
public interface MyMapper {
    @Select("select * from xsb")
    List<User> selectAllstudent();
}
//2.在主函数使用MyMapper接口。
  //加载mybatis的核心配置文件，获取SqlSessionFactory,只需读取一次
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

  //获取SqlSession对象，用他来执行Sql
SqlSession session = sqlSessionFactory.openSession();

MyMapper mapper = session.getMapper(MyMapper.class);
List<User> Users = session.selectAllstudent();

 session.close();
```

### 数据库表的字段名称	和	实体类的属性名称 不一致 则不能自动封装数据

​	解决方法：

​		resultMap

```xml
<resultMap id="UserRM" type="User">
    完成主键的映射
    <id></id>
    完成其他列的映射
    column为数据库的表的列名  property为实体类的属性名
    <result column="user_name" property="username"></result>
</resultMap>

<mapper namespace="test">
    						使用resultMap属性替换resulttype属性
               <select id="selectAll"  resultMap="UserRM"> 
                       select  
                 			 *
                   from User;
               </select>
</mapper>
```

## 5.sql条件查询

参数占位符号

 #{}：会将其替换为？，为了防止sql注入。

${}：会将其替换为 1，为单纯的拼接sql，有sql注入的风险。

```sql
select * from User where id = #{id};
```

## 6.接收多个参数

```xml
<mapper namespace="test">
    						使用resultMap属性替换resulttype属性
               <select id="selectAll"  resultMap="UserRM"> 
        select  
          *
       from User where id=#{id} and username like #{username};
               </select>
</mapper>
```

```java
//使用@Param注解  @Param("SQL参数占位符的名称")
List<User> selectByidAndUsername(@Param("id") int id,@Param("username") string username)
    
    /**
    username为模糊查询，使用关键字like
    String username = "王五";
    处理这个参数
    username = "%" + username + "%"; 
    */
//或者 将其封装成为一个Map对象,key为SQL参数占位符的名称，value为值
List<User> selectByidAndUsername(Map map);     
```

## 7.动态SQL

mybatis对动态SQL的支持

### if：多条件查询

局限性，不输入id，sql语句会多一个and，造成错误。

​		可使用恒等式 1=1来解决

```xml-dtd
<select id="selectAll"  resultMap="UserRM"> 
        select  
          *
       from User 
	   where 

<if test="id != null" >
		id=#{id} 
</if>		
	
<if test="username != null and username != ''">
		and 		username like #{username};
</if>
 </select>
```

where ：替换where关键字

```xml
<select id="selectAll"  resultMap="UserRM"> 
        select  
          *
       from User 
	   <where> 

<if test="id != null" >
		and 	id=#{id} 
</if>		
	
<if test="username != null and username != ''">
			and 	username like #{username};
</if>
    </where> 
 </select>
```

### choose(when,otherwise)：单条件动态查询

```xml
<select id="selectAll"  resultMap="UserRM"> 
        select  
          *
       from User 
	 where 
<choose>
<when test="id != null" >
	id=#{id} 
</when>		
	
<when test="username != null and username != ''">
	username like #{username};
</when>
 <otherwise>
     1=1
 </otherwise>
</choose> 
 </select>
```

### foreach：批量删除

参数：id数组

collection:遍历哪个集合或者数组

item:遍历出来的元素

```xml
<!-- mybatis会将数组参数，封装为一个map集合，
默认下key的名称为 array ，value才为数组-->
void  delUserbyIds(int ids)

<delete id="deleteByid">
	delect from User
    where id in
    
    <foreach collection="array" item="id" separator="," open="(" close=")">
    	#id
    </foreach>
</delete>
```





## 8.事务的提交

```xml
<insert id="add">
insert into xsb(name,snumber,sex) values(#{name},#{snumber},#{sex})
</insert>
```

插入后没有自动提交事务，需要手动提交事务。

```java
SqlSession session = sqlSessionFactory.openSession();

MyMapper mapper = session.getMapper(MyMapper.class);
List<User> Users = session.addstudent(user);

//提交事务
session.commit()
 session.close();
```

或者

```java
//在sqlSession里可以打开自动提交事务openSession(true)
SqlSession session = sqlSessionFactory.openSession(true);

MyMapper mapper = session.getMapper(MyMapper.class);
List<User> Users = session.addstudent(user);

 session.close();
```

## 9.主键返回

在数据添加成功后，需要获取插入数据库数据的主键的值。

```xml
				使用id指向插入的这条的数据的主键
<insert id="add" useGeneratedKeys="true" keyProperty="id">
insert into xsb(name,snumber,sex) values(#{name},#{snumber},#{sex})
</insert>
```

## 10.Mybatis参数传递

Mybatis接口可以接收单个参数和多个参数。

多个参数可以使用 @Param 绑定sql中的占位字符。

多个参数会被Mybatis封装为map集合,使用 @Param ,设置的值就为map的key，传递的值为value。

单个参数，可为实体类，Map集合，List，Array，Collection。

单个参数中的List，Array，Collection也通通被封装为Map集合。

## 11.注解开发

注解只能完成简单增删改查，复杂的使用xml来映射。

## 12.SqlSessionFactory的工具类

```java
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
```

在添加一个Service层，在Service层里面使用这个工具类。





# Mybatis -Spring的深入学习

使用spring框架来使用Mybatis

导入pom坐标：

```xml
 <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.7</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.13</version>
        </dependency>
```

1.Mybatis 的配置文件 mybatis-config.xml 可在spring主配置类中

注册为bean（利用注解）

首先，使用DataSource类确定与数据库的连接

```java
    @Bean
    public DataSource dataSource(){
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/book_mange");
        dataSource.setUsername("root");
        dataSource.setPassword("liyanjun");
        return dataSource;
    }
```

2.在使用SqlSessionFactoryBean类来创建session工程，同样可注册为bean。

SqlSessionFactoryBean要引入Sql配置，可用上面注册的DataSource的bean，利用注解@@Autowired 将DataSource的bean注入。

```java
@Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }
```



3.Mybatis中的mapper还是写一个mapper接口，用来写入sql语句和相对应的方法。

为了解耦，可使用service层，写一个service的接口，在接口中写要实现的方法。在写一个实现类implement，继承service接口,同时也注册为bean，实现接口中的方法。也可将Mapper注册为bean（在spring配置类中添加注解@MapperScan("mapper")），在实现类中注入mapper。

```java
@Component
public class TestServiceImpl implements TestService{


    @Resource
    TestMapper mapper;

    @Override
    public book getbook() {
        return mapper.getBook();
    }
}

```

4.在主方法实现实现类

```java
public class Mian {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MianConfigration.class);
        TestService service = context.getBean(TestService.class);
        System.out.println(service.getbook());

    }
}

```



我们使用Ioc容器将mybatis和实现类进行了解耦。