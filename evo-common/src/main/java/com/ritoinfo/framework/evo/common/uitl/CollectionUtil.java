package com.ritoinfo.framework.evo.common.uitl;

import java.util.Iterator;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-11-18 11:47
 */
public class CollectionUtil {
	public static void trim(Map map) {
		Iterator it  = map.keySet().iterator();
		while (it.hasNext()) {
			if (map.get(it.next()) == null) {
				it.remove();
			}
		}
	}
}
