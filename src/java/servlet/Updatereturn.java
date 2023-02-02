package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

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
 * Servlet implementation class Updatereturn
 */
@WebServlet("/Updatereturn")
public class Updatereturn extends HttpServlet {
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
			String name = request.getParameter("sei") + request.getParameter("mei");
			udto.setName(name);
			String furigana = request.getParameter("hurigana_sei") + request.getParameter("hurigana_mei");
			udto.setFurigana(furigana);

			String a = request.getParameter("password");
			String b = request.getParameter("repassword");
			if (!a.equals(b)) {
				sum = sum * 2;
			}
			String password = a;
			udto.setPassword(a);

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

			ServletContext application = this.getServletContext();
			application.setAttribute("ERRORSUM", sum);

			if (sum == 1) {
				//パスワードをハッシュ化
				MessageDigest sha256;
				sha256 = MessageDigest.getInstance("SHA-256");
				byte[] sha256_result = sha256.digest(password.getBytes());
				System.out.println("SHA-256：" + String.format("%040x", new BigInteger(1, sha256_result)));
				password = String.format("%040x", new BigInteger(1, sha256_result));

				String olduserId = (String) application.getAttribute("USERID");
				UserDto olduser = udao.UserExe(olduserId);
				udto.setRank(olduser.getRank());
				udto.setMile(olduser.getMile());
				String oldpassword = olduser.getPassword();

				udao.UpdateExe(userId, name, furigana, phonenumber, password, olduserId, oldpassword);
				application.setAttribute("USERID", userId);
				application.setAttribute("USER", udto);

				RequestDispatcher rd = request.getRequestDispatcher("/jsp/009mypage.jsp");
				rd.forward(request, response);

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/004update.jsp");
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
