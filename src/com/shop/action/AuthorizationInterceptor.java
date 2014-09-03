package com.shop.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shop.model.User;

public class AuthorizationInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invacation) throws Exception {
		User user = (User)invacation.getInvocationContext().getSession().get("currentUser");
		if(user!=null)
		{
			return invacation.invoke();
		}
		else{
			return "loginInit";
		}
	}

}
