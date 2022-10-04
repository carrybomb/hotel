package controller;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.smartcardio.CommandAPDU;

import hotelServiceDAO.bookingDAO;
import hotelServiceDAO.hotelDAO;
import hotelServiceDAO.memberDAO;
import hotelServiceDAO.roomDAO;
import hotelServiceDTO.bookingListDTO;
import hotelServiceDTO.memberDTO;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		System.out.println(command);
		
		if(command.equals("bookingForm")){
			bookingForm(request, response);
		}else if(command.equals("MyPage")) {
			mypage(request, response);
		}else if(command.equals("정보수정")) {
			modify(request, response);
		}else if(command.equals("update")) {
			update(request, response);
		}else if(command.equals("회원탈퇴")) {
			deleteMember(request, response);
		}else if(command.equals("bookingForm")){
			bookingForm(request, response);
		}else if(command.equals("booking")) {
			booking(request, response);
		}else if(command.equals("예약정보")) {
			try {
				myBooked(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(command.equals("cancel")) {
			myCancel(request, response);
		}
		
		
		
	}
		
	private void hotelList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String location=request.getParameter("location");
		String grade=request.getParameter("grade");
		
		if (name == null) {
			name = "";
		}
		if(location == null) {
			location="";
		}

		
		String url = "error.jsp";
		try {
			request.setAttribute("hotel", hotelDAO.getAllContents(name,location));
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
	
	private void bookingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		int hotelNum=Integer.parseInt(request.getParameter("hotelNum"));
		String category=request.getParameter("category");
		String[] Ddata = request.getParameter("daterange").split(" - ");
		
		String[] inCheck = Ddata[0].split("/");
		String[] outCheck = Ddata[1].split("/");
		String checkIn = inCheck[2]+"-"+inCheck[0]+"-"+inCheck[1];
		String checkOut = outCheck[2]+"-"+outCheck[0]+"-"+outCheck[1];

		try {
			request.setAttribute("bookingForm", bookingDAO.getbookingForm(hotelNum,category,checkIn,checkOut));
			url = "bookingForm.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "에러");
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
		
	}
	
	
	private void booking(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		String url = "error.jsp";
	    String guestNum = request.getParameter("guestNum");
	    String roomNum = request.getParameter("roomNum");
	    String checkIn = request.getParameter("checkIn");
	    String checkOut = request.getParameter("checkOut");
	    System.out.println("1");
	    
	    HttpSession memberSession = request.getSession();
		String memberId = (String)memberSession.getAttribute("memberId");
		System.out.println("controller : "+memberId);

	    try {
			memberDTO member = memberDAO.selectMember(memberId);
			int memberNum = member.getMemberNum();
			boolean result = false;
		    System.out.println(checkIn);
		    
			result = bookingDAO.booking(new bookingListDTO(memberNum,Integer.parseInt(roomNum),Integer.parseInt(guestNum),checkIn,checkOut));
		    if(result) {
		    	request.setAttribute("complete", bookingDAO.bookingComplete(memberId));
				url = "bookingComplete.jsp";
				System.out.println("ㅁㄴㅇㄻ");
		    	request.getRequestDispatcher(url).forward(request, response);
		    }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}  
	}
	
	
	private void myBooked(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		HttpSession memberSession = request.getSession();
		String memberId = (String)memberSession.getAttribute("memberId");

		String url = "error.jsp";
		 
		try {
			request.setAttribute("myBooked", bookingDAO.myBooked(memberId));
			url = "MyBooked.jsp";
	    	request.getRequestDispatcher(url).forward(request, response);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void myCancel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String bookingNum = request.getParameter("bookingNum");
		
		boolean result = false;
		try {
			result = bookingDAO.cancel(Integer.parseInt(bookingNum));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "해당 게시글 삭제 실패했습니다. 재 시도 하셔요");
		}
		
		if(result){
			response.sendRedirect("member?command=MyPage");
//			request.setAttribute("command", "MyPage");
			//request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}else{
			request.setAttribute("error", "삭제하려는 게시글이 존재하지 않습니다");
		}
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	
	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession memberSession = request.getSession();
		
		String memberId = (String)memberSession.getAttribute("memberId");
		
		try {
			memberDTO member = new memberDAO().selectMember(memberId);
			memberSession.setAttribute("member", member);
			System.out.println(member);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("MyPage.jsp").forward(request, response);
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");

		memberDTO member = new memberDTO(id, name, tel, email, Integer.parseInt(grade));
		System.out.println(member);
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("modify.jsp").forward(request, response);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		memberDTO member = new memberDTO();
		boolean result = true;
		
		try {
			result = memberDAO.updateInfo(name, tel, email, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result) {
			try {
				member = memberDAO.selectMember(id);
				request.setAttribute("member", member);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("MyPage.jsp").forward(request, response);
		}
	}
	
	private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		
		HttpSession session = request.getSession(false);
		System.out.println("meberSession : " + session.getAttribute("memberId"));
		System.out.println("adminSession : " + session.getAttribute("adminId"));
		session.invalidate();
		
		boolean result = true;
		
		try {
			result = memberDAO.deleteMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result) {
			response.sendRedirect("index.html");
		}
		
	}
}
