package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoLogoutput {
    private static Logger log = LoggerFactory.getLogger(DemoLogoutput.class);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello Application Insights World!");
        for (int i = 0; i < 10; i++) {
            log.debug("Debug");
            log.info("Info");
            log.warn("Warn");
            try {
                var content = Files.readString(Paths.get("c:\\notexists"));
                System.out.println(content);
            } catch (IOException e) {
                log.error("error", e);
                log.error("non exception error");
            }
            Thread.sleep(500);
        }
    }
}