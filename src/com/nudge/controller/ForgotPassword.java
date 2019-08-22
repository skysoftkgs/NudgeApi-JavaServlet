package com.nudge.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.MailFormat;
import com.nudge.common.PropertiesReader;
import com.nudge.common.SendMail;
import com.nudge.common.SendMailNew;
import com.nudge.common.SendMailOld;
import com.nudge.common.Util;
import com.nudge.model.LoginModel;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JSONObject user_details;

	JSONObject obj;
	Connection con;
	HttpServletResponse response;
	String verify_url;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
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
		
		PropertiesReader pr = new PropertiesReader();
		Properties prop = new Properties();
		prop = pr.getPropertyFile();


		String PASSWORD_RESET_URL = prop.getProperty("PASSWORD_RESET_URL");
		String PASSWORD_RESET_SUBJECT = prop.getProperty("PASSWORD_RESET_SUBJECT");
		String MAIL_FROM = prop.getProperty("MAIL_FROM");
		String LOGO = prop.getProperty("LOGO");
		String SERVER_NAME = prop.getProperty("SERVER_NAME");
		String SERVER_PORT = prop.getProperty("SERVER_PORT");
		String PASSWORD = prop.getProperty("PASSWORD");
		
		try {
			obj = new JSONObject();
			user_details =  new JSONObject();
			Connection	con = Config.getInstance().getConnection();


			String email = Util.getParamValue(request, "email");
			

			//System.out.println("email--->"+email);
			
			if(email.length()!=0 )
			{

				boolean add_response = new LoginModel().CheckForgotPassword(con, email);
				
				if(add_response)
				{
					verify_url=PASSWORD_RESET_URL+email;
					System.out.println(verify_url);

					String NewMailBody1 = MailFormat.ForgotPwdMailFormat(verify_url, LOGO);

					
					SendMailNew.SendMailUser(NewMailBody1,email, PASSWORD_RESET_SUBJECT,MAIL_FROM,PASSWORD);

					printOutput(Constant.StatusSuccess, Constant.FORGOTPWD);
	
				}
				
				else
				{
					printOutput(Constant.StatusFail, Constant.FORGOTPWDFAIL);
				}
			}
			else
			{
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS);	
			}
		}
		 catch (Exception e) {
				// TODO: handle exception
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
	
	/*public static String NewRegistrationHTMLMailFormat(String verify_url, String LOGO,String email, String SERVER_NAME, String SERVER_PORT)
	{
		String message_body="";
		
		message_body+="<div style='font-family:HelveticaNeue-Light,Arial,sans-serif;background-color:#eeeeee'>";
		message_body+="<table align='center' width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee'>";
		message_body+="<tbody>";
		message_body+="<tr>" ;
		message_body+="	<td>";  
		message_body+="<table align='center' width='750px' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee' style='width:750px!important'>";
		message_body+=" <tbody>";      
		message_body+="<tr>";
		message_body+="<td>";
		message_body+="<table width='690' align='center' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee'>";
		message_body+="<tbody>";
		message_body+="<tr>";
		message_body+="<td colspan='3' height''80' align='center' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee' style='padding:0;margin:0;font-size:0;line-height:0'>";
		//message_body+="<table width='690' align='center' border='0' cellspacing='0' cellpadding='0'>";
		//message_body+="<tbody>";
		//message_body+="<tr>";
		//message_body+="<td width='30'></td>";
		//message_body+="<td align='left' valign='middle' style='padding:0;margin:0;font-size:0;line-height:0'><a href='' target='_blank'><img style='height:100px;' src='http://36.255.3.15:8080/nudge_uploads/1497604990_Lighthouse.jpg' alt='NUDGE' ></a></td>";
		//message_body+="<td width='30'></td>";
		//message_body+="</tr>";
		//message_body+="</tbody>";
		//message_body+="</table>";
		message_body+="</td>";
		message_body+="</tr>";
		message_body+="<tr>";
		message_body+="<td colspan='3'>";
		message_body+="<table width='630'  border='0' cellspacing='0' cellpadding='0'>";
		message_body+="<tbody>";
		message_body+="<tr>";
		message_body+=" <td colspan='3' height='40'></td></tr><tr><td width='25'></td>";
		message_body+=" <td>";
		message_body+=" <p style='font-size:12px;color:#404040;line-height:24px;margin:0;padding:0'>Hello,<br>Welcome to The Gifting App - a truly unique social platform which lets you set reminders for important dates of your friends and family, and send them gifts to celebrate the occasion.To complete the sign in process, please reset your password by clicking the link below. </p><br>";
		message_body+="<a href='"+verify_url+"' style='text-decoration:underline;color:#051662;border-radius:10em;border:1px solid #0D3E9D;text-decoration:none;color:#fff;background-color:#660208;padding:7px 30px;font-size:16px;line-height:1.4em;font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-weight:normal' target='_blank'>Reset Password</a></p>";
		message_body+="  </td>";
		message_body+=" <td width='25'></td>";
		message_body+=" </tr>";
		message_body+="<tr>";
		message_body+="	<td colspan='3' height='40'></td></tr><tr><td colspan='5' >";
		message_body+="  <p style='color:#404040;font-size:12px;line-height:24px;padding:0;margin:0'>The link is valid for the next 24 hours only.<br>If you have any questions, send us an email at tim@thegiftingapp.com.</p><br><br>Cheers!<br>Team 'The Gifting App' ";
		message_body+="  <p style='color:#404040;font-size:12px;line-height:22px;padding:0;margin:0'>";
		//message_body+="<b>'.$licence_key.' </b>";
					 
		message_body+="	 </p>";
					   
				  
		message_body+=" </td>";
		message_body+=" </tr>";
		message_body+=" <tr>";
	                                           
		message_body+="</tr>";
		message_body+="<tr><td colspan='3' height='30'></td></tr>";
		message_body+="</tbody>";
		message_body+="</table>";
		message_body+="</td>";
		message_body+="</tr>";
	                            
	                           
		message_body+=" </tbody>";
		message_body+="</table>";
		message_body+="<table width='750px' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee' style='width:750px!important'>";
		message_body+=" <tbody>";
		message_body+="<tr>";
		message_body+="	<td>";
		message_body+=" <table width='630' align='center' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee'>";
		message_body+="<tbody>";
		message_body+="<tr><td colspan='2' height='30'></td></tr>";
		message_body+=" <tr>";
		message_body+=" <td width='360' valign='top'>";
		message_body+="<div style='color:#a3a3a3;font-size:12px;line-height:12px;padding:0;margin:0'>&copy; 2018 'The Gifting App'. All rights reserved.</div>";
		message_body+="	<div style='line-height:5px;padding:0;margin:0'>&nbsp;</div>";
		message_body+="	<div style='color:#a3a3a3;font-size:12px;line-height:12px;padding:0;margin:0'></div>";
		message_body+="</td>";
		//message_body+="<td align='right' valign='top'>";
		//message_body+="<span style='line-height:20px;font-size:10px'><a href='https://www.facebook.com/ShieldCrypt/' target='_blank'><img src='http://i.imgbox.com/BggPYqAh.png' alt='fb'></a>&nbsp;</span>";
	    //message_body+="<span style='line-height:20px;font-size:10px'><a href='https://twitter.com/shieldcrypt' target='_blank'><img src='http://i.imgbox.com/j3NsGLak.png' alt='twit'></a>&nbsp;</span>";
	    //message_body+="<span style='line-height:20px;font-size:10px'><a href='https://plus.google.com/116132685670688176031' target='_blank'><img src='http://i.imgbox.com/wFyxXQyf.png' alt='g'></a>&nbsp;</span>";
	    //message_body+="</td>";
		message_body+=" </tr>";
		message_body+="<tr><td colspan='2' height='5'></td></tr>";
		                                      
		message_body+="</tbody>";
		message_body+=" </table>";
		message_body+="</td>";
		message_body+="</tr>";
		message_body+="</tbody>";
		message_body+="</table>";
		message_body+="</td>";
		message_body+="</tr>";
		message_body+="</tbody>";
		message_body+="</table>";
		message_body+="</td>";
		message_body+="</tr>";
		message_body+="</tbody>";
		message_body+="</table>";
		message_body+="</div>";
		return message_body;
	}
*/
}
