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

import dao.ReservationDao;
import dto.ReservationDto;

/**
 * Servlet implementation class TravelPlanPurchaseConfirmation
 */
@WebServlet("/TravelPlanPurchaseConfirmation")
public class TravelPlanPurchaseConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReservationDao rdao = new ReservationDao();
		try {
		ServletContext application = this.getServletContext();
		ReservationDto rdto = (ReservationDto)application.getAttribute("RESERVATIONNEO");	
		ArrayList<ReservationDto> ResList = new ArrayList<ReservationDto>();
		ResList = rdao.findAll();
		int ResSize = ResList.size() + 2;
		String userId = rdto.getUserId();
		int planId = rdto.getPlanId();
		int checkinDay = rdto.getCheckInDay();
		int checkinTime = rdto.getCheckInTime();
		int fixedPrice = rdto.getFixedPrice();
		int accommodationDate = rdto.getAccommodationDate();
		int adultGuests = rdto.getAdultGuests();
		int childGuests = rdto.getChildGuests();
		int carId = rdto.getCarId();
		
		rdao.InsertExe(ResSize, userId, planId, checkinDay, checkinTime, fixedPrice, accommodationDate, adultGuests, childGuests, carId);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/012complete.jsp");
		rd.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			rdao.closeDB();
			
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
