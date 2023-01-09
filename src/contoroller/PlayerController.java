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
		//配列をループ(0番目・1番目・最後の配列は不要)
		for (int i = 2; i < scrapingArray.size()-1; i++) {
			//1選手のマップ
			PlayerBeans player = new PlayerBeans();
			//スクレイピングデータの文字列情報だけ抽出・配列化
			String[] data = scrapingArray.get(i).text().split(" ");
			//選手情報がDBに存在しない場合、ネストする(選手情報はユニーク)
			if(new PlayerService().select(data[1]).size() == 0) {
				//以下DBに必要な情報をマッピング
				player.setName(data[1]);
				//球団文字列の()を除去
				player.setTeam(data[2].replaceAll("\\(", "").replaceAll("\\)", ""));
				//引き渡されたリーグ情報(c or p)をDB登録用カナ文字へ変換(セ or パ)
				player.setLeague(StatsCalculation.getLeagueStringKatakana(league));
				//1選手分を選手配列群へ追加
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
		//配列をループ(0番目の配列は不要)
		for (int i = 1; i < scrapingData.size(); i++) {
			//1選手のマップ
			PlayerBeans player = new PlayerBeans();
			if (i != scrapingData.size() - 1) {
				//スクレイピングデータの文字列情報だけ抽出・配列化
				String[] data = scrapingData.get(i).text().split(" ");
				//以下DBに必要な情報をマッピング
				player.setName(data[1]);
				//球団文字列の()を除去
				player.setTeam(data[2].replaceAll("\\(", "").replaceAll("\\)", ""));
				//引き渡されたリーグ情報(c or p)をDB登録用カナ文字へ変換(セ or パ)
				player.setLeague(StatsCalculation.getLeagueStringKatakana(league));
				//1選手分を選手配列群へ追加
				players.add(player);
			}
		}
		return players;
	}
}
