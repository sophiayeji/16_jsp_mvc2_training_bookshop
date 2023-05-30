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

public class AdminOrderDAO {

	private AdminOrderDAO() {}
	private static AdminOrderDAO instance = new AdminOrderDAO();
	public static AdminOrderDAO getInstance() {
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
	
	
	public ArrayList<HashMap<String, Object>> getOrderList() {

		ArrayList<HashMap<String, Object>> orderList = new ArrayList<HashMap<String, Object>>();
		
		try {
			
			getConnection();
			String sql = "SELECT * FROM `ORDER` O ";
				   sql += "INNER JOIN BOOK B ";
				   sql += "ON O.BOOK_CD = B.BOOK_CD ";
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {

				HashMap<String, Object> orderMap = new HashMap<String, Object>();
				orderMap.put("orderCd" , rs.getLong("ORDER_CD"));
				orderMap.put("bookCd" , rs.getInt("BOOK_CD"));
				orderMap.put("bookNm" , rs.getString("BOOK_NM"));
				orderMap.put("imgNm" , rs.getString("IMG_NM"));
				orderMap.put("price" , rs.getString("PRICE"));
				orderMap.put("memberId" , rs.getString("MEMBER_ID"));
				orderMap.put("orderBookQty" , rs.getInt("ORDER_BOOK_QTY"));
				orderList.add(orderMap);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return orderList;
		
	}
	
	
	
}
