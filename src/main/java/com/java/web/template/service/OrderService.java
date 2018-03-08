package com.java.web.template.service;

import com.google.common.base.Preconditions;
import com.java.web.template.common.UserContext;
import com.java.web.template.constant.OrderResult;
import com.java.web.template.dao.OrderMapper;
import com.java.web.template.dao.TicketMapper;
import com.java.web.template.model.Order;
import com.java.web.template.model.OrderExample;
import com.java.web.template.model.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@Slf4j
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private TicketMapper ticketMapper;

    public Long countValidOrderByTicketId(Integer ticketId){
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.MINUTE,5);
        Date date=calendar.getTime();
        criteria.andTicketidEqualTo(ticketId).andCreatetimeGreaterThan(date);

        return orderMapper.countByExample(orderExample);
    }

    public String createOrder(Integer ticketId){
        Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
        Preconditions.checkNotNull(ticket,"该票不存在");
        Long count = countValidOrderByTicketId(ticket.getId());
        Preconditions.checkArgument(ticket.getRemaining()>=count,"该票已售空，请刷新页面");


        String orderNumber = ""+UserContext.getId()+ticketId+new Date().getTime();

        Integer casRetry = 3;

        while(casRetry>0) {

            if(tryToCreateOrder(ticketId,orderNumber)){
                break;
            }
            log.debug("cas锁提交失败,剩余次数{}",casRetry-1);
            casRetry--;
        }
        return orderNumber;
    }

    @Transactional
    protected boolean tryToCreateOrder(Integer ticketId,String orderNumber){
        Order order = new Order();
        order.setUserid(UserContext.getId());
        order.setTicketid(ticketId);
        order.setResult(OrderResult.CREATE);
        order.setNumber(orderNumber);
        orderMapper.insert(order);

        Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
        Long count = countValidOrderByTicketId(ticket.getId());
        if(ticket.getRemaining()>=count){
            return true;
        }
        else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
