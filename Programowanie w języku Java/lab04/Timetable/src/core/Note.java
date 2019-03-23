package core;

public class Note {

	private int ID;
	private String title;
	private NoteLabel label = NoteLabel.NOT_IMPORTANT;
	private String date;
	private int textAreaSize_X;
	private int textAreaSize_Y;
	private String NoteContent;

	public Note(Integer id, String title, NoteLabel label, String date, int izeOfNoteTextArea_X,
			int sizeOfNoteTextArea_Y, String NoteContent) {

		ID = id;
		this.title = title;
		this.label = label;
		this.date = date;
		this.setTextAreaSize_X(izeOfNoteTextArea_X);
		this.setTextAreaSize_Y(sizeOfNoteTextArea_Y);
		this.NoteContent = NoteContent;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public NoteLabel getLabel() {
		return label;
	}

	public void setLabel(NoteLabel label) {
		this.label = label;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNoteContent() {
		return NoteContent;
	}

	public void setNoteContent(String noteContent) {
		NoteContent = noteContent;
	}

	public int getTextAreaSize_X() {
		return textAreaSize_X;
	}

	public void setTextAreaSize_X(int textAreaSize_X) {
		this.textAreaSize_X = textAreaSize_X;
	}

	public int getTextAreaSize_Y() {
		return textAreaSize_Y;
	}

	public void setTextAreaSize_Y(int textAreaSize_Y) {
		this.textAreaSize_Y = textAreaSize_Y;
	}

}
