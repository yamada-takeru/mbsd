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
 * Servlet implementation class MemberRegistrationConfirmation
 */
@WebServlet("/MemberRegistrationConfirmation")
public class MemberRegistrationConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao udao = new UserDao();
		try {
			request.setCharacterEncoding("UTF-8");
			UserDto udto = new UserDto();
			int sum = 1;

			udto.setName(request.getParameter("sei") + request.getParameter("mei"));
			udto.setFurigana(request.getParameter("hurigana_sei") + request.getParameter("hurigana_mei"));

			String phonenumber = request.getParameter("phone_number");
			int plen = phonenumber.length();
			if (!(plen == 11 || plen == 10)) {
				sum = sum * 3;
			}
			udto.setPhoneNumber(phonenumber);

			String userId = request.getParameter("user_id");
			int userlen = userId.length();
			if (!(userlen >= 8 && userlen <= 16)) {
				sum = sum * 5;
			}
			udto.setUserId(userId);

			
			boolean kaburi = udao.kaburi(userId);

			if (kaburi) {
				sum = sum * 7;
			}

			String a = request.getParameter("password");
			String b = request.getParameter("repassword");
			if (!a.equals(b)) {
				sum = sum * 2;
			}
			udto.setPassword(a);

			ServletContext application = this.getServletContext();
			application.setAttribute("ERRORSUM", sum);

			if (sum == 1) {

				application.setAttribute("USER", udto);

				RequestDispatcher rd = request.getRequestDispatcher("/jsp/002memberRegistrationConfirmation.jsp");
				rd.forward(request, response);

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/001register.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			udao.closeDB();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
