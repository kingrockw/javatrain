package cn.rock.business.service;

import cn.rock.business.model.User;

public class UserService {
    public User getUser(String name) {
        User u = new User();
        u.setId(12);
        u.setName("rock"+name);
        return u;
    }
}
