package com.java.web.template.vo.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderCreateParam {

    @NotNull
    @Min(1)
    private Integer ticketId;
}
