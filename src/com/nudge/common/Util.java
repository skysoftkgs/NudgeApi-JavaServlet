package com.nudge.common;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

public class Util 
{

	public static String DateToMillisecond(String date_formated)
	{
		long time_show=System.currentTimeMillis();
		try
		{
			//SimpleDateFormat date_formet=new SimpleDateFormat("yyyy-MM-dd");

			SimpleDateFormat date_formet=new SimpleDateFormat("dd/MM/yyyy");

			Date date_data=date_formet.parse(date_formated);
			time_show=date_data.getTime();

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return String.valueOf(time_show);

	}

	public static String DateToMillisecondNew(String date_formated)
	{
		long time_show=System.currentTimeMillis();
		long time = 0;
		try
		{
			//		SimpleDateFormat date_formet=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");

			SimpleDateFormat date_formet=new SimpleDateFormat("dd/MM/yyyy");

			date_formet.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

			Date date_data=date_formet.parse(date_formated);
			time_show=date_data.getTime();

			time = time_show/1000;

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return String.valueOf(time);

	}

	public static String DateToMillisecondYYYYMMDD(String date_formated)
	{
		long time_show=System.currentTimeMillis();
		long time = 0;
		try
		{
			//		SimpleDateFormat date_formet=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");

			SimpleDateFormat date_formet=new SimpleDateFormat("yyyy/MM/dd");

			date_formet.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

			Date date_data=date_formet.parse(date_formated);
			time_show=date_data.getTime();

			time = time_show/1000;

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return String.valueOf(time);

	}



	public static String NewDateToMillisecond(String date_formated)
	{
		long time_show=System.currentTimeMillis();
		try
		{
			SimpleDateFormat date_formet=new SimpleDateFormat("dd/MM/yyyy");

			Date date_data=date_formet.parse(date_formated);
			time_show=date_data.getTime();

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return String.valueOf(time_show);

	}



	public static String MillisecondToDate(String date_format,String millisecond )
	{
		/*String formated_date="";

		SimpleDateFormat sdf=new SimpleDateFormat(date_format);

		Calendar calender=Calendar.getInstance();
		calender.setTimeInMillis(Long.valueOf(millisecond));

		formated_date=sdf.format(calender.getTime());


		return formated_date;*/

		String formated_date="";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);

		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		formated_date=sdf.format(Long.valueOf(millisecond));

		//		//System.out.println(formated_date);

		return formated_date;


	}


	public static String MillisecondToDateNew(String date_format,String millisecond )
	{

		String formated_date="";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);

		//Here you say to java the initial timezone. This is the secret
		sdf.setTimeZone(TimeZone.getTimeZone("UTC+05:30"));
		//Will print in UTC
		//System.out.println(sdf.format(calendar.getTime()));    

		//Here you set to your timezone
		sdf.setTimeZone(TimeZone.getDefault());
		//Will print on your default Timezone

		formated_date=sdf.format(calendar.getTime());

		//System.out.println(formated_date);

		return formated_date;
	}


	public static String MillisecondToDateNew(String millisecond )
	{
		String formated_date="";

		String date_format = "dd/MM/yyyy";
		//		String date_format = "yyyy-MM-dd";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);

		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		formated_date=sdf.format(Long.valueOf(millisecond)*1000);

		//		//System.out.println(formated_date);

		return formated_date;
	}
	
	public static String MillisecondToDateNewFathersDay(String millisecond )
	{
		String formated_date="";

		String date_format = "dd/MM/yyyy";
		//		String date_format = "yyyy-MM-dd";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);

		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		formated_date=sdf.format(Long.valueOf(millisecond));

		//		//System.out.println(formated_date);

		return formated_date;
	}

	public static String add_day(String day_no,String date )
	{


		String new_date="";

		int day = Integer.parseInt(day_no);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try
		{
			Date date1 = sdf.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(date1); // Now use today date.
			c.add(Calendar.DATE, day);
			new_date = sdf.format(c.getTime());
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}

		return new_date;
	}
	
	public static String sub_day(String day_no,String date )
	{


		String new_date="";

		int day = Integer.parseInt(day_no);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try
		{
			Date date1 = sdf.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(date1); // Now use today date.
			c.add(Calendar.DATE, -day);
			new_date = sdf.format(c.getTime());
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}

		return new_date;
	}


	public static String add_hours(String hours,String time )
	{


		String new_time="";

		int day = Integer.parseInt(hours);

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

		try
		{
			Date date1 = sdf.parse(time);
			Calendar c = Calendar.getInstance();
			c.setTime(date1); // Now use today date.
			c.add(Calendar.HOUR_OF_DAY, day);
			new_time = sdf.format(c.getTime());
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}

		return new_time;
	}



	public static String getParamValue(HttpServletRequest request,String TAG)
	{
		String tag_value = "";
		try
		{
			tag_value = request.getParameter(TAG);
			return tag_value;
		} 
		catch (Exception e)
		{
			return "";
		}

	}

	public static String getResultOld(ResultSet result_set,String TAG)
	{
		try
		{
			return result_set.getString(TAG);
		}
		catch (Exception e)
		{
			return "";
		}

	}

	public static String MillisecondToDateNew1(String millisecond )
	{

		String formated_date="";

		String date_format = "dd/MM/yyyy HH:MM:SS:a";
		//		String date_format = "yyyy-MM-dd";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);

		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		formated_date=sdf.format(Long.valueOf(millisecond));

		//		//System.out.println(formated_date);

		return formated_date;
	}

	public static String MillisecondToDdMmYYYY(String millisecond )
	{
		String formated_date="";

		if(millisecond.length() !=0 || millisecond != null)
		{
			String date_format = "dd/MM/yyyy";

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat(date_format);

			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

			formated_date=sdf.format(Long.valueOf(millisecond));

			//                //System.out.println(formated_date);
		}

		return formated_date;
	}
	public static String getResult(ResultSet result_set,String TAG)
	{
		String value = "";
		try
		{
			value =  result_set.getString(TAG);

			if(value == null || value.equalsIgnoreCase("null"))
			{
				return "";
			}

		}
		catch (Exception e)
		{
			return "";
		}
		return value;
	}
	
public static final long getSecondSundayOfMay () {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.WEEK_OF_MONTH,2);
		System.out.println("mothersunday=="+cal.getTime());
		System.out.println("mothersundaytimestamp=="+cal.getTimeInMillis());
		return cal.getTimeInMillis();
	}
	
	public static final long getThirdSundayOfJune () {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.WEEK_OF_MONTH,3);
		System.out.println("fathersunday=="+cal.getTime());
		System.out.println("fathersundaytimestamp=="+cal.getTimeInMillis());
		return cal.getTimeInMillis();
		
	}
	
public static final long getFirstSundayOfAugust () {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.WEEK_OF_MONTH,1);
		System.out.println("freindshipday=="+cal.getTime());
		System.out.println("freindshipdaytimestamp=="+cal.getTimeInMillis());
		return cal.getTimeInMillis();
		
	}
}
