package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HotelSightDao {
	
		Connection con = null;
		private Statement stmt = null;
		private ResultSet rs = null;
		// 接続先DB、ユーザ名、パスワード
		String url = "jdbc:mysql://127.0.0.1:3306/swat";
		String user = "root";
		String password = "";

		// コンストラクタ
		public HotelSightDao(){
					System.out.println("HotelSightDao,ログイン処理実行");
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

		// select文実行メソッド	
		public ArrayList<String> selectExe(int hotelId){
			System.out.println("HotelSightDao-selectExe,SELECT文実行");
			ArrayList<String> conlist = new ArrayList<String>();
			try{
				String a = "select * from m_hotel,t_hotel_sights,m_sights\n"
						+ "where m_hotel.hotel_id = t_hotel_sights.hotel_id\n"
						+ "AND t_hotel_sights.sights_id = m_sights.sights_id\n"
						+ "AND m_hotel.hotel_id = ?";
				PreparedStatement pstmt = con.prepareStatement(a);
				pstmt.setInt(1, hotelId);
				rs = pstmt.executeQuery();
				System.out.println("HotelSightDao-selectExe,SELECT文成功");
				while (rs.next()) {
					String sights = "";
					sights = rs.getString("sights_name");
					conlist.add(sights);
				}
					
			}catch(Exception ex){
				System.out.println("HotelSightDao-selectExe,SELECT文失敗");
				this.closeDB();
				ex.printStackTrace();
			}
			return conlist;
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
