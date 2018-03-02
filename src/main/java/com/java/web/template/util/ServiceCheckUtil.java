package com.java.web.template.util;

import com.java.web.template.exception.BusinessException;

import java.util.List;

public class ServiceCheckUtil {

    public static void checkNonExist(Long result) {
        if(result>0){
            throw new BusinessException();
        }
    }

    public static void checkNonExist(Long result,String message) {
        if(result>0){
            throw new BusinessException(message);
        }
    }

    public static void checkExist(List list) {
        if(list.size()==0){
            throw new BusinessException();
        }
    }

    public static void checkExist(List list,String message) {
        if(list.size()==0){
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
