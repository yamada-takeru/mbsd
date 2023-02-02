package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ScheduleDto;

public class ScheduleDao {

	Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	// 接続先DB、ユーザ名、パスワード
	String url = "jdbc:mysql://127.0.0.1:3306/swat";
	String user = "root";
	String password = "";

	// コンストラクタ
	public ScheduleDao() {
		System.out.println("ScheduleDao,ログイン処理実行");
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
	public ArrayList<ScheduleDto> selectExe(int planId) {
		System.out.println("ScheduleDao-selectExe,SELECT文実行");
		ArrayList<ScheduleDto> schlist = new ArrayList<ScheduleDto>();
		try {
			String a = "select * from t_plan,t_plan_schedule,t_schedule\n"
					+ "where t_plan.plan_id = t_plan_schedule.plan_id\n"
					+ "AND t_plan_schedule.schedule_id = t_schedule.schedule_id\n"
					+ "AND t_plan.plan_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setInt(1, planId);
			rs = pstmt.executeQuery();
			System.out.println("ScheduleDao-selectExe,SELECT文成功");
			while (rs.next()) {
				ScheduleDto sch = new ScheduleDto();
				sch.setSequentialNumber(rs.getInt("sequential_number"));
				sch.setTransportationId(rs.getInt("transportation_id"));
				sch.setSchedule(rs.getString("schedule"));

				schlist.add(sch);
			}
		} catch (Exception ex) {
			System.out.println("ScheduleDao-selectExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return schlist;
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
