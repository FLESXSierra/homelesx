package com.server.lesx.lesxserver.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/health")
public class BaseController {

    @GetMapping("/check")
    public ResponseEntity healthCheck(){
        log.info("=====[Health Check OK]=====");
        return ResponseEntity.ok().build();
    }

}
