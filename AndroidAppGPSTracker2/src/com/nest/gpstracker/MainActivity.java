package com.nest.gpstracker;


import java.util.List;

import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity implements LocationListener, OnItemSelectedListener 
{
	LocationManager lm; 
	StringBuilder sb;
	TextView txtInfo;
	static final String tag = "MainActivity"; // for Log
	int noOfFixes = 0;
	Location source,destination;
	double distance;
	double speed;
	boolean bFirstTime=true;
	private Handler handler = null;
	private SensorManager sensorManager = null;
	private Sensor orientSensor = null;
	private Spinner sp;
	private String locationInfo,compassInfo;
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 3; // 10 meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 30 * 1; // 30 seconds
	
	@Override
    public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) 
	{
    //  Toast.makeText(parent.getContext(), "Item is " +parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
      txtInfo=(TextView)findViewById(R.id.information);
      sp =	(Spinner)findViewById(R.id.spinner);
      
      String spinnerString = null;
      spinnerString = sp.getSelectedItem().toString();
      
      if(spinnerString.equals("Current Location"))
      {

    	  txtInfo.setTypeface(null, Typeface.BOLD);  //-- for only bold the text
    	  txtInfo.setText(locationInfo);//
      }
      if(spinnerString.equals("Average Speed"))
      {
    	  txtInfo.setText(spinnerString);
      }
      if(spinnerString.equals("Distance Covered"))
      {
    	  txtInfo.setTypeface(null, Typeface.BOLD); 
    	  txtInfo.setText(spinnerString);
      }
      if(spinnerString.equals("Current Direction"))
      {
    	  txtInfo.setText(compassInfo);//
      }
      if(spinnerString.equals("Driving Period"))
      {
    	  txtInfo.setText(spinnerString);
      }
      
    
    }
 
	@Override
    public void onNothingSelected(AdapterView parent) 
	{
      // Do nothing.
    }
	
	
	private final SensorEventListener orientListener = new SensorEventListener() 
	{
		public void onSensorChanged(SensorEvent event) {
			float x = event.values[0];
			String direction = "";
			if (x >= 337.5 || x < 22.5) {
				direction = "North";
			} else if (x >= 22.5 && x < 67.5) {
				direction = "North East";
			} else if (x >= 67.5 && x < 112.5) {
				direction = "East";
			} else if (x >= 112.5 && x < 157.5) {
				direction = "South East";
			} else if (x >= 157.5 && x < 202.5) {
				direction = "South";
			} else if (x >= 202.5 && x < 247.5) {
				direction = "South West";
			} else if (x >= 247.5 && x < 292.5) {
				direction = "West";
			} else if (x >= 292.5 && x < 337.5) {
				direction = "North West";
			}
		//	TextView compass = (TextView)findViewById(R.id.compass_text);
			compassInfo="Current Direction of Car : " + direction;
			
		//	updateTextView(compass,compassInfo);
		//	compass.setText(direction);
			
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) 
		{
			// TODO Auto-generated method stub
		}
	};

	
	
	
	
	public void updateTextView(final TextView view, final String txt) 
    {
    	handler.post(new Runnable() {
			public void run() {
				view.setText(txt);
			}
		});
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
//		View view = this.getWindow().getDecorView();
	    //view.setBackgroundColor(0xff00ff);

		
		sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(MainActivity.this);
		
		handler = new Handler();
		
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		
	//	sensorManager.registerListener(orientListener, orientSensor, SensorManager.SENSOR_DELAY_UI);
		
		
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sens = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if ( sens.size() <= 0) {
        	Toast.makeText(this, "No orientation available.", Toast.LENGTH_LONG).show();
        } else {
        	orientSensor = sens.get(0);
        }
		
	
		source=new Location("pos1");
		destination=new Location("pos2");
		
	
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	public void onSaveInstanceState(Bundle savedInstanceState) 
	{
		  super.onSaveInstanceState(savedInstanceState);
		  // Save UI state changes to the savedInstanceState.
		  // This bundle will be passed to onCreate if the process is
		  // killed and restarted.
		  /*savedInstanceState.putBoolean("MyBoolean", txtInfo.getText());
		  savedInstanceState.putDouble("myDouble", 1.9);
		  savedInstanceState.putInt("MyInt", 1);*/
		  savedInstanceState.putString("LocationInfo", txtInfo.getText().toString());
		  // etc.
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) 
	{
	  super.onRestoreInstanceState(savedInstanceState);
	  // Restore UI state from the savedInstanceState.
	  // This bundle has also been passed to onCreate.
	  
	  String locationData = savedInstanceState.getString("LocationInfo");
	  txtInfo.setText(locationData);
	}
	
	
	@Override
	protected void onResume() {
		/*
		 * onResume is is always called after onStart, even if the app hasn't been
		 * paused
		 * 
		 * add location listener and request updates every 1000ms or 10m
		 */
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
		
		sensorManager.registerListener(orientListener, orientSensor, SensorManager.SENSOR_DELAY_UI);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		/* GPS, as it turns out, consumes battery like crazy */
		lm.removeUpdates(this);
		super.onPause();
	}

	@Override
	public void onLocationChanged(Location location) {
		
		txtInfo.setText("Default Data....");
		Log.v(tag, "Location Changed");

		sb = new StringBuilder(512);
		
		noOfFixes++;

		/* display some of the data in the TextView */

		sb.append("No. of Fixes: ");
		sb.append(noOfFixes);
		sb.append('\n');
		sb.append('\n');

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
		sb.append('\n');
		
		sb.append("Speed: ");
		sb.append(location.getSpeed());
		sb.append(" meters/second");
		sb.append('\n');
		
		sb.append("Distance Covered: ");
		sb.append(getDistance(location));
		sb.append(" meters");
		sb.append('\n');
		
		sb.append("Time Taken: ");
		sb.append(getTimeDifference());
		sb.append(" sec");
		sb.append('\n');

		locationInfo=sb.toString();
//		txtInfo.setText(sb.toString());
	}

	public double getDistance(Location location)
	{
	// Location destination =new Location("gps");
	 if(bFirstTime)
	 {
	 source.setLatitude(location.getLatitude());
	 source.setLongitude(location.getLongitude());
	 destination.setLatitude(location.getLatitude());
	 destination.setLongitude(location.getLongitude());
	 
	 bFirstTime=false;
	 }
	 else
	 {
		 destination.setLatitude(location.getLatitude());
		 destination.setLongitude(location.getLongitude());
	 }
	 
	 distance=source.distanceTo(destination);
	 
	 source.setLatitude(destination.getLatitude());
	 source.setLongitude(destination.getLongitude());
	 return distance;
	 
	}
	
	public double getSpeed(Location location)
	{
		speed=location.getSpeed();
		return speed;
	}
	
	public double getTimeDifference()
	{
		double timeTaken=distance/speed;
		return timeTaken;
	}
	
	
	
	

	@Override
	protected void onStop() {
		/* may as well just finish since saving the state is not important for this toy app */
		finish();
		super.onStop();
	}

	


}
