package com.javaweb.utils;

import javax.servlet.http.HttpServletRequest;

import com.javaweb.constant.SystemConstant;

public class MessageUtil {
	
	public static void showMessage(HttpServletRequest request){
		if(request.getParameter("message") != null){
			String messageResponse = "";
			String alert = "";
			String message = request.getParameter("message");
			if(message.equals(SystemConstant.INSERT_SUCCESS)){
				messageResponse = "Insert success";
				alert = "success";
			}else if(message.equals(SystemConstant.UPDATE_SUCCESS)){
				messageResponse = "Update success";
				alert = "success";
			}else if(message.equals(SystemConstant.DELETE_SUCCESS)){
				messageResponse = "Delete success";
				alert = "success";
			}else if(message.equals(SystemConstant.ERROR_SYSTEM)){
				messageResponse = "Error system";
				alert = "danger";
			}
			request.setAttribute(SystemConstant.MESSAGE_RESPONSE, messageResponse);
			request.setAttribute("alert", alert);
		}
	}
}
