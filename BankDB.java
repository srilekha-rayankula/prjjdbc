package com.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankDB {
public static Connection getConnection() throws SQLException, ClassNotFoundException 
	
	{
		String driverName="oracle.jdbc.driver.OracleDriver";
		Class.forName(driverName);
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","abc","abc123");
		
		return conn;
		
	}
}
