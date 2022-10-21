package servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet，根据请求方法的路径来进行方法的分发
 * */
public class BaseServlet extends HttpServlet {

    //根据请求方法的路径来进行方法的分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的路径
        String uri = req.getRequestURI();//uri=/ajaxTest_war_exploded/User/selectAll
        //1.2获取最后一段路径，为方法名
        int index = uri.lastIndexOf("/");
        String methodname =uri.substring(index+1);

        //2.执行方法，
        //2.1.获取UserServlet的字节码对象（反射）
        Class<? extends BaseServlet> cls = this.getClass();
        //2.2.获取方法的method的对象
        try {
          Method method =  cls.getMethod(methodname,HttpServletRequest.class,HttpServletResponse.class);
          //2.3.执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
