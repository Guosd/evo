package com.ritoinfo.framework.evo.sp.base.assist;

/**
 * User: Kyll
 * Date: 2018-03-08 15:36
 */
public interface Converter<T, E> {
	void convert(T t, E e);
}
