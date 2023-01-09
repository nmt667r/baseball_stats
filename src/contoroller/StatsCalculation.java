package contoroller;

import java.io.IOException;

public class StatsCalculation {
	public final static String getLeagueStringKatakana(String league) throws IOException {
		if(league.equals("c")) {
			league = "セ";
		} else {
			league = "パ";
		}
		return league;
	}
	
	public final static String getLeagueInt(String league) throws IOException {
		if(league.equals("c")) {
			league = "1";
		} else {
			league = "2";
		}
		return league;
	}
}
