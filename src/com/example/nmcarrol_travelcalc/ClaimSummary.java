package com.example.nmcarrol_travelcalc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClaimSummary extends Activity {
	private Claim c;
	private ArrayList<Expense> Exp;
	private String sum = "";
	private String Exptext ="";
	
	//populate view with items appropriate to claim.
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_view);
		
		 c = (Claim)getIntent().getSerializableExtra("Sum");
		 sum += c.getName() + " Spending: \n";
		 Exp = c.getExp();
		 Iterator<Expense> it = Exp.iterator();
		 Hashtable<String, Double> numbers= new Hashtable<String, Double>();
		 //Add up currencies and get all expenses.
		 while(it.hasNext())
		 {
			 
		     Expense obj = it.next();
		     
		     Exptext += obj.getDescription() + " " + obj.getCurrency() + ": " + obj.getAmount() + "\n";
		     
		     double amt =(Double.valueOf(obj.getAmount()));
		     if(numbers.containsKey(obj.getCurrency())){
		     numbers.put(obj.getCurrency(), (numbers.get(obj.getCurrency())+amt));
		     
		     }
		     else{
		    	 numbers.put(obj.getCurrency(), amt);
		     }
		    
		 }
		 
		 	Enumeration e = numbers.keys();
		    while (e.hasMoreElements()) {
		      String key = (String) e.nextElement();
		      sum += (key + " : " + numbers.get(key)+"\n");
		    
		    }
		    
		    
		
		    sum += "\n" + Exptext;
		    TextView tt = (TextView)findViewById(R.id.textView1);
		    tt.setText(sum);
		
		
			 
	}
	
	//email claim as displayed in summary.
	public void shareClaim(View view){
		TextView tt = (TextView)findViewById(R.id.textView1);
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setData(Uri.parse("mailto:"));
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"nmcarrol@ualberta.ca"});
		intent.putExtra(Intent.EXTRA_SUBJECT, c.getName()+" Claim \n");
		intent.putExtra(Intent.EXTRA_TEXT   , tt.getText().toString());
		try {
		    startActivity(Intent.createChooser(intent, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	public void onBackPressed() {
        finish();
        }
	
}

