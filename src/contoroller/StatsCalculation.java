package contoroller;

import java.io.IOException;

public class StatsCalculation {
	public final static String getLeagueString(String league) throws IOException {
		if(league.equals("c")) {
			league = "セ";
		} else {
			league = "パ";
		}
		return league;
	}
}
