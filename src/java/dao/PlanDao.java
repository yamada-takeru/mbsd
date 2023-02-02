package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.PlanDto;

public class PlanDao {
	
	Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	//接続先DB、ユーザ名、パスワード
	String url ="jdbc:mysql://127.0.0.1:3306/swat";
	String user = "root";
	String password = "";

	//コンストラクタ
	public PlanDao(){
		System.out.println("PlanDao,ログイン処理実行");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,password);
			stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//select文実行メソッド

	
	public int MaxDateExe(int planId){
		System.out.println("PlanDao-MaxDateExe,SELECT文実行");
		int i = 0;
		try{
			String a = "select * from t_plan where plan_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setInt(1, planId);
			rs = pstmt.executeQuery();
			
			System.out.println("PlanDao-MaxDateExe,SELECT文成功");
			rs.next();
			i = rs.getInt("max_accommodation_date");
		}catch(Exception ex){
			System.out.println("PlanDao-MaxDateExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return i;
	}
	
	public ArrayList<PlanDto>  selectExe(String prefecture,String transportation,Integer date,Integer guests){
		System.out.println("PlanDao-selectExe,SELECT文実行");
		ArrayList<PlanDto> planlist = new ArrayList<PlanDto>();
		try{
				int datea = 0;
				int dateb = 0;
				int guestsa = 0;
				int guestsb = 0;
				if(prefecture == "") {
					prefecture = "%";
				}
				if(transportation == "") {
					transportation = "%";
				}
				if(date == 0) {
					datea = 99;
					dateb = 0;
				}else {
					datea = date;
					dateb = date;
				}
				if(guests == 0) {
					guestsa = 99;
					guestsb = 0;
				}else {
					guestsa = guests;
					guestsb = guests;
				}
			String a = "SELECT * from t_plan,t_plan_schedule,t_schedule,m_transportation\r\n"
					+ "WHERE t_plan.plan_id = t_plan_schedule.plan_id\r\n"
					+ "AND t_plan_schedule.schedule_id = t_schedule.schedule_id\r\n"
					+ "AND t_schedule.transportation_id = m_transportation.transportation_id\r\n"
					+ "AND prefecture LIKE ? \r\n"
					+ "AND transportation LIKE ? \r\n"
					+ "AND min_accommodation_date <= ? \r\n"
					+ "AND max_accommodation_date >= ? \r\n"
					+ "AND min_guests <= ? \r\n"
					+ "AND max_guests >= ? \r\n"
					+ "ORDER BY t_plan.plan_id";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setString(1, prefecture);
			pstmt.setString(2, transportation);
			pstmt.setInt(3, datea);
			pstmt.setInt(4, dateb);
			pstmt.setInt(5, guestsa);
			pstmt.setInt(6, guestsb);
			rs = pstmt.executeQuery();

			System.out.println("PlanDao-selectExe,SELECT文成功");
			
			while(rs.next()) {
				PlanDto plan = new PlanDto();
				plan.setPlanId(rs.getInt("plan_id"));
				plan.setHotelId(rs.getInt("hotel_id"));
				plan.setPlanName(rs.getString("plan_name"));
				plan.setPrefecture(rs.getString("prefecture"));
				plan.setMinAccommodationDate(rs.getInt("min_accommodation_date"));
				plan.setMaxAccommodationDate(rs.getInt("max_accommodation_date"));
				plan.setMinAmount(rs.getInt("min_amount"));
				plan.setMaxAmount(rs.getInt("max_amount"));
				plan.setMinGuests(rs.getInt("min_guests"));
				plan.setMaxGuests(rs.getInt("max_guests"));
				plan.setPlanDescription(rs.getString("plan_description"));
				plan.setTransportation(rs.getString("transportation"));
				planlist.add(plan);
			}
		}catch(Exception ex){
			System.out.println("PlanDao-selectExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return planlist;
	}
	
	public PlanDto planIdExe(int planId){
		System.out.println("PlanDao-planIdExe,SELECT文実行");
		PlanDto plan = new PlanDto();
		try{
			String a = "select * from t_plan where plan_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setInt(1, planId);
			rs = pstmt.executeQuery();
			System.out.println("PlanDao-planIdExe,SELECT文成功");
			
			rs.next();
				plan.setPlanId(rs.getInt("plan_id"));
				plan.setHotelId(rs.getInt("hotel_id"));
				plan.setPlanName(rs.getString("plan_name"));
				plan.setPrefecture(rs.getString("prefecture"));
				plan.setMinAccommodationDate(rs.getInt("min_accommodation_date"));
				plan.setMaxAccommodationDate(rs.getInt("max_accommodation_date"));
				plan.setMinAmount(rs.getInt("min_amount"));
				plan.setMaxAmount(rs.getInt("max_amount"));
				plan.setMinGuests(rs.getInt("min_guests"));
				plan.setMaxGuests(rs.getInt("max_guests"));
				plan.setPlanDescription(rs.getString("plan_description"));
			
		}catch(Exception ex){
			System.out.println("PlanDao-planIdExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return plan;
	}

	//DB切断メソッド
	public void closeDB(){
		try{
			System.out.println("DB切断処理");
			if(rs!=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(con!=null){
				con.close();
			}
			System.out.println("DB切断成功");
		}catch(Exception ex){
			System.out.println("DB切断失敗");
			ex.printStackTrace();
		}
	}


}
