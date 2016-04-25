package com.longyuanlee.dragon.mvc;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.longyuanlee.dragon.utils.json.DateJson;
import com.longyuanlee.dragon.utils.json.Json;

public class AjaxView extends AbstractView {

	private int status;
	private String message;
	private Object data;

	public AjaxView() {

	}

	public AjaxView(Object data) {
		this(data, false);
	}

	public AjaxView(Object data, boolean checkXss) {
		this(200, data);
	}

	public AjaxView(int status, Object data) {
		this.status = status;
		this.data = data;
		this.message = "";
	}

	public AjaxView(int status, Object data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getContentType() {
		return "text/plain; charset=UTF-8";
	}

	@Override
	public String getBody(HttpServletRequest request, HttpServletResponse response) {
		boolean format = "true".equals(request.getParameter("format"));
		String dateFormat = request.getParameter("dateFormat");
		Object result = this.getResult();
		return new JsonOutput().output(result, format, dateFormat, request);
	}

	/**
	 * 返回结果.
	 * 
	 * @return
	 */
	protected Map<String, Object> getResult() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("status", this.getStatus());
		map.put("message", this.message);
		map.put("data", this.getData());
		return map;
	}

	public static boolean useStringDateFormat = false;// 是否使用字符串的日期.

	/**
	 * 设置日期类型字段返回字符串日期格式.
	 * 
	 * @param useStringDateFormat
	 */
	public static void setUseStringDateFormat(boolean useStringDateFormat) {
		AjaxView.useStringDateFormat = useStringDateFormat;
	}

	public static class JsonOutput {

		public String output(Object data, boolean format, String dateFormat, HttpServletRequest request) {
			if (StringUtils.isEmpty(dateFormat)) {
				if (useStringDateFormat) {
					return toStringDateJson(data, format);
				} else {
					return toTimestampJson(data, format);
				}
			} else if ("string".equals(dateFormat)) {
				return toStringDateJson(data, format);
			} else {
				return toTimestampJson(data, format);
			}
		}

		protected String toTimestampJson(Object data, boolean format) {
			if (format) {
				return Json.toFormatJson(data);
			} else {
				return Json.toJson(data);
			}
		}

		protected String toStringDateJson(Object data, boolean format) {
			if (format) {
				return DateJson.toFormatJson(data);
			} else {
				return DateJson.toDateJson(data);
			}
		}
	}

}
