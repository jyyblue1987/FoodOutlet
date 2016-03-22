package com.example.john.barcode.dbmanager;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionClass {
	String ip = "184.68.13.210";
	String classs = "net.sourceforge.jtds.jdbc.Driver";
	String db = "TradePro";
	String un = "sa";
	String password = "Famous123";
	public Connection connect() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		Connection conn = null;
		String ConnURL = null;
		try {
			//STEP 2: Register JDBC driver
			Class.forName(classs);

			//STEP 3: Open a connection
			ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"+ "databaseName=" + db + ";user=" + un + ";password="+ password + ";";

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

	public static boolean login(String username, String password)
	{
		Connection conn = new ConnectionClass().connect();
		if(conn == null)
			return false;

		boolean loginOk = false;
		Statement stmt = null;
		try{
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT [LoginName], [Password], [LoginRights], E.[Inactive], [CustomerID], [CustomerNo2], [CustomerGroupID], [CompanyName], [CompanyOtherName], C.[CompanyID], C.[StockID] FROM [dbo].[Employee] AS E JOIN [dbo].[Customer] AS C ON E.CustomerID=C.ID WHERE LoginName='" + username + "'";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String loginame = rs.getString("LoginName");
				String pass = rs.getString("Password");

				if( password.equals(pass) )
				{
					loginOk = true;
					break;
				}
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");

		return loginOk;
	}


}