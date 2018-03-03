package com.java.web.template.vo.param;

import com.java.web.template.model.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserSignUpParam {

    @NotNull
    @Length(min = 3,max = 15,message = "用户名长度为3-10")
    private String name;

    @NotNull
    @Length(min = 6,max = 15,message = "密码长度为3-10")
    private String password;


    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private Byte sex;

    @NotNull
    @Length(min = 11,max = 11,message = "电话长度为11")
    private String phone;

    public User toUser(){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setSex(sex);
        return user;
    }
}
