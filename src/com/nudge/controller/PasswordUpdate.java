package com.nudge.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nudge.bean.registration_bean;
import com.nudge.common.AESHelper;
import com.nudge.common.Config;
import com.nudge.common.Util;
import com.nudge.model.registrationModel;

/**
 * Servlet implementation class PasswordUpdate
 */
@WebServlet("/PasswordUpdate")
public class PasswordUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	HttpServletResponse response;


       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordUpdate() {
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
			String password=Util.getParamValue(request, "password");
			
			System.out.println("emailnew"+email);
			System.out.println("pwd"+password);
			
			registrationModel obj_model=new registrationModel();
			
			registration_bean obj_bean=new registration_bean();
			obj_bean.setEmail(email);
			obj_bean.setPassword(AESHelper.GetEncryptValue(password));
			
			
			boolean add_response=obj_model.ResetPassword(con, obj_bean);
			
			if(add_response)
			{
				
				response.sendRedirect("PasswordUpdated.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
