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
 * Servlet implementation class RemoveEvents
 */
@WebServlet("/RemoveEvents")
public class RemoveEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;


	JSONObject obj;
	Connection con;
	HttpServletResponse response;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveEvents() {
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
			Connection	con = Config.getInstance().getConnection();

		//	String user_id=Util.getParamValue(request, "user_id");
		//	String friend_id=Util.getParamValue(request, "friend_id");
			String event_date_id=Util.getParamValue(request, "event_date_id");

			if( event_date_id.length()!=0)
			{
				UploadProfileModel obj=new UploadProfileModel();
				boolean remove_res=obj.removeEvents(con,event_date_id);

				if(remove_res)
				{
					printOutput(Constant.StatusSuccess, Constant.EVENT_DELETE);
				}
				else
				{
					printOutput(Constant.StatusFail, Constant.EVENT_DELETE_FAIL);
				}
			}
			else
			{
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			printOutput(Constant.StatusFail, Constant.FAIL_RESPONSE);
		}
	}

	public void printOutput(String status,String message)
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
