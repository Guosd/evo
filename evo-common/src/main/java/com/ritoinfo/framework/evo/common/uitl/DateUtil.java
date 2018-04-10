package com.ritoinfo.framework.evo.common.uitl;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-03-09 14:54
 */
@Slf4j
public class DateUtil {
	private static final String PATTERN_DATE = "yyyy-MM-dd";
	private static final String PATTERN_DATE_COMPACT = "yyyyMMdd";
	private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	private static final String PATTERN_DATE_TIME_COMPACT = "yyyyMMddHHmmss";

	public static Date parseDate(String str) {
		return parse(str, PATTERN_DATE);
	}

	public static Date parseDateCompact(String str) {
		return parse(str, PATTERN_DATE_COMPACT);
	}

	public static Date parseDatetime(String str) {
		return parse(str, PATTERN_DATE_TIME);
	}

	public static Date parseDatetimeCompact(String str) {
		return parse(str, PATTERN_DATE_TIME_COMPACT);
	}

	public static Date parse(String str, String pattern) {
		Date date = null;
		try {
			date = new SimpleDateFormat(pattern).parse(str);
		} catch (ParseException e) {
			log.error("日期解析失败", e);
		}
		return date;
	}

	public static String formatDate(Date date) {
		return format(date, PATTERN_DATE);
	}

	public static String formatDateCompact(Date date) {
		return format(date, PATTERN_DATE_COMPACT);
	}

	public static String formatDatetime(Date date) {
		return format(date, PATTERN_DATE_TIME);
	}

	public static String formatDatetimeCompact(Date date) {
		return format(date, PATTERN_DATE_TIME_COMPACT);
	}

	public static String format(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static Date removeHMS(Date date) {
		return parseDate(formatDate(date));
	}

	public static Date now() {
		return DateTime.now().toDate();
	}

	public static Date plusMinutes(Date date, int minutes){
		return new DateTime(date).plusMinutes(minutes).toDate();
	}
}
