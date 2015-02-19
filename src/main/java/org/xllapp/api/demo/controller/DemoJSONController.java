package org.xllapp.api.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.xllapp.api.core.JSONController;
import org.xllapp.api.core.exception.ApiException;
import org.xllapp.api.core.exception.InvalidRequestArgumentException;
import org.xllapp.api.util.RequestArgumentAssert;

/**
 * 请求：
 * {  
    "product_id": "icity", 
    "client_verion": "200", 
    "org_code": "3501", 
    "client_channel_type": "icity-ver", 
    "os_type": "android", 
    "imsi": "123",
    "imei": "abc", 
    "mobile": "18900000000", 
    "timestamp": "2022-09-16 17:08:00", 
    "sign": "9qS0v1ImAICQbT8qFx6TAMZnLrOXPQBK6L%2FL%2Fhg4KuC%2BRw3m9RQ1ilTvzx7ovkzyyPFWHva0H%2BQ%3D"
   }
 * 
 * sign规则:URLEncoding(BASE64(3DES(timestamp+$+MD5(imsi+$+imei+$+app_key+$+timestamp))))
 * 
 * 响应：
 *
 *{
    "result_code": "0",
    "result_desc": "request successful",
    "timestamp": "2014-09-18 10:40:27",
    "data": {
        "user_name": "abc",
    }
 }
 *
 * @author dylan.chen Sep 18, 2014
 * 
 */
public class DemoJSONController extends JSONController{
	
	/**
	 * 验证业务参数
	 */
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument) throws InvalidRequestArgumentException {
		RequestArgumentAssert.assertNotBlankString(requestArgument, "mobile"); //验证电话号码是否为空
	}

	/**
	 * 返回生成签名中MD5部分需要的请求参数值.注意返回数组中元素的顺序与签名规则中的一致,无需包含app_key和timestamp.详见JSONController.getSignItems()方法的注释.
	 */
	@Override
	public String[] getSignItems(Map<String, Object> requestArgument) {
		return new String[]{(String)requestArgument.get("imsi"),(String)requestArgument.get("imei")};
	}

	/**
	 * 处理请求
	 */
	@Override
	public Object handleRequest(Map<String, Object> requestArgument) throws ApiException {
		
/*		//也可以使用map返回数据
		Map<String,String> data=new HashMap<String, String>();
		data.put("user_name","abc");
		return data;*/
		
		/**
		 * 将对象转换成JSON时会自动将驼峰方式命名的属性名转换成小写加下划线方式
		 */
		return new DemoResponseData("abc");
	}
	
	public static class DemoResponseData{
		
		private String userName;
		
		public DemoResponseData(String userName) {
			super();
			this.userName = userName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
		
	}

}
