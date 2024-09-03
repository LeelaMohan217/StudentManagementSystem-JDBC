package com.jsf.db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
	public static void main(String[] args) {
		DB_Connection obj = new DB_Connection();
		System.out.println(obj.getConnection());
	}

	public Connection getConnection() {

		Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return connection;
	}
}
