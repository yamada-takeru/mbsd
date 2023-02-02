package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HotelconvenienceDao {

	Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	// 接続先DB、ユーザ名、パスワード
	String url = "jdbc:mysql://127.0.0.1:3306/swat";
	String user = "root";
	String password = "";

	// コンストラクタ
	public HotelconvenienceDao(){
					System.out.println("HotelconvenienceDao,ログイン処理実行");
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
		System.out.println("HotelconvenienceDao-selectExe,SELECT文実行");
		ArrayList<String> conlist = new ArrayList<String>();
		try{
			String a = "select * from m_hotel,t_hotel_convenience_store,m_convenience_store \n"
					+ "where m_hotel.hotel_id = t_hotel_convenience_store.hotel_id\n"
					+ "AND t_hotel_convenience_store.con_store_id = m_convenience_store.con_store_id\n"
					+ "AND m_hotel.hotel_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setInt(1, hotelId);
			rs = pstmt.executeQuery();
			System.out.println("HotelconvenienceDao-selectExe,SELECT文成功");
			while (rs.next()) {
				String b = "";
				b = rs.getString("con_store_name");
				conlist.add(b);
			}
				
		}catch(Exception ex){
			System.out.println("HotelconvenienceDao-selectExe,SELECT文失敗");
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
