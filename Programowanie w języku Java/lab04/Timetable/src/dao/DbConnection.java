package dao;

import java.sql.*;

public class DbConnection {
	private static String DB_URL = "jdbc:sqlite:notes.db";
	private Connection connection;

	public DbConnection() throws SQLException {
		connection = DriverManager.getConnection(DB_URL);
		connection.setAutoCommit(true);
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() throws SQLException {
		if (!connection.isClosed())
			connection.close();
	}
}
