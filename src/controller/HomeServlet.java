package controller;

import java.io.IOException;
import java.util.List;

import beans.PlayerBeans;
import beans.ResultBeans;
import service.PlayerService;
import service.ResultService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/" })
public class HomeServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PlayerBeans> Players = new PlayerService().select("");
		List<ResultBeans> Results = new ResultService().select();
		int[] intArray = { 1, 2, 3, 4, 5 };
	    request.setAttribute("intArray", intArray);
		request.setAttribute("players", Players);
		request.setAttribute("results", Results);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
}
