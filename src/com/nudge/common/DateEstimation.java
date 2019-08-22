package com.nudge.common;
import java.io.ObjectInputStream.GetField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateEstimation {
	
	public static void main(String args[])
	{
		long final_timestamp= getFinalTimestamp(978355615);
		}
	
	public static int getYearFromTimestamp(long timestamp)
	{
		
		int year=0;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		year= cal.get(Calendar.YEAR);
	//	System.out.println("Current Year=========>"+year);
		
		return year;
	}
	public static String getDateFromTimestamp(long timestamp)
	{
		
		String date="";
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		date= String.valueOf(cal.get(Calendar.DATE)+1);
		//System.out.println("Current Year=========>"+date);
		
		return date;
	}
	public static String getMonthFromTimestamp(long timestamp)
	{
		
		String month="";
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		month=  String.valueOf(cal.get(Calendar.MONTH)+1);
		//System.out.println("Current Year=========>"+month);
		
		return month;
	}
	public static int getMonthDateFromTimestamp(long timestamp)
	{
		int i=0;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		String month =  String.valueOf(cal.get(Calendar.MONTH)+1);
		String day = String.valueOf( cal.get(Calendar.DATE));
		if(month.length()==1)
		{
			month= "0"+month;
		}
		if(day.length()==1)
		{
			day= "0"+day;
		}
		String mon_date= month + day;
		
		//System.out.println("mon_date=========>"+mon_date);
		i= Integer.valueOf(mon_date);
		return i;
	}
	
	public static long getFinalTimestamp(long timestamp)
	{
		
		long final_timestamp =0;
		int current= getMonthDateFromTimestamp(System.currentTimeMillis());
		int newdate = getMonthDateFromTimestamp(timestamp*1000);
		
		//System.out.println("current String=========>"+current);
		//System.out.println("new String=========>"+newdate);
		int year= getYearFromTimestamp(System.currentTimeMillis());
		if(current>newdate)
		{
			year= year+1;
		}
		String month= getMonthFromTimestamp(timestamp*1000);
	    String date= getDateFromTimestamp(timestamp*1000);
		String final_date= date+"-"+month+"-"+year;
		//System.out.println("estimated date is==========>"+final_date);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date_new=null;
		try {
			date_new = (Date)formatter.parse(final_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//System.out.println("final date time stamp========>"+date_new.getTime());
		final_timestamp=date_new.getTime();
		final_timestamp= final_timestamp/1000;
		return final_timestamp;
	}
	
	public static long getFinalTimestampforFathersDay(long timestamp)
	{
		
		long final_timestamp =0;
		int current= getMonthDateFromTimestamp(System.currentTimeMillis());
		int newdate = getMonthDateFromTimestamp(timestamp);
		
		//System.out.println("current String=========>"+current);
		//System.out.println("new String=========>"+newdate);
		int year= getYearFromTimestamp(System.currentTimeMillis());
		if(current>newdate)
		{
			year= year+1;
		}
		String month= getMonthFromTimestamp(timestamp);
	    String date= getDateFromTimestamp(timestamp);
		String final_date= date+"-"+month+"-"+year;
		//System.out.println("estimated date is==========>"+final_date);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date_new=null;
		try {
			date_new = (Date)formatter.parse(final_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//System.out.println("final date time stamp========>"+date_new.getTime());
		final_timestamp=date_new.getTime();
		final_timestamp= final_timestamp/1000;
		return final_timestamp;
	}

}
