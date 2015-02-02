package com.example.nmcarrol_travelcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unused")
public class ClaimEditor extends Activity {
	private int pos;
	private boolean edit = true;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_editor_layout);
		pos = getIntent().getIntExtra("Position", 1000);
		Claim c = (Claim)getIntent().getSerializableExtra("Edit");
		if(c!=null){
			
			EditText name = ((EditText)this.findViewById(R.id.editText1));
			EditText description = ((EditText)this.findViewById(R.id.editText2));
			EditText startdate = ((EditText)this.findViewById(R.id.editText3));
			EditText enddate = ((EditText)this.findViewById(R.id.editText4));
			Spinner mySpinner=(Spinner) findViewById(R.id.spinner1);
			
			name.setText(c.getName());
			description.setText(c.getDescription());
			startdate.setText(c.getStartdate());
			enddate.setText(c.getEnddate());
			mySpinner.setSelection(c.getStatus());
			if(c.getStatus() == 2 || c.getStatus() == 4){
				edit = false;
			}
	}
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void saveClaim(View view){
		
		EditText name = ((EditText)this.findViewById(R.id.editText1));
		EditText description = ((EditText)this.findViewById(R.id.editText2));
		EditText startdate = ((EditText)this.findViewById(R.id.editText3));
		EditText enddate = ((EditText)this.findViewById(R.id.editText4));
		Spinner mySpinner=(Spinner) findViewById(R.id.spinner1);
		
		String def = name.getText().toString();
		int position = mySpinner.getSelectedItemPosition();
		if(def.matches("")){
			def="Default";
		}
		if(edit ==false && (position==2||position==4 )){
			Toast.makeText(getApplicationContext(), 
				    "Editting Locked, change status to edit.", Toast.LENGTH_LONG).show();
		}
		else{
			
			Intent output = new Intent();
			output.putExtra("Name", def);
			output.putExtra("Desc", description.getText().toString());
			output.putExtra("Start", startdate.getText().toString());
			output.putExtra("End", enddate.getText().toString());
			output.putExtra("Status", position);
			output.putExtra("Position", pos);
			setResult(RESULT_OK, output);
			finish();}
	}
	public void deleteClaim(View view){
		if(pos==1000){
			finish();
		}
		if(edit ==false){
			Toast.makeText(getApplicationContext(), 
				    "Editting Locked, save status to delete.", Toast.LENGTH_LONG).show();
		}
		else{
			Intent output = new Intent();
			output.putExtra("Position", pos);
			setResult(RESULT_CANCELED, output);
			finish();
		}
	}
}
