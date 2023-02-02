package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.HotelDto;

public class HotelDao {
	Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	//接続先DB、ユーザ名、パスワード
	String url ="jdbc:mysql://127.0.0.1:3306/swat";
	String user = "root";
	String password = "";

	//コンストラクタ
	public HotelDao(){
		System.out.println("HotelDao,ログイン処理実行");
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
	
	
	public HotelDto selectExe(int hotelId){
		System.out.println("HotelDao-selectExe,SELECT文実行");
		HotelDto hotel = new HotelDto();
		try{
			String a = "select * from m_hotel where hotel_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setInt(1, hotelId);
			rs = pstmt.executeQuery();
			
			System.out.println("HotelDao-selectExe,SELECT文成功");
			
			rs.next();
				
				hotel.setHotelId(rs.getInt("hotel_id"));
				hotel.setHotelName(rs.getString("hotel_name"));
				hotel.setHotelAddress(rs.getString("hotel_address"));
				hotel.setHotelDescription(rs.getString("hotel_description"));
				hotel.setHotelUrl(rs.getString("hotel_url"));
				hotel.setHotelPath(rs.getString("hotel_path"));
			
		}catch(Exception ex){
			System.out.println("HotelDao-selectExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return hotel;
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
