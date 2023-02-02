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

import dao.CarrentalDao;
import dao.HotelDao;
import dao.PlanDao;
import dao.ReservationDao;
import dto.CarrentalDto;
import dto.HotelDto;
import dto.PlanDto;
import dto.ReservationDto;
import dto.UserDto;

/**
 * Servlet implementation class Reservation
 */
@WebServlet("/Reservation")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReservationDao rdao = new ReservationDao();
		PlanDao pdao = new PlanDao();
		HotelDao hdao = new HotelDao();
		CarrentalDao cdao = new CarrentalDao();
		try {
		ServletContext application = this.getServletContext();
		ArrayList<ReservationDto> rdtolist = new ArrayList<ReservationDto>();
		ArrayList<PlanDto> pdtolist  = new ArrayList<PlanDto>();
		ArrayList<HotelDto> hdtolist  = new ArrayList<HotelDto>();
		ArrayList<CarrentalDto> cdtolist  = new ArrayList<CarrentalDto>();
		
		UserDto udto = (UserDto) application.getAttribute("USER");
		
		rdtolist = rdao.selectExe(udto.getUserId());
		
		for(int i = 0;i < rdtolist.size();i++){ 
			ReservationDto rdto = rdtolist.get(i);
			
			int planId = rdto.getPlanId();
			pdtolist.add(i, pdao.planIdExe(planId));
			
			PlanDto pdto = pdtolist.get(i);
			int hotelId = pdto.getHotelId();
			hdtolist.add(i, hdao.selectExe(hotelId));
			
			int carId = rdto.getCarId();
			cdtolist.add(i, cdao.selectExe(carId));
		
		}
		
		application.setAttribute("RESERVATION", rdtolist);
		application.setAttribute("RES_PLANLIST", pdtolist);
		application.setAttribute("RES_HOTELLIST", hdtolist);
		application.setAttribute("CARRENTALLIST", cdtolist);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/013reservation.jsp");
		rd.forward(request, response);
		}catch(Exception e) {
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
