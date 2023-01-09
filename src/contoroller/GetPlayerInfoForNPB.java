package contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import beans.PlayerBeans;
import service.PlayerInfoService;

public class GetPlayerInfoForNPB {	
	public final static List<PlayerBeans> getPlayersArray(int year,String league) throws IOException {
		//全htmlデータ取得
		List<Element> scrapingArray = Scraping.NPB(year, league);
		//プレイヤー配列を用意
		List<PlayerBeans> players = new ArrayList<PlayerBeans>();
		for (int i = 2; i < scrapingArray.size()-1; i++) {
			PlayerBeans player = new PlayerBeans();
			String[] data = scrapingArray.get(i).text().split(" ");				
			if(new PlayerInfoService().select(data[1]).size() == 0) {
				player.setId(i);
				player.setName(data[1]);
				player.setTeam(data[2].replaceAll("\\(", "").replaceAll("\\)", ""));
				player.setLeague(StatsCalculation.getLeagueStringKatakana(league));
				players.add(player);
			}
		}
		return players;
	}
}
