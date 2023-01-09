package contoroller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import beans.PlayerBeans;
import beans.ResultBeans;
import service.PlayerService;

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
		List<PlayerBeans> results = new PlayerService().select("中島　裕之");
    	for (PlayerBeans p: results){
    		System.out.println(p.getId());
    		System.out.println(p.getName());
    	}
	}
	
	//配列確認メソッド
	public final static void printStats(List<ResultBeans> results) throws IOException {
		for (ResultBeans p : results) {
			System.out.println(p.getId());
			System.out.println(p.getPlayerId());
			System.out.println(p.getBattingAverage());
			System.out.println(p.getGames());
			System.out.println(p.getAtBats());
			System.out.println(p.getPlateAppearance());
			System.out.println(p.getHits());
			System.out.println(p.getDoubles());
			System.out.println(p.getTriples());
			System.out.println(p.getHomeruns());
			System.out.println(p.getTotalBases());
			System.out.println(p.getRbi());
			System.out.println(p.getRuns());
			System.out.println(p.getStrikeOuts());
			System.out.println(p.getBaseOnBalls());
			System.out.println(p.getHitByPitches());
			System.out.println(p.getSacrificeBunts());
			System.out.println(p.getSacrificeFlies());
			System.out.println(p.getStolenBases());
			System.out.println(p.getStolenBaseDeath());
			System.out.println(p.getDoublePlay());
			System.out.println(p.getOnBasePercentage());
			System.out.println(p.getSluggingPercentage());
			System.out.println(p.getOps());
			System.out.println(p.getScoringRangePercentage());
			System.out.println(p.getErrors());
		}
	}
}
