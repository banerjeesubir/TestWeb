package com.nest.car.poc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import com.androidplot.Plot;
import com.androidplot.series.XYSeries;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CurrenTripActivity extends Activity {
    /** Called when the activity is first created. */
	String tag="CurrenTripActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	
    	Log.d(tag, "Inside CurrenTripActivity Class onCreate method");
        super.onCreate(savedInstanceState);
        this.setTitle("Macaw MyDrive");
        this.setTitleColor(Color.WHITE);
        setContentView(R.layout.currenttrip);
        
        
 //       CurrenTripActivity.this.distanceTravelledGraphInSingleTrip();

        CurrenTripActivity.this.averageSpeedInSingleTrip();
        CurrenTripActivity.this.distanceTraversed();
       
    }
    
  //  public void eventStart(View view)
    public void eventBack(View view)
    { 
    	finish();
    	startActivity(new Intent(CurrenTripActivity.this,MainActivity.class));
    }
    
   
    public void distanceTraversed()
    {
    	String unit;
    	TextView distanceText=(TextView)findViewById(R.id.distanceTraverseID);
    	double distanceTraversed=CarLocationApp.getDistanceTravelled();
    	Double distance=new Double(distanceTraversed);
    	
    	
    	if(distance > 1000)
    	{
    		unit=" Km";
    		distance=(distance/1000);
    	}
    	else
    		unit=" Meter";
    	
    	String strDistance=distance.toString();
    	strDistance.concat(unit);
    	distanceText.setText(strDistance);		
    }
    
    
    
    public void averageSpeedInSingleTrip()
    {
    	// initialize our XYPlot reference:
    	XYPlot mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot1);
    	
    	mySimpleXYPlot.setTitle("Average Speed Tracker");
        mySimpleXYPlot.setDomainLabel("Time(Minutes)");
        mySimpleXYPlot.setRangeLabel("Speed (Meter/Sec)");
        mySimpleXYPlot.getLegendWidget().setVisible(false);
    	
   // 	Number[] speed = {10, 40, 60, 30, 70, 20, 110, 50, 80,45,89,40,10};

       ArrayList<Double> speedArray=CarLocationApp.getAverageSpeedArray();
       
       
       
        // Turn the above arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
        		speedArray,          // SimpleXYSeries takes a List so turn our array into a List
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                "Series1");                             // Set the display title of the series

        // same as above
     //   XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");

        // Create a formatter to use for drawing a series using LineAndPointRenderer:
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),                   // line color
                Color.rgb(0, 100, 0),                   // point color
                null);                                  // fill color (none)

        // add a new series' to the xyplot:
        mySimpleXYPlot.addSeries(series1, series1Format);

        // same as above:
     //   mySimpleXYPlot.addSeries(series2,new LineAndPointFormatter(Color.rgb(0, 0, 200), Color.rgb(0, 0, 100), null));

        // reduce the number of range labels
        mySimpleXYPlot.setTicksPerRangeLabel(3);

        // by default, AndroidPlot displays developer guides to aid in laying out your plot.
        // To get rid of them call disableAllMarkup():
        mySimpleXYPlot.disableAllMarkup();

    }
    
    public void distanceTravelledGraphInSingleTrip()
    {
    	Log.d(tag, "Inside CurrenTripActivity Class distanceTravelledGraphInSingleTrip method");
    	XYPlot mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot2);
    	
    	// customize our domain/range labels
    	mySimpleXYPlot.setTitle("Distance Travelled");
        mySimpleXYPlot.setDomainLabel("Time(Minutes)");
        mySimpleXYPlot.setRangeLabel("Distance(Km)");
        mySimpleXYPlot.getLegendWidget().setVisible(false);
    	
    	Number[] distance = {10, 25, 40, 60, 70, 80, 110, 140, 160};

        Number[] timestamps = {10, 20, 30, 40, 50, 60, 70, 80, 90};

        // create our series from our array of nums:
        XYSeries series2 = new SimpleXYSeries(
                Arrays.asList(timestamps),
                Arrays.asList(distance),
                "USA");

        mySimpleXYPlot.getGraphWidget().getGridBackgroundPaint().setColor(Color.GRAY);
        mySimpleXYPlot.getGraphWidget().getGridLinePaint().setColor(Color.BLACK);
        mySimpleXYPlot.getGraphWidget().getGridLinePaint().setPathEffect(new DashPathEffect(new float[]{1,1}, 1));
        mySimpleXYPlot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
        mySimpleXYPlot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);

        mySimpleXYPlot.setBorderStyle(Plot.BorderStyle.SQUARE, null, null);
        mySimpleXYPlot.getBorderPaint().setStrokeWidth(1);
        mySimpleXYPlot.getBorderPaint().setAntiAlias(false);
        mySimpleXYPlot.getBorderPaint().setColor(Color.WHITE);

        // Create a formatter to use for drawing a series using LineAndPointRenderer:
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(0, 100, 0),                   // line color
                Color.rgb(0, 100, 0),                   // point color
                Color.rgb(100, 200, 0));                // fill color


        // setup our line fill paint to be a slightly transparent gradient:
        Paint lineFill = new Paint();
        lineFill.setAlpha(200);
        lineFill.setShader(new LinearGradient(0, 0, 0, 250, Color.WHITE, Color.rgb(102,0,204), Shader.TileMode.MIRROR));

        LineAndPointFormatter formatter  = new LineAndPointFormatter(Color.rgb(0, 0,0), Color.BLUE, Color.RED);
        formatter.setFillPaint(lineFill);
        mySimpleXYPlot.getGraphWidget().setPaddingRight(2);
        mySimpleXYPlot.addSeries(series2, formatter);

        // draw a domain tick for each year:
        mySimpleXYPlot.setDomainStep(XYStepMode.SUBDIVIDE, 9);
        mySimpleXYPlot.setRangeBoundaries(1,200, BoundaryMode.FIXED);

        

        // get rid of decimal points in our range labels:
        mySimpleXYPlot.setRangeValueFormat(new DecimalFormat("0"));

        //mySimpleXYPlot.setDomainValueFormat(new MyDateFormat());

        // by default, AndroidPlot displays developer guides to aid in laying out your plot.
        // To get rid of them call disableAllMarkup():
        mySimpleXYPlot.disableAllMarkup();

    }
    
}
