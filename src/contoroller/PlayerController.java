package contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import beans.PlayerBeans;
import service.PlayerService;

public class PlayerController {	
	public final static List<PlayerBeans> getPlayerNPB(int year,String league) throws IOException {
		//全htmlデータ取得
		List<Element> scrapingArray = ScrapinController.NPB(year, league);
		//プレイヤー配列を用意
		List<PlayerBeans> players = new ArrayList<PlayerBeans>();
		for (int i = 2; i < scrapingArray.size()-1; i++) {
			PlayerBeans player = new PlayerBeans();
			String[] data = scrapingArray.get(i).text().split(" ");				
			if(new PlayerService().select(data[1]).size() == 0) {
				player.setId(i);
				player.setName(data[1]);
				player.setTeam(data[2].replaceAll("\\(", "").replaceAll("\\)", ""));
				player.setLeague(StatsCalculation.getLeagueStringKatakana(league));
				players.add(player);
			}
		}
		return players;
	}
	
	public final static List<PlayerBeans> getPlayerSportsNavi(String league) throws IOException {
		//全htmlデータ取得
		List<Element> scrapingData = ScrapinController.SportsNavi(league);
		//プレイヤー配列を用意
		List<PlayerBeans> players = new ArrayList<PlayerBeans>();
		for (int i = 1; i < scrapingData.size(); i++) {
			PlayerBeans player = new PlayerBeans();
			if (i != scrapingData.size() - 1) {
				String[] data = scrapingData.get(i).text().split(" ");
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
