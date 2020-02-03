package com.github.framework.evo.common.uitl;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象深层复制工具
 */
public class BeanCopyUtils {

	private static MapperFacade mapper;
	private static MapperFacade notNullMapper;

	static {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapper = mapperFactory.getMapperFacade();
		MapperFactory notNullMapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
		notNullMapper = notNullMapperFactory.getMapperFacade();
	}

	/**
	 * 复制对象所有属性
	 * 
	 * @param source      源对象
	 * @param destination 目标对象
	 */
	public static void copy(Object source, Object destination) {
		mapper.map(source, destination);
	}

	/**
	 * 复制对象非null属性
	 * 
	 * @param source      源对象
	 * @param destination 目标对象
	 */
	public static void copyNotNull(Object source, Object destination) {
		notNullMapper.map(source, destination);
	}

	/**
	 * 深度复制对象
	 * 
	 * @param source           源对象
	 * @param destinationClass 目标类型
	 * @return 复制出的目标对象
	 */
	public static <T> T clone(Object source, Class<T> destinationClass) {
		return mapper.map(source, destinationClass);
	}

	/**
	 * 复制List
	 * 
	 * @param sourceList       源List
	 * @param destinationClass 目标List的元素类型
	 * @return 复制出的目标List
	 */
	public static <T> List<T> cloneList(List<?> sourceList, Class<T> destinationClass) {
		List<T> destList = new ArrayList<>(sourceList.size());
		for (Object source : sourceList) {
			T destination = clone(source, destinationClass);
			destList.add(destination);
		}
		return destList;
	}

	private BeanCopyUtils() {
	}

}
