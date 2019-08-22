package com.nudge.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;




import org.json.JSONArray;
import org.json.JSONObject;

import com.nudge.bean.UploadProfileBean;
import com.nudge.bean.registration_bean;
import com.nudge.common.Constant;
import com.nudge.common.Util;




public class registrationModel {

	public registrationModel()
	{


	}

	public int AddUser(Connection con, registration_bean bean_obj)
	{
		int response = 0;

		try {

			String qry = "INSERT INTO `registration`(`name`,`email`,`password`,`dob`,`image`,`unique_id`,`login_type`,`location`,`last_name`,`email_ver_status`,`device_type`,`gender`)"
					+ " VALUES ('"+bean_obj.getName()+"','"+bean_obj.getEmail()+"','"+bean_obj.getPassword()+"','"+bean_obj.getDob()+"','"+bean_obj.getImage()+"','"+bean_obj.getUnique_id()+"','"+bean_obj.getLogin_type()+"','"+bean_obj.getLocation()+"','"+bean_obj.getLast_name()+"','0','"+bean_obj.getDevice_type()+"','"+bean_obj.getGender()+"')";

		//	System.out.println(" qry---------------->"+qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(qry, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stm.getGeneratedKeys();
			
			if(i>0)
			{
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

	
	public boolean CheckEmail(Connection con, String unique_id)
	{
		boolean response = false;

		try {

			String qry = "Select * from registration WHERE unique_id='"+unique_id+"'";
	//		System.out.println("qry-------"+qry);
			Statement stm = con.createStatement();
			ResultSet rs =stm.executeQuery(qry);
			if(rs.next())
			{

				response = true;
			}
			else
			{
				response = false;
			}

		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean CheckUseraname(Connection con, String unique_id)
	{
		boolean response = false;

		try {

			String qry = "Select unique_id from registration WHERE unique_id='"+unique_id+"'";

			// System.out.println(" qry---------------->"+qry);

			Statement stm = con.createStatement();
			ResultSet rs =stm.executeQuery(qry);
			if(rs.next())
			{

				return true;
			}

		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}
	
	public boolean Email_verification_status(Connection con,String email) 
	{
		boolean response = false;
		
		try{
			String add_qry="UPDATE registration SET email_ver_status='1' WHERE unique_id='"+email+"' ";
			
		//	System.out.println("update_status--"+add_qry);
			Statement st = con.createStatement();
			int i = st.executeUpdate(add_qry);
			
			if(i>0){
				
				return response=true;
			}
			
			
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return response;
		}
		
	
		return response;
	}
	
	public boolean ResetPassword(Connection con,registration_bean user_bean) 
	{
		boolean response = false;
		
		try{
	//		System.out.println("email model"+user_bean.getEmail());
			String add_qry="UPDATE registration SET password='"+user_bean.getPassword()+"' WHERE unique_id='"+user_bean.getEmail()+"' ";
			
	//		System.out.println("update_status--"+add_qry);
			Statement st = con.createStatement();
			int i = st.executeUpdate(add_qry);
			
			if(i>0){
				
				response=true;
			}
			
			
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return response;
		}
		
	
		return response;
	}
	
	public JSONObject GetUserDetails(Connection con,String user_id)
	{
		//JSONArray jarray = new JSONArray();
		JSONObject jobj=new JSONObject();
		
		try 
		{
			String add_qry = "SELECT * from registration WHERE id='"+user_id+"'";
//			System.out.println("userdetails=="+add_qry);
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
			
				
				jobj.put("name",Util.getResult(result, "name"));
				jobj.put("last_name", Util.getResult(result, "last_name"));
				jobj.putOnce("email", Util.getResult(result, "email"));
				jobj.put("dob",Util.getResult(result, "dob"));
				jobj.put("location","London");
				jobj.put("image",Util.getResult(result, "image"));
				jobj.put("notification","true");

			
			//	jarray.put(jobj);
			
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jobj;
		}
		
		return jobj;
	}
	
	public boolean CheckUserId(Connection con, String user_id)
	{
		boolean response = false;

		try {

			String qry = "Select * from registration WHERE id='"+user_id+"'";
			//System.out.println("qry-------"+qry);
			Statement stm = con.createStatement();
			ResultSet rs =stm.executeQuery(qry);
			if(rs.next())
			{

				response = true;
			}
			else
			{
				response = false;
			}

		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}
	
	
	public boolean checkPassword(Connection con, String enc_password,String user_id)
	{
		boolean response = false;

		try {

			String qry = "Select * from registration WHERE id='"+user_id+"' and `password`='"+enc_password+"'";
		
			Statement stm = con.createStatement();
			ResultSet rs =stm.executeQuery(qry);
			if(rs.next())
			{

				response = true;
			}
			else
			{
				response = false;
			}

		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}
	
	public boolean UpdateRegistration(Connection con, registration_bean obj_bean)
	{
		boolean response=false;

		try {
			
			String add_qry="UPDATE registration SET `name`='"+obj_bean.getName()+"',`email`='"+obj_bean.getEmail()+"',`dob`='"+obj_bean.getDob()+"',`location`='"+obj_bean.getLocation()+"',`last_name`='"+obj_bean.getLast_name()+"',`password`='"+obj_bean.getPassword()+"',`gender`='"+obj_bean.getGender()+"'";
			
			
			if(obj_bean.getIsimage().equalsIgnoreCase("yes"))
			{
				add_qry = add_qry+" ,`image`='"+ obj_bean.getImage() + "'";
			}
			
			add_qry = add_qry+" WHERE `id`='"+obj_bean.getUser_id()+"' ";
			
		//	System.out.println("manual---------------->"+add_qry);

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(add_qry);
			
			if(i>0)
			{
					response= true;
			
			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}
	
	public boolean deleteUser(Connection con,String user_id)
	{
		boolean response=false;
		try {
			String qry="DELETE a.*, b.* ,c.* FROM `registration` a  LEFT JOIN `upload_profile` b  ON a.id = b.user_id "
					+ "LEFT JOIN `event_date` c ON a.id=c.`user_id` WHERE a.id = '"+user_id+"'";
		//	System.out.println(qry);
			Statement st=con.createStatement();
			int i=st.executeUpdate(qry);
			
			if(i>0)
			{   
				
				response=true;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
	

	public boolean addRating(Connection con,String user_id,String push_not,String email_not,String feedback,String rating)
	{
		boolean response=false;
		try {
			String qry="update registration set `push_not`='"+push_not+"',`email_not`='"+email_not+"',`feedback`='"+feedback+"',"
					+ "`rating`='"+rating+"' where id='"+user_id+"'";
			//System.out.println(qry);
			Statement st=con.createStatement();
			int i=st.executeUpdate(qry);
			
			if(i>0)
			{   
				
				response=true;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
	
	
}
