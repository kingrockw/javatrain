package cn.rock.frame12;

import com.alibaba.fastjson.JSONObject;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DispatcherServlet extends HttpServlet {
    @Override
    public void init() {
        ServletConfig servletConfig = this.getServletConfig();
        String initParameter = servletConfig.getInitParameter("controller-package");
        try {
            ApplicationMvcContext.init(initParameter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        if (requestURI.startsWith("/")){
            requestURI = requestURI.substring(1);
        }
        ApplicationMvcContext.MvcDefine mvcDefine = ApplicationMvcContext.getMvcDefine(requestURI);
        if (null == mvcDefine){
            resp.setStatus(404);
            PrintWriter writer = resp.getWriter();
            writer.write("请检查配置，404");
            writer.flush();
            writer.close();
        }
        try {
            javassistGetInfo(mvcDefine.getClazz());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Method method = mvcDefine.getMethod();
        Parameter[] parameters = method.getParameters();
        String name = null;
        if (null != parameters){

            for (Parameter parameter :parameters){
                 name = parameter.getName();
            }
        }
        try {
            Object invoke = method.invoke(mvcDefine.getTarget(),req.getParameter(name));
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


    void javassistGetInfo(Class<?> clazz) throws Exception {
//        Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("cn.rock.business.controller.UserController");
//
//                Class.forName("cn.rock.business.controller.UserController");
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        CtClass cc = pool.get(clazz.getName());

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method mt : declaredMethods) {
            String modifier = Modifier.toString(mt.getModifiers());
            Class<?> returnType = mt.getReturnType();
            String name = mt.getName();
            Class<?>[] parameterTypes = mt.getParameterTypes();

            System.out
                    .println("parameterTypes: " + Arrays.stream(parameterTypes).map(x -> x.toString())
                            .collect(Collectors.joining(",")));

            System.out.print("\n" + modifier + " " + returnType.getName() + " " + name + " (");

            //CtMethod[] declaredMethods1 = cc.getDeclaredMethods();
            CtMethod ctm = cc.getDeclaredMethod(name);
            MethodInfo methodInfo = ctm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attribute = (LocalVariableAttribute) codeAttribute
                    .getAttribute(LocalVariableAttribute.tag);

//          System.out.println("attribute: " + new Gson().toJson(attribute));

            int pos = Modifier.isStatic(ctm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < ctm.getParameterTypes().length; i++) {
                System.out.print(parameterTypes[i] + " " + attribute.variableName(i + pos));
                if (i < ctm.getParameterTypes().length - 1) {
                    System.out.print(",");
                }
            }

            System.out.print(")");

            Class<?>[] exceptionTypes = mt.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print(" throws ");
                int j = 0;
                for (Class<?> cl : exceptionTypes) {
                    System.out.print(cl.getName());
                    if (j < exceptionTypes.length - 1) {
                        System.out.print(",");
                    }
                    j++;
                }
            }
        }

    }

}
