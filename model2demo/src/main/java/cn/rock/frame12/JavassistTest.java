package cn.rock.frame12;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JavassistTest {
    public static void main(String[] args) throws Exception {
        javassistGetInfo(null);
    }
    static void javassistGetInfo(Class<?> cla) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("cn.rock.business.controller.UserController");
        Class<?> clazz = Class.forName("cn.rock.business.controller.UserController");

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
