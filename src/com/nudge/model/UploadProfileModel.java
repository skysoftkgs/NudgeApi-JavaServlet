package com.nudge.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.Bean1;
import org.json.JSONArray;
import org.json.JSONObject;

import com.nudge.bean.UploadProfileBean;
import com.nudge.bean.facebook_profile_bean;
import com.nudge.bean.registration_bean;
import com.nudge.common.AESHelper;
import com.nudge.common.DateEstimation;
import com.nudge.common.Util;

public class UploadProfileModel {


	public UploadProfileModel()
	{

	}

	public int UploadProfileManual(Connection con, UploadProfileBean obj_bean)
	{
		int response=0;

		try {

			String add_qry = "INSERT INTO upload_profile(`name`,`relationship`,`gender`,";
			if(obj_bean.getIsimage().equalsIgnoreCase("yes"))
			{
				add_qry+= " `image`, ";

			}
			add_qry+= "`entry_date`,`type`,`user_id`,`fb_id`,`contact_no`,`last_name`) ";
			add_qry+= "  VALUES('"+obj_bean.getName()+"','"+obj_bean.getRelationship()+"','"+obj_bean.getGender()+"', ";

			if(obj_bean.getIsimage().equalsIgnoreCase("yes"))
			{
				add_qry+= " '"+obj_bean.getImage()+"', ";

			}
			add_qry+= "'"+obj_bean.getEntry_date()+"','"+obj_bean.getType()+"',";
			add_qry+= "'"+obj_bean.getUser_id()+"','"+obj_bean.getFb_id()+"',";
			add_qry+= "'"+obj_bean.getContact_no()+"','"+obj_bean.getLast_name()+"')";

			//		System.out.println("manual---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				//response=true;

				if(rs.next())
				{
					response= rs.getInt(1);
				}
			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}



	public boolean AddDobAsEvent(Connection con, String user_id,int add_response,String event,String timestampToDate,String dob,long nudge_timestamp,String nudgeDateTimestampToDate )
	{
		boolean response=false;

		try {

			String add_qry = "INSERT INTO event_date(`friend_id`,`event`,`date`,`user_id`,`timestamp`,`nudge_timestamp`,`nudge_date`) VALUES('"+add_response+"','1','"+timestampToDate+"','"+user_id+"','"+dob+"','"+nudge_timestamp+"','"+nudgeDateTimestampToDate+"')";

			//		System.out.println("dob As Event---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);
			//ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				response=true;


			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean AddDobAsEventInEdit(Connection con, String user_id,String friend_id,String event,String timestampToDate,String dob,long nudge_timestamp,String nudgeDateTimestampToDate )
	{
		boolean response=false;

		try {

			String add_qry = "INSERT INTO event_date(`friend_id`,`event`,`date`,`user_id`,`timestamp`,`nudge_timestamp`,`nudge_date`) VALUES('"+friend_id+"','1','"+timestampToDate+"','"+user_id+"','"+dob+"','"+nudge_timestamp+"','"+nudgeDateTimestampToDate+"')";

			//	System.out.println("dob As Event---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);
			//ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				response=true;


			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public int AddRelationAsEvent(Connection con, String user_id,int add_response,String timestampToDate,long fathersdate,long nudge_timestamp,String nudgeDateTimestampToDate,String relationship,String event,String notify)
	{
		int response=0;

		try {

			String add_qry = "INSERT INTO event_date(`friend_id`,`date`,`user_id`,`timestamp`,`nudge_timestamp`,`nudge_date`,`event`,`notify`) VALUES('"+add_response+"','"+timestampToDate+"','"+user_id+"','"+fathersdate+"','"+nudge_timestamp+"','"+nudgeDateTimestampToDate+"','"+event+"','"+notify+"')";


			//	System.out.println("relation As Event---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				//response=true;
				if(rs.next())
				{
					response= rs.getInt(1);
				}


			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public int AddRelationAsEventNew(Connection con, String user_id,int add_response,String timestampToDate,String fathersdate,String relationship,String event,String notify )
	{
		int response = 0;

		try {

			String add_qry = "INSERT INTO event_date(`friend_id`,`date`,`user_id`,`timestamp`,`event`,`notify`,`nudge_timestamp`) VALUES('"+add_response+"','"+timestampToDate+"','"+user_id+"','"+fathersdate+"','"+event+"','"+notify+"','"+fathersdate+"')";


			//		System.out.println("relation As Event---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				//response=true;
				if(rs.next())
				{
					response= rs.getInt(1);
				}

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean UpdateRelationAsEventNew(Connection con, String user_id,String add_response,String timestampToDate,String fathersdate,String relationship ,String event,String notify)
	{
		boolean response=false;

		try {

			String add_qry = "UPDATE event_date set `date`='"+timestampToDate+"',`timestamp`='"+fathersdate+"',`notify`='"+notify+"',`nudge_timestamp`='"+fathersdate+"' WHERE user_id='"+user_id+"' AND friend_id='"+add_response+"' AND event='"+event+"'";

			//		System.out.println("relation As Event---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				response=true;



			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}



	public boolean UpdateRelationAsEvent(Connection con, String user_id,String add_response,String timestampToDate,long fathersdate,long nudge_timestamp,String nudgeDateTimestampToDate,String relationship,String event,String notify)
	{
		boolean response=false;

		try {

			String add_qry = "UPDATE event_date set `date`='"+timestampToDate+"',`timestamp`='"+fathersdate+"',`nudge_timestamp`='"+nudge_timestamp+"',`nudge_date`='"+nudgeDateTimestampToDate+"',`notify`='"+notify+"' WHERE user_id='"+user_id+"' AND friend_id='"+add_response+"' AND event='"+event+"'";


			//		System.out.println("relation As Event---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				response=true;



			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean UpdateProfileManual(Connection con, UploadProfileBean obj_bean)
	{
		boolean response=false;

		try {

			String add_qry = "UPDATE upload_profile SET `name`='" + obj_bean.getName()
			+ "',`relationship`='" + obj_bean.getRelationship()
			+ "',`gender`='" + obj_bean.getGender()
			+ "',`entry_date`='"
			+ obj_bean.getEntry_date() + "' ,`type`='"
			+ obj_bean.getType() + "' ,`fb_id`='"+obj_bean.getFb_id()+"',`contact_no`='"+obj_bean.getContact_no()+"',`last_name`='"+obj_bean.getLast_name()+"'  ";

			if(obj_bean.getIsimage().equalsIgnoreCase("yes"))
			{
				add_qry = add_qry+" ,`image`='"+ obj_bean.getImage() + "'";
			}

			add_qry = add_qry+" WHERE `user_id`='"+obj_bean.getUser_id()+"' AND `id`='"+obj_bean.getFriend_id()+"'";
			//	System.out.println("manual---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);
			//	ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				response=true;


			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean UpdateDobAsEvent(Connection con, String user_id,String friend_id,String event,String timestampToDate,String dob,long nudge_timestamp,String nudgeDateTimestampToDate)
	{
		boolean response=false;

		try {

			String add_qry = "UPDATE event_date SET `date`='"+timestampToDate+"',`timestamp`='"+dob+"',`nudge_timestamp`='"+nudge_timestamp+"',`nudge_date`='"+nudgeDateTimestampToDate+"' WHERE user_id='"+user_id+"' AND friend_id='"+friend_id+"' AND event='1' ";


			//	System.out.println("upadte DOBEvent---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);
			//	ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				response=true;


			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean UpdateDobAsEventInUpload(Connection con, String user_id,int friend_id,String event,String timestampToDate,String dob,long nudge_timestamp,String nudgeDateTimestampToDate)
	{
		boolean response=false;

		try {

			String add_qry = "UPDATE event_date SET `date`='"+timestampToDate+"',`timestamp`='"+dob+"',`nudge_timestamp`='"+nudge_timestamp+"',`nudge_date`='"+nudgeDateTimestampToDate+"' WHERE user_id='"+user_id+"' AND friend_id='"+friend_id+"' AND event='1' ";


			//		System.out.println("upadte DOBEvent---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);
			//	ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				response=true;


			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean CheckDobAsEvent(Connection con, String user_id,String friend_id)
	{
		boolean response=false;

		try {

			String add_qry = "Select * from event_date WHERE user_id='"+user_id+"' AND friend_id='"+friend_id+"' AND event='1' ";


			//		System.out.println("check DOBEvent---------------->"+add_qry);

			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				response = true;

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}


	public int UploadProfileMultiple(Connection con, UploadProfileBean obj_bean)
	{
		int response = 0;

		try {

			String add_qry = "INSERT INTO upload_profile(`user_id`,`type`,`name`,`relationship`,`gender`,`dob`,`image`,`entry_date`,`contact_no`,`fb_id`,`budget`,`location`,`last_name`) VALUES('"+obj_bean.getUser_id()+"','"+obj_bean.getType()+"','"+obj_bean.getName()+"','"+obj_bean.getRelationship()+"','"+obj_bean.getGender()+"','"+obj_bean.getDob()+"','"+obj_bean.getImage()+"','"+obj_bean.getEntry_date()+"','"+obj_bean.getContact_no()+"','"+obj_bean.getFb_id()+"','"+obj_bean.getBudget()+"','"+obj_bean.getLocation()+"','"+obj_bean.getLast_name()+"')";

			//		System.out.println(" qry---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				if(rs.next())
				{
					//response=true;

					response= rs.getInt(1);


				}
			}

		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	/*public int UpdateProfileMultiple(Connection con, UploadProfileBean obj_bean)
	{
		int response = 0;

		try {


			String add_qry="UPDATE upload_profile SET `user_id`='"+obj_bean.getUser_id()+"',`type`='"+obj_bean.getType()+"',`name`='"+obj_bean.getName()+"',"
					+ "`relationship`='"+obj_bean.getRelationship()+"',`gender`='"+obj_bean.getGender()+"',`dob`='"+obj_bean.getDob()+"',"
							+ "`image`='"+obj_bean.getImage()+"',`entry_date`='"+obj_bean.getEntry_date()+"',`contact_no`='"+obj_bean.getContact_no()+"',`fb_id`='"+obj_bean.getFb_id()+"',"
									+ "`budget`='"+obj_bean.getBudget()+"',`location`='"+obj_bean.getLocation()+"' WHERE `user_id`='"+obj_bean.getUser_id()+"' AND ";

			if(obj_bean.getType().equalsIgnoreCase("fb"))
			{
				add_qry+=" `fb_id`='"+obj_bean.getFb_id()+"'";
			}
			else if(obj_bean.getType().equalsIgnoreCase("contact_no"))
			{
				add_qry+=" `contact_no`='"+obj_bean.getContact_no()+"'";
			}

			System.out.println(" update_qry---------------->"+add_qry);


			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);


				if(result.next())
				{
				//response=true;

			//		response= rs.getInt(1);

             int id=result.getInt("id");
             System.out.println("id============="+id);
			}	



		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}*/

	public int UpdateProfileMultiple(Connection con, UploadProfileBean obj_bean)
	{
		int response = 0;

		try {


			String add_qry="UPDATE upload_profile SET `user_id`='"+obj_bean.getUser_id()+"',`type`='"+obj_bean.getType()+"',`name`='"+obj_bean.getName()+"',"
					+ "`relationship`='"+obj_bean.getRelationship()+"',`gender`='"+obj_bean.getGender()+"',`dob`='"+obj_bean.getDob()+"',"
					+ "`image`='"+obj_bean.getImage()+"',`entry_date`='"+obj_bean.getEntry_date()+"',`contact_no`='"+obj_bean.getContact_no()+"',`fb_id`='"+obj_bean.getFb_id()+"',"
					+ "`budget`='"+obj_bean.getBudget()+"',`location`='"+obj_bean.getLocation()+"',`last_name`='"+obj_bean.getLast_name()+"' WHERE `user_id`='"+obj_bean.getUser_id()+"' AND ";

			if(obj_bean.getType().equalsIgnoreCase("fb"))
			{
				add_qry+=" `fb_id`='"+obj_bean.getFb_id()+"'";
			}
			else if(obj_bean.getType().equalsIgnoreCase("contact_no"))
			{
				add_qry+=" `contact_no`='"+obj_bean.getContact_no()+"'";
			}

			//		System.out.println(" update_qry---------------->"+add_qry);


			Statement stm = con.createStatement();
			int i = stm.executeUpdate(add_qry);


			if(i>0)
			{

				response = getId(con,obj_bean);
				return response;
			}	



		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}


	public int getId(Connection con, UploadProfileBean obj_bean)
	{
		int id=0;

		try 
		{
			String add_qry = "Select * from upload_profile where user_id='"+obj_bean.getUser_id()+"' and";

			if(obj_bean.getType().equalsIgnoreCase("fb"))
			{
				add_qry+=" `fb_id`='"+obj_bean.getFb_id()+"'";
			}
			else if(obj_bean.getType().equalsIgnoreCase("contact_no"))
			{
				add_qry+=" `contact_no`='"+obj_bean.getContact_no()+"'";
			}

			//		System.out.println("getId==="+add_qry);


			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{


				id=result.getInt("id");

				return id;


			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return id;
		}

		return id;
	}


	public boolean CheckFbId(Connection con,String user_id,String fb_id)
	{
		boolean response = false;
		try 
		{
			String add_qry = "Select * from upload_profile WHERE user_id='"+user_id+"' AND fb_id='"+fb_id+"' ";
			//		System.out.println("fb_id=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			if(result.next())
			{
				response = true;

			}
			else
			{
				response = false;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return response;
		}

		return response;
	}	

	public boolean CheckContactNo(Connection con,String user_id,String contact_no)
	{
		boolean response = false;
		try 
		{
			String add_qry = "Select * from upload_profile WHERE user_id='"+user_id+"' AND contact_no='"+contact_no+"' ";
			//		System.out.println("contact=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				response = true;

			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return response;
		}

		return response;
	}	

	/*public JSONObject GetRelation(Connection con)
	{
		JSONObject jobj = new JSONObject();
		List<jobj> list =new ArrayList<jobj>();

		try 
		{
			String add_qry = "Select * from relationship ";//AND email_ver_status='1'
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj = new JSONObject();

				jobj.put("id",result.getInt("id"));
				jobj.put("relation",result.getString("relation"));

				list.add(jobj);


			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jobj;
		}

		return jobj;
	}		*/

	public JSONArray GetRelation(Connection con)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "Select * from relationship  ";
			//System.out.println("Relation=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj = new JSONObject();

				jobj.put("rid",result.getString("id"));
				jobj.put("relation", Util.getResult(result, "relation"));
				jobj.put("fw", Util.getResult(result, "gender"));

				jarray.put(jobj);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}	

	public JSONArray GetCategory(Connection con)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "Select * from category ";
			//System.out.println("Category=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj = new JSONObject();

				jobj.put("pid",result.getString("id"));
				jobj.put("category", Util.getResult(result, "name"));
				jobj.put("image_unselected", Util.getResult(result, "image_unselected"));
				jobj.put("image_selected", Util.getResult(result, "image_selected"));

				jarray.put(jobj);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}

	public JSONArray GetProducts(Connection con)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "Select * from product  ";
			//System.out.println("Category=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj = new JSONObject();

				jobj.put("prod_id",result.getString("id"));
				jobj.put("name", Util.getResult(result, "name"));
				jobj.put("description", Util.getResult(result, "description"));
				jobj.put("price", Util.getResult(result, "price"));
				jobj.put("image", Util.getResult(result, "image"));

				jarray.put(jobj);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}

	public JSONArray GetEvents(Connection con)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "Select * from events  ";
			//System.out.println("Category=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj = new JSONObject();

				jobj.put("events",result.getString("id"));
				jobj.put("event_name", Util.getResult(result, "event_name"));
				jobj.put("event_type", Util.getResult(result, "event_type"));
				//	jobj.put("event_date", Util.getResult(result, "timestamp"));

				String event_date=Util.getResult(result, "date");
				String eventMillis = Util.DateToMillisecondNew(event_date);

				if(event_date.length()!=0 )
				{
					long nudge_timestamp=DateEstimation.getFinalTimestamp(Long.parseLong(eventMillis));   
					//System.out.println("nudge_timestamp=="+nudge_timestamp);

					// To convert timestamp to date
					String timestampToDate_M= Util.MillisecondToDateNew((String.valueOf(eventMillis)));
					//System.out.println("ExistingDate====="+timestampToDate_M);

					// To convert timestamp to date
					String nudgeDateTimestampToDate_M= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
					//System.out.println("nudgeDate=="+nudge_timestamp);

					jobj.put("event_date", String.valueOf(nudge_timestamp));
				}
				else
				{
					jobj.put("event_date", Util.getResult(result, ""));
				}








				/*	String event_date=Util.getResult(result, "date");

				if(event_date!="" || event_date.length()!=0)
				{
					String eventMillis = Util.DateToMillisecondNew(event_date);
					long currentMillis = System.currentTimeMillis();
					if(currentMillis > Long.valueOf(eventMillis)){
						Calendar c = Calendar.getInstance(); 
						//Set time in milliseconds
						c.setTimeInMillis(currentMillis);
						int mYear = c.get(Calendar.YEAR);
						String[] evearr = eventMillis.split("/");
						String newValue= evearr[0]+"/"+evearr[1]+"/"+mYear;

					jobj.put("event_date", newValue);

					}else{
						jobj.put("event_date", Util.getResult(result, "date"));
					}	
				}
				else
				{
					jobj.put("event_date", Util.getResult(result, ""));
				}
				 */


				jarray.put(jobj);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}





	public JSONArray GetBudget(Connection con)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "Select * from budget  ";
			//System.out.println("Category=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj = new JSONObject();

				jobj.put("budget_id",result.getString("id"));
				jobj.put("range", Util.getResult(result, "range"));
				jobj.put("currency",Util.getResult(result, "currency"));


				jarray.put(jobj);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}

	public JSONArray getAge(Connection con)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "Select * from age_group  ";
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj = new JSONObject();

				jobj.put("age_id",result.getString("id"));
				jobj.put("age_name", Util.getResult(result, "name"));
				jobj.put("age_range",Util.getResult(result, "range"));


				jarray.put(jobj);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}

	public JSONArray GetProfile(Connection con,String user_id)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "Select * from upload_profile WHERE user_id='"+user_id+"'";

			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj=new JSONObject();

				jobj.put("id",Util.getResult(result, "id"));
				jobj.put("name",Util.getResult(result, "name"));
				jobj.put("relationship",Util.getResult(result, "relationship"));
				jobj.put("dob",Util.getResult(result, "dob"));
				jobj.put("gender",Util.getResult(result, "gender"));
				jobj.put("budget",Util.getResult(result, "budget"));
				jobj.put("location",Util.getResult(result, "location"));
				jobj.put("image",Util.getResult(result, "image"));
				jobj.put("user_id",Util.getResult(result, "user_id"));
				jobj.put("contact_no",Util.getResult(result, "contact_no"));
				jobj.put("fb_id",Util.getResult(result, "fb_id"));
				jobj.put("type",Util.getResult(result, "type"));
				jobj.put("personna",Util.getResult(result, "personna"));

				String event = Util.getResult(result, "event");
				String date = Util.getResult(result, "date");

				JSONObject ejob = GetEventObj(event,date);

				jobj.put("event", ejob);

				//jobj.put("event",result.getString("event"));
				//jobj.put("date",result.getString("date"));

				jarray.put(jobj);

			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}

	private JSONObject GetEventObj(String event, String date)
	{
		JSONObject jobj = new JSONObject();


		try
		{
			if(event.length() !=0 && date.length() !=0)
			{
				String eve[] = event.split(",");
				String dat[] = date.split(",");

				for (int i = 0; i < dat.length; i++) 
				{

					jobj.put(eve[i], dat[i]);

				}
			}
			else
			{
				return jobj;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jobj;
		}


		return jobj;
	}

	/*	public JSONArray GetEvent(Connection con,String user_id)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "Select event,date from upload_profile where user_id='"+user_id+"' ";
			System.out.println("event=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj = new JSONObject();

				for (int i = 0; i < jobj.length(); i++) {

					//JSONObject event=(JSONObject) jobj[i];
				}

				jobj.put(result.getString("event"),"");


				jarray.put(jobj);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}	
	 */
	/*public JSONArray GetEventWithDate(Connection con,String user_id)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "Select event,date from upload_profile where user_id='"+user_id+"' ";
			System.out.println("event=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj = new JSONObject();

				for (int i = 0; i < jobj.length(); i++) {

					int event=i;
				}

				jobj.put(result.getString("event"),"");


				jarray.put(jobj);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}*/

	public boolean UpdatePersonna(Connection con, UploadProfileBean obj_bean)
	{
		boolean response = false;

		try {

			String add_qry = "UPDATE upload_profile SET `personna`='"+obj_bean.getPersonna()+"' WHERE user_id='"+obj_bean.getUser_id()+"' AND id='"+obj_bean.getFriend_id()+"'";

			//	System.out.println(" qry---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);

			if(i>0)
			{
				response=true;

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean UpdateDateEvent(Connection con, UploadProfileBean obj_bean)
	{
		boolean response = false;

		try {

			String add_qry = "UPDATE event_date SET `event`='"+obj_bean.getEvent()+"',`date`='"+obj_bean.getDate()+"',`friend_id`='"+obj_bean.getFriend_id()+"'";

			//System.out.println(" qry---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);

			if(i>0)
			{
				response=true;

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public JSONArray GetProfileNew(Connection con,String user_id)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = "SELECT * from upload_profile WHERE user_id='"+user_id+"'";
			//	System.out.println("upload=="+add_qry);
			//Logger.PritntLog("add_qry");
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj=new JSONObject();

				jobj.put("id",Util.getResult(result, "id"));
				jobj.put("name",Util.getResult(result, "name"));
				jobj.put("last_name", Util.getResult(result, "last_name"));
				jobj.put("relationship",Util.getResult(result, "relationship"));
				//jobj.put("dob",Util.getResult(result, "dob"));
				jobj.put("gender",Util.getResult(result, "gender"));
				//	jobj.put("budget",Util.getResult(result, "budget"));
				jobj.put("location",Util.getResult(result, "location"));
				jobj.put("image",Util.getResult(result, "image"));
				jobj.put("user_id",Util.getResult(result, "user_id"));
				jobj.put("contact_no",Util.getResult(result, "contact_no"));
				jobj.put("fb_id",Util.getResult(result, "fb_id"));
				jobj.put("type",Util.getResult(result, "type"));
				//jobj.put("events",Util.getResult(result, "event"));
				//jobj.put("event_date",Util.getResult(result, "date"));


				String personna=Util.getResult(result, "personna");
				//System.out.println("per_id"+personna);
				JSONArray parray=new JSONArray();

				if(personna!=null && personna!="" && personna.length()!=0)
				{
					parray = GetPersonna(con,personna);

					jobj.put("personna_details", parray);
				}
				else
				{
					jobj.put("personna_details", parray);
				}

				String id=Util.getResult(result, "id");

				JSONArray earray = GetEventDate(con,user_id,id);
				if(earray!=null)
				{
					jobj.put("event", earray);
					jobj.put("events", Util.getResult(result, "event"));
					jobj.put("event_date", Util.getResult(result, "date"));
					jobj.put("custom_name", Util.getResult(result, "custom_name"));

					//		jobj.put("notify", Util.getResult(result, "notify"));

				}

				/*if(user_id!=null)
				{
					JSONArray farray=getFavorites(con, user_id);

					jobj.put("favorite_details", farray);
				}*/
				jarray.put(jobj);

			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}


	public JSONArray getFavorites(Connection con,String user_id)
	{
		JSONArray jarray=new JSONArray();

		try {

			String qry="SELECT * from `favorites` where user_id='"+user_id+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qry);

			while(rs.next())
			{
				JSONObject jobj=new JSONObject();

				jobj.put("prod_id", Util.getResult(rs, "prod_id"));
				jobj.put("prod_name", Util.getResult(rs, "prod_name"));
				jobj.put("price", Util.getResult(rs, "price"));
				jobj.put("prod_image", Util.getResult(rs, "prod_image"));

				jarray.put(jobj);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}


	public JSONArray GetPersonna(Connection con,String personna)
	{
		JSONArray jarray=new JSONArray();

		try 
		{
			String add_qry = "Select * from category where id IN("+personna+")";
			//		System.out.println("personna==="+add_qry);


			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj=new JSONObject();

				jobj.put("pers_id", Util.getResult(result, "id"));
				jobj.put("pers_name", Util.getResult(result, "name"));
				jobj.put("pers_image", Util.getResult(result, "image"));

				jarray.put(jobj);

			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}

	public JSONArray GetEventDate(Connection con,String user_id,String id)
	{
		JSONArray jarray = new JSONArray();

		try 
		{
			String add_qry = /*"Select et.event_type,up.user_id,ev.event,ev.nudge_timestamp,ev.custom_name,ev.notify "
					+ "from event_date AS ev LEFT JOIN upload_profile AS up ON ev.friend_id=up.id LEFT JOIN EVENTS AS et "
					+ "ON ev.event=et.id WHERE up.user_id='"+user_id+"' AND ev.friend_id='"+id+"'";*/


					/*"Select up.user_id,ev.event,ev.nudge_timestamp,ev.custom_name,ev.notify "
					+ "from event_date AS ev LEFT JOIN upload_profile AS up ON ev.friend_id=up.id "
					+ "WHERE up.user_id='"+user_id+"' AND ev.friend_id='"+id+"'";*/

					"SELECT ev.event_type,ed.* FROM event_date ed LEFT JOIN `events` ev ON ed.event=ev.id "
					+ "WHERE user_id='"+user_id+"' AND `friend_id`='"+id+"'";

			//	System.out.println("geteventdate==="+add_qry);


			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj=new JSONObject();

				//jobj.put("id", Util.getResult(result, "id"));
				//jobj.put("f_id", Util.getResult(result, "friend_id"));
				jobj.put("events", Util.getResult(result, "event"));
				jobj.put("event_date", Util.getResult(result, "timestamp"));
				jobj.put("custom_name",Util.getResult(result, "custom_name"));
				jobj.put("notify",Util.getResult(result, "notify"));
				jobj.put("notify_days",Util.getResult(result, "notify_days"));
				jobj.put("budget",Util.getResult(result, "budget"));
				jobj.put("repeat",Util.getResult(result, "repeat"));
				jobj.put("event_type",Util.getResult(result, "event_type"));
				jobj.put("event_date_id",Util.getResult(result, "id"));

				jarray.put(jobj);

			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}

	public boolean AddDateEvent(Connection con, String friend_id,String event, String date,String user_id,long nudge_timestamp,String timestampToDate,String nudgeDateTimestampToDate,String custom_name,int notify,int notify_days,String notification_time,String not_date,int budget,int repeat)
	{
		boolean response = false;

		try {

			String add_qry = "INSERT into event_date (`friend_id`,`event`,`timestamp`,`user_id`,`nudge_timestamp`,`date`,`nudge_date`,`custom_name`,`notify`,`notify_days`,`notification_time`,`notification_date`,`budget`,`repeat`) VALUES('"+friend_id+"','"+event+"','"+date+"','"+user_id+"','"+nudge_timestamp+"','"+timestampToDate+"','"+nudgeDateTimestampToDate+"','"+custom_name+"','"+notify+"','"+notify_days+"','"+notification_time+"','"+not_date+"','"+budget+"','"+repeat+"')";

			//	System.out.println(" add_event---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);
			ResultSet rs = stm.getGeneratedKeys();

			if(i>0)
			{
				response=true;
			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean UpdateDateEvent(Connection con, String friend_id,String event, String date,String user_id,long nudge_timestamp,String timestampToDate,String nudgeDateTimestampToDate,String custom_name,int notify,int notify_days,String notification_time,String not_date,int budget,int repeat)
	{
		boolean response = false;

		try {

			String add_qry = "UPDATE event_date SET `event`='"+event+"',`timestamp`='"+date+"',`nudge_timestamp`='"+nudge_timestamp+"',`date`='"+timestampToDate+"',`nudge_date`='"+nudgeDateTimestampToDate+"',`custom_name`='"+custom_name+"',`notify`='"+notify+"',`notify_days`='"+notify_days+"',`notification_time`='"+notification_time+"',"
					+ "`notification_date`='"+not_date+"',`budget`='"+budget+"',`repeat`='"+repeat+"' WHERE friend_id='"+friend_id+"' AND `user_id`='"+user_id+"' AND `event`='"+event+"'";

			//	System.out.println(" update_event---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);

			if(i>0)
			{
				response=true;

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean UpdateDateEventCustom(Connection con, String friend_id,String event, String date,String user_id,long nudge_timestamp,String timestampToDate,String nudgeDateTimestampToDate,String custom_name,int notify,int notify_days,String notification_time,String not_date,int budget,int repeat,String event_date_id)
	{
		boolean response = false;

		try {

			String add_qry = "UPDATE event_date SET `event`='"+event+"',`timestamp`='"+date+"',`nudge_timestamp`='"+nudge_timestamp+"',`date`='"+timestampToDate+"',`nudge_date`='"+nudgeDateTimestampToDate+"',`custom_name`='"+custom_name+"',`notify`='"+notify+"',`notify_days`='"+notify_days+"',`notification_time`='"+notification_time+"',"
					+ "`notification_date`='"+not_date+"',`budget`='"+budget+"',`repeat`='"+repeat+"' WHERE id='"+event_date_id+"'";

			//		System.out.println(" update_event_custom---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);

			if(i>0)
			{
				response=true;

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}
	public boolean CheckDateEvent(Connection con, String friend_id,String event,String user_id,String date)
	{
		boolean response = false;

		try {

			String add_qry = "SELECT * FROM event_date WHERE friend_id='"+friend_id+"' AND user_id='"+user_id+"'  AND event='"+event+"'";

			//		System.out.println(" check event---------------->"+add_qry);

			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				response=true;

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean CheckCustomEvent(Connection con, String event_date_id)
	{
		boolean response = false;

		try {

			String add_qry = "SELECT * FROM event_date WHERE id='"+event_date_id+"'";

			//			System.out.println(" check_custom---------------->"+add_qry);

			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				response=true;

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public JSONArray getUpcomingNudges(Connection con, String user_id)
	{
		JSONArray jarray=new JSONArray();

		try {
			String add_qry="SELECT up.`image`,up.`name`,up.`last_name`,up.`relationship`,ev. * FROM event_date AS ev LEFT JOIN upload_profile AS up ON ev.`friend_id`= up.`id` WHERE DATE(STR_TO_DATE(`nudge_date`, '%d/%m/%Y')) > DATE(NOW()) AND ev.`user_id`='"+user_id+"'";
			//  System.out.println("upcomingnudges=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj=new JSONObject();

				jobj.put("user_id", Util.getResult(result, "user_id"));
				jobj.put("f_id", Util.getResult(result, "friend_id"));
				jobj.put("event", Util.getResult(result, "event"));
				jobj.put("custom_name", Util.getResult(result, "custom_name"));
				jobj.put("event_date", Util.getResult(result, "timestamp"));
				jobj.put("image", Util.getResult(result, "image"));
				jobj.put("name", Util.getResult(result, "name"));
				jobj.put("last_name", Util.getResult(result, "last_name"));
				jobj.put("budget", Util.getResult(result, "budget"));
				jobj.put("relationship", Util.getResult(result, "relationship"));

				String event=Util.getResult(result, "event");


				if(event!=null)
				{
					String event_name = GetRelationAccEvent(con,event);

					jobj.put("event_name", event_name);
				}

				String friend_id=Util.getResult(result, "friend_id");

				if(user_id!=null && friend_id!=null)
				{

					int event_count = CountEvents(con,user_id,friend_id);

					jobj.put("event_count", event_count);
				}
				jarray.put(jobj);

			}


		} catch (Exception e) {
			// TODO: handle exception
		}
		return jarray;
	}

	public boolean checkUserId(Connection con, String user_id)
	{
		boolean response=false;

		try {

			String add_qry = "SELECT id from registration where `id`='"+user_id+"' ";

			//	System.out.println("check user_id---------------->"+add_qry);

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(add_qry);

			while(rs.next())
			{
				response=true;


			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public String GetRelationAccEvent(Connection con,String event)
	{
		//JSONObject jobj=new JSONObject();

		String event_name="";

		try 
		{
			String add_qry = "Select * from events where id='"+event+"' ";
			//	System.out.println("personna==="+add_qry);


			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{


				event_name=Util.getResult(result, "event_name");



			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return event_name;
		}

		return event_name;
	}

	public int CountEvents(Connection con,String user_id,String friend_id)
	{
		//JSONObject jobj=new JSONObject();

		int count=0;

		try 
		{
			String add_qry = "Select count(event) as count from event_date where `user_id`='"+user_id+"' and `friend_id`='"+friend_id+"'";
			//	System.out.println("count==="+add_qry);


			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{

				count=result.getInt("count");


			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return count;
		}

		return count;
	}

	public String addNotification(Connection con, String nudgeDateTimestampToDate,int notify_days)
	{
		String notification_date="";

		try {

			String dateToYYMMDD=convertToYYMMDD(con,nudgeDateTimestampToDate);

			String add_qry = "SELECT DATE_SUB('"+dateToYYMMDD+"', INTERVAL '"+notify_days+"' DAY) as notf_date ";

			//	System.out.println("notification date---------------->"+add_qry);

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(add_qry);

			while(rs.next())
			{
				notification_date=rs.getString("notf_date");
			}

		} catch (Exception e) {


			e.printStackTrace();
			return notification_date;
		}

		return notification_date;
	}

	public String convertToYYMMDD(Connection con, String nudgeDateTimestampToDate)
	{
		String date="";

		try {

			String arr[]=nudgeDateTimestampToDate.split("/"); 

			for(int i=0;i<arr.length;i++)
			{
				String year=arr[2];
				String month=arr[1];
				String date1=arr[0];

				date=year+"/"+month+"/"+date1;

				//	System.out.println("yymmdd==="+date);


			}

		} catch (Exception e) {


			e.printStackTrace();
			return date;
		}

		return date;
	}

	public boolean UpdateDateEventOld(Connection con, String friend_id,String event, String date,String user_id,long nudge_timestamp,String timestampToDate,String nudgeDateTimestampToDate,String custom_name)
	{
		boolean response = false;

		try {

			String add_qry = "UPDATE event_date SET `event`='"+event+"',`timestamp`='"+date+"',`nudge_timestamp`='"+nudge_timestamp+"',`date`='"+timestampToDate+"',`nudge_date`='"+nudgeDateTimestampToDate+"',`custom_name`='"+custom_name+"' WHERE friend_id='"+friend_id+"' AND `user_id`='"+user_id+"' AND `event`='"+event+"'";

			//		System.out.println(" qry---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);

			if(i>0)
			{
				response=true;

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean AddDateEventOld(Connection con, String friend_id,String event, String date,String user_id,long nudge_timestamp,String timestampToDate,String nudgeDateTimestampToDate,String custom_name)
	{
		boolean response = false;

		try {

			String add_qry = "INSERT into event_date (`friend_id`,`event`,`timestamp`,`user_id`,`nudge_timestamp`,`date`,`nudge_date`,`custom_name`) VALUES('"+friend_id+"','"+event+"','"+date+"','"+user_id+"','"+nudge_timestamp+"','"+timestampToDate+"','"+nudgeDateTimestampToDate+"','"+custom_name+"')";

			//	System.out.println(" qry---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);

			if(i>0)
			{
				response=true;

			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public String getYear(Connection con,long currentdate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currentdate);
		int year = cal.get(Calendar.YEAR);
		//String year="";

		/*try {

			String add_qry = "SELECT YEAR('"+currentdate+"')";

			Statement stm = con.createStatement();
			ResultSet rs=  stm.executeQuery(add_qry);


		    if(rs.next())
			{

				return year;


			}
			System.out.println("year---------------->"+add_qry);



		} catch (Exception e) {


			e.printStackTrace();
			return year;
		}
		 */
		return year+"";
	}

	public int getDay(Connection con,String year,String updateddate)
	{
		int day=0;

		try {

			String add_qry = "SELECT DAYOFWEEK('"+updateddate+"')";

			Statement stm = con.createStatement();
			ResultSet rs=  stm.executeQuery(add_qry);


			if(rs.next())
			{
				day=rs.getInt(1);
				return day;
			}

			//		System.out.println("day--------------->"+add_qry);


		} catch (Exception e) {


			e.printStackTrace();
			return day;
		}

		return day;
	}

	public JSONArray getEvents(Connection con, String user_id,String friend_id)
	{
		JSONArray jarray=new JSONArray();

		try {
			String add_qry="SELECT * from event_date WHERE user_id='"+user_id+"' AND friend_id='"+friend_id+"'";
			//	    System.out.println("event_date=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				JSONObject jobj=new JSONObject();

				jobj.put("events", Util.getResult(result, "event"));
				jobj.put("custom_name", Util.getResult(result, "custom_name"));
				jobj.put("event_date", Util.getResult(result, "nudge_timestamp"));
				jobj.put("notify", Util.getResult(result, "notify"));
				jobj.put("notify_days", Util.getResult(result, "notify_days"));
				jobj.put("budget", Util.getResult(result, "budget"));
				jobj.put("repeat", Util.getResult(result, "repeat"));
				jobj.put("notify_days", Util.getResult(result, "notify_days"));
				jobj.put("event_date_id", Util.getResult(result, "id"));


				String event=Util.getResult(result, "event");
				stm = con.createStatement();
				ResultSet rs= stm.executeQuery("SELECT event_type FROM `events` WHERE id ="+event);
				if(rs.next()){
					jobj.put("event_type", Util.getResult(rs, "event_type"));
				}	

				jarray.put(jobj);

			}


		} catch (Exception e) {
			// TODO: handle exception
		}
		return jarray;
	}



	/*public UploadProfileBean getNotDate(Connection con,String currentdate){

		UploadProfileBean obj_bean = new UploadProfileBean(); 

		try{

			String add_qry="select * from event_date where notify='1' AND DATE_FORMAT(notification_date, '%m/%d') = DATE_FORMAT('"+currentdate+"', '%m/%d') ";
			System.out.println("notificationn==="+add_qry);
			Statement st=con.createStatement();
			ResultSet result =st.executeQuery(add_qry);
			//this.noOfRecords=0;

			while(result.next())
			{
				obj_bean.setDate(Util.getResult(result, "notification_date"));
				obj_bean.setFriend_id(Util.getResult(result, "friend_id"));
				obj_bean.setUser_id(Util.getResult(result, "user_id"));
				obj_bean.setEvent(Util.getResult(result, "event"));
				obj_bean.setCustom_name(Util.getResult(result, "custom_name"));
				obj_bean.setEvent(Util.getResult(result, "nudge_date"));

			}
		}

		catch(Exception ex){
			ex.printStackTrace();

		}

		return obj_bean;

	}*/

	public List<UploadProfileBean> getNotDate(Connection con,String currentdate) {
		List<UploadProfileBean> all_form = new ArrayList<UploadProfileBean>();
		try {

			String add_qry="SELECT reg.device_token,reg.device_type, up.name AS frd_name,up.image,ev.event_name,ed.* FROM event_date ed "
					+ "LEFT JOIN `events` AS ev ON `ed`.event=`ev`.id "
					+ "LEFT JOIN upload_profile AS up ON `ed`.friend_id=up.id "
					+ "LEFT JOIN registration reg ON `ed`.user_id=reg.id "
					+ "WHERE ed.notify='1' AND DATE_FORMAT(ed.notification_date, '%m/%d') = DATE_FORMAT('"+currentdate+"', '%m/%d')"; 

			//		System.out.println(add_qry);
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery(add_qry);
			while (result.next()) {
				UploadProfileBean obj_bean = new UploadProfileBean();
				obj_bean.setDate(Util.getResult(result, "notification_date"));
				obj_bean.setFriend_id(Util.getResult(result, "friend_id"));
				obj_bean.setUser_id(Util.getResult(result, "user_id"));
				obj_bean.setEvent(Util.getResult(result, "event"));
				obj_bean.setCustom_name(Util.getResult(result, "custom_name"));
				obj_bean.setNudge_date(Util.getResult(result, "nudge_date"));
				obj_bean.setEvent_name(Util.getResult(result, "event_name"));
				obj_bean.setNotification_time(Util.getResult(result, "notification_time"));
				obj_bean.setNudge_timestamp(Util.getResult(result, "nudge_timestamp"));
				obj_bean.setNotify_days(Util.getResult(result, "notify_days"));
				obj_bean.setName(Util.getResult(result, "frd_name"));
				obj_bean.setDevice_token(Util.getResult(result, "device_token"));
				obj_bean.setDevice_type(Util.getResult(result, "device_type"));
				obj_bean.setRepeat(Util.getResult(result, "repeat"));
				obj_bean.setOriginal_date(Util.getResult(result, "date"));
                obj_bean.setImage(Util.getResult(result, "image"));

				all_form.add(obj_bean);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return all_form;

	}

	public boolean updateNotDate(Connection con,String user_id,String friend_id,String event,String custom_name,String convertedDate,String converted_ts,String notify_days)
	{
		boolean response=false;

		try {
			String qry="update event_date set notification_date='"+convertedDate+"',notification_time='"+converted_ts+"',notify_days='"+notify_days+"'-'1'"
					+ " where user_id='"+user_id+"' and friend_id='"+friend_id+"' and ";

			if(event.equals("1000"))
			{
				qry=qry+ "custom_name='"+custom_name+"'";
			}
			else
			{
				qry=qry+ "`event`='"+event+"'";
			}

			System.out.println("qry="+qry);
			Statement st=con.createStatement();
			int i=st.executeUpdate(qry);
			
			while(i>0)
			{
				response=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return response;
		}
		return response;
	}
	
	public boolean updateRepeat(Connection con,String user_id,String friend_id,String event,String custom_name)
	{
		boolean response=false;

		try {
			String qry="update event_date set `repeat`='0' where user_id='"+user_id+"' and friend_id='"+friend_id+"' and ";

			if(event.equals("1000"))
			{
				qry=qry+ "custom_name='"+custom_name+"'";
			}
			else
			{
				qry=qry+ "`event`='"+event+"'";
			}

			System.out.println("qry="+qry);
			Statement st=con.createStatement();
			int i=st.executeUpdate(qry);
			
			while(i>0)
			{
				response=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return response;
		}
		return response;
	}
	
	public registration_bean GetUserDetails(Connection con,String user_id)
	{

		registration_bean obj=new registration_bean();

		try 
		{
			String add_qry = "SELECT * from registration WHERE id='"+user_id+"'";
			//			System.out.println("userdetails=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{

				obj.setName(Util.getResult(result, "name"));
				obj.setLast_name(Util.getResult(result, "last_name"));
				obj.setEmail(Util.getResult(result, "email"));
				obj.setDob(Util.getResult(result, "dob"));
				obj.setUnique_id(Util.getResult(result, "unique_id"));
				obj.setDevice_token(Util.getResult(result, "device_token"));
				obj.setDevice_type(Util.getResult(result, "device_type"));



				//	jarray.put(jobj);

			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return obj;
		}

		return obj;
	}


	public UploadProfileBean GetFriendDetails(Connection con,String friend_id)
	{

		UploadProfileBean obj=new UploadProfileBean();

		try 
		{
			String add_qry = "SELECT * from upload_profile WHERE id='"+friend_id+"'";
			//			System.out.println("userdetails=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{

				obj.setName(Util.getResult(result, "name"));
				obj.setLast_name(Util.getResult(result, "last_name"));
				obj.setDob(Util.getResult(result, "dob"));
				obj.setRelationship(Util.getResult(result, "relationship"));
				obj.setGender(Util.getResult(result, "gender"));
				obj.setBudget(Util.getResult(result, "budget"));
				obj.setLocation(Util.getResult(result, "location"));
				obj.setContact_no(Util.getResult(result, "contact_no"));



				//	jarray.put(jobj);

			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return obj;
		}

		return obj;
	}

	public boolean removeEvents(Connection con,String event_date_id)
	{
		boolean response=false;
		try {
			String qry="Delete from event_date where id='"+event_date_id+"'";
			//	System.out.println("del=="+qry);
			Statement st=con.createStatement();
			int i = st.executeUpdate(qry);

			if(i>0)
			{
				response=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return response;
		}
		return response;
	}

	public JSONObject GetEventDetails(Connection con,String user_id,String friend_id,String event)
	{
		JSONObject obj=new JSONObject();

		try {
			String qry="select ev.event_type,ed.* from `event_date` ed left join `event_type` ev on `event_date`.event=`event_type`.id where ed.`user_id`='"+user_id+"' and ed.`friend_id`='"+friend_id+"' and ed.`event`='"+event+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qry);
			while(rs.next())
			{
				obj.put("events",Util.getResult(rs, "event"));
				obj.put("event_date",Util.getResult(rs, "nudge_timestamp"));
				obj.put("notify",Util.getResult(rs, "notify"));
				obj.put("custom_name",Util.getResult(rs, "custom_name"));
				obj.put("notify_days",Util.getResult(rs, "notify_days"));
				obj.put("budget",Util.getResult(rs, "budget"));
				obj.put("repeat",Util.getResult(rs, "repeat"));
				obj.put("event_type",Util.getResult(rs, "event_type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
