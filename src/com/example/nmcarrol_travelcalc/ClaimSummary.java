package com.example.nmcarrol_travelcalc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;

public class ClaimSummary extends Activity {
	private Claim c;
	private ArrayList<Expense> Exp;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 c = (Claim)getIntent().getSerializableExtra("Sum");
		 Exp = c.getExp();
		 Iterator<Expense> it = Exp.iterator();
		 Hashtable<String, Double> numbers= new Hashtable<String, Double>();
		 while(it.hasNext())
		 {
		     Expense obj = it.next();
		     double amt = Double.parseDouble(obj.getAmount());
		     if(numbers.contains(obj.getCurrency())){
		     numbers.put(obj.getCurrency(), (numbers.get(obj.getCurrency())+amt));
		     }
		     //Do something with obj
		 }
			 
		 }
	}
}
