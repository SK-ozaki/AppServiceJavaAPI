package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.joda.time.DateTime;
import java.util.TimeZone;

@SpringBootApplication
@RestController
public class DemoLogoutput {

    public static void main(String[] args) {
        SpringApplication.run(DemoLogoutput.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "★Hello Application Insights World";
    }

    @RequestMapping("/time")
    public String time() {
        DateTime nowDateTime = new DateTime();
        TimeZone tz = TimeZone.getDefault();
        return "タイムゾーン：" + tz.getID() + "   時刻：" + nowDateTime.toString();
    }
}