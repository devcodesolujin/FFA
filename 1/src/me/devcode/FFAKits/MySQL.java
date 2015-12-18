package me.devcode.FFAKits;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;




public class MySQL {
	
	
	public static String HOST = "";
	public static String USER = "";
	public static String DATABASE = "";
	public static String PASSWORD = "";
	public  static Connection con;
	
	public MySQL(String host, String user, String database, String password) {
		this.HOST = host;
		this.USER = user; 
		this.DATABASE = database;
		this.PASSWORD = password;
		this.connect();
		
	}
	
	

	public static void connect() {
	try{
		con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + USER, DATABASE, PASSWORD);
	
	System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt.");
	}catch(SQLException e) {
		System.out.println("[MySQL] Fehler bei der Verbindung zur MySQL!" + e.getMessage());
		
		isConnected();
	}
		
	}
	
	public static void close() {
		try{
			isConnected();
			if(con != null) {
				con.close();
				System.out.println("[MySQL] Die MySQL Verbindung wurde erfolgereich geclosed!");
			}
		}catch(SQLException e) {
			System.out.println("[MySQL] Fehler bei der Beendung von MySQL!");
		}
	}
	
	public static void update(String qry) {
		try{
			Statement st = con.createStatement();
			st.executeUpdate(qry);
			st.close();
		}catch(SQLException e) {
			System.err.println();
		}
	}
	
	public static ResultSet query(String qry) {
		ResultSet rs = null;
		try{
			Statement st = con.createStatement();
			rs = st.executeQuery(qry);
			
		}catch (SQLException e) {
			System.err.println();
		}
		return rs;
		
	}

	public static boolean isConnected() {
		try{
			return con != null || con.isValid(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

}
