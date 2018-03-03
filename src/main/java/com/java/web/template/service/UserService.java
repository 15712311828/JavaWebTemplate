package com.java.web.template.service;

import com.java.web.template.dao.UserMapper;
import com.java.web.template.model.User;
import com.java.web.template.model.UserExample;
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
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        return users.size()==0?null:users.get(0);
    }

    public void login(UserLoginParam param, HttpServletResponse response){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(param.getName()).andPasswordEqualTo(param.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        ServiceCheckUtil.checkExist(users,"用户名或密码错误");
        Cookie cookie = new Cookie("_u", users.get(0).getName());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
