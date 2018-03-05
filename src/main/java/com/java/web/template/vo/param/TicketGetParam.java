package com.java.web.template.vo.param;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class TicketGetParam {

    @Min(1)
    Integer page;

    @Min(1)
    Integer size;
}
