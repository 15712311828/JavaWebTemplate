package com.java.web.template.service;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Preconditions;
import com.java.web.template.dao.TicketMapper;
import com.java.web.template.dto.TicketDto;
import com.java.web.template.model.Ticket;
import com.java.web.template.model.TicketExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TicketService {

    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private OrderService orderService;

    public TicketDto getTickets(int page, int size){
        TicketDto dto = new TicketDto();
        TicketExample ticketExample = new TicketExample();
        dto.setTotal(ticketMapper.countByExample(ticketExample));
        PageHelper.startPage(page,size);
        List<Ticket> tickets = ticketMapper.selectByExample(ticketExample);
        tickets.forEach(ticket -> {
            Long count = orderService.countValidOrderByTicketId(ticket.getId());
            ticket.setRemaining(ticket.getRemaining()>=count?ticket.getRemaining()-count.intValue():0);
        });
        dto.setTickets(tickets);
        return dto;
    }

}
