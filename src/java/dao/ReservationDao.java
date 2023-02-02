package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ReservationDto;

public class ReservationDao {

	Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	// 接続先DB、ユーザ名、パスワード
	String url = "jdbc:mysql://127.0.0.1:3306/swat";
	String user = "root";
	String password = "";
	String sql;

	// コンストラクタ
	public ReservationDao() {
		System.out.println("ReservationDao,ログイン処理実行");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	// select文実行メソッド
	public ArrayList<ReservationDto> findAll() {
		System.out.println("ReservationDao-findAll,SELECT文実行");
		ArrayList<ReservationDto> reslist = new ArrayList<ReservationDto>();
		try {
			rs = stmt.executeQuery("select * from t_reservation");
			System.out.println("ReservationDao-findAll,SELECT文成功");
			while (rs.next()) {
				ReservationDto res = new ReservationDto();
				res.setReservationId(rs.getInt("reservation_id"));
				res.setUserId(rs.getString("user_id"));
				res.setPlanId(rs.getInt("plan_id"));
				res.setCheckInDay(rs.getInt("check_in_day"));
				res.setCheckInTime(rs.getInt("check_in_time"));
				res.setFixedPrice(rs.getInt("fixed_price"));
				res.setAccommodationDate(rs.getInt("accommodation_date"));
				res.setAdultGuests(rs.getInt("adult_guests"));
				res.setChildGuests(rs.getInt("child_guests"));
				res.setCarId(rs.getInt("car_id"));

				reslist.add(res);
			}
		} catch (Exception ex) {
			System.out.println("ReservationDao-findAll,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return reslist;
	}

	public ArrayList<ReservationDto> selectExe(String userId) {
		System.out.println("ReservationDao-selectExe,SELECT文実行");
		ArrayList<ReservationDto> reslist = new ArrayList<ReservationDto>();

		try {
			String a = "select * from t_reservation where user_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			System.out.println("ReservationDao-selectExe,SELECT文成功");

			while (rs.next()) {
				ReservationDto res = new ReservationDto();
				res.setReservationId(rs.getInt("reservation_id"));
				res.setUserId(rs.getString("user_id"));
				res.setPlanId(rs.getInt("plan_id"));
				res.setCheckInDay(rs.getInt("check_in_day"));
				res.setCheckInTime(rs.getInt("check_in_time"));
				res.setFixedPrice(rs.getInt("fixed_price"));
				res.setAccommodationDate(rs.getInt("accommodation_date"));
				res.setAdultGuests(rs.getInt("adult_guests"));
				res.setChildGuests(rs.getInt("child_guests"));
				res.setCarId(rs.getInt("car_id"));

				reslist.add(res);
			}

		} catch (Exception ex) {
			System.out.println("ReservationDao-selectExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return reslist;
	}

	// insert、update文実行メソッド
	public int InsertExe(int reservationId, String userId, int planId, int checkinDay, int checkinTime, int fixedPrice,
			int accommodationDate, int adultGuests, int childGuests, int carId) {
		int cnt = 0;
		System.out.println("ReservationDao-InsertExe,INSERT文実行");
		try {
			String a = "INSERT INTO `swat`.`t_reservation`\r\n"
					+ " (`reservation_id`, `user_id`, `plan_id`, `check_in_day`, `check_in_time`, `fixed_price`, `accommodation_date`, `adult_guests`, `child_guests`, `car_id`)\r\n"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setInt(1, reservationId);
			pstmt.setString(2, userId);
			pstmt.setInt(3, planId);
			pstmt.setInt(4, checkinDay);
			pstmt.setInt(5, checkinTime);
			pstmt.setInt(6, fixedPrice);
			pstmt.setInt(7, accommodationDate);
			pstmt.setInt(8, adultGuests);
			pstmt.setInt(9, childGuests);
			pstmt.setInt(10, carId);
			cnt = pstmt.executeUpdate();
			System.out.println("ReservationDao-InsertExe,INSERT文成功");
		} catch (Exception ex) {
			System.out.println("ReservationDao-InsertExe,INSERT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return cnt;
	}

	public ArrayList<ReservationDto> delete(String userId, int resId) {
		int cnt = 0;
		ArrayList<ReservationDto> reslist = new ArrayList<ReservationDto>();
		ReservationDao resdao = new ReservationDao();
		System.out.println("ReservationDao-delete,DELETE文実行");
		try {	
				String a = "DELETE FROM t_reservation WHERE reservation_id = ?";
				PreparedStatement pstmt = con.prepareStatement(a);
				pstmt.setInt(1, resId);
				cnt = pstmt.executeUpdate();
				System.out.println("ReservationDao-delete,DELETE文成功");	
				reslist = resdao.selectExe(userId);
		} catch (Exception ex) {
			System.out.println("ReservationDao-delete,DELETE文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return reslist;
	}

	// DB切断メソッド
	public void closeDB() {
		try {
			System.out.println("DB切断処理");
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
			System.out.println("DB切断成功");
		} catch (Exception ex) {
			System.out.println("DB切断失敗");
			ex.printStackTrace();
		}
	}
}
