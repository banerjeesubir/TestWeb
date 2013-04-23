package com.example.androidappgui;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void clickHandler(View view) {
	    // Do something in response to button click
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder
    	.setTitle("Erase hard drive")
    	.setMessage("Are you sure?")
    	.setIcon(android.R.drawable.ic_dialog_alert)
    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int which) {			      	
    	    	//Yes button clicked, do something
    	    	Toast.makeText(MainActivity.this, "Yes button pressed", Toast.LENGTH_SHORT).show();
    	    	
    	    	Intent i = new Intent();
    	    	PackageManager manager = getPackageManager();
    	    	i = manager.getLaunchIntentForPackage("AndroidMultipleActivity.apk");
    	    	i.addCategory(Intent.CATEGORY_LAUNCHER);
    	    	startActivity(i);
    	    	
    	    	
    	    }
    	})
    	.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int which) {			      	
    	    	//Yes button clicked, do something
    	    	Toast.makeText(MainActivity.this, "No button pressed", 
                               Toast.LENGTH_SHORT).show();
    	    }
    	})						//Do nothing on no
    	.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
