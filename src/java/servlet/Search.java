package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PlanDao;
import dto.PlanDto;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlanDao pdao = new PlanDao();
		try {
			request.setCharacterEncoding("UTF-8");
			String prefecture = request.getParameter("prefecture");
			String transportation = request.getParameter("transportation");
			Integer date = Integer.parseInt(request.getParameter("date"));
			Integer guests = Integer.parseInt(request.getParameter("guests"));
			ArrayList<PlanDto> pdtolist = new ArrayList<PlanDto>();
			pdtolist = pdao.selectExe(prefecture, transportation, date, guests);
			
			ServletContext application = this.getServletContext();
			application.setAttribute("PLANLIST", pdtolist);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/007travelPlanSearchResultDisplay.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			pdao.closeDB();		
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
