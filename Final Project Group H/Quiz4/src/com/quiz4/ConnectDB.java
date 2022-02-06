package com.quiz4;
import java.sql.*;

//Author by Mayuri Jujgar(Worked on All database connection code and table creations and Joining tables)


public class ConnectDB {
	
	public static Connection getConnection()
	{
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "qwertyuiop");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
return connection;
	}

	

	

	
	
	
}
