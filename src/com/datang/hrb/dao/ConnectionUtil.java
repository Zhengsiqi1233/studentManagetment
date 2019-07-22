package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection conn= null;
	public static Connection getConnection() {
		if(conn==null) {
			System.out.println("new connection");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanager1?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC","root","ZHENGsiqi1998");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("not new connection");
		}
		return conn;
	}
}


