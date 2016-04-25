package com.longyuanlee.dragon.web.errorpage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.longyuanlee.dragon.exception.Constant;
import com.longyuanlee.dragon.exception.StatusCodeException;
import com.longyuanlee.dragon.mvc.JsonView;

public class DragonExceptionResolver implements HandlerExceptionResolver {

	protected Log logger = LogFactory.getLog(DragonExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {

		String uri = request.getRequestURI();

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		Class<?> returnType = handlerMethod.getMethod().getReturnType();

		if (returnType.equals(JsonView.class)) {
			return toJsonView(request, uri, exception);
		} else {
			return null;
		}
	}

	public JsonView toJsonView(HttpServletRequest request, String uri, Exception exception) {
		JsonView jsonView = new JsonView();
		if (exception instanceof StatusCodeException) {
			StatusCodeException e = (StatusCodeException) exception;
			jsonView.setStatus(e.getStatusCode());
			jsonView.setMessage(e.getMessage());
		} else {
			logger.error(String.format("请求异常：uri:%s,exception message:%s,", uri, exception.getMessage()));
			exception.printStackTrace();
			jsonView.setStatus(Constant.code);
			jsonView.setMessage(Constant.message);
		}
		return jsonView;
	}
}
