package model;

public enum Error{
	   BLANK_SESSION("BLANK_SESSION"), NOT_FOUNDED("NOT_FOUNDED"), COMPLETED("COMPLETED"), GENERIC_ERROR("GENERIC_ERROR"), NOT_APPROVED("NOT_APPROVED"),
	   WRONG_PASSWORD("WRONG_PASSWORD"), ALREADY_EXISTS("ALREADY_EXISTS"), LOGGED("LOGGED");
	
	private String displayName;
	Error(String displayName)
	{
		this.displayName = displayName;
	}
	
	public String displayName() {return displayName;}
}