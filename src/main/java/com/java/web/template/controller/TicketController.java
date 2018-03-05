package com.java.web.template.controller;


import com.java.web.template.common.JsonResult;
import com.java.web.template.service.TicketService;
import com.java.web.template.vo.param.TicketGetParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Resource
    private TicketService ticketService;

    @RequestMapping("/get")
    public JsonResult get(@RequestBody @Valid TicketGetParam param){
        return JsonResult.success(ticketService.getTickets(param.getPage(),param.getSize()));
    }
}
