package com.nudge.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nudge.common.Util;

/**
 * Servlet implementation class checktime
 */
@WebServlet("/checktime")
public class checktime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checktime() {
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
		
		
		long timestamp = 1503559172;
		long new_timestamp = timestamp*1000;
		
		String old_date = Util.MillisecondToDateNew1(String.valueOf(new_timestamp));
		
		int day = 3;
		
		long day_time = day*24*60*60;
		
		long diff = timestamp - day_time;
		
		long new_diff = diff*1000;
		
		String new_date = Util.MillisecondToDateNew1(String.valueOf(new_diff));
		
	//	System.out.println("old_date--->"+old_date);
		//System.out.println("new_date--->"+new_date);
		
	}

}
