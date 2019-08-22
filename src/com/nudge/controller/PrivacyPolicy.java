package com.nudge.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrivacyPolicy
 */
@WebServlet("/PrivacyPolicy")
public class PrivacyPolicy extends HttpServlet {
	private static final long serialVersionUID = 1L;
   HttpServletResponse response;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrivacyPolicy() {
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
			 RequestDispatcher rd =request.getRequestDispatcher("PrivacyPolicy.jsp");
			   rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
