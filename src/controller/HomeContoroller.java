package controller;

import java.io.IOException;
import java.util.List;

import beans.PlayerBeans;
import beans.ResultBeans;
import service.PlayerService;
import service.ResultService;

public class HomeContoroller {

	public static void main(String[] args) throws IOException {

		//2005~2022のデータをDB登録する(NPB公式サイトの仕様)
		for (int year = 2005; year < 2023; year++) {
			//cはcentral leagueの意
			//pはpacific leagueの意
			//選手情報を取得
			List<PlayerBeans> centralPlayers = PlayerController.getPlayerNPB(year, "c");
			List<PlayerBeans> pacificPlayers = PlayerController.getPlayerNPB(year, "p");
			//選手情報をDBへinsert
			new PlayerService().insert(centralPlayers);
			new PlayerService().insert(pacificPlayers);

			//選手成績を取得
			List<ResultBeans> centralresults = ResultController.getResultNPB(year, "c");
			List<ResultBeans> pacificResults = ResultController.getResultNPB(year, "p");
			//選手成績をDBへinsert
			new ResultService().insert(centralresults);
			new ResultService().insert(pacificResults);
		}
	}
}
