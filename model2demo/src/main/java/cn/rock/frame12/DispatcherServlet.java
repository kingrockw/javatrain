package cn.rock.frame12;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ApplicationMvcContext.MvcDefine mvcDefine = ApplicationMvcContext.getMvcDefine(requestURI);
        Method method = mvcDefine.getMethod();
        try {
            Object invoke = method.invoke(mvcDefine.getTarget());
            PrintWriter writer = resp.getWriter();
            writer.write(JSONObject.toJSONString(invoke));
            writer.flush();
            writer.close();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
