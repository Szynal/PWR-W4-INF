package core;

import java.text.SimpleDateFormat;

public class Clinic {

	private int doctorID;
	private int room;
	private String begin;
	private String end;
	private int dayOfTheWeek;

	public Clinic(int doctorID, int room, String begin, String end, int dayOfTheWeek) {
		this.doctorID = doctorID;
		this.room = room;
		this.begin = begin;
		this.end = end;
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public int getRoom() {
		return room;
	}

	public String getBegin() {
		return begin;
	}

	public String getEnd() {
		return end;
	}

	public int getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public int getDoctorID() {
		return doctorID;
	}

	@Override
	public String toString() {
		String dayName = "";
		switch (dayOfTheWeek) {
		case 0:
			dayName = "Monday";
			break;
		case 1:
			dayName = "Tuesday";
			break;
		case 2:
			dayName = "Wednesday";
			break;
		case 3:
			dayName = "Thursday";
			break;
		case 4:
			dayName = "Friday";
			break;
		case 5:
			dayName = "Saturday";
			break;
		case 6:
			dayName = "Sunday";
			break;
		}
		return dayName + " | " + begin + " - " + end + " | Room " + room;
	}
}