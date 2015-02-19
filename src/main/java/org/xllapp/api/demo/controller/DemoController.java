package org.xllapp.api.demo.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xllapp.api.demo.dao.DemoDao;
import org.xllapp.api.demo.entity.Demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.xllapp.api.core.BaseController;
import org.xllapp.api.util.JSONHelper;


/**
 * 无缓存的例子.
 *
 * @author dylan.chen Feb 8, 2014
 * 
 */
public class DemoController extends BaseController {
	
	private final static Logger logger=LoggerFactory.getLogger(DemoController.class);
	
	public static class DemoResponse{
		
		private int resultCode;
		
		private String resultDesc;
		
		private Date timestamp;
		
		public DemoResponse(){
		}
		
		public DemoResponse(int resultCode, String resultDesc, Date timestamp) {
			super();
			this.resultCode = resultCode;
			this.resultDesc = resultDesc;
			this.timestamp = timestamp;
		}
		public DemoResponse(int resultCode, String resultDesc) {
			this(resultCode, resultDesc, new Date());
		}
		
		@JsonProperty("result_code")
		public int getResultCode() {
			return resultCode;
		}
		public void setResultCode(int resultCode) {
			this.resultCode = resultCode;
		}
		
		@JsonProperty("result_desc")
		public String getResultDesc() {
			return resultDesc;
		}
		public void setResultDesc(String resultDesc) {
			this.resultDesc = resultDesc;
		}
		public Date getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("DemoResponse [resultCode=");
			builder.append(resultCode);
			builder.append(", resultDesc=");
			builder.append(resultDesc);
			builder.append(", timestamp=");
			builder.append(timestamp);
			builder.append("]");
			return builder.toString();
		}
		
	}
	
	@Autowired
	private DemoDao demoDao;

	@Override
	public Object resolveAndVerifyArgument(HttpServletRequest request) throws Exception {
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		if(StringUtils.isBlank(name) || StringUtils.isBlank(password)){
			throw new Exception("name和passord均不能为空");
		}
		Demo demo=new Demo();
		demo.setName(name);
		demo.setPassword(password);
		return demo;
	}

	@Override
	public String handleRequest(Object argument, HttpServletRequest request) throws Exception {
		this.demoDao.save((Demo)argument);
		return JSONHelper.toJSONString(new DemoResponse(0, "success"));
	}

	@Override
	public void handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) {
		String content=null;
		try {
			content=JSONHelper.toJSONString(new DemoResponse(1, exception.getLocalizedMessage()));
		} catch (Exception e) {
			logger.warn(e.getLocalizedMessage(),e);
		}
		out(response, content);
	}
	
}
