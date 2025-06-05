package com.sigma.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {

	private static Connection connection;

	public static Connection connectToDB() {
		String url = "jdbc:mysql://172.22.20.9:3306/live_25_02_2025";
		String user = "admin-user";
		String password = "Vc1kzSUyhFxQHgsu";

		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Database connected successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
			System.out.println("Database connected closed successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ResultSet executeQuery(String query) {
	    if (connection == null) {
	        System.out.println("❌ Connection is null. Aborting query.");
	        return null;
	    }
	    ResultSet rs = null;
	    try {
	        Statement stmt = connection.createStatement();
	        rs = stmt.executeQuery(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return rs;
	}

}
