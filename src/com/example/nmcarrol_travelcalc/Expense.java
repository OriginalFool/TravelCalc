package com.example.nmcarrol_travelcalc;

import java.io.Serializable;

//basic expense class.
public class Expense implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String currency;
	private String amount;
	private String description;
	private String date;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}
