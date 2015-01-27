package com.example.nmcarrol_travelcalc;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ExpenseView extends Activity {
	private ListView tweetListView; 
	private ExpenseAdapter claimItemAdapter;
	public ArrayList<Expense> expenses = new ArrayList<Expense>();
	public Claim c;
	private int position;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_view_layout);
		c = (Claim)getIntent().getSerializableExtra("Edit");
		position = getIntent().getIntExtra("Pos", 1000);

		
		expenses = c.getExp();

		
		claimItemAdapter = new ExpenseAdapter(this, c.getExp());
		tweetListView = (ListView) findViewById(R.id.list);
	    tweetListView.setAdapter(claimItemAdapter);
	    tweetListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.v("CLASSNAME", "toasty");
				editExpense(view,id);
				
				
			}
			
	    });
	    tweetListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	        @Override
	        public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
	        	
	            Log.v("CLASSNAME", ""+id);
	        	return true;
	        }
	    });
	    
	    //OUTPUT
	    Log.v("CLASSNAME", expenses.get(0).getName());
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addExpense(View view){
		Intent intent = new Intent(this, ExpenseEditor.class);
		startActivityForResult(intent, 0);
	}
	
	public void editExpense(View view, long id){
		Intent intent = new Intent(this, ExpenseEditor.class);
		intent.putExtra("Edit",expenses.get((int)(id)));
		intent.putExtra("Position", (int)(id));
		startActivityForResult(intent, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Expense c = new Expense();
		if(expenses.size()>0 && expenses.get(0).getName()=="No Claims Started"){
		expenses.remove(0);
		}
	    if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
	    	String num1 = data.getStringExtra("Cat");
	        String num2 = data.getStringExtra("Desc");
	        String num3 = data.getStringExtra("Date");
	        String num4 = data.getStringExtra("Cur");
	        String num5 = data.getStringExtra("Amt");
	        
	        c.setDescription(num2);
	        c.setCurrency(num4);
	        c.setName(num1);
	        c.setDate(num3);
	        c.setAmount(num5);
	        
	        expenses.add(0, c);
	        
	        
	        
	        
	        
	    }
	    if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
	        String num1 = data.getStringExtra("Cat");
	        String num2 = data.getStringExtra("Desc");
	        String num3 = data.getStringExtra("Date");
	        String num4 = data.getStringExtra("Cur");
	        String num5 = data.getStringExtra("Amt");
	        int index =  data.getIntExtra("Position", 1000);
	        
	        c.setDescription(num2);
	        c.setCurrency(num4);
	        c.setName(num1);
	        c.setDate(num3);
	        c.setAmount(num5);
	        
	        expenses.set(index, c);
	        
	        
	        
	        
	}
	    if (requestCode == 1 && resultCode == RESULT_CANCELED
	    		&& data != null) {
	    	int index =  data.getIntExtra("Position", 1000);
	    	expenses.remove(index);
	    	
	    	
	    }
	    claimItemAdapter.notifyDataSetChanged();
	}
	@Override
	public void onBackPressed() {
		Intent output = new Intent();
		c.setExp(expenses);
        output.putExtra("Exp", c);
        output.putExtra("Pos", position);
        setResult(RESULT_OK, output);
        finish();
        }
	
}
