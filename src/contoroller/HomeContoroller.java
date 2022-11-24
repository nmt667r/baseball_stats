package contoroller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import beans.Player;
import service.PlayerService;
public class HomeContoroller {


    public static void main(String[] args) throws IOException {
    	getStats(args);
    }

    public static void getStats(String[] args) throws IOException {
    	//全htmlデータ取得
    	Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId=1&type=avg").get();
    	//クラス・タグで絞って配列化
    	Elements statsArray = document.getElementsByClass("bb-playerTable__row").select("tr");
   	
    	//プレイヤー配列を用意
    	List<Player> players = new ArrayList<Player>();
    	
    	//スクレイピングデータをループして出力
    	for (int i = 1; i < statsArray.size(); i++) {
    		Player player = new Player();
    		if(i != statsArray.size()-1) {
    			String[] data = statsArray.get(i).text().split(" ");

    			player.setId(i);
        		player.setName(data[1]);
        		player.setBattingAverage(Double.parseDouble(data[3]));
        		players.add(player);
        		
    		}
    		
    		System.out.println(player.getId());
    		System.out.println(player.getName());
    		System.out.println(player.getBattingAverage());
    	}
        

    }
    
	public static void getStatsAll(String[] args) throws IOException {
	   	Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId=1&type=avg").get();
    	//クラス・タグで絞って配列化
        Elements statsArray = document.getElementsByClass("bb-playerTable__row");
        System.out.println(statsArray);
//        for (Element str: statsArray){
//        		System.out.println(str.getElementsByClass("bb-playerTable__data"));
//        }
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
