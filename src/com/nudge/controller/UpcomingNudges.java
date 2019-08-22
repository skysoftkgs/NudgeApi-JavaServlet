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
 * Servlet implementation class UpcomingNudges
 */
@WebServlet("/UpcomingNudges")
public class UpcomingNudges extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JSONObject obj;
	JSONArray upcoming_nudges;
	Connection con;
	HttpServletResponse response;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpcomingNudges() {
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
			upcoming_nudges=new JSONArray();
			Connection	con = Config.getInstance().getConnection();
			
			String user_id=Util.getParamValue(request, "user_id");
			
			if(user_id.length()!=0)
			{
				UploadProfileModel obj_model=new UploadProfileModel();
				
				upcoming_nudges=obj_model.getUpcomingNudges(con,user_id);
				
				if(upcoming_nudges.length()!=0)
				{
					printOutput(Constant.StatusSuccess,Constant.MessageUPNUDGE,upcoming_nudges);	
				}
				else
				{
					printOutput(Constant.StatusSuccess,Constant.MessageNUDGEFAIL,upcoming_nudges);
				}
			}
			else
			{
				printOutput(Constant.StatusFail,Constant.INVALID_INPUTS,upcoming_nudges);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			printOutput(Constant.StatusFail,Constant.FAIL_RESPONSE,upcoming_nudges);
		}
		
		
	}
	
	public void printOutput(String status,String message,JSONArray upcoming_nudges)
	{
		try {
			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);
			obj.put(Constant.NUDGESDETAILS,upcoming_nudges);
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

}
