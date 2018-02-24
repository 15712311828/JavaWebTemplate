package com.java.web.template.service;

import com.java.web.template.dao.UserMapper;
import com.java.web.template.model.User;
import com.java.web.template.model.UserExample;
import com.java.web.template.util.ServiceCheckUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void saveUser(User user){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        long count =  userMapper.countByExample(userExample);
        ServiceCheckUtil.checkExist(count,"用户名已存在");

        int result = userMapper.insert(user);
        ServiceCheckUtil.checkInsert(result,"插入失败");
    }
}
