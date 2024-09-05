package com.jz.test.redistest.util;


public class UrlStringUtils {

	public static Integer toInt(Object input) {
		try {
			return Integer.parseInt(String.valueOf(input));
		} catch (Exception e) {
			return 0;
		}
	}


	public static Long toLong(Object input) {
		try {
			return Long.parseLong(String.valueOf(input));
		} catch (Exception e) {
			return 0L;
		}
	}

	public static Float toFloat(Object input) {
		try {
			return Float.parseFloat(String.valueOf(input));
		} catch (Exception e) {
			return 0.0f;
		}
	}

	public static Double toDouble(Object input) {
		try {
			Double datas = Double.parseDouble(String.valueOf(input));
			if (Double.isNaN(datas) || Double.isInfinite(datas)) {
				return 0.0;
			} else {
				return datas;
			}
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static String toTrim(Object input) {
		if (input == null) {
			return null;
		}
		return String.valueOf(input).trim();
	}

	public static boolean isBlank(Object str) {
		try {
			if (str == null || String.valueOf(str).length() == 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
