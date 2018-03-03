package com.java.web.template.vo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginParam {

    @NotNull
    String name;

    @NotNull
    String password;
}
