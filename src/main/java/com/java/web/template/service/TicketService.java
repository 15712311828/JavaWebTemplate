package com.java.web.template.service;

import com.github.pagehelper.PageHelper;
import com.java.web.template.dao.TicketMapper;
import com.java.web.template.dto.TicketsDto;
import com.java.web.template.model.TicketExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TicketService {

    @Resource
    private TicketMapper ticketMapper;

    public TicketsDto getTickets(int page,int size){
        TicketsDto dto = new TicketsDto();
        TicketExample ticketExample = new TicketExample();
        dto.setTotal(ticketMapper.countByExample(ticketExample));
        PageHelper.startPage(page,size);
        dto.setTickets(ticketMapper.selectByExample(ticketExample));
        return dto;
    }
}
