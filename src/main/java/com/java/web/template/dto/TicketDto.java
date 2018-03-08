package com.java.web.template.dto;

import com.java.web.template.model.Ticket;
import lombok.Data;

import java.util.List;

@Data
public class TicketDto {

    Long total;

    List<Ticket> tickets;
}
