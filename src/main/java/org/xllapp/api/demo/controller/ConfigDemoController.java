package org.xllapp.api.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.xllapp.api.core.JSONController;
import org.xllapp.api.core.exception.ApiException;
import org.xllapp.api.core.exception.InvalidRequestArgumentException;
import org.xllapp.service.config.ConfigService;

/**
 *
 *
 * @author dylan.chen Jan 15, 2015
 * 
 */
public class ConfigDemoController extends JSONController {
	
	@Autowired
	private ConfigService configService;
	
	@Override
	public Object resolveAndVerifyArgument(HttpServletRequest request) throws InvalidRequestArgumentException {
		return null;
	}

	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument) throws InvalidRequestArgumentException {
	}

	@Override
	public String[] getSignItems(Map<String, Object> requestArgument) {
		return null;
	}

	@Override
	public Object handleRequest(Map<String, Object> requestArgument) throws ApiException {
		try {
			return configService.getGroup("a");
		} catch (Exception e) {
			throw new ApiException(e);
		}
	}

}
