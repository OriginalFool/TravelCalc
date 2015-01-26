package com.example.nmcarrol_travelcalc;

import java.io.Serializable;

public class Claim implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String dates;
	private String name;
	
	public String getId() {
		return id;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	

	
}
