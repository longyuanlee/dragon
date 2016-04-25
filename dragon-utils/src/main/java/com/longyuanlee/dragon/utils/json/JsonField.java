package com.longyuanlee.dragon.utils.json;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class JsonField {

	public static Integer getInteger(String json, String field) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		Map<String, Object> map = Json.toMap(json, Object.class);
		Integer num = (Integer) map.get(field);
		return num;
	}

	public static String getString(String json, String field) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		Map<String, Object> map = Json.toMap(json, Object.class);
		String str = (String) map.get(field);
		return str;
	}

}
