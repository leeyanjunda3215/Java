package servlet;

import com.alibaba.fastjson.JSON;
import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/User/*")
public class UserServlet extends BaseServlet{
    private UserService service = new UserService();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = service.selectALL();
        String jsonsyr = JSON.toJSONString(users);

        System.out.println(jsonsyr);

        //响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonsyr);

    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");

        //接收json数据，获取请求体
        BufferedReader br = req.getReader();
        String params = br.readLine();

        //将json字符串转为java对象
        User user = com.alibaba.fastjson2.JSON.parseObject(params,User.class);
        System.out.println(user);

        //添加到数据库中
        service.addUser(user);
        resp.getWriter().write("success");
        System.out.println("success");

    }
    public void update(){}
    public void delect(){}

}
