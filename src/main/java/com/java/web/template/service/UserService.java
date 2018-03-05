package com.java.web.template.service;

import com.java.web.template.dao.UserMapper;
import com.java.web.template.model.User;
import com.java.web.template.model.UserExample;
import com.java.web.template.util.RedisUtil;
import com.java.web.template.util.ServiceCheckUtil;
import com.java.web.template.vo.param.UserLoginParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void saveUser(User user){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(user.getName());
        long count =  userMapper.countByExample(userExample);
        ServiceCheckUtil.checkNonExist(count,"用户名已存在");

        int result = userMapper.insert(user);
        ServiceCheckUtil.checkInsert(result,"插入失败");
    }

    public User getUser(String name){
        User user = RedisUtil.get(userKey(name),User.class);
        if(user!=null){
            return user;
        }

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==0){
            return null;
        }
        user=users.get(0);
        RedisUtil.set(userKey(name),user);
        return user;
    }

    public void login(UserLoginParam param, HttpServletResponse response){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(param.getName()).andPasswordEqualTo(param.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        ServiceCheckUtil.checkExist(users,"用户名或密码错误");
        User user=users.get(0);
        RedisUtil.set(userKey(user.getName()),user);
        Cookie cookie = new Cookie("_u", user.getName());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private String userKey(String name){
        return "user_"+name;
    }
}
