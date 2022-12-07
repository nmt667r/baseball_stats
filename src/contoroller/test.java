package contoroller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import beans.Player;
import service.PlayerService;

public class test {
	
	//スクレイピングテスト
	public static void getStatsAll(String[] args) throws IOException {
	   	Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId=1&type=avg").get();
    	//クラス・タグで絞って配列化
        Elements statsArray = document.getElementsByClass("bb-playerTable__row");
        System.out.println(statsArray);
        
        for (Element str: statsArray){
        		System.out.println(str.getElementsByClass("bb-playerTable__data"));
        }
	}
	
	//DB接続用メソッド
	public static void getDBstats(String[] args) throws IOException {
		List<Player> players = new PlayerService().select();
    	for (Player p: players){
    		System.out.println(p.getId());
    		System.out.println(p.getName());
    	}
	}
}
