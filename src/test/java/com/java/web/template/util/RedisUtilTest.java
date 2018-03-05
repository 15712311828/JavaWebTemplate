package com.java.web.template.util;

import com.java.web.template.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class RedisUtilTest {

    @Test
    public void test(){

        User user = new User();
        user.setSex((byte)1);
        user.setName("haha");
        user.setPassword("hehehe");
        user.setPhone("112345678912");

        RedisUtil.set("aa",user);
        log.info(RedisUtil.get("aa",User.class).getName());
    }
}
