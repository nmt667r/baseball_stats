package controller;

import java.io.IOException;
import java.util.List;

import beans.PlayerBeans;
import beans.ResultBeans;
import service.PlayerService;
import service.ResultService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/" })
public class HomeServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//2005~2022のデータをDB登録する(NPB公式サイトの仕様)
		for (int year = 2005; year < 2023; year++) {
			//cはcentral leagueの意
			//pはpacific leagueの意
			//選手情報を取得
			List<PlayerBeans> centralPlayers = PlayerServlet.getPlayerNPB(year, "c");
			List<PlayerBeans> pacificPlayers = PlayerServlet.getPlayerNPB(year, "p");
			//選手情報をDBへinsert
			new PlayerService().insert(centralPlayers);
			new PlayerService().insert(pacificPlayers);

			//選手成績を取得
			List<ResultBeans> centralresults = ResultServlet.getResultNPB(year, "c");
			List<ResultBeans> pacificResults = ResultServlet.getResultNPB(year, "p");
			//選手成績をDBへinsert
			new ResultService().insert(centralresults);
			new ResultService().insert(pacificResults);
		}
	}
}
