package core;

import java.util.Arrays;

public enum NoteLabel {
	NOT_IMPORTANT("Not important"), IMPORTANT("Important"), CONTACT("Contact"), LONG_TERM("Long term"),;

	private String value;

	NoteLabel(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static NoteLabel fromText(String text) {
		return Arrays.stream(values()).filter(bl -> bl.value.equalsIgnoreCase(text)).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
