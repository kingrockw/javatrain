package cn.rock.mybatis01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wjl48511
 * @create 2019/6/25-11:16
 **/
public class BlogMapperXml {

    public static final String nameSpace = "cn.rock.mybatis01.BlogMapper01";

    private static Map<String,String> method2SqlMap = new HashMap<>();

    static {
        method2SqlMap.put("findBlogById","SELECT * FROM blog WHERE blogId = %s");
    }

    public static String getMethodSql(String method){
        return  method2SqlMap.get(method);
    }
}
