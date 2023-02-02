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
import dto.UserDto;

/**
 * Servlet implementation class ReservationUpdate
 */
@WebServlet("/ReservationUpdate")
public class ReservationUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReservationDao resdao = new ReservationDao();
		try {
		request.setCharacterEncoding("UTF-8");
		ServletContext application = this.getServletContext();
		
		int resId = Integer.parseInt(request.getParameter("reservation"));
		UserDto udto = (UserDto) application.getAttribute("USER");
		
		ArrayList<ReservationDto> reslist = new ArrayList<ReservationDto>();
		
		String userid = udto.getUserId();
		
		reslist = resdao.delete(userid,resId);
		
		application.setAttribute("RESERVATION", reslist);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/013reservation.jsp");
		rd.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			resdao.closeDB();
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
