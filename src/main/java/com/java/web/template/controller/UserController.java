package com.java.web.template.controller;

import com.java.web.template.model.User;
import com.java.web.template.result.JsonResult;
import com.java.web.template.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/user/signUp")
    public JsonResult signUp(@RequestBody User user){
        userService.saveUser(user);
        return JsonResult.success();
    }
}
