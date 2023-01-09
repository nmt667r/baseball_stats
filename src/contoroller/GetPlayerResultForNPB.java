package contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import beans.ResultBeans;
import service.PlayerInfoService;

public class GetPlayerResultForNPB {
	public final static List<ResultBeans> getResultArray(int year, String league) throws IOException {
		//全htmlデータ取得
		List<Element> scrapingArray = Scraping.NPB(year, league);
		//成績配列を用意
		List<ResultBeans> results = new ArrayList<ResultBeans>();
		for (int i = 2; i < scrapingArray.size()-1; i++) {
			ResultBeans result = new ResultBeans();
			if (i != scrapingArray.size()) {
				String[] data = scrapingArray.get(i).text().split(" ");
				Double SluggingPercentage = Double.parseDouble(data[23]);
				Double OnBasePercentage = Double.parseDouble(data[24]);
				result.setPlayerId(new PlayerInfoService().select(data[1]).get(0).getId());
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
				results.add(result);
			}
		}
		return results;
	}
	

}
