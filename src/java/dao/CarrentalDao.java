package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.CarrentalDto;

public class CarrentalDao {

	Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	// 接続先DB、ユーザ名、パスワード
	String url = "jdbc:mysql://127.0.0.1:3306/swat";
	String user = "root";
	String password = "";

	// コンストラクタ
	public CarrentalDao() {
		System.out.println("CarrentalDao,ログイン処理実行");
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
	
	
	public CarrentalDto selectExe(int carId) {
		System.out.println("CarrentalDao-selectExe,SELECT文実行");
		CarrentalDto car = new CarrentalDto();
	try {
			String a = "select * from m_car_rental where car_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setInt(1, carId);
			rs = pstmt.executeQuery();
			System.out.println("CarrentalDao-selectExe,SELECT文成功");
				rs.next();
				car.setCarId(rs.getInt("car_id"));
				car.setCarPhotoPath(rs.getString("car_photo_path"));
				car.setCarName(rs.getString("car_name"));
				car.setCompanyName(rs.getString("company_name"));
				car.setSize(rs.getString("size"));
				car.setHoldPeople(rs.getInt("hold_people"));
				car.setRentalPrice(rs.getInt("rental_price"));
				car.setCarDescription(rs.getString("car_description"));
		} catch (Exception ex) {
			System.out.println("CarrentalDao-selectExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return car;
	}
	
	public ArrayList<CarrentalDto> selectAll(int planId) {
		System.out.println("CarrentalDao-selectAll,SELECT文実行");
		ArrayList<CarrentalDto> carlist = new ArrayList<CarrentalDto>();
		try {
			String a = "select * from t_plan,m_car_choice,m_car_rental\n"
					+ "where t_plan.plan_id = m_car_choice.plan_id\n"
					+ "AND m_car_choice.car_id = m_car_rental.car_id\n"
					+ "AND t_plan.plan_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setInt(1, planId);
			rs = pstmt.executeQuery();
			System.out.println("CarrentalDao-selectAll,SELECT文成功");
			while (rs.next()) {
				CarrentalDto car = new CarrentalDto();
				car.setCarId(rs.getInt("car_id"));
				car.setCarPhotoPath(rs.getString("car_photo_path"));
				car.setCarName(rs.getString("car_name"));
				car.setCompanyName(rs.getString("company_name"));
				car.setSize(rs.getString("size"));
				car.setHoldPeople(rs.getInt("hold_people"));
				car.setRentalPrice(rs.getInt("rental_price"));
				car.setCarDescription(rs.getString("car_description"));
				carlist.add(car);
			}
		} catch (Exception ex) {
			System.out.println("CarrentalDao-selectAll,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return carlist;
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
