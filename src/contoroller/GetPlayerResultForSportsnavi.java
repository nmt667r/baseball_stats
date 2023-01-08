package contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import beans.ResultBeans;
import service.PlayerInfoService;

public class GetPlayerResultForSportsnavi {
	public static List<ResultBeans> getStats(String[] args) throws IOException {
		//全htmlデータ取得
		Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId=2").get();
		//クラス・タグで絞って配列化
		Elements statsArray = document.getElementsByClass("bb-playerTable__row").select("tr");
		List<ResultBeans> results = setStats(statsArray);
		//スクレイピングデータをループして出力
		return results;
	}

	public final static List<ResultBeans> setStats(Elements statsArray) throws IOException {
		//プレイヤー配列を用意
		List<ResultBeans> results = new ArrayList<ResultBeans>();
		for (int i = 1; i < statsArray.size(); i++) {
			ResultBeans result = new ResultBeans();
			if (i != statsArray.size() - 1) {
				String[] data = statsArray.get(i).text().split(" ");
				result.setPlayerId(new PlayerInfoService().select(data[1]).get(0).getId());
				result.setBattingAverage(Double.parseDouble(data[3]));
				result.setGames(Integer.parseInt(data[4]));
				result.setAtBats(Integer.parseInt(data[5]));
				result.setPlateAppearance(Integer.parseInt(data[6]));
				result.setHits(Integer.parseInt(data[7]));
				result.setDoubles(Integer.parseInt(data[8]));
				result.setTriples(Integer.parseInt(data[9]));
				result.setHomeruns(Integer.parseInt(data[10]));
				result.setTotalBases(Integer.parseInt(data[11]));
				result.setRbi(Integer.parseInt(data[12]));
				result.setRuns(Integer.parseInt(data[13]));
				result.setStrikeOuts(Integer.parseInt(data[14]));
				result.setBaseOnBalls(Integer.parseInt(data[15]));
				result.setHitByPitches(Integer.parseInt(data[16]));
				result.setSacrificeBunts(Integer.parseInt(data[17]));
				result.setSacrificeFlies(Integer.parseInt(data[18]));
				result.setStolenBases(Integer.parseInt(data[19]));
				result.setStolenBaseDeath(Integer.parseInt(data[20]));
				result.setDoublePlay(Integer.parseInt(data[21]));
				result.setOnBasePercentage(Double.parseDouble(data[22]));
				result.setSluggingPercentage(Double.parseDouble(data[23]));
				result.setOps(Double.parseDouble(data[24]));
				result.setScoringRangePercentage(Double.parseDouble(data[25]));
				result.setErrors(Integer.parseInt(data[26]));
				results.add(result);
			}
		}
		return results;
	}

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
