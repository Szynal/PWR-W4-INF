package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.Doctor;

public class DoctorDAO {

	private DbConnection dbConnection;

	public DoctorDAO(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public Doctor getDoctor(String name, String surname) throws SQLException {
		String statement = "SELECT * FROM DOCTORS WHERE NAME = ? AND SURNAME = ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, surname);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			int ID = rs.getInt("ID");
			return new Doctor(ID, name, surname);
		}
		return null;
	}

	public Doctor getDoctor(int ID) throws SQLException {
		String statement = "SELECT * FROM DOCTORS WHERE ID_DOCTOR = ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			String name = rs.getString("NAME");
			String surname = rs.getString("SURNAME");
			return new Doctor(ID, name, surname);
		}
		return null;
	}

	public void addDoctor(String name, String surname) throws SQLException {
		String statement = "INSERT INTO DOCTORS(NAME, SURNAME) VALUES(?,?)";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, surname);
		preparedStatement.executeUpdate();
		System.out.println("INSERT INTO DOCTORS(NAME, SURNAME) VALUES(" + name + "," + surname + ")");
	}

}
