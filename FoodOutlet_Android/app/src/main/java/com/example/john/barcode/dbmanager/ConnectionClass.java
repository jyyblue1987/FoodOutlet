package com.example.john.barcode.dbmanager;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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


	public static List<JSONObject> fetchCategories()
	{
		return runQuery("SELECT [ID], [CategoryName], [CategoryOtherName], [CategoryLevel] FROM [dbo].[ProductCategory]");
	}

	public static List<JSONObject> fetchPacking()
	{
		return runQuery("SELECT [ID], [Packing] FROM [dbo].[Packing]");
	}

	public static List<JSONObject> fetchUnit()
	{
		return runQuery("SELECT [ID], [Unit] FROM [dbo].[Unit]");
	}

	public static List<JSONObject> fetchItems()
	{
		List<JSONObject> list = new ArrayList<JSONObject>();
		for( int i = 0; i < 100; i++ )
		{
			list = runQuery("SELECT [PartNo], [ItemName], [ItemNameOtherLang], [UnitID], [PackingID], [PackingQty], [ProductID], [SubProductID], 'P' AS Unit, [Price6] AS UnitPrice, [ID] FROM dbo.Item WHERE OutcomeCode1='P' ORDER BY PartNo");
			if( list != null && list.size() > 0 )
				break;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<JSONObject> runQuery(String sql)
	{
		List<JSONObject> list = new ArrayList<JSONObject>();

		Connection conn = new ConnectionClass().connect();
		if(conn == null)
			return list;

		Statement stmt = null;
		try{
			//STEP 4: Execute a query
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			list = getRecordData(rs);

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

		return list;
	}




	public static List<JSONObject> getRecordData(ResultSet rs)
	{
		List<JSONObject> list = new ArrayList<JSONObject>();

		if( rs == null )
			return list;

		try{
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();

			while( rs.next() )
			{
				JSONObject record = new JSONObject();
				for(int i = 0; i < columnCount; i++ )
				{
					try{
						String name = rsmd.getColumnName(i + 1);
						String value = rs.getString(i + 1);
						record.put(name, value);
					}catch(Exception e){

					}
				}
				list.add(record);
			}
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return list;
	}

}