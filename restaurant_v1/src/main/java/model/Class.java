package model;

public enum Class{
	   ANALYTIC("ANALYTIC"), INGREDIENT("INGREDIENT"), LOG("LOG"), NOTICE("NOTICE"), ORDER("ORDER"),
	   PRODUCT("PRODUCT"), PRODUCTORDER("PRODUCTORDER"), RESTAURANT("RESTAURANT"), REVIEWPRODUCT("REVIEWPRODUCT"),
	   REVIEWRESTAURANT("REVIEWRESTAURANT"), TYPE("TYPE"), USER("USER");
	
	private String displayName;
	Class(String displayName)
	{
		this.displayName = displayName;
	}
	
	public String displayName() {return displayName;}
}