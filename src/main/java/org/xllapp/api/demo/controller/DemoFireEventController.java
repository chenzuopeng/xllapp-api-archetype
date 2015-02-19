package org.xllapp.api.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.xllapp.api.core.BaseController;
import org.xllapp.api.core.event.EventFirer;
import org.xllapp.api.core.vo.ApiResponse;
import org.xllapp.api.core.vo.ResultCode;
import org.xllapp.api.util.JSONHelper;
import org.xllapp.api.util.RequestUtils;
import org.xllapp.jms.JMSProducer;

/**
 * 触发事件的例子.
 *
 * @author dylan.chen Aug 18, 2014
 * 
 */
public class DemoFireEventController extends BaseController implements EventFirer {

	private JMSProducer jmsProducer;

	@Override
	public Object resolveAndVerifyArgument(HttpServletRequest request) throws Exception {
		return RequestUtils.resolveBody(request);
	}

	@Override
	public String handleRequest(Object requestArgument, HttpServletRequest request) throws Exception {
		return JSONHelper.toJSONString(new ApiResponse(ResultCode.SUCCESS, ResultCode.SUCCESS.getDesc()));
	}

	@Override
	public void fireEvent(Class<?> clazz, HttpServletRequest request, final Object requestArgument, String response) {
		if (null != this.jmsProducer) {
			this.jmsProducer.sendAsync("demo-event", new JMSProducer.MessageBuilder() {
				@Override
				public Object buildMessage() {
					return requestArgument;
				}
			});
		}
	}

	public void setJmsProducer(JMSProducer jmsProducer) {
		this.jmsProducer = jmsProducer;
		setEventFirer(this);//这行很关键
	}

}
