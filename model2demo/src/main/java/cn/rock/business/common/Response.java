package cn.rock.business.common;

import cn.rock.business.model.User;

public class Response {
    private Integer code;

    private Object data;

    public static Response ok(User user) {
        Response r = new Response();
        r.setCode(200);
        r.setData(user);
        return r;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
