## HTTP-请求数据格式

请求数据分为3部分：

> 1、请求行：请求数据的第一行。其中GET表示请求方式， / 表示请求资源路径，HTTP/1,1表示协议版本
>
> 2、请求头：第二行开始，格式为key：value形式
>
> 3、请求体：POST请求的最后一部分，存放请求参数

常见的HTTP请求头：

● Host：表示请求的主机名

● User-Agent：浏览器版本，例如Chrome浏览器的标识类似Mozilla/5.0...Chrime/79，IE浏览器的标识类似Mozilla/5.0(Windows NT...) like Gecko;

● Accept：表示浏览器能接收的资源类型，如text/*,image/*或者*/*表示所有；

● Accept-Language：表示浏览器偏好的语言，服务器可以据此返回不同语言的网页；

● Accept-Encoding：表示浏览器可以支持的压缩类型，例如gzip,deflate等。

## HTTP响应数据格式

响应数据分为3部分：

> 1、响应行：响应数据的第一行。其中HTTP/1.1表示协议版本，200表示响应[状态码](https://so.csdn.net/so/search?q=状态码&spm=1001.2101.3001.7020)，OK表示状态码描述
>
> 2、响应头：第二行开始，格式为：key：value形式
>
> 3、响应体：最后一部分。存放响应数据

● Content-Type：表示该响应内容的类型，例如text/html，image/jpeg；

● Content-Length：表示响应内容长度（字节数）;

● Content-Encoding：表示该响应压缩算法，例如gzip；

● Cache-Control：指示客户端应如何缓存，例如max-age=300表示可以最多缓存300秒

状态码大类：
1xx	响应中 — 临时状态码，表示请求已经接受，告诉客户端应该继续请求或者如果它已经完成则忽略它
2xx	成功 — 表示请求已经被成功接收，处理已完成
3xx	重定向 — 重定向到其他地方：它让客户端再发起一个请求以完成整个处理
4xx	客户端错误 — 处理发生错误，责任在客户端，如：客户端未被授权，禁止访问等
5xx	服务器端错误 — 处理发生错误，责任在服务端，如：服务端抛出异常，路由出错，HTTP版本不支持等

常见响应状态码：

| 200  | OK                    | 客户端请求成功，即处理成功。最想看到的状态码               |
| ---- | --------------------- | ---------------------------------------------------------- |
| 404  | Not Found             | 请求资源不存在，一般是URL输入有误，或者网站资源被删除      |
| 500  | Internal Server Error | 服务器发生不可预期的错误，服务器出异常，赶紧看日志查找异常 |

## Filter

过滤器可以把对资源的请求拦截下来，来实现一些特殊的功能（权限控制，统一编码，敏感字符）。

过滤器，放行后访问对应的资源，资源访问完后，还会回到Filter中，执行放行后的逻辑。

1.定义类，实现Filter接口，并重写其所有方法

```java
package filter;

import javax.servlet.*;
import java.io.IOException;
@WebFilter("/*")
public class userFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

```

实现Filter后，重写其三个方法init，doFilter，destroy。业务代码写在doFilter中。

2.配置Filter拦截资源的路径，在类上定义@WebFilter注解

```java
@WebFilter("/*")//过滤器拦截的路径，*拦截全部
public class userFilter implements Filter
```

3.在doFilter方法中写业务代码，并**放行**

```java
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("已拦截。。。");
        filterChain.doFilter(servletRequest,servletResponse);
    }
```

## Filter拦截路径配置

### 注解

@WebFilter("/*")

拦截具体的资源：/index.html，只有访问index.htm时才被访问。

拦截目录：/user/*，访问/user下的所有资源，都会被拦截

后缀名拦截：*.html，访问后缀名为html的资源，都会被拦截

## 过滤器链

一个Web应用，可以配置多个过滤器，这多个过滤器称位过滤器链

![image-20220725211804045](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20220725211804045.png)

javax.servlet 包中提供了一个 FilterChain 接口，该接口由容器实现。容器将其实例对象作为参数传入 Filter 对象的 doFilter() 方法中。Filter 对象可以使用 FilterChain 对象调用链中下一个 Filter 的 doFilter() 方法，若该 Filter 是链中最后一个过滤器，则调用目标资源的 service() 方法。

 Filter链的执行顺序

通过 web.xml 配置的 Filter 过滤器，执行顺序由 <filter-mapping> 标签的配置顺序决定。<filter-mapping> 靠前，则 Filter 先执行，靠后则后执行。通过修改 <filter-mapping> 的顺序便可以修改 Filter 的执行顺序。

通过 @WebFilter 注解配置的 Filter 过滤器，无法进行排序，若需要对 Filter 过滤器进行排序，建议使用 web.xml 进行配置。// 但如果需要，修改filter目录中对应过滤器的文件名按首字母顺序调整自己想要的顺序即可

# Mybatis

mybatis是持久层框架，用于简化jdbc开发。

持久层：负责数据保存到数据库

JavaEE的三层架构：表现层（页面展示），业务层（逻辑处理），持久层（对数据持久化，保存到数据库）

1.编写Mybatis的核心配置文件。

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
<!--        <mapper class="mapper.MyMapper"/>-->
            <mapper resource="XSBMapper.xml" />
    </mappers>
</configuration>
```

2.写sql映射文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--namespace：名称空间-->
<mapper namespace="test">
        <select id="selectXSB" resultType="enity.User">
                select * from XSB;
        </select>
</mapper>
```

3.（1）定义实体类

​	（2）加载核心配置文件，获取SqlSessionFactory对象

​	（3）获取SqlSession对象，执行SQL语句

​	（4）释放资源

```java
public class test {
    public static void main(String[] args) throws Exception {
        //1.加载核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactor = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象，用来执行sql
        SqlSession session = sqlSessionFactor.openSession();

        //执行sql
        List<User> user = session.selectList("test.selectXSB");
        System.out.println(user);
        //释放资源
        session.close();
    }
}
```

## Mapper代理开发

![image-20220805184319626](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20220805184319626.png)

# HTML元素

## table

![image-20220805184519545](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20220805184519545.png)

# HTTP无状态

http无状态是 服务器无法判断两次请求是同一个客户端发过来的，还是不同客户端发送过来的。

所带来的现实问题：第一次请求添加商品到购物车，第二次请求结账；如果两次请求无法区分是否是同一个客户端，就无法结账。

解决方法：通过会话跟踪技术（通过session ID来区分不同客户端）

## 会话（session）

客户端第一次发请求给服务器，服务器获取session获取不到，则创建新的，然后响应给客户端

下次客户端给服务器发请求时，会把sessionID带给服务器，那么服务器就可以获取到了，那么服务器就可以判断这次请求和上次的某一次请求是否是同一个客户端，从而能够区分开客户端。

常用的API

```java
//获取session,如果获取不到就创建一个新的
HttpSession session = req.getSession();
//获取session,没有就返回null，不会创建新的
 req.getSession(false)
//获取sessionID，全球唯一。
 session.getid()
//判断session是否是新的
session.isNew()  
//session会话的有效时间是半小时，sesion非激活间隔时长，默认1800秒。
session.getMaxInactiveInterval();
//设置sessin非激活间隔时长
session.setMaxInactiveInterval();
//强制性让会话失效
session.invalidate();
```

## session保存作用域

session作用域是全局的，从打开浏览器到关闭浏览器之前，都可以访问到Session中的数据

如果是重定向，就不能访问到Request域中的共享数据；可以使用session作用域；

在java开出一个内存来保存session

```java
//向当前session作用域保存一个数据“lina”，数据的key是uname
void session.setAttribute("uname","lina");
 //获取session作用域指定key的数据
Object session.getAttribute("uname");
```

## 服务器内部转发

就一次请求，跳转是发送在服务器内部。地址栏没有变化。

```java
request.getRequestDispatcher("/login.html").forward(request,response);
```

## 客户端重定向

302状态码表示重定向。

```java
response.sendRedirect("login.html");
```

服务器给客户端立即响应一个消息，消息为给login.html发送请求。客户端就请求login.html。地址栏有变化。

