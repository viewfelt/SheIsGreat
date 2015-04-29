package com.viewfelt.sheisgreat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Send extends MainActivity{
	Double lat1;
	Double long1;
	Button snd;
	TextView txt1;
	EditText phone1;
	EditText phone2;
	String p1;
	String p2;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.senddetails);
	Intent sd=getIntent();
	txt=(TextView)findViewById(R.id.location);
	snd=(Button) findViewById (R.id.button1);
	txt.setText( "My location is:"+"\nLatitude:"+lat1+"\nLongitude:"+long1);
	phone1=(EditText)findViewById(R.id.phone1);
	phone2=(EditText)findViewById(R.id.phone2);
	
	lat1=sd.getDoubleExtra("lat", (double) 0.0);
			long1= sd.getDoubleExtra("long", (double) 0.0);	
	snd.setOnClickListener(new OnClickListener()
	{
		public void onClick(View v)
		{
p1=phone1.getText().toString();//getting data from edittext
p2=phone2.getText().toString();

Intent il= new Intent(Intent.ACTION_SEND);
il.putExtra(Intent.EXTRA_PHONE_NUMBER,p1);
il.putExtra(Intent.EXTRA_PHONE_NUMBER,p2);

il.putExtra(Intent.EXTRA_TEXT,"Help me:\n My location is:"+"\nLatitude:"+lat1+"\nLongitude:"+long1);
il.setType("text/plain");
startActivity(il);


		}
	
	});

	}}
