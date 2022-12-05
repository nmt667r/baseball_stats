package contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import beans.Player;

public class GetPlayerStatsWebAPI {
    public static List<Player> getStats(String[] args) throws IOException {
    	//全htmlデータ取得
    	Document document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/stats/batter?gameKindId=1&type=avg").get();
    	//クラス・タグで絞って配列化
    	Elements statsArray = document.getElementsByClass("bb-playerTable__row").select("tr");
    	List<Player> players = setStats(statsArray);
    	//スクレイピングデータをループして出力
    	return players;
    }
    
    public final static List<Player> setStats(Elements statsArray) throws IOException {     	
    	//プレイヤー配列を用意
    	List<Player> players = new ArrayList<Player>();
    	for (int i = 1; i < statsArray.size(); i++) {
    		Player player = new Player();
    		if(i != statsArray.size()-1) {
    			String[] data = statsArray.get(i).text().split(" ");
    			player.setId(i);
    			player.setName(data[1]);
    			player.setBattingAverage(Double.parseDouble(data[3]));
    			player.setGames(Integer.parseInt(data[4]));
    			player.setAtBats(Integer.parseInt(data[5]));
    			player.setPlateAppearance(Integer.parseInt(data[6]));
    			player.setHits(Integer.parseInt(data[7]));
    			player.setDoubles(Integer.parseInt(data[8]));
    			player.setTriples(Integer.parseInt(data[9]));
    			player.setHomeruns(Integer.parseInt(data[10]));
    			player.setTotalBases(Integer.parseInt(data[11]));
    			player.setRbi(Integer.parseInt(data[12]));
    			player.setRuns(Integer.parseInt(data[13]));
    			player.setStrikeOuts(Integer.parseInt(data[14]));
    			player.setBaseOnBalls(Integer.parseInt(data[15]));
    			player.setHitByPitches(Integer.parseInt(data[16]));
    			player.setSacrificeBunts(Integer.parseInt(data[17]));
    			player.setSacrificeFlies(Integer.parseInt(data[18]));
    			player.setStolenBases(Integer.parseInt(data[19]));
    			player.setStolenBaseDeath(Integer.parseInt(data[20]));
    			player.setDoublePlay(Integer.parseInt(data[21]));
    			player.setOnBasePercentage(Double.parseDouble(data[22]));
    			player.setSluggingPercentage(Double.parseDouble(data[23]));
    			player.setOps(Double.parseDouble(data[24]));
    			player.setScoringRangePercentage(Double.parseDouble(data[25]));
    			player.setErrors(Integer.parseInt(data[26]));

        		players.add(player);      		
    		}
    	}
    	return players;
    }
    
    public final static void printStats(List<Player> players) throws IOException {
    	for (Player p: players){
    		System.out.println(p.getId());
    		System.out.println(p.getName());
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
