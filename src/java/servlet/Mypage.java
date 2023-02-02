package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.UserDto;

/**
 * Servlet implementation class Mypage
 */
@WebServlet("/Mypage")
public class Mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao udao = new UserDao();
		ServletContext application = this.getServletContext();
		try {
			
			request.setCharacterEncoding("UTF-8");
			String userId = (String)application.getAttribute("USERID");
			
			if(userId == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/005login.jsp");
				rd.forward(request, response);
			}else {
				UserDto udto = new UserDto();
				udto = udao.UserExe(userId);
				application.setAttribute("USER", udto);
				application.setAttribute("USERID", userId);
			 
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/009mypage.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			udao.closeDB();	
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
