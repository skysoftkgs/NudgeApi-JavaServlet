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
import com.nudge.model.LoginModel;
import com.nudge.model.UploadProfileModel;

/**
 * Servlet implementation class GetRelation
 */
@WebServlet("/GetConfig")
public class GetConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	Connection con;
	HttpServletResponse response;
	JSONObject obj;
	
	JSONObject userobj;
	JSONArray relation_details;
	JSONArray category_details;
	JSONArray product_details;
	JSONArray event_details;
	JSONArray budget_details;
	JSONArray age_details;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetConfig() {
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
			
			con = Config.getInstance().getConnection();
			
			obj = new JSONObject();
			relation_details =  new JSONArray();	
			relation_details = new UploadProfileModel().GetRelation(con);
			category_details =  new JSONArray();	
			category_details = new UploadProfileModel().GetCategory(con);
			product_details =  new JSONArray();	
			product_details = new UploadProfileModel().GetProducts(con);
			event_details =  new JSONArray();	
			event_details = new UploadProfileModel().GetEvents(con);
			budget_details =  new JSONArray();	
			budget_details = new UploadProfileModel().GetBudget(con);
			age_details=new JSONArray();
			age_details=new UploadProfileModel().getAge(con);
		
			printOutput(Constant.StatusSuccess,relation_details,category_details,product_details,event_details,budget_details,age_details);
			
		} 
		catch (Exception e) {
			// TODO: handle exception
			printOutput(Constant.StatusFail,relation_details,category_details,product_details,event_details,budget_details,age_details);
		}
		
	}
	public void printOutput(String status,JSONArray relation_details,JSONArray category_details,JSONArray product_details,JSONArray event_details,JSONArray budget_details,JSONArray age_details)
	{
		try {
			obj.put(Constant.STATUS, status);
			//obj.put(Constant.MESSAGE,message);
			obj.put(Constant.RELATIONDETAILS,relation_details);
			obj.put(Constant.CATEGORYDETAILS,category_details);
			obj.put(Constant.PRODUCTDETAILS,product_details);
			obj.put(Constant.EVENTDETAILS,event_details);
			obj.put(Constant.BUDGETDETAILS,budget_details);
			obj.put(Constant.AGEDETAILS,age_details);

			//System.out.println("response--->"+obj.toString());

			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} 

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		
		}

		finally
		{
			Config.DB_colse();
		}


	}
}
