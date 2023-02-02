package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.UserDto;

public class UserDao {

	Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	// 接続先DB、ユーザ名、パスワード
	String url = "jdbc:mysql://127.0.0.1:3306/swat";
	String user = "root";
	String password = "";

	// コンストラクタ
	public UserDao() {
		System.out.println("UserDao,ログイン処理実行");
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
	public boolean selectExe(String userId,String password) {
		System.out.println("UserDao-selectExe,SELECT文実行");
		boolean kekka = false;
		try {
			String a = "select * from m_user where user_id = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			System.out.println("UserDao-selectExe,SELECT文成功");
			
			if(rs.next()) {
				kekka = true;
			}
			
		} catch (Exception ex) {
			System.out.println("UserDao-selectExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return kekka;
	}
	
	public UserDto UserExe(String userId) {
		System.out.println("UserDao-UserExe,SELECT文実行");
		UserDto user = new UserDto();
		try {
			String a = "select * from m_user where user_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			System.out.println("UserDao-UserExe,SELECT文成功");
			rs.next();
			user.setUserId(rs.getString("user_id"));
			user.setName(rs.getString("name"));
			user.setFurigana(rs.getString("furigana"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setPassword(rs.getString("password"));
			user.setRank(rs.getInt("rank"));
			user.setMile(rs.getInt("mile"));
			
			
		} catch (Exception ex) {
			System.out.println("UserDao-UserExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return user;
	}

	public boolean kaburi(String userId) {
		System.out.println("UserDao-kaburi,SELECT文実行");
		boolean kaburi = true;
		try {
			String a = "select * from m_user where user_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			System.out.println("UserDao-kaburi,SELECT文成功");
			kaburi = rs.next();
			
			
		} catch (Exception ex) {
			System.out.println("UserDao-UserExe,SELECT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return kaburi;
	}
	
	
	// insert、update文実行メソッド
	public int InsertExe(String userId,String name,String furigana,String phonenumber,String password) {
		int cnt = 0;
		System.out.println("UserDao-InsertExe,INSERT文実行");
		try {
			String a = "INSERT INTO `swat`.`m_user` (`user_id`, `name`, `furigana`, `phone_number`, `password`, `rank`, `mile`) "
					+ "VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setString(1, userId);
			pstmt.setString(2, name);
			pstmt.setString(3, furigana);
			pstmt.setString(4, phonenumber);
			pstmt.setString(5, password);
			pstmt.setInt(6, 1);
			pstmt.setInt(7, 1);
			cnt = pstmt.executeUpdate();
			System.out.println("UserDao-InsertExe,INSERT文成功");
		} catch (Exception ex) {
			System.out.println("UserDao-InsertExe,INSERT文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return cnt;
	}
	
	public int UpdateExe(String userId,String name,String furigana,String phonenumber,String password,String olduserId,String oldpassword) {
		int cnt = 0;
		System.out.println("UserDao-UpdateExe,UPDATE文実行");
		try {
			String a = "UPDATE m_user SET user_id = ? , name = ?, furigana = ?, phone_number = ?, password = ?  WHERE user_id = ? AND password = ?";
;
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setString(1, userId);
			pstmt.setString(2, name);
			pstmt.setString(3, furigana);
			pstmt.setString(4, phonenumber);
			pstmt.setString(5, password);
			pstmt.setString(6, olduserId);
			pstmt.setString(7, oldpassword);
			cnt = pstmt.executeUpdate();
			System.out.println("UserDao-UpdateExe,UPDATE文成功");
		} catch (Exception ex) {
			System.out.println("UserDao-UpdateExe,UPDATE文失敗");
			this.closeDB();
			ex.printStackTrace();
		}
		return cnt;
	}
	
	public int ExitExe(String userId) {
		int cnt = 0;
		System.out.println("UserDao-UpdateExe,DELETE文実行");
		try {
			String a = "DELETE from m_user where user_id = ?";
			PreparedStatement pstmt = con.prepareStatement(a);
			pstmt.setString(1, userId);
			cnt = pstmt.executeUpdate();
			System.out.println("UserDao-UserExe,DELETE文成功");
		} catch (Exception ex) {
			System.out.println("UserDao-UserExe,DELETE文失敗");
			System.out.println(ex);
			this.closeDB();
		}
		return cnt;
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
