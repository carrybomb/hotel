package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotelServiceDAO.hotelDAO;
import hotelServiceDAO.roomDAO;

@WebServlet("/hotel")
public class HotelController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		if(command == null){
			command = "hotel";
		}
		
		if(command.equals("hotel")){
			hotelList(request, response);
		}else if(command.equals("room")){
			roomList(request, response);
		}
	}

	private void hotelList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		String name=request.getParameter("name");
		String location=request.getParameter("location");
		
		if (name == null) {
			name = "";
		}
		if(location == null) {
			location="";
		}
		
		try {
			request.setAttribute("hotel", hotelDAO.getAllContents(name, location));
			url = "list.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "紐⑤몢 蹂닿린 �떎�뙣 �옱 �떎�뻾 �빐 二쇱꽭�슂");
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}

	private void roomList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		String hotelNum=request.getParameter("hotelNum");
		try {
			request.setAttribute("room", roomDAO.getRoomContents(Integer.parseInt(hotelNum)));
			url = "roomList.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "紐⑤몢 蹂닿린 �떎�뙣 �옱 �떎�뻾 �빐 二쇱꽭�슂");
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}
}
