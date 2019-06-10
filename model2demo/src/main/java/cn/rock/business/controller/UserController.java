package cn.rock.business.controller;

import cn.rock.business.common.Response;
import cn.rock.business.model.User;
import cn.rock.business.service.UserService;
import cn.rock.frame12.ApplicationContext;
import cn.rock.frame12.Controller;
import cn.rock.frame12.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("getUser")
    public Response getUser(){
        UserService userService =  ApplicationContext.getBean(UserService.class);
       User user =  userService.getUser();
       return  Response.ok(user);
    }
}
