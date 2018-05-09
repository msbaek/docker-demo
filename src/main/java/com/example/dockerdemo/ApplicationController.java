package com.example.dockerdemo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@EnableAutoConfiguration
public class ApplicationController {
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World: Spring-boot Sample Application using Maven and Docker";
    }
}
