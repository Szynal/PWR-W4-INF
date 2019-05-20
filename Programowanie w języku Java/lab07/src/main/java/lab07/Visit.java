package lab07;

import java.sql.Date;
import java.sql.Time;

public class Visit {

	private int visitID;
	private int patientID;
	private Doctor doctor;
	private String date;
	private String time;
	private boolean confirmed;
	private String recommendation;
	
	Visit(int visitID, int patientID, Doctor doctor, boolean confirmed, String recommendation, String date, String time){
		this.visitID = visitID;
		this.patientID = patientID;
		this.doctor = doctor;
		this.confirmed = confirmed;
		this.recommendation = recommendation;
		this.date = date;
		this.time = time;
	}
	
	public int getVisitID() {
		return visitID;
	}
	
	public int getPatientID() {
		return patientID;
	}
	
	public boolean getConfirmed() {
		return confirmed;
	}
	
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	
	public String getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return "Data: " + date + " | Godzina: " + time.toString() + " | Lekarz: " + doctor.toString();
	}
	
	public String toStringShorter() {
		return " Godz: " + time.toString();
	}
}
