JDBC

```java
   //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取数据连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalx","root","liyanjun");
        //操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from xsb");//查
        //如果有数据，rs.next()返回true
        while (rs.next()){
            System.out.println("姓名:  "+rs.getString("name"));
            System.out.println("学号:  "+rs.getInt("snumber"));
            System.out.println("性别:  "+rs.getString("sex"));
        }
```

