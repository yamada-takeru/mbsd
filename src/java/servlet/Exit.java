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
 * Servlet implementation class Exit
 */
@WebServlet("/Exit")
public class Exit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao udao = new UserDao();
		try {
		ServletContext application = this.getServletContext();
		
		UserDto a = (UserDto)application.getAttribute("USER");
		String userId = a.getUserId();
		
		
		udao.ExitExe(userId);
		
		UserDto udto = new UserDto();
		application.setAttribute("USER",udto);
		
		boolean loginkekka = false;
		application.setAttribute("LOGINKEKKA",loginkekka); 
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/006home.jsp");
		rd.forward(request, response);	
		}catch(Exception e) {
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
