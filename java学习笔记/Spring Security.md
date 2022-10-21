# Spring Security

认证，授权的框架

## 认证

用户名密码登录认证方式方式，指纹打卡认证方式。注册账号为认证。认证是为了保护系统的隐式数据与资源，用户身份合法可访问该系统的资源。

![img](file:///C:\Users\李焱军\AppData\Local\Temp\ksohtml1360\wps1.jpg) 

## 会话

用户信息保存到会话中，例如session，token

![img](file:///C:\Users\李焱军\AppData\Local\Temp\ksohtml1360\wps2.jpg) 

![img](file:///C:\Users\李焱军\AppData\Local\Temp\ksohtml1360\wps3.jpg) 

## 授权

是用户认证通过用户的权限来控制用户的访问的资源的过程。

### 授权的模型

### 例如：

![img](file:///C:\Users\李焱军\AppData\Local\Temp\ksohtml1360\wps4.jpg) 

数据模型：



![img](file:///C:\Users\李焱军\AppData\Local\Temp\ksohtml1360\wps5.jpg) 

通常将资源和权限合为一张表。

![img](file:///C:\Users\李焱军\AppData\Local\Temp\ksohtml1360\wps6.jpg) 

## RBAC

基于RBAC的授权

### 基于角色的访问控制\、

### 基于资源的访问控制

# SpringSecurity的原理

## 流程

![image-20221007124259786](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221007124259786.png)

需要实现，填写用户名和密码的登录请求，和登录出现的任何异常，还有负责权限的过滤器。

## 认证图解

![image-20221007123602251](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221007123602251.png)

![image-20221008082219718](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221008082219718.png)

通过验证后，用户的userid为key,用户信息为value存入Redis

## 校验

![image-20221008082804152](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221008082804152.png)

# 快速入门

 

## **1.** 利用模块化创建Springboot工程

 导入maven工程坐标

```xml
<parent>

    <groupId>org.springframework.boot</groupId>

    <artifactId>spring-boot-starter-parent</artifactId>

    <version>2.6.3</version>

  </parent>

  <dependencies>

    <dependency>

      <groupId>org.springframework.boot</groupId>

      <artifactId>spring-boot-starter-web</artifactId>

    </dependency>

    <dependency>

      <groupId>org.projectlombok</groupId>

        <artifactId>lombok</artifactId>

    </dependency>

  </dependencies>
```

 

 做一个启动模块

给注解@SpringBootApplication，然后在main方法中，使用SpringBootApplication的run方法，指向本身。

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
```

 在做controller

## 2.引入SpringSecurity

引入 SpringSecurity的启动器

```xml
     <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```

 

 区别，引入SpringSecurity后，在去访问接口，后跳转到一个登录页面

![image-20221007123121385](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221007123121385.png)

必须登录才能对接口进行访问。

## 3.引入redis

```xml
<!--        redis依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```

redis的配置

## 4.引入jwt

```xml
<!--        jwt依赖-->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>

```

## 5.引入fastjson

```xml
     <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>2.0.7</version>
        </dependency>
```



## .登录

### 3.1自定义登录接口

调用ProvideManage，认证通过，生成jwt，把用户信息存入redis中。

### 3.2自定义UserDetailService

在这个类中查询数据库

## .校验

### 4.1定义jwt认证过滤器

获取token，解析token，获取其中的userid，从redis中获取对应的用户信息，存入SecurityContextHolder。







 

 

 