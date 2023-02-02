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

import dao.HotelDao;
import dao.HotelSightDao;
import dao.HotelconvenienceDao;
import dao.PlanDao;
import dao.ScheduleDao;
import dto.HotelDto;
import dto.PlanDto;
import dto.ScheduleDto;

/**
 * Servlet implementation class TravelPlanSearchResultDisplay
 */
@WebServlet("/TravelPlanSearchResultDisplay")
public class TravelPlanSearchResultDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlanDao pdao = new PlanDao();
		HotelDao hdao = new HotelDao();
		HotelconvenienceDao hcdao = new HotelconvenienceDao();
		HotelSightDao hsdao = new HotelSightDao();
		ScheduleDao sdao = new ScheduleDao();
		try {
		ServletContext application = this.getServletContext();
		int planId = Integer.parseInt(request.getParameter("plan_id"));
		
		PlanDto pdto = pdao.planIdExe(planId);
		int hotelId = pdto.getHotelId();
		HotelDto hdto = hdao.selectExe(hotelId);	
		ArrayList<String> con = hcdao.selectExe(hotelId);
		ArrayList<String> hslist = hsdao.selectExe(hotelId);
		ArrayList<ScheduleDto> schlist = sdao.selectExe(planId);
		
		
		application.setAttribute("PDTO", pdto);
		application.setAttribute("HDTO", hdto);
		application.setAttribute("CON", con);
		application.setAttribute("SIGHTS", hslist);
		application.setAttribute("PLANID", planId);
		application.setAttribute("SCHLIST", schlist);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/008plancontent.jsp");
		rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			pdao.closeDB();
			
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
