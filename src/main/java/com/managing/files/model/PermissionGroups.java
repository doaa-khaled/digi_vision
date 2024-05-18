package com.managing.files.model;

public enum PermissionGroups {
	ADMIN_SPACE("ADMIN_SPACE"),
	VIEW_SPACE("VIEW_SPACE"),
	ADMIN_FOLDER("VIEW_SPACE"),
	VIEW_FOLDER("VIEW_FOLDER"),
	ADMIN_FILE("ADMIN_FILE"),
	VIEW_FILE("VIEW_FILE");
	
	private final String text;
	private PermissionGroups(final String text) {
		this.text = text;
	}

	@Override
    public String toString() {
        return text;
    }
}
