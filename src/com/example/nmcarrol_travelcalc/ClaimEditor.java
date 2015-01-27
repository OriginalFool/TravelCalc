package com.example.nmcarrol_travelcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ClaimEditor extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claim_editor_layout);
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
		
		String def = name.getText().toString();
		if(def == null){
			def="Default";
		}
     
        
        Intent output = new Intent();
        output.putExtra("Name", def);
        output.putExtra("Desc", description.getText().toString());
        output.putExtra("Start", startdate.getText().toString());
        output.putExtra("End", enddate.getText().toString());
        setResult(RESULT_OK, output);
        finish();
	}
}
