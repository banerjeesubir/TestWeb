package com.nest.car.poc;


import android.graphics.Color;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity implements LocationListener
{
//	LocationManager lm;
	Location source,destination;
	double distance;
	double speed;
	private String tag="MainActivity";
	boolean bFirstTime=true;
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 3; // 3 meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 30 * 1; // 30 seconds
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		 this.setTitle("Macaw MyDrive");
	     this.setTitleColor(Color.WHITE);
	     
		setContentView(R.layout.activity_main);
	
		EditText editScore=(EditText)findViewById(R.id.edittext1);
	    editScore.setEnabled(false);
	     
	   
	    CarLocationApp.setLocationManager((LocationManager) getSystemService(LOCATION_SERVICE));
	  //   lm = (LocationManager) getSystemService(LOCATION_SERVICE);
	     
//	     Button stopButton=(Button)findViewById(R.id.button2);
//	     stopButton.setEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	@Override
	protected void onResume() {
		/*
		 * onResume is is always called after onStart, even if the app hasn't been
		 * paused
		 * 
		 * add location listener and request updates every 1000ms or 10m
		 */
		LocationManager lm=CarLocationApp.getLocationManager();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		/* GPS, as it turns out, consumes battery like crazy */
		LocationManager lm=CarLocationApp.getLocationManager();
		lm.removeUpdates(this);
		super.onPause();
	}
	
	public void onLocationChanged(Location location) 
	{
/*		Log.v(tag, "Location Changed");

		StringBuilder sb = new StringBuilder(512);
		
		

		sb.append("Londitude: ");
		sb.append(location.getLongitude());
		sb.append('\n');

		sb.append("Latitude: ");
		sb.append(location.getLatitude());
		sb.append('\n');

		sb.append("Altitiude: ");
		sb.append(location.getAltitude());
		sb.append('\n');

		sb.append("Accuracy: ");
		sb.append(location.getAccuracy());
		sb.append('\n');

		sb.append("Timestamp: ");
		sb.append(location.getTime());
		sb.append('\n');*/
		
		
	//	double averageSpeed=getSpeed(location);
	//	double distanceCovered=getDistance(location);
		
		
		CarLocationApp.recordCurrentLocation(location);
		CarLocationApp.recordAverageSpped(location);
	/*	sb.append("Time Taken: ");
		sb.append(getTimeDifference());
		sb.append(" sec");
		sb.append('\n');*/
		
	}
	



	@Override
	public void onProviderDisabled(String provider) {
		/* this is called if/when the GPS is disabled in settings */
		Log.v(tag, "Disabled");

		/* bring up the GPS settings */
		Intent intent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(intent);
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.v(tag, "Enabled");
		Toast.makeText(this, "GPS Enabled", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		/* This is called when the GPS status alters */
		switch (status) {
		case LocationProvider.OUT_OF_SERVICE:
			Log.v(tag, "Status Changed: Out of Service");
			Toast.makeText(this, "Status Changed: Out of Service",
					Toast.LENGTH_SHORT).show();
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			Log.v(tag, "Status Changed: Temporarily Unavailable");
			Toast.makeText(this, "Status Changed: Temporarily Unavailable",
					Toast.LENGTH_SHORT).show();
			break;
		case LocationProvider.AVAILABLE:
			Log.v(tag, "Status Changed: Available");
			Toast.makeText(this, "Status Changed: Available",
					Toast.LENGTH_SHORT).show();
			break;
		}
	}
	
	
	public void eventStart(View view) 
    {    
    	// Do something in response to button click}
    	Log.d(tag, "Start Button Clicked");
    	
    	
    	Button startButton=(Button)findViewById(R.id.button1);
    //	startButton.setEnabled(false);
  //  	startButton.setText("Stop");
    	String str=startButton.getText().toString();
    	if(str.equals("Start"))
    	{
    		startButton.setText("Stop");
    //		startButton.setEnabled(false);
    		
    	}
    	if(str.equals("Stop"))
    	{
    		startButton.setText("Start");
    //		startButton.setEnabled(true);
    		
    	}
    //	Button stopButton=(Button)findViewById(R.id.button2);
  //  	stopButton.setEnabled(true);
    	
   /* 	EditText editScore=(EditText)findViewById(R.id.editText1);
    	editScore.setEnabled(true);
    	editScore.setText(50);
    	editScore.setEnabled(false);*/
       
    	
    	
    }
    
    public void eventStop(View view) 
    {    
    	// Do something in response to button click}
    	
    	
    	Button startButton=(Button)findViewById(R.id.button1);
    	startButton.setEnabled(true);
//    	Button stopButton=(Button)findViewById(R.id.button2);
 //   	stopButton.setEnabled(false);
    	
/*    	EditText editScore=(EditText)findViewById(R.id.editText1);
    	editScore.setEnabled(true);
    	editScore.setText(80);
    	editScore.setEnabled(false);*/

    }
    
    public void eventCurrentTrip(View view) 
    {    
    	// Do something in response to button click}
    	Log.d(tag, "eventCurrentTrip Button Clicked - Start");
   // 	Toast.makeText(getApplicationContext(), "Send Button Clicked", 30);
    	startActivity(new Intent(MainActivity.this,CurrenTripActivity.class));
    	Log.d(tag, "eventCurrentTrip Button Clicked - Stop");

    }
    
    public void eventExperienceTillDate(View view) 
    {    
    	// Do something in response to button click}
    	Log.d(tag, "eventExperienceTillDate Button Clicked - Start");
   // 	Toast.makeText(getApplicationContext(), "Send Button Clicked", 30);
    	startActivity(new Intent(MainActivity.this,ExperimentTillDateAvtivity.class));
    	Log.d(tag, "eventExperienceTillDate Button Clicked - stop");

    	
    }


}
