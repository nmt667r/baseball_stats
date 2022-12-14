package contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import beans.ResultBeans;
import service.PlayerService;

public class ResultController {
	public final static List<ResultBeans> getResultNPB(int year, String league) throws IOException {
		//全htmlデータ取得
		List<Element> scrapingArray = ScrapinController.NPB(year, league);
		//成績配列を用意
		List<ResultBeans> results = new ArrayList<ResultBeans>();
		//配列をループ(0番目・1番目・最後の配列は不要)
		for (int i = 2; i < scrapingArray.size()-1; i++) {
			//1選手分成績のマップ
			ResultBeans result = new ResultBeans();
			//スクレイピングデータの文字列情報だけ抽出・配列化
			String[] data = scrapingArray.get(i).text().split(" ");
			//長打率計算(NPB公式には長打率情報が存在しない為)
			Double SluggingPercentage = Double.parseDouble(data[23]);
			//出塁率計算(NPB公式には出塁率情報が存在しない為)
			Double OnBasePercentage = Double.parseDouble(data[24]);
			//以下DBに必要な情報をマッピング
			result.setPlayerId(new PlayerService().select(data[1]).get(0).getId());
			result.setYear(year);
			result.setBattingAverage(Double.parseDouble(data[3]));
			result.setGames(Integer.parseInt(data[4]));
			result.setAtBats(Integer.parseInt(data[5]));
			result.setPlateAppearance(Integer.parseInt(data[6]));
			result.setRuns(Integer.parseInt(data[7]));
			result.setHits(Integer.parseInt(data[8]));
			result.setDoubles(Integer.parseInt(data[9]));
			result.setTriples(Integer.parseInt(data[10]));
			result.setHomeruns(Integer.parseInt(data[11]));
			result.setTotalBases(Integer.parseInt(data[12]));
			result.setRbi(Integer.parseInt(data[13]));
			result.setStolenBases(Integer.parseInt(data[14]));
			result.setStolenBaseDeath(Integer.parseInt(data[15]));
			result.setSacrificeBunts(Integer.parseInt(data[16]));
			result.setSacrificeFlies(Integer.parseInt(data[17]));
			result.setBaseOnBalls(Integer.parseInt(data[18]));
			result.setHitByPitches(Integer.parseInt(data[20]));
			result.setStrikeOuts(Integer.parseInt(data[21]));
			result.setDoublePlay(Integer.parseInt(data[22]));
			result.setSluggingPercentage(SluggingPercentage);
			result.setOnBasePercentage(OnBasePercentage);
			result.setOps(SluggingPercentage + OnBasePercentage);
			//1選手分成績を成績配列群へ追加
			results.add(result);
		}
		return results;
	}

	public final static List<ResultBeans> getResultSportsNavi(String league) throws IOException {
		//全htmlデータ取得
		List<Element> scrapingArray = ScrapinController.SportsNavi(league);
		//成績配列を用意
		List<ResultBeans> results = new ArrayList<ResultBeans>();
		for (int i = 1; i < scrapingArray.size()-1; i++) {
			ResultBeans result = new ResultBeans();
			//スクレイピングデータの文字列情報だけ抽出・配列化
			String[] data = scrapingArray.get(i).text().split(" ");
			//以下DBに必要な情報をマッピング
			result.setPlayerId(new PlayerService().select(data[1]).get(0).getId());
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
			//1選手分成績を成績配列群へ追加
			results.add(result);
		}
		return results;
	}

}
