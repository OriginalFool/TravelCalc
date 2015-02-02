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
	private ListView expenseListView; 
	private ExpenseAdapter claimItemAdapter;
	public ArrayList<Expense> expenses = new ArrayList<Expense>();
	public Claim c;
	private int position;

	//starts activity with needed data.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_view_layout);
		c = (Claim)getIntent().getSerializableExtra("Edit");
		position = getIntent().getIntExtra("Pos", 1000);
		
		//Creates a blank claim to add into list.
		if(c.getExp()==null){
			c.setExp(new ArrayList<Expense>());
			Expense e = new Expense();
			e.setAmount("0.00");
			e.setCurrency("CAD");
			e.setName("No Expenses Added");
			e.setDate("Today");
			e.setDescription("No Expenses Added");
			c.addExpense(e);	
		}
		expenses = c.getExp();
		
		
		claimItemAdapter = new ExpenseAdapter(this, c.getExp());
		expenseListView = (ListView) findViewById(R.id.list);
	    expenseListView.setAdapter(claimItemAdapter);
	    
		    expenseListView.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					
					editExpense(view,id);
				}
		    });
		    expenseListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		        @Override
		        public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {

		        	return true;
		        }
		    });	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//add new expense
	public void addExpense(View view){
		Intent intent = new Intent(this, ExpenseEditor.class);
		startActivityForResult(intent, 0);
	}
	
	//edit old expense
	public void editExpense(View view, long id){
		Intent intent = new Intent(this, ExpenseEditor.class);
		intent.putExtra("Edit",expenses.get((int)(id)));
		intent.putExtra("Position", (int)(id));
		startActivityForResult(intent, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Expense c = new Expense();
		
		//Remove the default item
		if(expenses.size()>0 && expenses.get(expenses.size()-1).getName().matches("No Expenses Added")){
		expenses.remove(expenses.size()-1);
		}
		
		//New Item added
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
	    //Item modified
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
	        if(expenses.size()>index){
	        expenses.set(index, c);
	        }
	        else{
	        	expenses.add(c);
	        }
	        
	        
	        
	}
	    //Delete button pressed
	    if (requestCode == 1 && resultCode == RESULT_CANCELED
	    		&& data != null) {
	    	
	    	int index =  data.getIntExtra("Position", 1000);
	    	if(expenses.size()>0){
	    	expenses.remove(index);
	    	}
	    	
	    }
	    claimItemAdapter.notifyDataSetChanged();
	}
	
	//ensures saving of expenses but poorly.
	@Override
	public void onBackPressed() {
		Intent output = new Intent();
		c.setExp(expenses);
        output.putExtra("Exp", c);
        output.putExtra("Pos", position);
        setResult(RESULT_OK, output);
        finish();
        }
	
	//view the summary
	public void viewSummary(View view){
		Intent intent = new Intent(this, ClaimSummary.class);
		intent.putExtra("Sum", c);
		startActivityForResult(intent, 0);
	}
	

	
}
