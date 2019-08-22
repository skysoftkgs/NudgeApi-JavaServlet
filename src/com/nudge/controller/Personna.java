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

import com.nudge.bean.UploadProfileBean;
import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.Util;
import com.nudge.model.UploadProfileModel;

/**
 * Servlet implementation class Personna
 */
@WebServlet("/Personna")
public class Personna extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	HttpServletResponse response;
	JSONObject obj;
	JSONObject personna;
	String id_list="";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Personna() {
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

		this.response = response;

		try 
		{
			int api_response  = 0;
			obj= new JSONObject();


			String body = Util.getParamValue(request, "body");


			con = Config.getInstance().getConnection();

			if(body.length()!=0)
			{	
				JSONObject jObject = new JSONObject(body);

				String user_id = jObject.getString("user_id");
				String friend_id = jObject.getString("f_id");
				JSONArray jArray = jObject.getJSONArray("personna");

				for (int i = 0; i < jArray.length(); i++)
				{
					JSONObject explrObject = jArray.getJSONObject(i);

					String id = explrObject.getString("id");
					
					id_list = id_list+","+id;
					System.out.println("personna=="+id_list);

						
				}
				
				id_list = id_list.replaceFirst(",", "");
				UploadProfileBean bean_obj = new UploadProfileBean();

				bean_obj.setUser_id(user_id);
				bean_obj.setFriend_id(friend_id);
				bean_obj.setPersonna(id_list);

				UploadProfileModel model_obj = new UploadProfileModel();

				boolean update_response = model_obj.UpdatePersonna(con, bean_obj);
				
				if(update_response)
				{
					personna = new JSONObject();

					personna.put("personna",id_list);
					
					
					printOutput(Constant.StatusSuccess, Constant.PERSONNAADD,personna);
				}
				else
				{
					printOutput(Constant.StatusFail, Constant.PERSONNANOTADD,personna);
				}
				id_list="";

			}

			else
			{
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS,personna);
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			printOutput(Constant.StatusFail, Constant.FAIL_RESPONSE,personna);
		}
	}


	public void printOutput(String status,String message,JSONObject personna)
	{
		try {


			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);
			obj.put(Constant.PERSONNADETAILS,personna);


			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} 

		catch (Exception e) {
			// TODO: handle exception
		}
	}


}
