package hotelServiceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hotelServiceDTO.memberDTO;
import util.DBUtil;

public class LoginDAO {
	
	public static String login(String id, String pwd) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String result = "";
		String sql = "select member_pw from member where member_id=?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				if(rset.getString(1).equals("admin123")) {
					result = "Admin";
				}
				else if(rset.getString(1).equals(pwd)) {
					result = "Member";
				}
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return result;
	}
	
	public static boolean checkId(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean result = true;
		String sql = "select member_id from member where member_id=?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = false;
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return result;
	}
	
	public static boolean signUp(memberDTO member) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql = "insert into member(member_id, member_pw, member_name, tel, email) values(?, ?, ?, ?, ?)";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getTel());
			pstmt.setString(5, member.getEmail());
			
			int count = pstmt.executeUpdate();
			
			if(count != 0) {
				result = true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return result;
	}
	
	public static String findPw(String id, String email) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String result = "";
		String sql = "select member_pw from member where member_id=? and email=?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}	
		return result;
		
	}
}
