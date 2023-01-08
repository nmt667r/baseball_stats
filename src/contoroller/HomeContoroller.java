package contoroller;

import java.io.IOException;
import java.util.List;

import beans.ResultBeans;
import service.PlayerResultService;

public class HomeContoroller {

	public static void main(String[] args) throws IOException {
		//		test.getStatsAll(args);
		for (int year = 2005; year < 2023; year++) {
			String league = "c"; //central league
			List<ResultBeans> results = GetPlayerResultForNPB.getStats(year, league);
			new PlayerResultService().insert(results);
			league = "p"; //central league
			results = GetPlayerResultForNPB.getStats(year, league);
			new PlayerResultService().insert(results);
        }
		//List<PlayerBeans> players = GetPlayerInfoAPI.getPlayers(args);
		//new PlayerInfoService().insert(players);
	}
}
