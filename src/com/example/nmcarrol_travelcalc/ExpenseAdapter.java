package com.example.nmcarrol_travelcalc;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ExpenseAdapter extends ArrayAdapter{
	private LayoutInflater inflater;
    private ArrayList<Expense> item;
    
    //adapts expense items
    public ExpenseAdapter(ExpenseView activity, ArrayList<Expense> items){
       super(activity, R.layout.claim_row_layout, items);
       inflater = activity.getWindow().getLayoutInflater();
       item = items;
       
    }
    
    //populates listview with expenses as specified.
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    	View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.claim_row_layout, null);

        }
        
    	Expense p = item.get(position);

        if (p != null) {

            TextView tt = (TextView) v.findViewById(R.id.textView1);
            TextView tt1 = (TextView) v.findViewById(R.id.textView2);
            TextView tt2 = (TextView) v.findViewById(R.id.textView3);
            TextView tt3 = (TextView) v.findViewById(R.id.textView4);

            if (tt != null) {
                tt.setText(p.getDescription());
            }
            if (tt1 != null) {

                tt1.setText(p.getDate());
            }
            if(tt2 != null){
            	tt2.setText(p.getCurrency()+": "+p.getAmount());
            }
            if(tt3 != null){
            	tt3.setText("");
            }
            
        }
      return v;
      
    }

}
