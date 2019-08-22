package com.nudge.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;




public class Config {

	//		public static String SERVER_NAME="localhost";	


	//public static int JAVA_PORT=8443;							//local-->9099//server-->8443
	/*public static int JAVA_PORT=8080;

	public static String DB_USERNAME="root";

//		public static String DB_PASSWORD = "Bpclnewinitiatives";				//local--> //server-->password
	public static String DB_PASSWORD = "root";0


	public static String DB_NAME="crm";


	public static String OPENFIRE_DB_NAME="ford";

	public static String APPLICATION_ID="O03";


	public static final String ALPHA_NUMERIC_PASSWORD = "abcdefghijklmnopqrstuvwxyz0123456789";


	public static String MAIL_FROM="fordenquiries@fordind.com";

	public static String MAIL_HOST="localhost";*/

	public static String MAIL_FROM="advantala@gmail.com";

	public static TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");

	private static Connection connection = null;
	private static Config instance;
	private int i = 0;

	private Config()
	{
		PropertiesReader pr = new PropertiesReader();
		Properties prop = new Properties();
		prop = pr.getPropertyFile();

		String DB_NAME = prop.getProperty("DB_NAME");
		String DB_USERNAME = prop.getProperty("DB_USERNAME");
		String DB_PASSWORD = prop.getProperty("DB_PASSWORD");
		String HOST = prop.getProperty("HOST");
		String DB_PORT = prop.getProperty("DB_PORT");

		try
		{

			i++;
			Class.forName("com.mysql.jdbc.Driver");
		    connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB_NAME,DB_USERNAME,DB_PASSWORD); // SERVER
		    //  connection=DriverManager.getConnection("jdbc:mysql://36.255.3.15:3306"+"/"+DB_NAME,DB_USERNAME,"shieldadvantal"); // LOCAL TO SERVER
			//System.out.println("connection open");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			//System.out.println(ex.getMessage());
		}
	}


	public static boolean checkCloseConnection()
	{
		try {
			connection.isClosed();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}

	public static Config getInstance()
	{
		if(instance == null || connection == null || checkCloseConnection() ) {
			instance = new Config();
		}
		return instance;
	}
	public Connection getConnection()
	{
		return connection;
	}



	//	public static Connection DB_connect()
	//	{
	//		/*PropertiesReader pr = new PropertiesReader();
	//		Properties prop = new Properties();
	//		prop = pr.getPropertyFile();
	//		
	//		String DB_NAME = prop.getProperty("DB_NAME");
	//		String DB_USERNAME = prop.getProperty("DB_USERNAME");
	//		String DB_PASSWORD = prop.getProperty("DB_PASSWORD");
	//		String HOST = prop.getProperty("HOST");
	//		String DB_PORT = prop.getProperty("DB_PORT");*/
	//		
	//		if(connection == null)
	//		{
	//			try
	//			{
	//				Class.forName("com.mysql.jdbc.Driver");
	////				connection=DriverManager.getConnection("jdbc:mysql://"+HOST+":"+DB_PORT+"/"+DB_NAME,DB_USERNAME,DB_PASSWORD);
	//				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB_NAME,DB_USERNAME,DB_PASSWORD);
	//
	//			}
	//			catch(Exception ex)
	//			{
	//				ex.printStackTrace();
	//				//System.out.println(ex.getMessage());
	//			}
	//			return connection;
	//		}
	//		else
	//			return connection;
	//		
	//	}
	//
	public static void DB_colse()
	{
		try {
			connection.close();	
			//System.out.println("connection closed");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	
	public static void dbClose(Connection con)
	{
		try {
			if(!con.isClosed()){
				con.close();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}



	//	public static void Openfire_DB_colse()
	//	{
	//		try {
	//			connection.close();			
	//		}
	//		catch (Exception e) 
	//		{
	//			e.printStackTrace();
	//			// TODO: handle exception
	//		}
	//
	//	}



	public static String getDate(long milliSeconds, String dateFormat)
	{

		//milliSeconds = milliSeconds+19800000;
		//milliSeconds=milliSeconds+86400000;
		// Create a DateFormatter object for displaying date in specified format.
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in milliseconds to date. 
		Calendar calendar = Calendar.getInstance();

		formatter.setTimeZone(Config.istTimeZone);

		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}

	public static  String getMysqlRealScapeStringDec(String str) {
		String data = "";
		if (str != null && str.length() > 0) {

			str = str.replace("'", "\\'");
			//                str = str.replace("\\", "\\\\");
			//                str = str.replace("\0", "\\0");
			//                str = str.replace("\n", "\\n");
			//                str = str.replace("\r", "\\r");
			//                str = str.replace("\b", "\\b");
			//                str = str.replace("\t", "\\t");
			//                str = str.replace("\"", "\\\"");
			//                str = str.replace("\\x1a", "\\Z");


			data = str;
		}
		else
		{
			return data;
		}
		return data;
	}

	

}
