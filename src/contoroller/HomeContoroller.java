package contoroller;

import java.io.IOException;
import java.util.List;

import beans.PlayerBeans;
import beans.ResultBeans;
import service.PlayerInfoService;
import service.PlayerResultService;

public class HomeContoroller {

	public static void main(String[] args) throws IOException {
		//		test.getStatsAll(args);
		List<ResultBeans> results = GetPlayerResultAPI.getStats(args);
		//    	GetPlayerResultAPI.printStats(results);
		new PlayerResultService().insert(results);
		
		List<PlayerBeans> players = GetPlayerInfoAPI.getPlayers(args);
		new PlayerInfoService().insert(players);
	}
}
