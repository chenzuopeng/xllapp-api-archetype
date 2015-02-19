package org.xllapp.api.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.xllapp.api.demo.dao.DemoDao;
import org.xllapp.api.demo.entity.Demo;

import org.xllapp.api.core.CachedController;
import org.xllapp.api.util.JSONHelper;

/**
 * 使用缓存的例子.
 * 
 * @author dylan.chen Sep 20, 2013
 * 
 */
public class DemoCachedController extends CachedController {

	@Autowired
	private DemoDao demoDao;

	@Override
	public Object resolveAndVerifyArgument(final HttpServletRequest request) throws Exception {
		return request.getParameter("name");
	}

	@Override
	public String getCacheKey(Object argument, HttpServletRequest request) {
		return (String) argument;
	}

	@Override
	public int getCacheExpiry(Object argument, HttpServletRequest request) {
		return 300;
	}

	@Override
	public String handleRequest(Object argument, HttpServletRequest request) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", argument);
		List<Demo> result = this.demoDao.search(parameters);
		return JSONHelper.toJSONString(result);
	}

}
