package contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import beans.Player;

public class GetPlayerInfoAPI {
	public static List<Player> getPlayers(String[] args) throws IOException {
		//全htmlデータ取得
		Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId=2").get();
		//クラス・タグで絞って配列化
		Elements statsArray = document.getElementsByClass("bb-playerTable__row").select("tr");
		List<Player> players = setPlayers(statsArray);
		//スクレイピングデータをループして出力
		return players;
	}
	

	
	public final static List<Player> setPlayers(Elements statsArray) throws IOException {
		List<Player> players = new ArrayList<Player>();
		for (int i = 1; i < statsArray.size(); i++) {
			Player player = new Player();
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
