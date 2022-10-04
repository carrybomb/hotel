package hotelServiceDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hotelServiceDTO.bookingListDTO;
import hotelServiceDTO.roomDTO;
import util.DBUtil;



public class bookingDAO {
	public static ArrayList<bookingListDTO> getAllContents(String name) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<bookingListDTO> alist = null;
		String sql = "select b.*,m.member_name from bookingList as b left join member as m on b.member_num=m.member_num";
		
		if(name.equals("")) {
			sql += " where m.member_name is not null";
		} else {
			sql += " where m.member_name = ?";
		}
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			
			if(!name.equals("")) {
				pstmt.setString(1, name);						
			} 	
			
			rset = pstmt.executeQuery();
			alist = new ArrayList<bookingListDTO>();
			while (rset.next()) {
				alist.add(new bookingListDTO(rset.getInt(1),rset.getInt(2),
						rset.getInt(3),rset.getInt(4),rset.getString(5),
						rset.getString(6),rset.getDate(7),rset.getDate(8), rset.getString(9)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return alist;
	}
	
	
	public static bookingListDTO getContent(int num) throws SQLException{		
		Connection con = null;	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		bookingListDTO am  = null;
		String sql="select b.*,m.member_id from bookingList as b left join member as m on b.member_num=m.member_num where booking_num=?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);	
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				am = new bookingListDTO(rset.getInt(1),rset.getInt(2),
						rset.getInt(3),rset.getInt(4),rset.getString(5),
						rset.getString(6),rset.getDate(7),rset.getDate(8), rset.getString(9));
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return am;
	}
	
	public static boolean deleteContent(int num) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		String sql="delete from bookingList where booking_num=?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1,num);

			int count = pstmt.executeUpdate();
			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result;
	}
	
	public static ArrayList<roomDTO> getRoomContents(int hotelNum) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<roomDTO> alist = null;
		String sql = "select * from room where hotel_num = ?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,hotelNum);
			rset = pstmt.executeQuery();
			alist = new ArrayList<roomDTO>();
			while (rset.next()) {
				alist.add(new roomDTO(rset.getInt(1), rset.getInt(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getInt(6), rset.getInt(7),rset.getString(8)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return alist;
}

	public static List<Object> getbookingForm(int hotelNum, String category, String checkIn, String checkOut) throws SQLException {
	      
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      List<Object> list = new ArrayList<>();
	      String sql = "select room_name, max_guest_num, price, category,room_num,hotel_num "
	               +"from room "
	               +"where category= ? and hotel_num = ? and room_name not in (select room_name "
	               +"from room as r left join bookinglist as b on r.room_num=b.room_num "
	               +"where not( ? <= checkin_date or checkout_date <= ?))";

	      try {
		      con = DBUtil.getConnection();
		      pstmt = con.prepareStatement(sql);
		      pstmt.setString(1, category);
		      pstmt.setInt(2, hotelNum);
		      pstmt.setString(3, checkOut);
		      pstmt.setString(4, checkIn);
		      rset = pstmt.executeQuery();
		      
		      
		      while(rset.next()) {
		    	 Map<String, String> hm = new HashMap<>();
		    	  
		         hm.put("roomName",rset.getString("room_name"));
		         hm.put("maxGuestNum", Integer.toString(rset.getInt("max_guest_num")));
		         hm.put("price", Integer.toString(rset.getInt("price")));
		         hm.put("category",rset.getString("category"));
		         hm.put("checkIn",checkIn);
		         hm.put("checkOut",checkOut);
		         hm.put("roomNum", Integer.toString(rset.getInt("room_num")));
		         hm.put("hotelNum", Integer.toString(rset.getInt("hotel_num")));
		         list.add(hm);
		      	}
	      } finally {
				DBUtil.close(con, pstmt, rset);
		}
	      return list;
	   }

	public  static boolean booking(bookingListDTO booking) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			con = DBUtil.getConnection();
		
			pstmt = con.prepareStatement("insert into bookinglist(member_num, room_num,guest_num,checkin_date, checkout_date) values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, booking.getMemberNum());
			pstmt.setInt(2,booking.getRoomNum());
			pstmt.setInt(3,booking.getGuestNum());
		    pstmt.setString(4, booking.getCheckin_date());
		    pstmt.setString(5, booking.getCheckout_date());
			int count = pstmt.executeUpdate();
			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result;
	}
	   
	   public static List<Object> bookingComplete(String memberId) throws SQLException {
	         
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rset = null;
	         List<Object> list = new ArrayList<>();
	         String sql = "select member_name, tel, checkin_date, checkout_date, booked_date, max_guest_num, guest_num, category, price, room_name, hotel_grade, hotel_location, hotel_name " + 
	                     "from hotel as h left join room as r on h.hotel_num=r.hotel_num left join bookinglist as b on r.room_num = b.room_num left join member as m on b.member_num = m.member_num " + 
	                     "where booking_num in (select max(booking_num) " + 
	                     "from bookinglist " + 
	                     "where member_id = ?)";

	         try {
	            con = DBUtil.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, memberId);
	            rset = pstmt.executeQuery();
	            while(rset.next()) {
	              Map<String, String> hm = new HashMap<>();
	               hm.put("memberName",rset.getString("member_name"));
	               hm.put("tel",rset.getString("tel"));
	               hm.put("checkinDate",rset.getString("checkin_date"));
	               hm.put("checkoutDate",rset.getString("checkout_date"));
	               hm.put("bookedDate",rset.getString("booked_date"));
	               hm.put("maxGuestNum", Integer.toString(rset.getInt("max_guest_num")));
	               hm.put("guestNum", Integer.toString(rset.getInt("guest_num")));
	               hm.put("category",rset.getString("category"));
	               hm.put("price", Integer.toString(rset.getInt("price")));
	               hm.put("roomName",rset.getString("room_name"));
	               hm.put("hotelGrade", Integer.toString(rset.getInt("hotel_grade")));
	               hm.put("hotelLocation",rset.getString("hotel_location"));
	               hm.put("hotelName",rset.getString("hotel_name"));
	               list.add(hm);
	               }
	         } finally {
	            DBUtil.close(con, pstmt, rset);
	      }
	         return list;
		   }
	   
	   public static List<Object> myBooked(String memberId) throws SQLException, ParseException {
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rset = null;
	         List<Object> list = new ArrayList<>();
	         String sql = "select member_name, hotel_name, room_name,category, hotel_location,checkin_date, checkout_date, booked_date, booking_num " + 
	                     "from hotel as h left join room as r on h.hotel_num=r.hotel_num left join bookinglist as b on r.room_num = b.room_num left join member as m on b.member_num = m.member_num " + 
	                     "where member_id = ?";

	         Date nowTime = null;
	         SimpleDateFormat sf = null;
	         try {
	            con = DBUtil.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, memberId);
	            rset = pstmt.executeQuery();
	            
	            nowTime = new Date();
	            sf = new SimpleDateFormat("yyyy-MM-dd");
	            
	            while(rset.next()) {
	              Map<String, String> hm = new HashMap<>();
	               hm.put("memberName",rset.getString("member_name"));
	               hm.put("hotelName",rset.getString("hotel_name"));
	               hm.put("category",rset.getString("category"));
	               hm.put("roomName",rset.getString("room_name"));
	               hm.put("hotelLocation",rset.getString("hotel_location"));
	               
	               hm.put("checkinDate",rset.getString("checkin_date"));
	               hm.put("checkoutDate",rset.getString("checkout_date"));
	               hm.put("bookedDate",rset.getString("booked_date"));
	               
	               hm.put("bookingNum", Integer.toString(rset.getInt("booking_num")));
	               
	               if(nowTime.compareTo(sf.parse((String)rset.getString("checkin_date"))) > 0) {
	            	   hm.put("cancelFlag", "false");
	               }else {
	            	   hm.put("cancelFlag", "true");
	               }
	               
	               list.add(hm);
	               }
	         } finally {
	            DBUtil.close(con, pstmt, rset);
	      }
	         return list;
		   }


	
	public static boolean cancel(int bookingNum) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql="delete from bookinglist where booking_num=?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1,bookingNum);

			int count = pstmt.executeUpdate();
			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result;
	}

}

