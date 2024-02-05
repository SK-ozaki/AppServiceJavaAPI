package com.example.demo;

import java.util.TimeZone;

import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "★Hello Test Web App";
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/value")
	public String Value(@RequestParam String key) {

		// 確認用環境変数"TEST_KeyValut_Secret_01"
		return System.getenv(key);
	}

	@RequestMapping("/time")
	public String time() {
		DateTime nowDateTime = new DateTime();
		TimeZone tz = TimeZone.getDefault();
		return "タイムゾーン：" + tz.getID() + " 時刻：" + nowDateTime.toString();
	}
}