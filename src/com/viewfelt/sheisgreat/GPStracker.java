package com.viewfelt.sheisgreat;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPStracker extends Service implements LocationListener{
	private final Context mContext;
	boolean isGPSEnabled=false;
	boolean isNetworkEnabled=false;
	boolean canGetLocation=false;
	Location location;
	double latitude;
	double longitude;
	private static final long minDistance=10;
	private static final long minTime= 1000*60*1;
	protected LocationManager locationManager;
	public GPStracker(Context context)
	{
		this.mContext=context;
		getLocation();
	}
	public Location getLocation()
	{
		try
		{
			locationManager=(LocationManager) mContext.getSystemService(LOCATION_SERVICE);
			isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if(!isGPSEnabled && !isNetworkEnabled)
			{
			}
			else{
				this.canGetLocation=true;
				if(isNetworkEnabled)
				{
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minDistance, minTime, this);
					Log.d("network","Network");
					if(locationManager!=null)
					{
						location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if(location!=null)
						{
							latitude=location.getLatitude();
							longitude=location.getLongitude();
						
						}
					}
				}
			}
			
		
		
		
	}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
	
return location;
	}

	@Override
	public void onLocationChanged(Location location){}
	@Override
	public void onProviderDisabled(String provider){}
	@Override
	public void onProviderEnabled(String provider){}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras){}
	@Override
	public IBinder onBind(Intent arg0){return null;}
	public double getLatitude()
	{if(location!=null){
		latitude=location.getLatitude();
	  }
	return latitude;
	
	}
	public double getLongitude()
	{
		if(location!=null)
		{
			longitude=location.getLongitude();
		}
		return longitude;
		
	}
	public boolean canGetLocation()
	{
		return this.canGetLocation;
		
	}
	public void showSettingsAlert()
	{
		AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
		alertDialog.setTitle("GPS settings");
		alertDialog.setMessage("Click on the Settings to turn on access location");
		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which)
			{
				Intent intent = new Intent (Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				mContext.startActivity(intent);
				
			}
		});
			
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.cancel();
			}
			
		});
		alertDialog.show();
		
	
		
	}
	public void stopusingGPS()
	{
		if(locationManager!=null)
		{locationManager.removeUpdates(GPStracker.this);
		}
	}
	
}
