package com.shop.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shop.model.ShopCart;

public final class JspPrinter {
	public static void print(boolean bol) throws Exception{
		HashMap retMap = new HashMap<String, Object>();
		retMap.put("success", bol);
		JSONObject json = JSONObject.fromObject(retMap);
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json.toString());
	}
	
	public static void print(String bol) throws Exception{
		HashMap retMap = new HashMap<String, Object>();
		retMap.put("success", bol);
		JSONObject json = JSONObject.fromObject(retMap);
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json.toString());
	}

}
