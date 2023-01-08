package contoroller;

import java.io.IOException;
import java.util.List;

import beans.PlayerBeans;
import beans.ResultBeans;
import service.PlayerInfoService;
import service.PlayerResultService;

public class HomeContoroller {

	public static void main(String[] args) throws IOException {
//				test.getDBstats(args);
		
		for (int year = 2005; year < 2023; year++) {
			//Cはcentral leagueの意
			List<PlayerBeans> centralPlayers = GetPlayerInfoForNPB.getPlayers(year, "c");
			new PlayerInfoService().insert(centralPlayers);
			List<ResultBeans> centralresults = GetPlayerResultForNPB.getStats(year, "c");
			new PlayerResultService().insert(centralresults);
			
			//pはpacific leagueの意
			List<PlayerBeans> pacificPlayers = GetPlayerInfoForNPB.getPlayers(year, "p");
			new PlayerInfoService().insert(pacificPlayers);
			List<ResultBeans> pacificResults = GetPlayerResultForNPB.getStats(year, "p");
			new PlayerResultService().insert(pacificResults);
        }
		//List<PlayerBeans> players = GetPlayerInfoAPI.getPlayers(args);
		//new PlayerInfoService().insert(players);
	}
}
