package contoroller;

import java.io.IOException;
import java.util.List;

import beans.PlayerBeans;
import beans.ResultBeans;
import service.PlayerService;
import service.ResultService;

public class HomeContoroller {

	public static void main(String[] args) throws IOException {
//				test.getDBstats(args);		
		for (int year = 2005; year < 2023; year++) {
			//Cはcentral leagueの意
			List<PlayerBeans> centralPlayers = PlayerController.getPlayerNPB(year, "c");
			new PlayerService().insert(centralPlayers);
			List<ResultBeans> centralresults = ResultController.getResultNPB(year, "c");
			new ResultService().insert(centralresults);
			
			//pはpacific leagueの意
			List<PlayerBeans> pacificPlayers = PlayerController.getPlayerNPB(year, "p");
			new PlayerService().insert(pacificPlayers);
			List<ResultBeans> pacificResults = ResultController.getResultNPB(year, "p");
			new ResultService().insert(pacificResults);
        }
	}
}
