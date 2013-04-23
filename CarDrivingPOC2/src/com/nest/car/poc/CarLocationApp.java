package com.nest.car.poc;

import java.util.ArrayList;

import android.location.Location;
import android.location.LocationManager;

public class CarLocationApp 
{
	private static CarLocationApp	carLocationObj=null;
	private static ArrayList<Location>	locationArray=new ArrayList<Location>();
	private static ArrayList<Double>	averageSpeedArray=new ArrayList<Double>();
	private static  double distance;
	private static Location source,destination;
	private static boolean bFirstTime=true;
	private static LocationManager lm;
	
	
	public CarLocationApp getApplication()
	{
		if(carLocationObj==null)
		{
			carLocationObj=new CarLocationApp();
			source=new Location("pos1");
			destination=new Location("pos2");
		}
		
		return carLocationObj;
	}
	
	public static void setLocationManager(LocationManager lmObj)
	{
		lm=lmObj;
	}
	
	public static LocationManager getLocationManager()
	{
		return lm;
	}
	
	public static void recordCurrentLocation(Location location)
	{
		locationArray.add(location);
		
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
	//	this.getDistance(location);
		
	}
	
	public static void recordAverageSpped(Location location)
	{
		double speed=location.getSpeed();
		averageSpeedArray.add(new Double(Double.valueOf(speed)));
	}
	
	public static ArrayList<Double> getAverageSpeedArray()
	{
		return averageSpeedArray;
	}
	
	public static double getDistanceTravelled()
	{
		return distance;
	}
	
/*	public double getDistance(Location location)
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
*/

/*	public static double getTimeDifference()
	{
		double timeTaken=distance/speed;
		return timeTaken;
	}*/

	
}
