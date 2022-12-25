package contoroller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import beans.ResultBeans;
import service.PlayerResultService;

public class test {
	
	//スクレイピングテスト
	public static void getStatsAll(String[] args) throws IOException {
	   	Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId=1&type=avg").get();
    	//クラス・タグで絞って配列化
        Elements statsArray = document.getElementsByClass("bb-resultTable__row");
        System.out.println(statsArray);
        
        for (Element str: statsArray){
        		System.out.println(str.getElementsByClass("bb-resultTable__data"));
        }
	}
	
	//DB接続用メソッド
	public static void getDBstats(String[] args) throws IOException {
		List<ResultBeans> results = new PlayerResultService().select();
    	for (ResultBeans p: results){
    		System.out.println(p.getId());
    		System.out.println(p.getName());
    	}
	}
}
