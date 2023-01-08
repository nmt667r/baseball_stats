package contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import beans.PlayerBeans;
import service.PlayerInfoService;

public class GetPlayerInfoForNPB {
	public static List<PlayerBeans> getPlayers(int year,String league) throws IOException {
		//全htmlデータ取得
		Document document = Jsoup.connect("https://npb.jp/bis/"+year+"/stats/bat_"+league+".html").get();//クラス・タグで絞って配列化
		Elements statsArray = document.select("tr");
		List<PlayerBeans> players = setPlayers(statsArray, league);
		//スクレイピングデータをループして出力
		return players;
	}
	

	
	public final static List<PlayerBeans> setPlayers(Elements statsArray, String league) throws IOException {
		List<PlayerBeans> players = new ArrayList<PlayerBeans>();
		for (int i = 2; i < statsArray.size()-1; i++) {
			PlayerBeans player = new PlayerBeans();
			if (i != statsArray.size()) {
				String[] data = statsArray.get(i).text().split(" ");				
				if(new PlayerInfoService().select(data[1]).size() == 0) {
					player.setId(i);
					player.setName(data[1]);
					player.setTeam(data[2].replaceAll("\\(", "").replaceAll("\\)", ""));
					player.setLeague(StatsCalculation.getLeagueString(league));
					players.add(player);
				}
			}
		}
		return players;
	}
}
