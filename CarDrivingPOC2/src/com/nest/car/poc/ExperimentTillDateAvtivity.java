package com.nest.car.poc;
//import java.text.DecimalFormat;
//import java.text.Format;
//import java.text.SimpleDateFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
//import android.graphics.*;
import android.os.Bundle;
//import com.androidplot.Plot;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.series.XYSeries;
import com.androidplot.xy.*;

//import java.text.*;

import android.content.Intent;
import android.graphics.Color;
//import android.graphics.DashPathEffect;
//import android.graphics.LinearGradient;
//import android.graphics.Paint;
import android.util.Log;
import android.view.View;


public class ExperimentTillDateAvtivity extends Activity {
	String tag="ExperimentTillDateAvtivity";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Macaw MyDrive");
	    this.setTitleColor(Color.WHITE);
	     
        Log.d(tag, "Inside ExperimentTillDateAvtivity Class onCreate method");
        setContentView(R.layout.experiment);
        
     //   ExperimentTillDateAvtivity.this.averageSpeedHistoryGraph();
    //    ExperimentTillDateAvtivity.this.distanceTravelledHistoryGraph();
        
       
        
        ExperimentTillDateAvtivity.this.averageSpeedHistoryGraph();
        ExperimentTillDateAvtivity.this.distanceTravelledHistoryGraph();
            
    }
    
    
    public void eventBack(View view) 
    { 
    	finish();
    	startActivity(new Intent(ExperimentTillDateAvtivity.this,MainActivity.class));
    }
    
   
    
    
    public void distanceTravelledHistoryGraph()
    {
    	
    	Log.d(tag, "Inside ExperimentTillDateAvtivity Class distanceTravelledHistoryGraph method");
    	 XYPlot plot = (XYPlot) findViewById(R.id.mySimpleXYPlot1);
    	 plot.setDomainLabel("Date");
    	 plot.setRangeLabel("Distance (km)");
    	 plot.setTitle("Travelled Distance History");
    	 
    	 plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
         plot.setRangeBottomMin(1);
         plot.setRangeBottomMax(250);
   //      plot.setDomainBoundaries(1, 250, BoundaryMode.AUTO);
         plot.setDomainValueFormat(new MyDateFormat());
  //       plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 1);

    	 Number[] distanceHistory = {130,120,79,80,58};
    	
         XYSeries series = new SimpleXYSeries(Arrays.asList(distanceHistory), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Distance (Km)");
         
      //   plot.setTicksPerRangeLabel(series1Numbers.length);
         plot.setDomainValueFormat(new MyDateFormat());
        
         BarFormatter formatter = new BarFormatter(
        		 Color.rgb(0, 0, 255),
                 Color.rgb(102, 0, 204));
         
         plot.addSeries(series, formatter);
 //        BarRenderer renderer = (BarRenderer)plot.getRenderer(BarRenderer.class);
        
    }
    
    public void averageSpeedHistoryGraph()
    {
    	
    	Log.d(tag, "Inside ExperimentTillDateAvtivity Class averageSpeedHistoryGraph method");
    	 XYPlot plot = (XYPlot) findViewById(R.id.mySimpleXYPlot2);
    	 plot.setDomainLabel("Date");
    	 plot.setRangeLabel("Average Speed (Km/hour");
    	 plot.setTitle("Average Speed History");
    	 
    	 
    	 plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
         plot.setRangeBottomMin(1);
         plot.setRangeBottomMax(250);
   //      plot.setDomainBoundaries(1, 250, BoundaryMode.AUTO);
         plot.setDomainValueFormat(new MyDateFormat());
         
 //        plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 1);

    	 Number[] speedSeries = {45,110,60,30,67};
         XYSeries series = new SimpleXYSeries(Arrays.asList(speedSeries), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Average Speed (meters/sec)");
       
      //   plot.setTicksPerRangeLabel(series1Numbers.length);
         
         
         BarFormatter formatter = new BarFormatter(
        		 Color.rgb(204, 0, 204),
                 Color.rgb(102, 0, 204));
         
         plot.addSeries(series, formatter);
 //        BarRenderer renderer = (BarRenderer)plot.getRenderer(BarRenderer.class);
         
         
         
    }
    
  
    

    private class MyDateFormat extends Format { 

        private static final long serialVersionUID = 1L;

        private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            long timestamp = ((Number) obj).longValue();
            Date date = new Date();
            return dateFormat.format(date, toAppendTo, pos);
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return null;

        }
}

    
    
    
}
