package lab07;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {

	private static String DB_URL = "jdbc:sqlite:db.db";;

	public Connection connect() {
		Connection conn = null;
		try {
			File dbfile = new File("");
			// String url = "jdbc:sqlite:db.db";
			conn = DriverManager.getConnection(DB_URL);
			return conn;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void addPatient(String name, String surname) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO PATIENTS(NAME, SURNAME) VALUES(?,?)")) {

			ps.setString(1, name);
			ps.setString(2, surname);

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Patient getPatient(int ID) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM PATIENTS WHERE ID_PATIENT = ?")) {

			ps.setInt(1, ID);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("NAME");
				String surname = rs.getString("SURNAME");
				return new Patient(ID, name, surname);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Patient> getPatients(String filter) {
		List<Patient> patients = new ArrayList<Patient>();
		try (Connection conn = this.connect();
				PreparedStatement ps = conn
						.prepareStatement("SELECT * FROM PATIENTS WHERE (NAME || ' ' || SURNAME) LIKE ?")) {

			filter = "%" + filter + "%";
			ps.setString(1, filter);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("ID_PATIENT");
				String name = rs.getString("NAME");
				String surname = rs.getString("SURNAME");
				Patient patient = new Patient(ID, name, surname);
				patients.add(patient);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return patients;
	}

	public void modifyPatient(Patient patient) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn
						.prepareStatement("UPDATE PATIENTS SET NAME = ?, SURNAME = ? WHERE ID_PATIENT = ?")) {

			ps.setString(1, patient.getName());
			ps.setString(2, patient.getSurname());
			;
			ps.setInt(3, patient.getID());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Doctor getDoctor(String name, String surname) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM DOCTORS WHERE NAME = ? AND SURNAME = ?")) {

			ps.setString(1, name);
			ps.setString(2, surname);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt("ID_DOCTOR");
				return new Doctor(ID, name, surname);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Doctor getDoctor(int ID) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM DOCTORS WHERE ID_DOCTOR = ?")) {

			ps.setInt(1, ID);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("NAME");
				String surname = rs.getString("SURNAME");
				return new Doctor(ID, name, surname);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void deletePatient(Patient patient) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM PATIENTS WHERE ID_PATIENT = ?")) {

			ps.setInt(1, patient.getID());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Admission getCurrentAdmission(int idDoctor) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM ADMISSIONS " + "WHERE ID_DOCTOR = ? " // 1
						+ "AND DAY_OF_THE_WEEK = strftime('%w', 'now') " // 2
						+ "AND BEGIN <= time('now') " // 3
						+ "AND END >= time('now')")) { // 4

			ps.setInt(1, idDoctor);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String begin = rs.getString("BEGIN");
				String end = rs.getString("END");
				int room = rs.getInt("ROOM");
				int dayOfTheWeek1 = rs.getInt("DAY_OF_THE_WEEK");
				int doctorID = rs.getInt("ID_DOCTOR");
				return new Admission(doctorID, room, begin, end, dayOfTheWeek1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Admission> getAdmissionsByDate(String date, Doctor doctor) {
		List<Admission> admissions = new ArrayList<Admission>();
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM ADMISSIONS " + "WHERE ID_DOCTOR = ? "
						+ "AND DAY_OF_THE_WEEK = strftime('%w', ?)")) {

			ps.setInt(1, doctor.getID());
			ps.setString(2, date);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String begin = rs.getString("BEGIN");
				String end = rs.getString("END");
				int room = rs.getInt("ROOM");
				int dayOfTheWeek = rs.getInt("DAY_OF_THE_WEEK");
				int doctorID = rs.getInt("ID_DOCTOR");
				admissions.add(new Admission(doctorID, room, begin, end, dayOfTheWeek));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return admissions;
	}

	public List<Admission> getAdmissions(int doctorID) {
		List<Admission> admissions = new ArrayList<Admission>();
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM ADMISSIONS " + "WHERE ID_DOCTOR = ?")) {

			ps.setInt(1, doctorID);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String begin = rs.getString("BEGIN");
				String end = rs.getString("END");
				int room = rs.getInt("ROOM");
				int dayOfTheWeek = rs.getInt("DAY_OF_THE_WEEK");
				admissions.add(new Admission(doctorID, room, begin, end, dayOfTheWeek));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return admissions;
	}

	public void deleteAdmission(Admission admission) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM ADMISSIONS " + "WHERE ID_DOCTOR = ? "
						+ "AND ROOM = ? " + "AND DAY_OF_THE_WEEK = ? " + "AND BEGIN = ? " + "AND END = ?")) {

			ps.setInt(1, admission.getDoctorID());
			ps.setInt(2, admission.getRoom());
			ps.setInt(3, admission.getDayOfTheWeek());
			ps.setString(4, admission.getBegin());
			ps.setString(5, admission.getEnd());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void createAdmission(Admission admission) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO ADMISSIONS(ID_DOCTOR, ROOM, DAY_OF_THE_WEEK, BEGIN, END) VALUES(?, ?, ?, ?, ?)")) {

			ps.setInt(1, admission.getDoctorID());
			ps.setInt(2, admission.getRoom());
			ps.setInt(3, admission.getDayOfTheWeek());
			ps.setString(4, admission.getBegin());
			ps.setString(5, admission.getEnd());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Visit> getVisits(Admission admission) {
		List<Visit> visits = new ArrayList<Visit>();

		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM VISITS " + "WHERE TIME >= ? " // 1
						+ "AND TIME <= ? " + "AND ID_DOCTOR = ? " + "AND CONFIRMED = TRUE " + "ORDER BY TIME")) {

			ps.setString(1, admission.getBegin());
			ps.setString(2, admission.getEnd());
			ps.setInt(3, admission.getDoctorID());

			Doctor doctor = getDoctor(admission.getDoctorID());

			ResultSet rs = ps.executeQuery();
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return visits;
	}

	public List<Visit> getVisits(Patient patient) {
		List<Visit> visits = new ArrayList<Visit>();

		try (Connection conn = this.connect();
				PreparedStatement ps = conn
						.prepareStatement("SELECT * FROM VISITS " + "WHERE ID_PATIENT = ? " + "ORDER BY DATE ASC")) {

			ps.setInt(1, patient.getID());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int visitID = rs.getInt("ID_visit");
				int patientID = rs.getInt("ID_patient");
				boolean confirmed = rs.getBoolean("CONFIRMED");
				String recommendation = rs.getString("RECOMMENDATION");
				String date = rs.getString("DATE");
				String time = rs.getString("TIME");
				int doctorID = rs.getInt("ID_DOCTOR");
				Doctor doctor = getDoctor(doctorID);
				Visit visit = new Visit(visitID, patientID, doctor, confirmed, recommendation, date, time);
				visits.add(visit);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return visits;
	}

	public void modifyVisit(Visit currentVisit) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn
						.prepareStatement("UPDATE VISITS SET CONFIRMED = ?, RECOMMENDATION = ? WHERE ID_VISIT = ?")) {

			ps.setBoolean(1, currentVisit.getConfirmed());
			ps.setString(2, currentVisit.getRecommendation());
			ps.setInt(3, currentVisit.getVisitID());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteVisit(Visit visit) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM VISITS WHERE ID_VISIT = ?")) {

			ps.setInt(1, visit.getVisitID());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Visit order(Admission admission, Patient patient, Doctor doctor, String date) {
		List<Visit> visits = getVisits(admission);

		if (visits.size() == 0) {
			// dodaj pierwszy
			return createVisit(patient, doctor, date, Time.valueOf(admission.getBegin()).toString());
		} else {
			Visit last = visits.get(visits.size() - 1);
			LocalTime localtime = Time.valueOf(last.getTime()).toLocalTime();
			LocalTime localtime2 = localtime.plusMinutes(40);
			if (localtime2.toString().compareTo(admission.getEnd()) <= 0) {
				// rezerwacja po ostatnim
				return createVisit(patient, doctor, date, Time.valueOf(localtime.plusMinutes(20)).toString());
			}
		}

		return null;
	}

	public Visit createVisit(Patient patient, Doctor doctor, String date, String time) {
		try (Connection conn = this.connect();
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO VISITS(ID_PATIENT, ID_DOCTOR, DATE, TIME) VALUES(?, ?, ?, ?)")) {

			ps.setInt(1, patient.getID());
			ps.setInt(2, doctor.getID());
			ps.setString(3, date);
			ps.setString(4, time);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		try (Connection conn = this.connect();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM VISITS " + "WHERE ID_PATIENT = ? "
						+ "AND ID_DOCTOR = ? " + "AND DATE = ? " + "AND TIME = ?")) {

			ps.setInt(1, patient.getID());
			ps.setInt(2, doctor.getID());
			ps.setString(3, date);
			ps.setString(4, time);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int visitID = rs.getInt("ID_VISIT");
				return new Visit(visitID, patient.getID(), doctor, false, "", date, time);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
