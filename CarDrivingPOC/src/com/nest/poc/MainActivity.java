package com.nest.poc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	String tag="MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void eventStart(View view) 
    {    
    	// Do something in response to button click}
    	Log.d(tag, "Start Button Clicked");
    	
    	
    	Button startButton=(Button)findViewById(R.id.button1);
    	startButton.setEnabled(false);
    	Button stopButton=(Button)findViewById(R.id.button2);
    	stopButton.setEnabled(true);
    	
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
    	Button stopButton=(Button)findViewById(R.id.button2);
    	stopButton.setEnabled(false);
    	
/*    	EditText editScore=(EditText)findViewById(R.id.editText1);
    	editScore.setEnabled(true);
    	editScore.setText(80);
    	editScore.setEnabled(false);*/

    }
    
    public void eventCurrentTrip(View view) 
    {    
    	// Do something in response to button click}
    	Log.d(tag, "CurrentButton Button Clicked");
   // 	Toast.makeText(getApplicationContext(), "Send Button Clicked", 30);
    //	startActivity(new Intent(MainActivity.this,CurrenTripActivity.class));

    }
    
    public void eventExperienceTillDate(View view) 
    {    
    	// Do something in response to button click}
    	Log.d(tag, "ExperienceTillDate Button Clicked");
   // 	Toast.makeText(getApplicationContext(), "Send Button Clicked", 30);
  //  	startActivity(new Intent(MainActivity.this,ExperimentTillDateAvtivity.class));

    	
    }
    

}
