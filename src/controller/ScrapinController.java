package controller;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ScrapinController {
	public static Elements NPB(int year,String league) throws IOException {
		//全htmlデータ取得
		Document document = Jsoup.connect("https://npb.jp/bis/"+year+"/stats/bat_"+league+".html").get();//クラス・タグで絞って配列化
		//タグで絞って配列化
		Elements scrapingArray = document.select("tr");
		return scrapingArray;
	}

	public static Elements SportsNavi(String league) throws IOException {
		//リーグ文字列をスポナビURL用に変換
		String SportsNaviLeague = StatsCalculation.getLeagueInt(league);
		//全htmlデータ取得
		Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId="+SportsNaviLeague).get();
		//クラス・タグで絞って配列化
		Elements scrapingArray = document.getElementsByClass("bb-playerTable__row").select("tr");
		return scrapingArray;
	}
}
