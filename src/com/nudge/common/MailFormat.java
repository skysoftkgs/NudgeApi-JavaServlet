package com.nudge.common;

import java.util.Properties;

public class MailFormat {

	

	public static String EmailVerMailFormat(String verify_url,String LOGO,String email)
	{
		String message_body="";
		
		message_body+="<table width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#566D60' style='width:100%;background:#566D60'>";
		
		message_body+="<tbody><tr><td><table border='0' cellspacing='0' cellpadding='0' align='center' width='550' style='width:100%;padding:40px;padding-bottom:25px'>";
        
		message_body+="<tbody><tr><td><div style='direction:ltr;max-width:600px;margin:0 auto'>";
		
		message_body+="<table border='0' cellspacing='0' cellpadding='0' bgcolor='#ffffff' style='width:100%;background-color:#fff;text-align:left;margin:0 auto;max-width:1024px;min-width:320px'>";
		
		message_body+="<tbody><tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0' height='3'  style='width:100%;background-color:#8DB49E;height:8px'>";
		
		message_body+="<tbody><tr><td></td></tr></tbody></table><table style='width:100%' width='100%' border='0' cellspacing='0' cellpadding='20' bgcolor='#ffffff'><tbody>";
		
		message_body+="<tr><td valign='top' style='padding:20px'><img src='"+LOGO+"' width='80px' height='80px'>";
		
		message_body+="<h2>Welcome to The Gifting App!</h2>";
		
		message_body+="<p style='direction:ltr;font-size:14px;line-height:1.4em;color:#444444;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif;margin:0 0 1em 0;margin:0 0 20px 0'>A truly unique social platform which lets you set reminders for important dates of your friends and family, and send them gifts to celebrate the occasion.Just one step away for creating your account with us. Click the button below to verify your email.</p>";
		
		message_body+="<p style='direction:ltr;font-size:14px;line-height:1.4em;color:#444444;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif;margin:0 0 1em 0;margin:5px 0 10px 0'>";
		
		message_body+="<a href='"+verify_url+"' style='text-decoration:underline;color:#566D60;border-radius:10em;border:1px solid #8DB49E;text-decoration:none;color:#fff;background-color:#566D60;padding:7px 30px;font-size:16px;line-height:1.4em;font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-weight:normal' target='_blank'>Verify Email</a></p>";
		
		message_body+="</td><td></td></tr><tr></tr></tbody></table></td></tr></tbody></table>";
		
		message_body+="<table width='100%' border='0' cellspacing='0' cellpadding='0' height='3'  style='width:100%;background-color:#8DB49E;height:4px'>";

		message_body+="<tbody><tr><td></td></tr></tbody></table></div></td></tr></tbody></table>";
		
		message_body+="<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='width:100%;padding-bottom:2em;color:#555555;font-size:12px;height:18px;text-align:center;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif'>";
		
	//	message_body+="<tbody><tr><td align='center'><a href='#' style='text-decoration:underline;color:#2585b2;font-size:14px;color:#FFF!important;text-decoration:none;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif;color:#FFF!important;font-size:14px;text-decoration:none' target='_blank'>Thanks for registering with <img border='0' src='"+Constant.PREFME_WHITE_MAIL_LOGO +"' alt='' style='vertical-align:middle' width='16' height='16' class='CToWUd'> Prefme</a></td>";
		
		message_body+="</tr></tbody></table></td></tr></tbody></table>";
		return message_body;
		
	}
	
	

	public static String ForgotPwdMailFormat(String verify_url,String LOGO)
	{
		String message_body="";
		
		message_body+="<table width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#566D60' style='width:100%;background:#566D60'>";
		
		message_body+="<tbody><tr><td><table border='0' cellspacing='0' cellpadding='0' align='center' width='550' style='width:100%;padding:40px;padding-bottom:25px'>";
        
		message_body+="<tbody><tr><td><div style='direction:ltr;max-width:600px;margin:0 auto'>";
		
		message_body+="<table border='0' cellspacing='0' cellpadding='0' bgcolor='#ffffff' style='width:100%;background-color:#fff;text-align:left;margin:0 auto;max-width:1024px;min-width:320px'>";
		
		message_body+="<tbody><tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0' height='3'  style='width:100%;background-color:#8DB49E;height:8px'>";
		
		message_body+="<tbody><tr><td></td></tr></tbody></table><table style='width:100%' width='100%' border='0' cellspacing='0' cellpadding='20' bgcolor='#ffffff'><tbody>";
		
		message_body+="<tr><td valign='top' style='padding:20px'><img src='"+LOGO+"' width='80px' height='80px'>";
		
		message_body+="<h2>Welcome to The Gifting App!</h2>";
		
		message_body+="<p style='direction:ltr;font-size:14px;line-height:1.4em;color:#444444;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif;margin:0 0 1em 0;margin:0 0 20px 0'>A truly unique social platform which lets you set reminders for important dates of your friends and family, and send them gifts to celebrate the occasion. Click the button below to reset your password.</p>";
		
		message_body+="<p style='direction:ltr;font-size:14px;line-height:1.4em;color:#444444;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif;margin:0 0 1em 0;margin:5px 0 10px 0'>";
		
		message_body+="<a href='"+verify_url+"' style='text-decoration:underline;color:#566D60;border-radius:10em;border:1px solid #8DB49E;text-decoration:none;color:#fff;background-color:#566D60;padding:7px 30px;font-size:16px;line-height:1.4em;font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-weight:normal' target='_blank'>Reset Password</a></p>";
		
		message_body+="</td><td></td></tr><tr></tr></tbody></table></td></tr></tbody></table>";
		
		message_body+="<table width='100%' border='0' cellspacing='0' cellpadding='0' height='3'  style='width:100%;background-color:#8DB49E;height:4px'>";

		message_body+="<tbody><tr><td></td></tr></tbody></table></div></td></tr></tbody></table>";
		
		message_body+="<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='width:100%;padding-bottom:2em;color:#555555;font-size:12px;height:18px;text-align:center;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif'>";
		
	//	message_body+="<tbody><tr><td align='center'><a href='#' style='text-decoration:underline;color:#2585b2;font-size:14px;color:#FFF!important;text-decoration:none;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif;color:#FFF!important;font-size:14px;text-decoration:none' target='_blank'>Thanks for registering with <img border='0' src='"+Constant.PREFME_WHITE_MAIL_LOGO +"' alt='' style='vertical-align:middle' width='16' height='16' class='CToWUd'> Prefme</a></td>";
		
		message_body+="</tr></tbody></table></td></tr></tbody></table>";
		return message_body;
		
	}
	

}
