package bookshop.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.AdminOrderDAO;

@WebServlet("/adminOrderList") // 주문정보 조회 (인덱스,회원명,회원id,주문일,주문도서,수량)
public class AdminOrderList extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("orderMapList" , AdminOrderDAO.getInstance().getOrderList());
		
		RequestDispatcher dis = request.getRequestDispatcher("views/admin/adminOrderList.jsp");
		dis.forward(request, response);
		
	}


}
