package contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import beans.PlayerBeans;

public class GetPlayerInfoForSportsnavi {
	public static List<PlayerBeans> getPlayers(String[] args) throws IOException {
		//全htmlデータ取得
		Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId=2").get();
		//クラス・タグで絞って配列化
		Elements statsArray = document.getElementsByClass("bb-playerTable__row").select("tr");
		List<PlayerBeans> players = setPlayers(statsArray);
		//スクレイピングデータをループして出力
		return players;
	}
	

	
	public final static List<PlayerBeans> setPlayers(Elements statsArray) throws IOException {
		List<PlayerBeans> players = new ArrayList<PlayerBeans>();
		for (int i = 1; i < statsArray.size(); i++) {
			PlayerBeans player = new PlayerBeans();
			if (i != statsArray.size() - 1) {
				String[] data = statsArray.get(i).text().split(" ");
				player.setId(i);
				player.setName(data[1]);
				player.setTeam(data[2].replaceAll("\\(", "").replaceAll("\\)", ""));
				player.setLeague("パ");
				players.add(player);
			}
		}
		return players;
	}
}
