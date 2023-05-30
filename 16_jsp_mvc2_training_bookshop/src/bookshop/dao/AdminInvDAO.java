package bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminInvDAO {

	private AdminInvDAO() {}
	private static AdminInvDAO instance = new AdminInvDAO();
	public static AdminInvDAO getInstance() {
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
	
	public ArrayList<HashMap<String, Object>> getAdminInvList(int bookCd) {

		ArrayList<HashMap<String, Object>> invList = new ArrayList<HashMap<String, Object>>();
		
		try {
			
			getConnection();
			String sql = "SELECT * FROM BOOK B ";
				   sql += "INNER JOIN `ORDER` O ";
				   sql += "ON O.BOOK_CD = B.BOOK_CD ";
				   sql += "AND O.BOOK_CD  = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookCd);
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {

				HashMap<String, Object> invMap = new HashMap<String, Object>();
				invMap.put("bookCd" , rs.getInt("BOOK_CD"));
				invMap.put("book_Nm" , rs.getString("BOOK_NM "));
				invMap.put("isbn" , rs.getString("ISBN"));
				invMap.put("publisher" , rs.getString("PUBLISHER"));
				invMap.put("published_Dt" , rs.getDate("PUBLISHED_DT"));
				invMap.put("stock" , rs.getInt("STOCK"));
				invMap.put("orderBookQty" , rs.getInt("ORDERBOOKQTY"));
				invList.add(invMap);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return invList;
		
	}


	public Object getAdminInvList(String bookCd) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
