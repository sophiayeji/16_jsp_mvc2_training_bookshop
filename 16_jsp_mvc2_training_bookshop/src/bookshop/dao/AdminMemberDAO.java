package bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bookshop.dto.AdminMemberDTO;

public class AdminMemberDAO {

	private AdminMemberDAO() {}
	private static AdminMemberDAO instance = new AdminMemberDAO();
	public static AdminMemberDAO getInstance() {
		return instance;
	}

	private Connection conn         = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs            = null;

	public void getConnection() {
		
		try {
			
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");       
			DataSource ds = (DataSource) envctx.lookup("jdbc/bookshop"); 		  
			conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void getClose() {
    	if (rs != null)    {try {rs.close();}   catch (SQLException e) {}}
    	if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
        if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
    }
	
	
	 public boolean adminLogin(AdminMemberDTO adminMemberDTO) {
	    	
    	boolean isLogin = false;
    	
    	try {
    		
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM `ADMIN` WHERE ADMIN_ID = ? AND PASSWD = ?");
			pstmt.setString(1, adminMemberDTO.getAdminId());
			pstmt.setString(2, adminMemberDTO.getPasswd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isLogin = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
    	
    	return isLogin;
	    	
	 }
	 
}
