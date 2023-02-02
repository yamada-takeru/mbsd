package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarrentalDao;
import dto.CarrentalDto;

/**
 * Servlet implementation class Plancontent
 */
@WebServlet("/Plancontent")
public class Plancontent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CarrentalDao cdao = new CarrentalDao();
		try {
		ServletContext application = this.getServletContext();
		int planId = (int)application.getAttribute("PLANID");
		ArrayList<CarrentalDto> carlist = cdao.selectAll(planId);
		application.setAttribute("CARLIST", carlist);
		String userId = (String)application.getAttribute("USERID");
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = LocalDateTime.now().format(dateTimeFormatter);
		
		application.setAttribute("TODAY", today);
		if(userId == null) {
			//どこからログイン画面へいくかの判定
			boolean a = true;
			application.setAttribute("ANGELS", a);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/005login.jsp");
			rd.forward(request, response);
		}else {
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/010buyTravelPlan.jsp");
			rd.forward(request, response);
		}
		}catch (Exception e) {
			e.printStackTrace();
			cdao.closeDB();
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
