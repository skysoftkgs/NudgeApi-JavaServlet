package com.nudge.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



import java.util.ArrayList;
import java.util.List;








import org.json.JSONArray;
import org.json.JSONObject;

import com.nudge.bean.registration_bean;
import com.nudge.common.AESHelper;
import com.nudge.common.Util;

public class LoginModel {

	
	public LoginModel()
	{
		
	}
	
	
	
	public int CheckEmailVerify(Connection con,String email,String password)
	{
		int emailver=0;
		try 
		{
			String add_qry = "Select email_ver_status from registration WHERE unique_id='"+email+"' AND password='"+AESHelper.GetEncryptValue(password)+"' ";//AND email_ver_status='1'
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				
				
				 emailver=result.getInt("email_ver_status");
				
			
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			//return jobj;
		}
		
		return emailver;
	}	
	
	public int CheckLoginStatus(Connection con,String email,String password)
	{
		int status=0;
		try 
		{
			String add_qry = "Select login_status from registration WHERE unique_id='"+email+"' AND password='"+AESHelper.GetEncryptValue(password)+"' ";//AND email_ver_status='1'
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				
				
				status=result.getInt("login_status");
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			//return jobj;
		}
		
		return status;
	}	
	
	
	
	public boolean CheckForgotPassword(Connection con,String email)
	{
		boolean response =false;
		try 
		{
			String add_qry = "Select unique_id from registration WHERE unique_id='"+email+"'";//AND email_ver_status='1'
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				
				 response=true;
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return response;
		}
		
		return response;
	}	
	





	public JSONObject CheckLoginByEmail(Connection con,String email,String password)
	{
		JSONObject jobj = new JSONObject();
		try 
		{
			String add_qry = "Select * from registration WHERE unique_id='"+email+"' AND password='"+AESHelper.GetEncryptValue(password)+"'  ";//AND email_ver_status='1'
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				
				
				jobj.put("id",result.getInt("id"));
				jobj.put("name",Util.getResult(result, "name"));
				jobj.put("last_name",Util.getResult(result, "last_name"));
				jobj.put("email",Util.getResult(result, "email"));
				jobj.put("dob",Util.getResult(result, "dob"));
				jobj.put("location",Util.getResult(result, "location"));
				jobj.put("image",Util.getResult(result, "image"));
				jobj.put("gender",Util.getResult(result, "gender"));
				jobj.put("push_not",Util.getResult(result, "push_not"));
				jobj.put("email_not",Util.getResult(result, "email_not"));
				jobj.put("feedback",Util.getResult(result, "feedback"));
				jobj.put("rating",Util.getResult(result, "rating"));
				
			
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jobj;
		}
		
		return jobj;
	}	
	
	

	public JSONObject CheckLoginByFb(Connection con,String fb_id,String password)
	{
		JSONObject jobj = new JSONObject();
		
		try 
		{
			String add_qry = "Select * from registration WHERE unique_id='"+fb_id+"' AND password='"+AESHelper.GetEncryptValue(password)+"'";
			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(add_qry);

			while(result.next())
			{
				
				
				jobj.put("id",result.getInt("id"));
				jobj.put("name",Util.getResult(result, "name"));
				jobj.put("last_name",Util.getResult(result, "last_name"));
				jobj.put("email",Util.getResult(result, "email"));
				jobj.put("dob",Util.getResult(result, "dob"));
				jobj.put("location",Util.getResult(result, "location"));
				jobj.put("image",Util.getResult(result, "image"));
				jobj.put("gender",Util.getResult(result, "gender"));
				jobj.put("push_not",Util.getResult(result, "push_not"));
				jobj.put("email_not",Util.getResult(result, "email_not"));
				jobj.put("feedback",Util.getResult(result, "feedback"));
				jobj.put("rating",Util.getResult(result, "rating"));
				
			
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return jobj;
		}
		
		return jobj;
	}	
	
	public int updateLoginStatus(Connection con,String email,String password) 
	{
		int response = 0;
		
		try{
			String add_qry="UPDATE registration SET login_status='1' WHERE unique_id='"+email+"' AND password='"+AESHelper.GetEncryptValue(password)+"'";
			
		//	System.out.println("update_status--"+add_qry);
			Statement st = con.createStatement();
			int i = st.executeUpdate(add_qry);
			
			if(i>0){
				
				return response=1;
			}
			
			
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return response;
		}
		
	
		return response;
	}
	
	public boolean addDeviceToken(Connection con,String email,String password,String device_token,String device_type)
	{
		boolean response =false;
		try 
		{
			String add_qry = "UPDATE registration set `device_token`='"+device_token+"',`device_type`='"+device_type+"' where `unique_id`='"+email+"' AND `password`='"+AESHelper.GetEncryptValue(password)+"'";
			Statement stm = con.createStatement();
		    int i=stm.executeUpdate(add_qry);

		    if(i>0)
			{
				
				 response=true;
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return response;
		}
		
		return response;
	}	
	
	
}
