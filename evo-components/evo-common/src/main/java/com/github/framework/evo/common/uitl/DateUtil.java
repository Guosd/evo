package com.github.framework.evo.common.uitl;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
		return Date.from(LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern)).atZone(ZoneId.systemDefault()).toInstant());
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
		return date == null ? null : DateTimeFormatter.ofPattern(pattern).format(LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault()));
	}

	public static Date toDate(long millis) {
		return new DateTime(millis).toDate();
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
