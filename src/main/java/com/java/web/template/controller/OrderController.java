package com.java.web.template.controller;


import com.java.web.template.common.JsonResult;
import com.java.web.template.service.OrderService;
import com.java.web.template.util.ServiceCheckUtil;
import com.java.web.template.vo.param.OrderCreateParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @RequestMapping("/create")
    public JsonResult create(@RequestBody @Valid OrderCreateParam orderCreateParam){
        ServiceCheckUtil.checkLogin();
        return JsonResult.success(orderService.createOrder(orderCreateParam.getTicketId()));
    }
}
