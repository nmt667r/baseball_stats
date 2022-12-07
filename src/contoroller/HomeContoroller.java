package contoroller;
import java.io.IOException;
import java.util.List;

import beans.Result;
import service.PlayerResultService;
public class HomeContoroller {


    public static void main(String[] args) throws IOException {
//    	test.getStatsAll(args);
    	List<Result> results = GetPlayerResultWebAPI.getStats(args);
    	GetPlayerResultWebAPI.printStats(results);
    	new PlayerResultService().insert(results);
    }
}
