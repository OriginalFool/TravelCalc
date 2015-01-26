package com.example.nmcarrol_travelcalc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayList<Claim> items = new ArrayList<Claim>();
		
		for ( int i = 0; i < 20; i++ ) {
		    Claim tweet = new Claim();
		    tweet.setName("Fuck" +i);
		    tweet.setDates("This " +i);
		    items.add(tweet);
		}
		
		//INPUT
		FileInputStream fis = null;
		try {
			fis = openFileInput("TEST.TXT");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(fis);
		} catch (StreamCorruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			items = (ArrayList<Claim>) ois.readObject();
		} catch (OptionalDataException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			ois.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//IMPORTANT
		
		ClaimAdapter tweetItemArrayAdapter = new ClaimAdapter(this, items);
		ListView tweetListView = (ListView) findViewById(R.id.list);
	    tweetListView.setAdapter(tweetItemArrayAdapter);
	    
	    
	    //OUTPUT
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
