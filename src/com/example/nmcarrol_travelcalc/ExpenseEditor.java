package com.example.nmcarrol_travelcalc;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

@SuppressWarnings("unused")
public class ExpenseEditor extends Activity {
	private int pos;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_editor_layout);
		pos = getIntent().getIntExtra("Position", 1000);
		Expense c = (Expense)getIntent().getSerializableExtra("Edit");
		if(c!=null){
			
			
			EditText category = ((EditText)this.findViewById(R.id.editText4));
			EditText description = ((EditText)this.findViewById(R.id.editText2));
			EditText date = ((EditText)this.findViewById(R.id.editText3));
			EditText currency = ((EditText)this.findViewById(R.id.editText1));
			EditText amount = ((EditText)this.findViewById(R.id.editText5));
			
			category.setText(c.getName());
			description.setText(c.getDescription());
			date.setText(c.getDate());
			currency.setText(c.getCurrency());
			amount.setText(c.getAmount());
	}
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void saveClaim(View view){
		
		EditText category = ((EditText)this.findViewById(R.id.editText4));
		EditText description = ((EditText)this.findViewById(R.id.editText2));
		EditText date = ((EditText)this.findViewById(R.id.editText3));
		EditText currency = ((EditText)this.findViewById(R.id.editText1));
		EditText amount = ((EditText)this.findViewById(R.id.editText5));
		String amt2=null;
		String def = category.getText().toString();
		
		if(def.matches("")){
			def="Default";
		}
		else{
		double amt = Double.parseDouble(amount.getText().toString());
		DecimalFormat df = new DecimalFormat("#.00"); 
		amt2 = df.format(amt);
		//amt =Double.parseDouble(amt2);
		}
        
        Intent output = new Intent();
        output.putExtra("Cat", def);
        output.putExtra("Desc", description.getText().toString());
        output.putExtra("Date", date.getText().toString());
        output.putExtra("Cur", currency.getText().toString());
        output.putExtra("Amt", amt2);
        output.putExtra("Position", pos);
        setResult(RESULT_OK, output);
        finish();
	}
	
	//Remove the claim unless only one in list.
	public void deleteClaim(View view){
		if(pos==1000){
			finish();
		}
		else{
			Intent output = new Intent();
			output.putExtra("Position", pos);
			setResult(RESULT_CANCELED, output);
			finish();
		}
	}
}
