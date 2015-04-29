package com.viewfelt.sheisgreat;



import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
Button button;
GPStracker gps;
TextView txt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
txt=(TextView) findViewById(R.id.textView);
	button =(Button)findViewById(R.id.button);
	button.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View arg0)
		{
			gps= new GPStracker(MainActivity.this);
		if(gps.canGetLocation())
		{
			double latitude=gps.getLatitude();
			double longitude=gps.getLongitude();
			Intent sd = new Intent(getApplicationContext(),
					Send.class);
			
			sd.putExtra("long",longitude);
			sd.putExtra("lat",latitude );
			startActivity(sd);
			txt.setText("Latitude:"+latitude+"\nLongitude:"+longitude);
			
			
		}
		else
		{
			gps.showSettingsAlert();
		}
		}
		
		
	});
		
	}

		

}
