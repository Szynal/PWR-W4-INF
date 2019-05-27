package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import core.Clinic;
import core.Doctor;
import core.Patient;
import core.Visit;

public class VisitDAO {
	private DbConnection dbConnection;

	public VisitDAO(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public Visit createVisit(Patient patient, Doctor doctor, String date, String time) throws SQLException {
		String statement = "INSERT INTO VISITS(ID_PATIENT, ID_DOCTOR, DATE, TIME) VALUES(?, ?, ?, ?)";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);

		preparedStatement.setInt(1, patient.getID());
		preparedStatement.setInt(2, doctor.getID());
		preparedStatement.setString(3, date);
		preparedStatement.setString(4, time);
		preparedStatement.executeUpdate();

		statement = "SELECT * FROM VISITS " + "WHERE PatientID = ? " + "AND ID_DOCTOR = ? " + "AND DATE = ? "
				+ "AND TIME = ?";
		preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setInt(1, patient.getID());
		preparedStatement.setInt(2, doctor.getID());
		preparedStatement.setString(3, date);
		preparedStatement.setString(4, time);

		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			int visitID = rs.getInt("ID");
			return new Visit(visitID, patient.getID(), doctor, false, "", date, time);
		}

		return null;
	}

	public List<Visit> getVisits(Clinic clinic) throws SQLException {
		List<Visit> visits = new ArrayList<Visit>();

		String statement = "SELECT * FROM VISITS " + "WHERE TIME >= ? " // 1
				+ "AND TIME <= ? " + "AND ID_DOCTOR = ? " + "AND CONFIRMED = TRUE " + "ORDER BY TIME";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);

		preparedStatement.setString(1, clinic.getBegin());
		preparedStatement.setString(2, clinic.getEnd());
		preparedStatement.setInt(3, clinic.getDoctorID());

		DoctorDAO doctorDAO = new DoctorDAO(dbConnection);
		Doctor doctor = doctorDAO.getDoctor(clinic.getDoctorID());

		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int visitID = rs.getInt("ID_VISIT");
			int patientID = rs.getInt("ID_PATIENT");
			boolean confirmed = rs.getBoolean("CONFIRMED");
			String recommendation = rs.getString("RECOMMENDATION");
			String date = rs.getString("DATE");
			String time = rs.getString("TIME");
			Visit visit = new Visit(visitID, patientID, doctor, confirmed, recommendation, date, time);
			visits.add(visit);
		}

		return visits;
	}

	public List<Visit> getVisits(Patient patient) throws SQLException {
		List<Visit> visits = new ArrayList<Visit>();

		String statement = "SELECT * FROM VISITS " + "WHERE ID_PATIENT = ? " + "ORDER BY DATE ASC";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);

		preparedStatement.setInt(1, patient.getID());

		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int visitID = rs.getInt("ID_VISIT");
			int patientID = rs.getInt("ID_PATIENT");
			boolean confirmed = rs.getBoolean("CONFIRMED");
			String recommendation = rs.getString("RECOMMENDATION");
			String date = rs.getString("DATE");
			String time = rs.getString("TIME");
			int doctorID = rs.getInt("ID_DOCTOR");

			DoctorDAO doctorDAO = new DoctorDAO(dbConnection);
			Doctor doctor = doctorDAO.getDoctor(doctorID);
			Visit visit = new Visit(visitID, patientID, doctor, confirmed, recommendation, date, time);
			visits.add(visit);
		}

		return visits;
	}

	public void modifyVisit(Visit currentVisit) throws SQLException {

		String statement = "UPDATE VISITS SET CONFIRMED = ?, RECOMMENDATION = ? WHERE ID_VISIT = ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setBoolean(1, currentVisit.getConfirmed());
		preparedStatement.setString(2, currentVisit.getRecommendation());
		preparedStatement.setInt(3, currentVisit.getVisitID());
		preparedStatement.executeUpdate();
	}

	public void deleteVisit(Visit visit) throws SQLException {
		String statement = "DELETE FROM VISITS WHERE ID_VISIT = ?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
		preparedStatement.setInt(1, visit.getVisitID());
		preparedStatement.executeUpdate();
	}

	public Visit order(Clinic clinic, Patient patient, Doctor doctor, String date) throws SQLException {
		List<Visit> visits = getVisits(clinic);

		if (visits.size() == 0) {
			return createVisit(patient, doctor, date, Time.valueOf(clinic.getBegin()).toString());
		} else {
			Visit last = visits.get(visits.size() - 1);
			LocalTime localtime = Time.valueOf(last.getTime()).toLocalTime();
			LocalTime localtime2 = localtime.plusMinutes(40);
			if (localtime2.toString().compareTo(clinic.getEnd()) <= 0) {
				return createVisit(patient, doctor, date, Time.valueOf(localtime.plusMinutes(30)).toString());
			}
		}
		return null;
	}

}
