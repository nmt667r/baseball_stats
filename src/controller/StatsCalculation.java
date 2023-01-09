package controller;

import java.io.IOException;

public class StatsCalculation {
	//引数用リーグ文字列をDB用カタカナへ変換
	public final static String getLeagueStringKatakana(String league) throws IOException {
		if(league.equals("c")) {
			league = "セ";
		} else {
			league = "パ";
		}
		return league;
	}

	//引数用リーグ文字列をスポナビURL用文字列へ変換
	public final static String getLeagueInt(String league) throws IOException {
		if(league.equals("c")) {
			league = "1";
		} else {
			league = "2";
		}
		return league;
	}
}
