package dao;

import java.util.List;

import core.Clinic;
import core.Doctor;
import core.Patient;
import core.Visit;

public class ClinicDAO {

	private DbConnection dbConnection;

	public ClinicDAO(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public Clinic getCurrentAdmission(int idDoctor) {

		return null;
	}

	public List<Clinic> getAdmissionsByDate(String date, Doctor doctor) {

		return null;
	}

	public List<Clinic> getAdmissions(int doctorID) {

		return null;
	}

	public void deleteAdmission(Clinic admission) {

	}

	public void createAdmission(Clinic admission) {
	}
}
