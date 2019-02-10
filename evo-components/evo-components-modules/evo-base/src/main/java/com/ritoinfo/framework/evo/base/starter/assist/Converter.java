package com.ritoinfo.framework.evo.base.starter.assist;

/**
 * User: Kyll
 * Date: 2018-03-08 15:36
 */
public interface Converter<Dest, Orig> {
	void convert(Dest dest, Orig orig);
}
