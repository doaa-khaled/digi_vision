package com.managing.files.model;

public enum Permissions {
	EDIT_SPACE("EDIT_SPACE"),
	VIEW_SPACE("VIEW_SPACE"),
	EDIT_FOLDER("EDIT_FOLDER"),
	VIEW_FOLDER("VIEW_FOLDER"),
	EDIT_FILE("EDIT_FILE"),
	VIEW_FILE("VIEW_FILE");
	
	private final String text;
	private Permissions(final String text) {
		this.text = text;
	}

	@Override
    public String toString() {
        return text;
    }
}
