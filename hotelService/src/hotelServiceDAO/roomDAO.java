package hotelServiceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hotelServiceDTO.hotelDTO;
import hotelServiceDTO.memberDTO;
import hotelServiceDTO.roomDTO;
import util.DBUtil;

public class roomDAO {

	public static ArrayList<roomDTO> getRoomContents(int hotelNum) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<roomDTO> alist = null;
		String sql = "select * from room where hotel_num = ? group by category";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotelNum);
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

	public static ArrayList<roomDTO> getRoomContents2(int hotelNum,String name, String category) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<roomDTO> alist = null;
		String sql = "select * from room where hotel_num=?";
		
		if(name.equals("")) {
			sql += " and room_name is not null";
		} else {
			sql += " and room_name = ?";
		}
		if(category.equals("all")) {
			sql += " and category is not null";
		} else {
			sql += " and category = ?";
		}
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotelNum);					
			if(name.equals("")) {
				if(!category.equals("all")) {
					pstmt.setString(2, category);					
				}
			} else {
				pstmt.setString(2, name);					
				if(!category.equals("all")) {
					pstmt.setString(3, category);					
				}
			}
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
	
	public static boolean addRoom(roomDTO am) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into room (hotel_num,room_name,room_image_path,max_guest_num,price,category) values (?,?,?,?,?,?)");

	        pstmt.setInt(1, am.getHotelNum());
	        pstmt.setString(2, am.getRoomName());
	        pstmt.setString(3, am.getRoomImage());
	        pstmt.setInt(4, am.getMaxGuestNum());
	        pstmt.setInt(5, am.getPrice());
	        pstmt.setString(6, am.getCategory());
	        
			int count = pstmt.executeUpdate();			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result;		
	}
	
	public static roomDTO getContent(int num) throws SQLException{		
		Connection con = null;	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		roomDTO am  = null;
		String sql="select * from room where room_num=?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);	
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				am = new roomDTO(rset.getInt(1), rset.getInt(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getInt(6), rset.getInt(7),rset.getString(8));
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return am;
	}
	
	public  static boolean updateContent(roomDTO am) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		
		try {
			con = DBUtil.getConnection();
		
			pstmt = con.prepareStatement("update room set room_name=?, room_image_path=? , max_guest_num=? , price=? , category=? where room_num=?");
			pstmt.setString(1, am.getRoomName());
			pstmt.setString(2,am.getRoomImage());
		    pstmt.setInt(3, am.getMaxGuestNum());
		    pstmt.setInt(4, am.getPrice());
		    pstmt.setString(5, am.getCategory());
		    pstmt.setInt(6, am.getRoomNum());
		    
			int count = pstmt.executeUpdate();
			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result;
	}
	
	public static boolean deleteContent(int num) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql="delete from room where room_num=?";
		
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
}