package com.shop.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.shop.model.FirstLevelType;
import com.shop.service.TypeService;

@Controller("typeAction")
public class TypeAction {
		private String msg="hello";
		@Resource(name = "typeService")
		private TypeService service;
		private int  firstId;
		
		private FirstLevelType first = new FirstLevelType();
		public int getFirstId() {
			return firstId;
		}
		public void setFirstId(int firstId) {
			this.firstId = firstId;
		}
		public FirstLevelType getFirst() {
			return first;
		}
		public void setFirst(FirstLevelType first) {
			this.first = first;
		}
		
		public String setType()
		{	
			first = service.findFirstById(firstId);
			return msg;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		
}
