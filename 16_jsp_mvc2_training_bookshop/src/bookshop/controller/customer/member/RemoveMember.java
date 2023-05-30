package bookshop.controller.customer.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookshop.dao.MemberDAO;

@WebServlet("/removeMember")
public class RemoveMember extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
	
		MemberDAO.getInstance().deleteMember((String)session.getAttribute("memberId"));
		session.invalidate();
		
		String jsScript = "<script>";
		jsScript += "alert('탈퇴 되었습니다.');";
		jsScript += "location.href='bookList';";
		jsScript += "</script>";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(jsScript);
	
	}

}
