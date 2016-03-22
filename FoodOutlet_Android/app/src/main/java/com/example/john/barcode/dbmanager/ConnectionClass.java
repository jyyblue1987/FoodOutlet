package com.example.john.barcode.dbmanager;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
	String ip = "184.68.13.210";
	String classs = "net.sourceforge.jtds.jdbc.Driver";
	String db = "TradePro";
	String un = "sa";
	String password = "Famous123";
	public Connection CONN() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		Connection conn = null;
		String ConnURL = null;
		try {
			Class.forName("com.example.john.barcode.dbmanager.DBManager");
			//Class.forName("org.apache.commons.io.FileUtils");
			Class.forName(classs);
			ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
			+ "databaseName=" + db + ";user=" + un + ";password="
			+ password + ";";
			conn = DriverManager.getConnection(ConnURL);
		} catch (SQLException se) {
		Log.e("ERRO", se.getMessage());
		} catch (ClassNotFoundException e) {
		Log.e("ERRO", e.getMessage());
		} catch (Exception e) {
		Log.e("ERRO", e.getMessage());
		}
		return conn;
	}
}