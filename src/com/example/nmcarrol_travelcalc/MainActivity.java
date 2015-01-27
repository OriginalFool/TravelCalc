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
				Log.v("CLASSNAME", "toasty");
				
			}
			
	    });
	    tweetListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	        @Override
	        public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
	            Log.v("CLASSNAME", "TOASTY2");
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
	private void saveToFile(){
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
	public void editClaim(View view){
		Intent intent = new Intent(this, ClaimEditor.class);
		startActivityForResult(intent, 1);
	}
	
	
	/*protected void onListItemClick(ListView l, View v, int position, long id) {
	    Context context = getApplicationContext();
	    CharSequence text = "Hello toast!";
	    int duration = Toast.LENGTH_SHORT;

	    Toast toast = Toast.makeText(context, text, duration);
	    toast.show();
	    

	}*/
	
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
	        
	        c.setDescription(num2);
	        c.setEnddate(num4);
	        c.setName(num1);
	        c.setStartdate(num3);
	        
	        items.add(c);
	        saveToFile();
	        
	        
	        claimItemAdapter.notifyDataSetChanged();
	        
	    }
	}
	

}
