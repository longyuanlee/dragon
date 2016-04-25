package com.longyuanlee.dragon.utils.json;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class DateJson {
	private static ObjectMapper mapper = new ObjectMapper();
	private static ObjectWriter formatWriter = mapper.writer().withDefaultPrettyPrinter()
			.withDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
	private static ObjectWriter dateWriter = mapper.writer()
			.withDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

	/**
	 * 将对象转成json.
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	public static String toFormatJson(Object obj) {
		return Json.toJson(formatWriter, obj, "com.duowan.leopard.json.DateJson.toFormatJson");
	}

	public static String toDateJson(Object obj) {
		return Json.toJson(dateWriter, obj, "com.duowan.leopard.json.DateJson.toDateJson");
	}

}
