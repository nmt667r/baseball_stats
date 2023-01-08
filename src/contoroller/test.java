package contoroller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import beans.PlayerBeans;
import beans.ResultBeans;
import service.PlayerInfoService;
import service.PlayerResultService;

public class test {
	
	//スクレイピングテスト
	public static void getStatsAll(String[] args) throws IOException {
		Document document = Jsoup.connect("https://npb.jp/bis/2022/stats/bat_p.html").get();
		Elements statsArray = document.select("tr");
		//System.out.println(statsArray);
        for (Element str: statsArray){
        		System.out.println(str.text());
        }
	}
	
	
	//DB接続用メソッド
	public static void getDBstats(String[] args) throws IOException {
		List<PlayerBeans> results = new PlayerInfoService().select("中島　裕之");
    	for (PlayerBeans p: results){
    		System.out.println(p.getId());
    		System.out.println(p.getName());
    	}
	}
}
