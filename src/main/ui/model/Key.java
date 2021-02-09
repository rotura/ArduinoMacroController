package main.ui.model;

public class Key {

	private String displayName;
	private String key;
	
	public Key(String displayName, String key) {
		super();
		this.displayName = displayName;
		this.key = key;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public String getKey() {
		return key;
	}
}
