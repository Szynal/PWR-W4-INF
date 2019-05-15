package core;

public class Patient {

	private int ID;
	private String name;
	private String surname;

	public Patient(int ID, String name, String surname) {
		this.ID = ID;
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getID() {
		return ID;
	}

	@Override
	public String toString() {
		return ID + " | " + name + " " + surname;
	}

}
