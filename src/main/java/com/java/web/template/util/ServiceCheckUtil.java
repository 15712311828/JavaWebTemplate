package com.java.web.template.util;

import com.java.web.template.exception.BusinessException;

public class ServiceCheckUtil {

    public static void checkExist(Long result) {
        if(result>0){
            throw new BusinessException();
        }
    }

    public static void checkExist(Long result,String message) {
        if(result>0){
            throw new BusinessException(message);
        }
    }

    public static void checkInsert(Integer result) {
        if(result<=0){
            throw new BusinessException();
        }
    }

    public static void checkInsert(Integer result,String message) {
        if(result<=0){
            throw new BusinessException();
        }
    }
}
