package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.TimeZone;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

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

	private String dbFile = "src\\main\\java\\com\\example\\demo\\datebase.json";

	// サイト一覧を取得
	@GetMapping("/siteRecord")
	public String SiteRecord(@RequestParam String key) throws JsonProcessingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode json = objectMapper
				.readTree(Paths.get(dbFile).toFile());

		String jsonstr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

		return jsonstr;
	}

	// サイト情報を追加
	@GetMapping("/siteAdd")
	public String SiteAdd(@RequestParam String url, @RequestParam String tag, @RequestParam String comment)
			throws JsonProcessingException, IOException {

		// 現在のデータベースを取得
		String returnString = "";
		ObjectMapper mapper = new ObjectMapper();
		JsonSiteLists siteList;

		try {

			JsonNode json = mapper
					.readTree(Paths.get(dbFile).toFile());
			siteList = mapper.readValue(json.toString(), JsonSiteLists.class);

		} catch (FileNotFoundException e) {
			return "データベースを読み込めませんでした";
		}

		// テスト用タグ
		List<String> tags = new ArrayList<String>();
		tags.add("");

		// データを追加
		List<JsonSiteList> list = new ArrayList<JsonSiteList>();
		JsonSiteList indata = new JsonSiteList(url, tags, comment);

		//// 既存データをリスト化
		siteList.getsites().forEach(s -> list.add(s));

		//// 新規データを追加
		list.add(indata);
		siteList.setsites(list);
		returnString = siteList.toString();

		try {
			// JSON文字列に変換
			returnString = mapper.writeValueAsString(siteList);
			// データベースを更新
			mapper.writeValue(new File(dbFile), siteList);
			return returnString;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping("/time")
	public String time() {
		DateTime nowDateTime = new DateTime();
		TimeZone tz = TimeZone.getDefault();
		return "タイムゾーン：" + tz.getID() + "   時刻：" + nowDateTime.toString();
	}
}