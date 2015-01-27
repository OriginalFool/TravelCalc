package com.example.nmcarrol_travelcalc;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import android.text.format.DateFormat;

@SuppressWarnings("unused")
public class Claim implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String startdate;
	private String enddate;
	private String description;
	private int status;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String convertStatus(){
		if(status==1){
			return "Submitted";
		}
		else if(status==2){
			return "Returned";
		}
		else if(status==3){
			return "InApproved";
		}
		else {
			return "In Progress";
		}
	}

	

	
}
