package com.nudge.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nudge.bean.registration_bean;
import com.nudge.common.Config;
import com.nudge.common.Util;
import com.nudge.model.registrationModel;

/**
 * Servlet implementation class EmailVerification
 */
@WebServlet("/EmailVerification")
public class EmailVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	HttpServletResponse response;


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailVerification() {
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
		
		try {
			
			con = Config.getInstance().getConnection();
			
			String email=Util.getParamValue(request, "email");
			
			registrationModel obj_model=new registrationModel();
			
			boolean add_response=obj_model.Email_verification_status(con, email);
			
			if(add_response)
			{
				response.sendRedirect("EmailVerification.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
