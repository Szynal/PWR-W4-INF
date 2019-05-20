package lab07;

import java.text.SimpleDateFormat;

public class Admission {

	private int doctorID;
	private int room;
	private String begin;
	private String end;
	private int dayOfTheWeek;
	
	Admission(int doctorID, int room, String begin, String end, int dayOfTheWeek){
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
		switch(dayOfTheWeek) {
		case 0:
			dayName = "Niedziela";
			break;
		case 1:
			dayName = "Poniedzia³ek";
			break;
		case 2:
			dayName = "Wtorek";
			break;
		case 3:
			dayName = "Œroda";
			break;
		case 4:
			dayName = "Czwartek";
			break;
		case 5:
			dayName = "Pi¹tek";
			break;
		case 6:
			dayName = "Sobota";
			break;
		}
		return dayName + " | " + begin + " - " + end + " | pokój " + room;
	}
}