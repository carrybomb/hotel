package hotelServiceDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hotelServiceDTO.hotelDTO;
import hotelServiceDTO.memberDTO;
import util.DBUtil;

public class hotelDAO {

	// 모든 게시물 조회
	public static ArrayList<hotelDTO> getAllContents(String name, String location) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<hotelDTO> alist = null;
		String sql = "select * from hotel";
		
		if(name.equals("")) {
			sql += " where hotel_name is not null";
		} else {
			sql += " where hotel_name = ?";
		}
		if(location.equals("")) {
			sql += " and hotel_location is not null";
		} else {
			sql += " and hotel_location = ?";
		}
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			
			if(name.equals("")) {
				if(!location.equals("")) {
					pstmt.setString(1, location);				
				} 
			} else {
				pstmt.setString(1, name);				
				if(!location.equals("")) {
					pstmt.setString(2, location);
				}
			}
					
			
			rset = pstmt.executeQuery();
			alist = new ArrayList<hotelDTO>();
			while (rset.next()) {
				alist.add(new hotelDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
						rset.getString(5), rset.getInt(6)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return alist;
	}
	
	public static boolean addHotel(hotelDTO am) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into hotel (hotel_name,hotel_image,hotel_location,hotel_grade) values (?,?,?,?)");

	        pstmt.setString(1, am.getHotelName());
	        pstmt.setString(2, am.getHotelImagePath());
	        pstmt.setString(3, am.getHotelLocation());
	        pstmt.setInt(4, am.getHotelGrade());
	        
			int count = pstmt.executeUpdate();			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result;		
	}
	
	
	public static hotelDTO getContent(int num) throws SQLException{		
		Connection con = null;	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		hotelDTO am  = null;
		String sql="select * from hotel where hotel_num=?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);	
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				am = new hotelDTO(rset.getInt(1),rset.getString(2), rset.getString(3),
						rset.getDouble(4),rset.getString(5),rset.getInt(6));
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return am;
	}

	public  static boolean updateContent(hotelDTO am) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
		
			pstmt = con.prepareStatement("update hotel set hotel_name=?, hotel_image=?, star=?, hotel_location=?, hotel_grade=? where hotel_num=?");
			pstmt.setString(1, am.getHotelName());
			pstmt.setString(2,am.getHotelImagePath());
			pstmt.setDouble(3, am.getStar());
		    pstmt.setString(4, am.getHotelLocation());
		    pstmt.setInt(5, am.getHotelGrade());
		    pstmt.setInt(6, am.getHotelNum());
		    

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
		
		String sql="delete from hotel where hotel_num=?";
		
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
