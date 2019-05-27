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
		String statement = "SELECT * FROM PATIENTS";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next())
			patients.add(
					new Patient(resultSet.getInt("ID_PATIENT"), resultSet.getString("NAME"), resultSet.getString("SURNAME")));

		preparedStatement.close();
		System.out.println("SELECT * FROM PATIENTS");
		return patients;
	}

	public List<Patient> getPatients(String filter) throws SQLException {
		List<Patient> patients = new ArrayList<Patient>();
		String statement = "SELECT * FROM PATIENTS WHERE (NAME || ' ' || Surname) LIKE ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		filter = "%" + filter + "%";
		preparedStatement.setString(1, filter);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int ID = resultSet.getInt("ID_PATIENT");
			String name = resultSet.getString("NAME");
			String surname = resultSet.getString("SURNAME");
			Patient patient = new Patient(ID, name, surname);
			patients.add(patient);
		}
		System.out.println("SELECT * FROM PATIENTS WHERE (NAME || ' ' || SURNAME) LIKE ?");
		return patients;
	}

	public Patient getPatientsById(int id) throws SQLException {
		String statement = "SELECT * FROM PATIENTS WHERE ID_PATIENT=?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		Patient patient = new Patient(resultSet.getInt("ID_PATIENT"), resultSet.getString("NAME"),
				resultSet.getString("SURNAME"));
		preparedStatement.close();
		System.out.println("SELECT * FROM PATIENTS WHERE ID_PATIENT= " + id);
		return patient;
	}

	public void addPatient(String name, String surname) throws SQLException {
		String statement = "INSERT INTO PATIENTS(NAME, SURNAME) VALUES(?,?)";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, surname);
		preparedStatement.executeUpdate();
		System.out.println("INSERT INTO PATIENTS(NAME, Surname) VALUES(" + name + "," + surname + ")");
	}

	public void modifyPatient(Patient patient) throws SQLException {
		String statement = "UPDATE PATIENTS SET NAME = ?, SURNAME = ? WHERE ID_PATIENT = ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, patient.getName());
		preparedStatement.setString(2, patient.getSurname());
		preparedStatement.setInt(3, patient.getID());
		preparedStatement.executeUpdate();
		System.out.println("UPDATE PATIENTS SET NAME = " + patient.getName() + " SURNAME = " + patient.getSurname());
	}

	public void deletePatient(Patient patient) throws SQLException {
		String statement = "DELETE FROM PATIENTS WHERE ID_PATIENT = ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setInt(1, patient.getID());
		preparedStatement.executeUpdate();
		System.out.println(statement + patient.getID());
	}

}
