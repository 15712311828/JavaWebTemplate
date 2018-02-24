package com.java.web.template.result;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonResult<T> {

    private Integer status;

    private String message;

    private T data;

    public static <T> JsonResult<T> success(T data){
        return new JsonResult<T>(0,"",data);
    }

    public static <T> JsonResult<T> success(){
        return new JsonResult<T>(0,"",null);
    }

    public static <T> JsonResult<T> fail(int status,String message){
        return new JsonResult<T>(status,message,null);
    }

    public static <T> JsonResult<T> fail(String message){
        return new JsonResult<T>(-1,message,null);
    }
}
