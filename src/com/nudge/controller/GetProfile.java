package com.nudge.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.Util;
import com.nudge.model.UploadProfileModel;

/**
 * Servlet implementation class GetProfile
 */
@WebServlet("/GetProfile")
public class GetProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	JSONArray user_details;
	JSONArray personna_details;

	JSONObject obj;
	Connection con;
	HttpServletResponse response;
	//JSONArray event;
	


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfile() {
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
			
			obj = new JSONObject();
			user_details =  new JSONArray();
			personna_details = new JSONArray();
			Connection	con = Config.getInstance().getConnection();


			//String type = Util.getParamValue(request, "type");
			String user_id=Util.getParamValue(request, "user_id");
			
			UploadProfileModel obj_model=new UploadProfileModel();
			
			if( user_id.length()!=0)
			{
				
				   
				user_details=obj_model.GetProfileNew(con, user_id);
				
				printOutput(Constant.StatusSuccess,Constant.MessageUDF,user_details);
				
				/*//personna_details=obj_model.GetPersonna(con, user_id);	
					
					if(user_details.length()!=0)
					{
						
						printOutput(Constant.StatusSuccess,Constant.MessageUDF,user_details);	

					}
					else
					{
						printOutputForErrorMsg(Constant.StatusFail,Constant.MessageNOTUDF);
					}*/
				
			}
			else{
				printOutputForErrorMsg(Constant.StatusFail,Constant.INVALID_INPUTS);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			printOutputForErrorMsg(Constant.StatusFail,Constant.FAIL_RESPONSE);
		}
	}
	
	public void printOutput(String status,String message,JSONArray user_details)
	{
		try {
			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);
			obj.put(Constant.USERDETAILS,user_details);
		//	obj.put(Constant.PERSONNADETAILS,personna_details);

			

			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} 
		
		catch (Exception e) {
			// TODO: handle exception
		}
		
		finally
		{
			Config.DB_colse();
		}
    
		
	}
	
	public void printOutputForErrorMsg(String status,String message)
	{
		try {
			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);
			
			
			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} 
		
		catch (Exception e) {
			// TODO: handle exception
		}
		
		finally
		{
			Config.DB_colse();
		}
		
		
	}

}
