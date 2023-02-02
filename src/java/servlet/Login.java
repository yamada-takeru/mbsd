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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int sum = 1;
		boolean login = false;
		UserDao udao = new UserDao();
		try {
			request.setCharacterEncoding("UTF-8");
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			
			//パスワードをハッシュ化
			MessageDigest sha256;
			sha256 = MessageDigest.getInstance("SHA-256");
			byte[] sha256_result = sha256.digest(password.getBytes());
			System.out.println("SHA-256：" + String.format("%040x", new BigInteger(1, sha256_result)));
			password = String.format("%040x", new BigInteger(1, sha256_result));
			
			boolean kekka = udao.selectExe(userId, password);

			ServletContext application = this.getServletContext();
			
			//どこからログイン画面に来たか判定
			boolean a = (boolean)application.getAttribute("ANGELS");
			
			if (kekka == true) {
				login = true;
				application.setAttribute("LOGINKEKKA", login);
				UserDto user = udao.UserExe(userId);
				application.setAttribute("USERID", userId);
				application.setAttribute("USER", user);
				System.out.println("ログインできました");
					if(a == false) {
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/006home.jsp");
						rd.forward(request, response);
					}else {
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/010buyTravelPlan.jsp");
						rd.forward(request, response);
					}
			} else {
				
				sum = sum * 2;
				application.setAttribute("ERRORSUM", sum);
					
				System.out.println("ログインできない");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/005login.jsp");
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
