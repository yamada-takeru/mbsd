package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
 * Servlet implementation class MembershipRegistrationComplete
 */
@WebServlet("/MembershipRegistrationComplete")
public class MembershipRegistrationComplete extends HttpServlet {
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
			ServletContext application = this.getServletContext();
			UserDto udto = (UserDto) application.getAttribute("USER");
			String userId = udto.getUserId();
			String name = udto.getName();
			String furigana = udto.getFurigana();
			String phonenumber = udto.getPhoneNumber();
			String password = udto.getPassword();
			
			// パスワードをハッシュ化
			MessageDigest sha256;
			sha256 = MessageDigest.getInstance("SHA-256");
			byte[] sha256_result = sha256.digest(password.getBytes());
			System.out.println("SHA-256：" + String.format("%040x", new BigInteger(1, sha256_result)));
			password = String.format("%040x", new BigInteger(1, sha256_result));
			
			udao.InsertExe(userId, name, furigana, phonenumber, password);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/003membershipRegistrationComplete.jsp");
			rd.forward(request, response);

		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			udao.closeDB();
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
