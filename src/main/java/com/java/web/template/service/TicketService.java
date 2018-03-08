package com.java.web.template.service;

import com.github.pagehelper.PageHelper;
import com.java.web.template.dao.TicketMapper;
import com.java.web.template.dto.TicketDto;
import com.java.web.template.model.Ticket;
import com.java.web.template.model.TicketExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TicketService {

    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private OrderFormService orderFormService;

    public TicketDto getTickets(int page, int size){
        TicketDto dto = new TicketDto();
        TicketExample ticketExample = new TicketExample();
        dto.setTotal(ticketMapper.countByExample(ticketExample));
        PageHelper.startPage(page,size);
        List<Ticket> tickets = ticketMapper.selectByExample(ticketExample);
        tickets.forEach(ticket -> {
            Long count = orderFormService.countValidOrderByTicketId(ticket.getId());
            ticket.setRemaining(ticket.getRemaining()>=count?ticket.getRemaining()-count.intValue():0);
        });
        dto.setTickets(tickets);
        return dto;
    }

}
