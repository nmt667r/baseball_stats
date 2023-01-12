package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import beans.PlayerBeans;
import beans.ResultBeans;
import service.PlayerService;
import service.ResultService;


public class ScrapingServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//2005~2022のデータをDB登録する(NPB公式サイトの仕様)
		for (int year = 2005; year < 2023; year++) {
			//cはcentral leagueの意
			//pはpacific leagueの意
			//選手情報を取得
			List<PlayerBeans> centralPlayers = PlayerServlet.getPlayerNPB(year, "c");
			List<PlayerBeans> pacificPlayers = PlayerServlet.getPlayerNPB(year, "p");
			//選手情報をDBへinsert
			new PlayerService().insert(centralPlayers);
			new PlayerService().insert(pacificPlayers);

			//選手成績を取得
			List<ResultBeans> centralresults = ResultServlet.getResultNPB(year, "c");
			List<ResultBeans> pacificResults = ResultServlet.getResultNPB(year, "p");
			//選手成績をDBへinsert
			new ResultService().insert(centralresults);
			new ResultService().insert(pacificResults);
		}
	}
	
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
