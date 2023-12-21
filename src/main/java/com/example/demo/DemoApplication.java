package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@RestController
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
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

	private static Logger log = LoggerFactory.getLogger(DemoApplication.class);

	@GetMapping("/log")
	public static void log(String[] args) throws InterruptedException {
		System.out.println("Hello Application Insights World!");
		for (int i = 0; i < 10; i++) {
			// log.debug("APIを呼び出しました");
			// log.info("テスト出力情報");
			// log.warn("Warn");
			log.warn("{111-222-333}Warn");
			log.info("{111-222-333}info");
			try {
				var content = Files
						.readString(Paths.get("C:\\Users\\kaede_ozaki\\ホームフォルダ\\★17_次期eBB開発\\07_検証\\13_API\\temp"));
				System.out.println(content);
			} catch (IOException e) {
				log.error("error", e);
				log.error("non exception error");
			}
		}
	}

	@RequestMapping("/api/")
	public String apitop() {
		return "Welcome!";
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