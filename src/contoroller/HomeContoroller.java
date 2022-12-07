package contoroller;
import java.io.IOException;
import java.util.List;

import beans.Player;
import service.PlayerService;
public class HomeContoroller {


    public static void main(String[] args) throws IOException {
//    	test.getStatsAll(args);
    	List<Player> players = GetPlayerStatsWebAPI.getStats(args);
//    	GetPlayerStatsWebAPI.printStats(players);
    	new PlayerService().insert(players);
    }
}
