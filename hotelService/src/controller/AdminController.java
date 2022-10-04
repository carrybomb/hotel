 package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotelServiceDAO.bookingDAO;
import hotelServiceDAO.hotelDAO;
import hotelServiceDAO.memberDAO;
import hotelServiceDAO.roomDAO;
import hotelServiceDTO.bookingListDTO;
import hotelServiceDTO.hotelDTO;
import hotelServiceDTO.memberDTO;
import hotelServiceDTO.roomDTO;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		if (command == null) {
			command = "memberList";
		} if (command.equals("memberList")) {
			memberList(request, response);
		} else if(command.equals("addAdmin")) {
			addAdmin(request, response);
		} else if(command.equals("memberchange")) {
			memberChange(request, response);
		} else if(command.equals("memberupdate")) {
			memberUpdate(request, response);
		} else if(command.equals("memberdelete")) {
			memberDelete(request, response);
		} else if(command.equals("bookingList")) {
			bookingList(request, response);
		}  else if(command.equals("hotelList")) {
			hotelList(request, response);
		} else if(command.equals("addHotel")) {
			addHotel(request, response);
		} else if(command.equals("hotelchange")) {
			hotelChange(request, response);
		} else if(command.equals("hotelupdate")) {
			hotelUpdate(request, response);
		} else if(command.equals("hoteldelete")) {
			hotelDelete(request, response);
		} else if(command.equals("roomList")) {
			roomList(request, response);
		} else if(command.equals("addRoom")) {
			addRoom(request, response);
		} else if(command.equals("roomchange")) {
			roomChange(request, response);
		} else if(command.equals("roomupdate")) {
			roomUpdate(request, response);
		} else if(command.equals("roomdelete")) {
			roomDelete(request, response);
		} 
	}


	private void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String se=request.getParameter("se");
		
		if (name == null) {
			name = "";
		}
		if(se == null) {
			se="all";
		}
		
		String url = "error.jsp";
		try {
			request.setAttribute("memberList", memberDAO.getAllContents(se,name));
			request.getParameter("memberList");
			url = "memberList.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "모두 보기 실패 재 실행 해 주세요");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name=request.getParameter("name");
		String id=request.getParameter("id");				
		String pw=request.getParameter("pw");				
		String tel=request.getParameter("tel");				
		String email=request.getParameter("email");
		
		//데이터값 입력 유무만 유효성 검증
		if(name == null || name.trim().length() == 0 ||
			id == null || id.trim().length() == 0 ||
			pw == null || pw.trim().length() == 0 ||
			tel == null || tel.trim().length() == 0 ||
			email == null || email.trim().length() == 0 ){
			response.sendRedirect("addAdmin.jsp");
			return;//write() 메소드 종료
		}
		
		boolean result = false;
		
		try {
			result = memberDAO.addAdmin(new memberDTO(name,id,pw,tel,email));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 저장 시도 실패 재 시도 하세요");
		}
		
		if(result){
			response.sendRedirect("admin"); 
		}else{
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	private void memberChange(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String memberNum=request.getParameter("num");
		if(memberNum==null || memberNum.length() == 0){
			response.sendRedirect("admin");
			return;
		}
		String url = "error.jsp";
		memberDTO gContent = null;
		try {
			gContent = memberDAO.getContent(Integer.parseInt(memberNum));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 읽기 실패");
		}
		if(gContent == null){
			request.setAttribute("error", "해당 게시글이 존재하지 않습니다");
		}else{
			request.setAttribute("resultContent", gContent);
			url = "memberchange.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void memberUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strNum=request.getParameter("num");
		String name=request.getParameter("name");
		String id=request.getParameter("id");				
		String pw=request.getParameter("pw");				
		String tel=request.getParameter("tel");				
		String email=request.getParameter("email");
		String grade=request.getParameter("grade");
		String authority=request.getParameter("authority");
		
		if(strNum == null || strNum.trim().length() == 0 ||
			name == null || name.trim().length() == 0 ||
			id == null || id.trim().length() == 0 ||
			pw == null || tel.trim().length() == 0 ||
			email == null || pw.trim().length() == 0 ||
			tel == null || email.trim().length() == 0 ||
			grade == null || grade.trim().length() == 0 ||
			authority == null || authority.trim().length() == 0 ){
				response.sendRedirect("admin");
				return;
		}
		
		boolean result = false;
		
		try {
			result = memberDAO.updateContent(new memberDTO(Integer.parseInt(strNum),name,id,pw,tel,email,Integer.parseInt(grade),authority));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 수정 실패");
		}
		if(result){
			response.sendRedirect("admin");
			return;
		}
		request.setAttribute("error", "게시글 수정 실패");
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	
	private void memberDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strNum=request.getParameter("num");
		String pw = request.getParameter("pw");
		
		if(strNum == null || strNum.trim().length() == 0 ||
			pw == null || pw.trim().length() == 0){
			response.sendRedirect("admin");
			return;				
		}
		boolean result = false;
		try {
			result = memberDAO.deleteContent(Integer.parseInt(strNum),pw);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "해당 게시글 삭제 실패했습니다. 재 시도 하셔요");
		}
		if(result){
			response.sendRedirect("admin");			
			return;
		}else{
			request.setAttribute("error", "삭제하려는 게시글이 존재하지 않습니다");
		}
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	
	private void bookingList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		if (name == null) {
			name = "";
		}
		
		String url = "error.jsp";
		try {
			request.setAttribute("bookingList", bookingDAO.getAllContents(name));
			request.getParameter("bookingList");
			url = "bookingList.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "모두 보기 실패 재 실행 해 주세요");
		}
		request.getRequestDispatcher(url).forward(request, response);

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
			request.setAttribute("hotelList", hotelDAO.getAllContents(name,location));
			request.getParameter("hotelList");
			url = "hotelList.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "모두 보기 실패 재 실행 해 주세요");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void addHotel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name=request.getParameter("name");
		String img=request.getParameter("img");				
		String location=request.getParameter("location");				
		String grade=request.getParameter("grade");				
		
		//데이터값 입력 유무만 유효성 검증
		if(name == null || name.trim().length() == 0 ||
		img == null || img.trim().length() == 0 ||
		location == null || location.trim().length() == 0 ||
		grade == null || grade.trim().length() == 0){
			response.sendRedirect("addHotel.jsp");
			return;//write() 메소드 종료
		}
		
		boolean result = false;
		
		try {
			result = hotelDAO.addHotel(new hotelDTO(name,img,location, Integer.parseInt(grade)));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 저장 시도 실패 재 시도 하세요");
		}
		
		if(result){
			response.sendRedirect("admin?command=hotelList") ;
		}else{
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	
	private void hotelChange(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String hotelNum=request.getParameter("num");
		if(hotelNum==null || hotelNum.length() == 0){
			response.sendRedirect("admin");
			return;
		}
		String url = "error.jsp";
		hotelDTO gContent = null;
		try {
			gContent = hotelDAO.getContent(Integer.parseInt(hotelNum));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 읽기 실패");
		}
		if(gContent == null){
			request.setAttribute("error", "해당 게시글이 존재하지 않습니다");
		}else{
			request.setAttribute("resultContent", gContent);
			url = "hotelchange.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void hotelUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strNum=request.getParameter("num");
		String name=request.getParameter("name");
		String img=request.getParameter("img");				
		String strstar=request.getParameter("star");				
		String location=request.getParameter("location");				
		String strhotelgrade=request.getParameter("hotelgrade");
		
		if(strNum == null || strNum.trim().length() == 0 ||
			name == null || name.trim().length() == 0 ||
			img == null || img.trim().length() == 0 ||
			strstar == null || strstar.trim().length() == 0 ||
			location == null || location.trim().length() == 0 ||
			strhotelgrade == null || strhotelgrade.trim().length() == 0) {			
				response.sendRedirect("admin");
				return;
		}
		
		boolean result = false;
		
		try {
			result = hotelDAO.updateContent(new hotelDTO(Integer.parseInt(strNum),name,img,Double.parseDouble(strstar),location,Integer.parseInt(strhotelgrade)));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 수정 실패");
		}
		if(result){
			response.sendRedirect("admin?command=hotelList") ;
			return;
		}
		request.setAttribute("error", "게시글 수정 실패");
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	
	private void hotelDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strNum=request.getParameter("num");
		
		if(strNum == null || strNum.trim().length() == 0){
			response.sendRedirect("admin");
			return;				
		}
		boolean result = false;
		try {
			result = hotelDAO.deleteContent(Integer.parseInt(strNum));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "해당 게시글 삭제 실패했습니다. 재 시도 하셔요");
		}
		if(result){
			response.sendRedirect("admin?command=hotelList") ;		
			return;
		}else{
			request.setAttribute("error", "삭제하려는 게시글이 존재하지 않습니다");
		}
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	
	private void roomList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String room_name=request.getParameter("room_name");
		String category=request.getParameter("category");
		String hotelNum=request.getParameter("num");
		
		if (room_name == null) {
			room_name = "";
		}
		if(category == null) {
			category="all";
		}
		
		
		if(hotelNum==null || hotelNum.length() == 0){
			response.sendRedirect("admin");
			return;
		}
		String url = "error.jsp";
		roomDTO gContent = null;
		try {
			request.setAttribute("test", hotelNum);
			request.setAttribute("roomList", roomDAO.getRoomContents2(Integer.parseInt(hotelNum),room_name,category));
			url = "roomList2.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "정보 읽기 실패");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void addRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String hotelnum=request.getParameter("num");
		String roomname=request.getParameter("roomname");
		String img=request.getParameter("img");				
		String max_guest=request.getParameter("max_guest");				
		String price=request.getParameter("price");				
		String category=request.getParameter("category");
		//데이터값 입력 유무만 유효성 검증
		if(	hotelnum == null || hotelnum.trim().length() == 0 ||
			roomname == null || roomname.trim().length() == 0 ||
			img == null || img.trim().length() == 0 ||
			max_guest == null || max_guest.trim().length() == 0 ||
			price == null || price.trim().length() == 0 ||
			category == null || category.trim().length() == 0 ){
			response.sendRedirect("admin");
			return;
		}
		boolean result = false;
		
		try {
			result = roomDAO.addRoom(new roomDTO(Integer.parseInt(hotelnum),roomname,img,Integer.parseInt(max_guest),Integer.parseInt(price),category));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 저장 시도 실패 재 시도 하세요");
		}
		
		if(result){
			response.sendRedirect("admin?command=roomList") ;	 
		}else{
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	private void roomChange(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String roomNum=request.getParameter("num");
		if(roomNum==null || roomNum.length() == 0){
			response.sendRedirect("admin");
			return;
		}
		String url = "error.jsp";
		roomDTO gContent = null;
		try {
			gContent = roomDAO.getContent(Integer.parseInt(roomNum));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 읽기 실패");
		}
		if(gContent == null){
			request.setAttribute("error", "해당 게시글이 존재하지 않습니다");
		}else{
			request.setAttribute("resultContent", gContent);
			url = "roomchange.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void roomUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomnum=request.getParameter("num");
		String hotelnum=request.getParameter("num");		
		String roomName=request.getParameter("roomName");
		String roomImage=request.getParameter("roomImage");
		String maxGuestNum=request.getParameter("maxGuestNum");				
		String price=request.getParameter("price");				
		String category=request.getParameter("category");
				
		
		
		if(roomName == null || roomName.trim().length() == 0 ||
			roomImage == null || roomImage.trim().length() == 0 ||
			roomnum == null || roomnum.trim().length() == 0 ||
			maxGuestNum == null || maxGuestNum.trim().length() == 0 ||
			price == null || price.trim().length() == 0 ||
			category == null || category.trim().length() == 0 ){
				response.sendRedirect("admin");
				return;
		}
		
		boolean result = false;
		
		try {
			result = roomDAO.updateContent(new roomDTO(Integer.parseInt(roomnum),Integer.parseInt(hotelnum),roomName,roomImage,Integer.parseInt(maxGuestNum),Integer.parseInt(price),category));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "게시글 수정 실패");
		}
		if(result){
			response.sendRedirect("admin?command=roomList") ;
			return;
		}
		request.setAttribute("error", "게시글 수정 실패");
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	private void roomDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strNum=request.getParameter("num");
		
		if(strNum == null || strNum.trim().length() == 0 ){
			response.sendRedirect("admin");
			return;				
		}
		boolean result = false;
		try {
			result = roomDAO.deleteContent(Integer.parseInt(strNum));
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "해당 게시글 삭제 실패했습니다. 재 시도 하셔요");
		}
		if(result){
			response.sendRedirect("admin?command=roomList") ;		
			return;
		}else{
			request.setAttribute("error", "삭제하려는 게시글이 존재하지 않습니다");
		}
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	
	
}
