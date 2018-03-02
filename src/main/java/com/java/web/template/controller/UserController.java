package com.java.web.template.controller;

import com.java.web.template.common.UserContext;
import com.java.web.template.model.User;
import com.java.web.template.common.JsonResult;
import com.java.web.template.service.UserService;
import com.java.web.template.vo.param.LoginParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/signUp")
    public JsonResult signUp(@RequestBody User user){
        userService.saveUser(user);
        return JsonResult.success();
    }

    @RequestMapping("/login")
    public JsonResult login(@RequestBody LoginParam param, HttpServletResponse response){
        userService.login(param,response);
        return JsonResult.success();
    }

    @RequestMapping("/info")
    public JsonResult info(){
        User user = UserContext.get();
        return user==null?JsonResult.fail("未登录"):JsonResult.success(user);
    }
}
