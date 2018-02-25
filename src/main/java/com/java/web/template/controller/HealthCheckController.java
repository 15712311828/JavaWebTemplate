package com.java.web.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthCheckController {

    @RequestMapping("healthCheck")
    public String healthCheck(){
        log.info("check");
        return "Check";
    }
}
