package com.nudge.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import com.nudge.bean.UploadProfileBean;
import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.DateEstimation;
import com.nudge.common.PropertiesReader;
import com.nudge.common.Util;
import com.nudge.model.UploadProfileModel;
import com.sun.mail.iap.Response;

/**
 * Servlet implementation class UploadProfileManual
 */
@WebServlet("/UploadProfileManual")
public class UploadProfileManual extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	HttpServletResponse response;
	JSONObject obj;
	JSONObject userobj;
	JSONArray events;

	String image="";
	String name="";
	String relationship="";
	String gender="";
	String dob="";
	String user_id="";
	String personna="";
	String event="";
	String date="";
	String email="";
	String isimage="";
	String last_name="";
	String notify="";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadProfileManual() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.response=response;

		try {

			obj=new JSONObject();
			events=new JSONArray();

			PropertiesReader pr = new PropertiesReader();
			Properties prop = new Properties();
			prop = pr.getPropertyFile();

			String UPLOAD_IMAGE_PATH = prop.getProperty("UPLOAD_IMAGE_PATH");
			String IMAGE_PATH = prop.getProperty("IMAGE_PATH");


			List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest((request));

			long seconds=System.currentTimeMillis()/1000;

			for(FileItem item: multiparts)
			{
				if(!item.isFormField())
				{
					if(item.getFieldName().equals("image"))
					{
						image = new File(item.getName()).getName();

						// System.out.println(image);

						if(image!="")
						{
							String file_name=seconds+"_"+image;

							//System.out.println(file_name);

							image = file_name;

							System.out.println(image);

							item.write(new File(UPLOAD_IMAGE_PATH, file_name));

						}
						else
						{
							image = "";
						}
					}


				}
				else
				{
					if(item.getFieldName().equals("user_id"))
					{
						user_id=item.getString();
					}
					else if(item.getFieldName().equals("name"))
					{
						name=item.getString();
					}
					else if(item.getFieldName().equals("last_name"))
					{
						last_name=item.getString();
					}
					else if(item.getFieldName().equals("relationship"))
					{
						relationship=item.getString();
					}
					else if(item.getFieldName().equals("gender"))
					{
						gender=item.getString();
					}
				/*	else if(item.getFieldName().equals("dob"))
					{
						dob=item.getString();
					}
					*/
					
					else if(item.getFieldName().equals("personna"))
					{
						personna=item.getString();
					}
					else if(item.getFieldName().equals("event"))
					{
						event=item.getString();
					}
					else if(item.getFieldName().equals("date"))
					{
						date=item.getString();
					}
					else if(item.getFieldName().equals("isimage"))
					{
						isimage=item.getString();
					}
				}
			}

			con = Config.getInstance().getConnection();

			if(user_id.length()!=0 && name.length()!=0 && relationship.length()!=0 && gender.length()!=0  &&  last_name.length()!=0)
			{
				userobj=new JSONObject() ;





				UploadProfileBean obj_bean =new UploadProfileBean();

				obj_bean.setUser_id(user_id);
				obj_bean.setName(name);
				obj_bean.setRelationship(relationship);
				obj_bean.setGender(gender);;
			//	obj_bean.setDob(dob);
				obj_bean.setImage(image);
				obj_bean.setType("manual");
				obj_bean.setFb_id("");
				obj_bean.setContact_no("");
				obj_bean.setIsimage(isimage);
				long seconds1=System.currentTimeMillis();
				obj_bean.setEntry_date(String.valueOf(seconds1));
				obj_bean.setLast_name(last_name);

				UploadProfileModel obj_model = new UploadProfileModel();
				boolean checkresponse=obj_model.checkUserId(con,user_id);

				if(checkresponse)
				{
					int add_response=obj_model.UploadProfileManual(con, obj_bean);


					if(add_response!=0)
					{
						userobj = new JSONObject();

						userobj.put("id",add_response);
/*
						//	String dobToTimestamp=Util.DateToMillisecondNew(dob);

						//timestamp to current year or 1+ year conversion
						long nudge_timestamp=DateEstimation.getFinalTimestamp(Long.parseLong(dob));   

						// To convert timestamp to date
						String timestampToDate= Util.MillisecondToDateNew(dob);

						// To convert timestamp to date
						String nudgeDateTimestampToDate= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));


						boolean nudge_response = obj_model.AddDobAsEvent(con, user_id,add_response,event,timestampToDate,dob,nudge_timestamp,nudgeDateTimestampToDate);
*/

						//christmas
						notify="1";
						String event_christmas="6";
						long christmas=1514187528;

						long nudge_timestamp_Chris=DateEstimation.getFinalTimestamp(christmas);   
						System.out.println("nudge_timestamp_Chris=="+nudge_timestamp_Chris);

						// To convert timestamp to date
						String timestampToDate_Chris= Util.MillisecondToDateNew(String.valueOf(christmas));
						System.out.println("timestampToDate_Chris=="+timestampToDate_Chris);

						// To convert timestamp to date
						String nudgeDateTimestampToDate_Chris= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp_Chris));
						System.out.println("nudgeDateTimestampToDate_Chris=="+nudgeDateTimestampToDate_Chris);

						int chris_response1 = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_Chris,christmas,nudge_timestamp_Chris,nudgeDateTimestampToDate_Chris,relationship,event_christmas,notify);

						if (chris_response1 != 0) {

							JSONObject eveobj = new JSONObject();

							eveobj.put("events", event_christmas);
							eveobj.put("event_date", nudge_timestamp_Chris);
							eveobj.put("notify", notify);
							eveobj.put("custom_name", "");
							eveobj.put("notify_days", "0");
							eveobj.put("budget", "");
							eveobj.put("repeat", "");
							eveobj.put("event_type", "1");
							eveobj.put("event_date_id", chris_response1);
							

							events.put(eveobj);

						}



						if(relationship.equalsIgnoreCase("20"))
						{

							//fathersday

							String event_fathersday="4";
							notify="1";
							long currenttimestamp=System.currentTimeMillis();
							//	String currentdate=Util.MillisecondToDateNewFathersDay(String.valueOf(currenttimestamp));
							//	String a=obj_model.convertToYYMMDD(con, currentdate);
							String year=obj_model.getYear(con,currenttimestamp);

							String updateddate=year +"/"+"06"+"/"+"01";
							System.out.println("updateddate==="+updateddate);

							int day=obj_model.getDay(con,year,updateddate);

							if(day == 1)
							{

								String updateddate1=year +"/"+"06"+"/"+"15";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate1);
								//String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate1,updatedatetimestamp,relationship,event_fathersday,notify);

								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();

									eveobj.put("events", event_fathersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);

									events.put(eveobj);


								}
							}

							if(day == 2)
							{

								String updateddate2=year +"/"+"06"+"/"+"21";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate2);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate2,updatedatetimestamp,relationship,event_fathersday,notify);

								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();

									eveobj.put("events", event_fathersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);

									events.put(eveobj);


								}
							}

							if(day == 3)
							{

								String updateddate3=year +"/"+"06"+"/"+"20";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate3);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate3,updatedatetimestamp,relationship,event_fathersday,notify);

								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();

									eveobj.put("events", event_fathersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);
								}
							}

							if(day == 4)
							{

								String updateddate4=year +"/"+"06"+"/"+"19";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate4);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate4,updatedatetimestamp,relationship,event_fathersday,notify);

								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();

									eveobj.put("events", event_fathersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);


								}
							}

							if(day == 5)
							{

								String updateddate5=year +"/"+"06"+"/"+"18";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate5);
								//		String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate5,updatedatetimestamp,relationship,event_fathersday,notify);

								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();

									eveobj.put("events", event_fathersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);


								}
							}
							if(day == 6)
							{

								String updateddate6=year +"/"+"06"+"/"+"17";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate6);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate6,updatedatetimestamp,relationship,event_fathersday,notify);

								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();

									eveobj.put("events", event_fathersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);

								}
							}
							if(day == 7)
							{

								String updateddate7=year +"/"+"06"+"/"+"16";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate7);
								//String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate7,updatedatetimestamp,relationship,event_fathersday,notify);

								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();
									eveobj.put("events", event_fathersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);

								}
							}

							/*	long fathersdate=Util.getThirdSundayOfJune();
							System.out.println("fathersdate=="+fathersdate);

							long nudge_timestamp_F=DateEstimation.getFinalTimestampforFathersDay(fathersdate);   
                            System.out.println("nudge_timestamp_M=="+nudge_timestamp_F);

							// To convert timestamp to date
							String timestampToDate_F= Util.MillisecondToDateNewFathersDay(String.valueOf(fathersdate));
							System.out.println("timestampToDate_M=="+timestampToDate_F);

							// To convert timestamp to date
							String nudgeDateTimestampToDate_F= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
                            System.out.println("nudgeDateTimestampToDate_M=="+nudgeDateTimestampToDate_F);

							boolean event_response = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_F,fathersdate,nudge_timestamp_F,nudgeDateTimestampToDate_F,relationship);
							 */
						}

						if(relationship.equalsIgnoreCase("19"))
						{ 
							//mothersday
							String event_mothersday="3";
							notify="1";
							long currenttimestamp=System.currentTimeMillis();
							//	String currentdate=Util.MillisecondToDateNewFathersDay(String.valueOf(currenttimestamp));
							String year=obj_model.getYear(con,currenttimestamp);

							String updateddate=year +"/"+"05"+"/"+"01";
							System.out.println("updateddate==="+updateddate);

							int day=obj_model.getDay(con,year,updateddate);

							if(day == 1)
							{
								String updateddate1=year +"/"+"05"+"/"+"08";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate1);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate1,updatedatetimestamp,relationship,event_mothersday,notify);

								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();
									eveobj.put("events", event_mothersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);


								}
							}

							if(day == 2)
							{
								String updateddate2=year +"/"+"05"+"/"+"14";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate2);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate2,updatedatetimestamp,relationship,event_mothersday,notify);


								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();
									eveobj.put("events", event_mothersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);

								}
							}

							if(day == 3)
							{
								String updateddate3=year +"/"+"05"+"/"+"13";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate3);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate3,updatedatetimestamp,relationship,event_mothersday,notify);


								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();
									eveobj.put("events", event_mothersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);

								}
							}

							if(day == 4)
							{
								String updateddate4=year +"/"+"05"+"/"+"12";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate4);
								//String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate4,updatedatetimestamp,relationship,event_mothersday,notify);


								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();

									eveobj.put("events", event_mothersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);


								}
							}

							if(day == 5)
							{
								String updateddate5=year +"/"+"05"+"/"+"11";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate5);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate5,updatedatetimestamp,relationship,event_mothersday,notify);


								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();

									eveobj.put("events", event_mothersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);


								}
							}
							if(day == 6)
							{
								String updateddate6=year +"/"+"05"+"/"+"10";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate6);
								//String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate6,updatedatetimestamp,relationship,event_mothersday,notify);


								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();
									eveobj.put("events", event_mothersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);

								}
							}
							if(day == 7)
							{
								String updateddate7=year +"/"+"05"+"/"+"09";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate7);
								//String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate7,updatedatetimestamp,relationship,event_mothersday,notify);


								if(event_response!=0)
								{
									JSONObject eveobj= new JSONObject();
									eveobj.put("events", event_mothersday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");
									eveobj.put("notify_days", "0");
									eveobj.put("budget", "");
									eveobj.put("repeat", "");
									eveobj.put("event_type", "0");
									eveobj.put("event_date_id", event_response);
									
									events.put(eveobj);


								}
							}


							/*long mothersdate=Util.getSecondSundayOfMay();
							System.out.println("fathersdate=="+mothersdate);

							long nudge_timestamp_M=DateEstimation.getFinalTimestampforFathersDay(mothersdate);   
                            System.out.println("nudge_timestamp_M=="+nudge_timestamp_M);

							// To convert timestamp to date
							String timestampToDate_M= Util.MillisecondToDateNewFathersDay(String.valueOf(mothersdate));
							System.out.println("timestampToDate_M=="+timestampToDate_M);

							// To convert timestamp to date
							String nudgeDateTimestampToDate_M= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
                            System.out.println("nudgeDateTimestampToDate_M=="+nudgeDateTimestampToDate_M);

							boolean event_response = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_M,mothersdate,nudge_timestamp_M,nudgeDateTimestampToDate_M,relationship);
							 */
						}

						if(relationship.equalsIgnoreCase("101") || relationship.equalsIgnoreCase("102") || relationship.equalsIgnoreCase("141") || relationship.equalsIgnoreCase("139") || relationship.equalsIgnoreCase("135") || relationship.equalsIgnoreCase("134") || relationship.equalsIgnoreCase("37") || relationship.equalsIgnoreCase("38") || relationship.equalsIgnoreCase("97") || relationship.equalsIgnoreCase("98") || relationship.equalsIgnoreCase("143"))
						{


							//halloween
							String event_halloween="9";
							notify="1";
							long halloween=1509435528;

							long nudge_timestamp_M=DateEstimation.getFinalTimestamp(halloween);   
							System.out.println("nudge_timestamp_M=="+nudge_timestamp_M);

							// To convert timestamp to date
							String timestampToDate_M= Util.MillisecondToDateNew((String.valueOf(halloween)));
							System.out.println("timestampToDate_M=="+timestampToDate_M);

							// To convert timestamp to date
							String nudgeDateTimestampToDate_M= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp_M));
							System.out.println("nudgeDateTimestampToDate_M=="+nudgeDateTimestampToDate_M);

							int event_response = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_M,halloween,nudge_timestamp_M,nudgeDateTimestampToDate_M,relationship,event_halloween,notify);


							if (event_response != 0) {
								JSONObject eveobj= new JSONObject();

								eveobj.put("events", event_halloween);
								eveobj.put("event_date", nudge_timestamp_M);
								eveobj.put("notify", notify);
								eveobj.put("custom_name", "");
								eveobj.put("notify_days", "0");
								eveobj.put("budget", "");
								eveobj.put("repeat", "");
								eveobj.put("event_type", "1");
								eveobj.put("event_date_id", event_response);
								
								events.put(eveobj);

							}

							/*//christmas
							String event_christmas="6";
							long christmas=1514187528;

							long nudge_timestamp_B=DateEstimation.getFinalTimestamp(christmas);   
							System.out.println("nudge_timestamp_B=="+nudge_timestamp_B);

							// To convert timestamp to date
							String timestampToDate_B= Util.MillisecondToDateNew(String.valueOf(christmas));
							System.out.println("timestampToDate_B=="+timestampToDate_B);

							// To convert timestamp to date
							String nudgeDateTimestampToDate_B= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
							System.out.println("nudgeDateTimestampToDate_B=="+nudgeDateTimestampToDate_B);

							int event_response1 = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_B,christmas,nudge_timestamp_B,nudgeDateTimestampToDate_B,relationship,event_christmas,notify);

							if(event_response1!=0)
							{
								JSONObject eveobj= new JSONObject();


								eveobj.put("events", event_christmas);
								eveobj.put("event_date", nudge_timestamp_B);
								eveobj.put("notify", notify);
								eveobj.put("custom_name", "");


								events.put(eveobj);
							}*/

						}

						/*if(relationship.equalsIgnoreCase("5") )
						{

							//friendshipday
							String event_friendshipday="7";
							long currenttimestamp=System.currentTimeMillis();
							//	String currentdate=Util.MillisecondToDateNewFathersDay(String.valueOf(currenttimestamp));
							String year=obj_model.getYear(con,currenttimestamp);

							String updateddate=year +"/"+"08"+"/"+"01";
							System.out.println("updateddate==="+updateddate);

							int day=obj_model.getDay(con,year,updateddate);

							if(day == 1)
							{
								//String updateddate1=year +"-"+"05"+"-"+"08";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response1 = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate,updatedatetimestamp,relationship,event_friendshipday,notify);

								if (event_response1 != 0) {
									JSONObject eveobj = new JSONObject();

									eveobj.put("events", event_friendshipday);
									eveobj.put("event_date",updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");

									events.put(eveobj);
								}
							}

							if(day == 2)
							{
								String updateddate2=year +"/"+"08"+"/"+"07";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate2);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response1 = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate2,updatedatetimestamp,relationship,event_friendshipday,notify);

								if(event_response1!=0)
								{	
									JSONObject eveobj=new JSONObject();


									eveobj.put("events", event_friendshipday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");


									events.put(eveobj);
								}
							}

							if(day == 3)
							{
								String updateddate3=year +"/"+"08"+"/"+"06";
								System.out.println("dateaug==="+updateddate3);

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate3);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response1 = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate3,updatedatetimestamp,relationship,event_friendshipday,notify);

								if(event_response1!=0)
								{    
									JSONObject eveobj=new JSONObject();

									eveobj.put("events", event_friendshipday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");

									events.put(eveobj);

								}
							}

							if(day == 4)
							{
								String updateddate4=year +"/"+"08"+"/"+"05";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate4);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response1 = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate4,updatedatetimestamp,relationship,event_friendshipday,notify);

								if(event_response1!=0)
								{

									JSONObject eveobj=new JSONObject();

									eveobj.put("events", event_friendshipday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");

									events.put(eveobj);
								}
							}

							if(day == 5)
							{
								String updateddate5=year +"/"+"08"+"/"+"04";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate5);
								//		String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response1 = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate5,updatedatetimestamp,relationship,event_friendshipday,notify);

								if (event_response1 != 0) {
									JSONObject eveobj = new JSONObject();

									eveobj.put("events", event_friendshipday);
									eveobj.put("event_date",updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");

									events.put(eveobj);

								}
							}
							if(day == 6)
							{
								String updateddate6=year +"/"+"08"+"/"+"03";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate6);
								//	String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response1 = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate6,updatedatetimestamp,relationship,event_friendshipday,notify);

								if (event_response1 != 0) {

									JSONObject eveobj = new JSONObject();

									eveobj.put("events", event_friendshipday);
									eveobj.put("event_date",updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");

									events.put(eveobj);
								}
							}
							if(day == 7)
							{
								String updateddate7=year +"/"+"08"+"/"+"02";

								String updatedatetimestamp=Util.DateToMillisecondYYYYMMDD(updateddate7);
								//String timestampToDate_F= Util.MillisecondToDateNewFathersDay(updatedatetimestamp);

								int event_response1 = obj_model.AddRelationAsEventNew(con, user_id,add_response,updateddate7,updatedatetimestamp,relationship,event_friendshipday,notify);

								if(event_response1!=0)
								{  
									JSONObject eveobj=new JSONObject();

									eveobj.put("events", event_friendshipday);
									eveobj.put("event_date", updatedatetimestamp);
									eveobj.put("notify", notify);
									eveobj.put("custom_name", "");


									events.put(eveobj);


								}
							}


							long freindshipday=Util.getFirstSundayOfAugust();
							System.out.println("freindshipday=="+freindshipday);

							long nudge_timestamp_F=DateEstimation.getFinalTimestampforFathersDay(freindshipday);   
                            System.out.println("nudge_timestamp_F=="+nudge_timestamp_F);

							// To convert timestamp to date
							String timestampToDate_F= Util.MillisecondToDateNewFathersDay(String.valueOf(freindshipday));
							System.out.println("timestampToDate_F=="+timestampToDate_F);

							// To convert timestamp to date
							String nudgeDateTimestampToDate_F= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
                            System.out.println("nudgeDateTimestampToDate_F=="+nudgeDateTimestampToDate_F);

							boolean event_response1 = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_F,freindshipday,nudge_timestamp_F,nudgeDateTimestampToDate_F,relationship);


						}*/
						/*if(relationship.equalsIgnoreCase("13") || relationship.equalsIgnoreCase("14") )
						{
							//christmas
							String notify="1";
							String event_christmas="6";
							long christmas=1514187528;

							long nudge_timestamp_Chris=DateEstimation.getFinalTimestamp(christmas);   
							System.out.println("nudge_timestamp_Chris=="+nudge_timestamp_Chris);

							// To convert timestamp to date
							String timestampToDate_Chris= Util.MillisecondToDateNew(String.valueOf(christmas));
							System.out.println("timestampToDate_Chris=="+timestampToDate_Chris);

							// To convert timestamp to date
							String nudgeDateTimestampToDate_Chris= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
							System.out.println("nudgeDateTimestampToDate_Chris=="+nudgeDateTimestampToDate_Chris);

							int event_response1 = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_Chris,christmas,nudge_timestamp_Chris,nudgeDateTimestampToDate_Chris,relationship,event_christmas,notify);

							if (event_response1 != 0) {

								JSONObject eveobj = new JSONObject();

								eveobj.put("events", event_christmas);
								eveobj.put("event_date", nudge_timestamp_Chris);
								eveobj.put("notify", notify);
								eveobj.put("custom_name", "");

								events.put(eveobj);

							}

						}*/
						if(relationship.equalsIgnoreCase("106") || relationship.equalsIgnoreCase("107") || relationship.equalsIgnoreCase("132") || relationship.equalsIgnoreCase("133") )
						{
							//valentines
							notify="1";
							String event_valentines="8";
							long valentines=1487058334;

							long nudge_timestamp_Val=DateEstimation.getFinalTimestamp(valentines);   
							System.out.println("nudge_timestamp_Val=="+nudge_timestamp_Val);

							// To convert timestamp to date
							String timestampToDate_Val= Util.MillisecondToDateNew(String.valueOf(valentines));
							System.out.println("timestampToDate_Val=="+timestampToDate_Val);

							// To convert timestamp to date
							String nudgeDateTimestampToDate_Val= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp_Val));
							System.out.println("nudgeDateTimestampToDate_Val=="+nudgeDateTimestampToDate_Val);

							int event_response = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_Val,valentines,nudge_timestamp_Val,nudgeDateTimestampToDate_Val,relationship,event_valentines,notify);

							if (event_response != 0) {
								JSONObject eveobj = new JSONObject();

								eveobj.put("events", event_valentines);
								eveobj.put("event_date", nudge_timestamp_Val);
								eveobj.put("notify", notify);
								eveobj.put("custom_name", "");
								eveobj.put("notify_days", "0");
								eveobj.put("budget", "");
								eveobj.put("repeat", "");
								eveobj.put("event_type", "1");
								eveobj.put("event_date_id", event_response);
								
								events.put(eveobj);

							}
						}

						if(relationship.equalsIgnoreCase("106") || relationship.equalsIgnoreCase("107"))
						{
							//wedding anniversary

							String notify1="0";
							String event_anniversary="2";
							long anniversary=0;
							String timestampToDate_Ann="";
							long nudge_timestamp_Ann=0;
							String nudgeDateTimestampToDate_Ann="";

							int event_response1 = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_Ann,anniversary,nudge_timestamp_Ann,nudgeDateTimestampToDate_Ann,relationship,event_anniversary,notify1);

							if(event_response1!=0)
							{
								JSONObject eveobj=new JSONObject();

								eveobj.put("events", event_anniversary);
								eveobj.put("event_date", "");
								eveobj.put("notify", notify1);
								eveobj.put("custom_name", "");
								eveobj.put("notify_days", "0");
								eveobj.put("budget", "");
								eveobj.put("repeat", "");
								eveobj.put("event_type", "0");
								eveobj.put("event_date_id", event_response1);
								
								events.put(eveobj);

							}
						}
						
						if(relationship.equalsIgnoreCase("132") || relationship.equalsIgnoreCase("133"))
						{
							//relationship anniversary

							String notify1="0";
							String event_anniversary="11";
							long anniversary=0;
							String timestampToDate_Ann="";
							long nudge_timestamp_Ann=0;
							String nudgeDateTimestampToDate_Ann="";

							int event_response1 = obj_model.AddRelationAsEvent(con, user_id,add_response,timestampToDate_Ann,anniversary,nudge_timestamp_Ann,nudgeDateTimestampToDate_Ann,relationship,event_anniversary,notify1);

							if(event_response1!=0)
							{
								JSONObject eveobj=new JSONObject();

								eveobj.put("events", event_anniversary);
								eveobj.put("event_date", "");
								eveobj.put("notify", notify1);
								eveobj.put("custom_name", "");
								eveobj.put("notify_days", "0");
								eveobj.put("budget", "");
								eveobj.put("repeat", "");
								eveobj.put("event_type", "0");
								eveobj.put("event_date_id", event_response1);
								
								events.put(eveobj);

							}
						}

						userobj.put("event", events);
						//	System.out.println(events.toString()); 

						printOutput(Constant.StatusSuccess, Constant.MessagePUPLOAD,userobj);
					}
					else
					{
						printOutput(Constant.StatusFail, Constant.MessagePNOTUPLOAD,userobj);
					}
				}
				else{

					printOutput(Constant.StatusFail, Constant.MessageUserIdExist,userobj);
				}
			}
			else
			{  
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS,userobj);
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			printOutput(Constant.StatusFail, Constant.FAIL_RESPONSE,userobj);
		}
		finally
		{
			Config.DB_colse();
		}
	}

	public void printOutput(String status,String message,JSONObject userobj)
	{
		try {


			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);
			obj.put(Constant.USERDETAILS,userobj);

			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");


		} 

		catch (Exception e) {
			// TODO: handle exception
		}



	}

}
