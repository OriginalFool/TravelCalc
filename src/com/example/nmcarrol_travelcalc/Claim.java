package com.example.nmcarrol_travelcalc;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import android.text.format.DateFormat;

public class Claim implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String startdate;
	private String enddate;
	private String description;
	private ArrayList<Expense> exp;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Expense> getExp() {
		return exp;
	}
	public void setExp(ArrayList<Expense> exp) {
		this.exp = exp;
	}
	public void addExpense(Expense expense){
		this.exp.add(expense);
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	

	
}
