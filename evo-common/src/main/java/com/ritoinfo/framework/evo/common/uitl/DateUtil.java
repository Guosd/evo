package com.ritoinfo.framework.evo.common.uitl;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2018-03-09 14:54
 */
public class DateUtil {
	public static Date now() {
		return DateTime.now().toDate();
	}

	public static Date plusMinutes(Date date, int minutes){
		return new DateTime(date).plusMinutes(minutes).toDate();
	}
}
