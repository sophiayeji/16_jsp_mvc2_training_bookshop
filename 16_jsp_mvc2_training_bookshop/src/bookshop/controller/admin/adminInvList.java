package bookshop.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.AdminInvDAO;
import bookshop.dao.AdminOrderDAO;
import bookshop.dao.BookDAO;
import bookshop.dto.BookDTO;


@WebServlet("/adminInvList")   // 재고목록(인덱스,도서명,수량) , 가용재고수량
public class adminInvList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    

		String bookCd = String.valueOf(request.getParameter("bookCd"));
		System.out.println("!!!!!!!!");
		System.out.println(bookCd);
		
		request.setAttribute("invMapList", AdminInvDAO.getInstance().getAdminInvList(bookCd));
	
		RequestDispatcher dis = request.getRequestDispatcher("views/admin/adminInvList.jsp");
		dis.forward(request, response);

	}
}
