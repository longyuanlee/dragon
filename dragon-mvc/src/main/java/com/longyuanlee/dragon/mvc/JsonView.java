package com.longyuanlee.dragon.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.longyuanlee.dragon.utils.json.DateJson;
import com.longyuanlee.dragon.utils.json.Json;

public class JsonView extends AjaxView {
	public JsonView() {

	}

	public JsonView(Object data) {
		this(data, false);
	}

	public JsonView(Object data, boolean checkXss) {
		super(data, checkXss);
	}

	public JsonView(int status, Object data) {
		super(status, data);
	}

	public JsonView(int status, Object data, String message) {
		super(status, data, message);
	}

	@Override
	public String getBody(HttpServletRequest request, HttpServletResponse response) {
		boolean format = "true".equals(request.getParameter("format"));
		String dateFormat = request.getParameter("dateFormat");

		Object result = this.getResult();
		{
			String callback = request.getParameter("callback");
			if (StringUtils.isNotEmpty(callback)) {
				// JSON劫持漏洞防范
				// RefererSecurityValidator.checkReferer(request);
				return new JsonpOutput(callback).output(result, format, dateFormat, request);
			}
		}
		{
			String var = request.getParameter("var");
			if (StringUtils.isNotEmpty(var)) {
				// JSON劫持漏洞防范
				// RefererSecurityValidator.checkReferer(request);
				return new ScriptOutput(var).output(result, format, dateFormat, request);
			}
		}
		return new JsonOutput().output(result, format, dateFormat, request);
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

	public static class JsonpOutput extends JsonOutput {

		protected static final Log logger = LogFactory.getLog(JsonpOutput.class);

		private final String callback;

		public JsonpOutput(String callback) {
			this.callback = callback;
		}

		@Override
		public String output(Object data, boolean format, String dateFormat, HttpServletRequest request) {
			try {

				String json = super.output(data, format, dateFormat, request);
				return callback + "(" + json + ");";
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return "// " + e.getMessage();
			}

		}
	}

	public static class ScriptOutput extends JsonOutput {

		protected static final Log logger = LogFactory.getLog(ScriptOutput.class);
		private final String var;

		public ScriptOutput(String var) {
			this.var = var;
		}

		@Override
		public String output(Object data, boolean format, String dateFormat, HttpServletRequest request) {
			try {
				String json = super.output(data, format, dateFormat, request);
				return "var " + var + " = " + json + ";";
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return "// " + e.getMessage();
			}

		}
	}
}
