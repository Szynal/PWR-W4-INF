package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.Patient;

public class PatientDAO {
	private DbConnection dbConnection;

	public PatientDAO(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public List<Patient> getPatients() throws SQLException {
		List<Patient> patients = new ArrayList<Patient>();
		String statement = "SELECT * FROM Patient";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next())
			patients.add(
					new Patient(resultSet.getInt("ID"), resultSet.getString("Name"), resultSet.getString("Surname")));

		preparedStatement.close();
		System.out.println("SELECT * FROM Patient");
		return patients;
	}

	public List<Patient> getPatients(String filter) throws SQLException {
		List<Patient> patients = new ArrayList<Patient>();
		String statement = "SELECT * FROM Patient WHERE (Name || ' ' || Surname) LIKE ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		filter = "%" + filter + "%";
		preparedStatement.setString(1, filter);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int ID = resultSet.getInt("ID");
			String name = resultSet.getString("Name");
			String surname = resultSet.getString("Surname");
			Patient patient = new Patient(ID, name, surname);
			patients.add(patient);
		}
		System.out.println("SELECT * FROM Patient WHERE (Name || ' ' || Surname) LIKE ?");
		return patients;
	}

	public Patient getPatientsById(int id) throws SQLException {
		String statement = "SELECT * FROM Patient WHERE ID=?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		Patient patient = new Patient(resultSet.getInt("ID"), resultSet.getString("Name"),
				resultSet.getString("Surname"));
		preparedStatement.close();
		System.out.println("SELECT * FROM Patient WHERE ID= " + id);
		return patient;
	}

	public void addPatient(String name, String surname) throws SQLException {
		String statement = "INSERT INTO Patient(Name, Surname) VALUES(?,?)";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, surname);
		preparedStatement.executeUpdate();
		System.out.println("INSERT INTO Patient(Name, Surname) VALUES(" + name + "," + surname + ")");
	}

	public void modifyPatient(Patient patient) throws SQLException {
		String statement = "UPDATE Patient SET Name = ?, Surname = ? WHERE ID = ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, patient.getName());
		preparedStatement.setString(2, patient.getSurname());
		preparedStatement.setInt(3, patient.getID());
		preparedStatement.executeUpdate();
		System.out.println("UPDATE Patient SET Name = " + patient.getName() + " Surname = " + patient.getSurname());
	}

	public void deletePatient(Patient patient) throws SQLException {
		String statement = "DELETE FROM Patient WHERE ID = ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setInt(1, patient.getID());
		preparedStatement.executeUpdate();
		System.out.println(statement + patient.getID());
	}

}
