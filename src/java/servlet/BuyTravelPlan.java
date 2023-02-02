package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarrentalDao;
import dto.CarrentalDto;
import dto.PlanDto;
import dto.ReservationDto;

/**
 * Servlet implementation class BuyTravelPlan
 */
@WebServlet("/BuyTravelPlan")
public class BuyTravelPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CarrentalDao cdao = new CarrentalDao();
		try {
		request.setCharacterEncoding("UTF-8");
		ServletContext application = this.getServletContext();
		
		String userId = (String)application.getAttribute("USERID");
		int planId = (int)application.getAttribute("PLANID");
		
		String checkInDay = request.getParameter("checkInDay");
		String year = checkInDay.substring(0,4);
		String month = checkInDay.substring(5,7);
		String day = checkInDay.substring(8,10);
		String result = year + month + day;
		int intcheckInDay = Integer.parseInt(result);
		
		Integer checkInTime = Integer.parseInt(request.getParameter("checkInTime"));
		
		Integer accommodationDate = Integer.parseInt(request.getParameter("accommodationDate"));
		
		Integer adult = Integer.parseInt(request.getParameter("adult"));
		Integer kids =  Integer.parseInt(request.getParameter("kids"));
		
		String ScarId = request.getParameter("carid");
		int icarId = 0;
		if(ScarId != null) {
			icarId = Integer.parseInt(ScarId);
		}
		
		CarrentalDto cdto = cdao.selectExe(icarId);
		
		int carprice = cdto.getRentalPrice();
		PlanDto pdto = (PlanDto)application.getAttribute("PDTO");	
		int fixedPrice = (pdto.getMinAmount() * (adult + kids) + carprice) * accommodationDate;
		
		
		ReservationDto rdto = new ReservationDto();
		rdto.setUserId(userId);
		rdto.setPlanId(planId);
		rdto.setCheckInDay(intcheckInDay);
		rdto.setCheckInTime(checkInTime);
		rdto.setAccommodationDate(accommodationDate);
		rdto.setAdultGuests(adult);
		rdto.setChildGuests(kids);
		rdto.setCarId(icarId);
		rdto.setFixedPrice(fixedPrice);
		
		application.setAttribute("RESERVATIONNEO", rdto);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/011travelPlanPurchaseConfirmation.jsp");
		rd.forward(request, response);	
	
		}catch(StringIndexOutOfBoundsException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/010buyTravelPlan.jsp");
			rd.forward(request, response);
	}catch(NumberFormatException e) {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/010buyTravelPlan.jsp");
		rd.forward(request, response);
	}catch(Exception e) {
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
