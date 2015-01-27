package com.example.nmcarrol_travelcalc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

@SuppressWarnings("unused")
public class MainActivity extends Activity {
	private ListView tweetListView; 
	private ClaimAdapter claimItemAdapter;
	public ArrayList<Claim> items;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.v("CLASSNAME", "TEWST");
		items = null;
		items = loadFromFile();
		
		claimItemAdapter = new ClaimAdapter(this, items);
		tweetListView = (ListView) findViewById(R.id.list);
	    tweetListView.setAdapter(claimItemAdapter);
	    tweetListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				int status = items.get((int)id).getStatus();
				Log.v("CLASSNAME", "" +status);
				if(status != 1 && status != 3){
					viewExpense(view, id);
				}
				else{
					
				}
				
			}
			
	    });
	    tweetListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	        @Override
	        public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
	        	
	            Log.v("CLASSNAME", ""+id);
	            editClaim(v,id);
	        	return true;
	        }
	    });
	    
	    saveToFile();
	    //OUTPUT
	    Log.v("CLASSNAME", items.get(0).getName());
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void saveToFile(){
		FileOutputStream fos = null;
		try {
			fos = openFileOutput("TEST.TXT", MODE_PRIVATE);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			oos.writeObject(items);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ArrayList<Claim> loadFromFile() {
		try {
			FileInputStream fis = openFileInput("TEST.TXT");
			//http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html Jan 22
			ObjectInputStream ois = new ObjectInputStream(fis);
			items = (ArrayList<Claim>) ois.readObject();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(items ==null){
			items = new ArrayList<Claim>();
			Claim def = new Claim();
			def.setStartdate("today");
			def.setEnddate("");
			def.setName("No Claims Started");
			items.add(def);
		}
		return items;
	}
	
	public void addClaim(View view){
		Intent intent = new Intent(this, ClaimEditor.class);
		startActivityForResult(intent, 0);
	}
	public void editClaim(View view, long id){
		Intent intent = new Intent(this, ClaimEditor.class);
		intent.putExtra("Edit",items.get((int)(id)));
		intent.putExtra("Position", (int)(id));
		startActivityForResult(intent, 1);
	}
	
	public void viewExpense(View view, long id){
		Intent intent = new Intent(this, ExpenseView.class);
		intent.putExtra("Edit",items.get((int)(id)));
		intent.putExtra("Pos", (int)(id));
		startActivityForResult(intent, 2);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Claim c = new Claim();
		if(items.get(0).getName()=="No Claims Started"){
		items.remove(0);
		}
	    if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
	        String num1 = data.getStringExtra("Name");
	        String num2 = data.getStringExtra("Desc");
	        String num3 = data.getStringExtra("Start");
	        String num4 = data.getStringExtra("End");
	        int num5 = data.getIntExtra("Status", -1);
	        
	        c.setDescription(num2);
	        c.setEnddate(num4);
	        c.setName(num1);
	        c.setStartdate(num3);
	        c.setStatus(num5);
	        
	        items.add(0, c);
	        
	        
	        
	        
	        
	    }
	    if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
	        String num1 = data.getStringExtra("Name");
	        String num2 = data.getStringExtra("Desc");
	        String num3 = data.getStringExtra("Start");
	        String num4 = data.getStringExtra("End");
	        int index =  data.getIntExtra("Position", 1000);
	        int num5 = data.getIntExtra("Status", -1);
	        
	        c.setDescription(num2);
	        c.setEnddate(num4);
	        c.setName(num1);
	        c.setStartdate(num3);
	        c.setStatus(num5);
	        
	        items.set(index, c);
	        
	        
	        
	        
	}
	    if (requestCode == 1 && resultCode == RESULT_CANCELED
	    		&& data != null) {
	    	int index =  data.getIntExtra("Position", 1000);
	    	items.remove(index);
	    	
	    	
	    }
	    if (requestCode == 2 && resultCode == RESULT_OK && data != null){
	    	int index =  data.getIntExtra("Pos", 1000);
	    	//ArrayList<Expense>  exp = (ArrayList<Expense>) data.getSerializableExtra("Exp");
	    	//Log.v("CLASSNAME",exp.get(0).getName() + "TESTING");
	    	Claim d =(Claim)data.getSerializableExtra("Exp");
	    	Log.v("CLASSNAME",d.getExp().get(0).getName() + "THISE IS ");
	    	items.set(index, (Claim)data.getSerializableExtra("Exp"));
	    }
	    
	    saveToFile();
	    claimItemAdapter.notifyDataSetChanged();
	

}
}
