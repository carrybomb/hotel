package hotelServiceDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hotelServiceDTO.bookingListDTO;
import hotelServiceDTO.memberDTO;
import util.DBUtil;


public class memberDAO {
	
	public static ArrayList<memberDTO> getAllContents(String se, String name) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<memberDTO> alist = null;
		String sql = "select * from member";
		if (se.equals("all")) {
			sql += " where authority is not null";
		} else {
			sql += " where authority = ?";
		}
		
		if(name.equals("")) {
			sql += " and member_name is not null";
		} else {
			sql += " and member_name = ?";
		}
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			if(se.equals("all")) {
				if(!name.equals("")) {
					pstmt.setString(1, name);																
				}
			} else {
				pstmt.setString(1, se);
				if(!name.equals("")) {
					pstmt.setString(2, name);						
				} 	
			}
			rset = pstmt.executeQuery();
			alist = new ArrayList<memberDTO>();
			
			while (rset.next()) {
				alist.add(new memberDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getInt(7), rset.getString(8)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return alist;
	}
	
	
	public static boolean addAdmin(memberDTO am) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into member (member_id,member_pw,member_name,tel,email,member_grade,authority) values (?,?,?,?,?,0,'admin')");

	        pstmt.setString(1, am.getId());
	        pstmt.setString(2, am.getPw());
	        pstmt.setString(3, am.getMemberName());
	        pstmt.setString(4, am.getTel());
	        pstmt.setString(5, am.getEmail());
	        
			int count = pstmt.executeUpdate();			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result;		
	}
	
	
	public static memberDTO getContent(int num) throws SQLException{		
		Connection con = null;	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		memberDTO am  = null;
		String sql="select * from member where member_num=?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);	
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				am = new memberDTO(rset.getInt(1),rset.getString(2),
						rset.getString(3),rset.getString(4),rset.getString(5),
						rset.getString(6),rset.getInt(7),rset.getString(8));
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return am;
	}
	
	public  static boolean updateContent(memberDTO am) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
		
			pstmt = con.prepareStatement("update member set member_name=?, member_id=?, member_pw=?, tel=?, email=?, member_grade=?, authority=?  where member_num=?");
			pstmt.setString(1, am.getMemberName());
			pstmt.setString(2,am.getId());
			pstmt.setString(3, am.getPw());
		    pstmt.setString(4, am.getTel());
		    pstmt.setString(5, am.getEmail());
		    pstmt.setInt(6, am.getMemberGrade());
		    pstmt.setString(7, am.getAuthority());		    
		    pstmt.setInt(8, am.getMemberNum());

			int count = pstmt.executeUpdate();
			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result;
	}
	
	
	public static boolean deleteContent(int num, String pw) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		String sql="delete from member where member_num=? and member_pw=?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1,num);
	        pstmt.setString(2,pw);

			int count = pstmt.executeUpdate();
			
			if(count != 0){
				result = true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return result;
	}
	
	public static memberDTO selectMember(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		memberDTO member = null;
		
		String sql="select * from member where member_id=?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new memberDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getInt(7), rset.getString(8));
			}
			}finally {
				DBUtil.close(con, pstmt, rset);
			}
			
		return member;
	}
	
	public static boolean updateInfo(String name, String tel, String email, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		String sql = "update member set member_name=?, tel=?, email=? where member_id=?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareCall(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			
			int count = pstmt.executeUpdate();
			
			if(count != 0) {
				result = true;
			}
			
		} finally {
			DBUtil.close(con, pstmt);
		}
		return result;
	}

	public static boolean deleteMember(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		String sql = "delete from member where member_id=?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			int count = pstmt.executeUpdate();
			
			if(count != 0) {
				result = true;
			}
		}finally {
			DBUtil.close(con, pstmt);
		}
		return result;
	}
}
