package com.github.framework.evo.common.uitl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.framework.evo.common.exception.JsonOperateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: Kyll
 * Date: 2018-04-02 09:16
 */
@Slf4j
public class JsonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
		mapper.setDefaultPropertyInclusion(JsonInclude.Value.construct(JsonInclude.Include.ALWAYS, JsonInclude.Include.NON_NULL));
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}

	/**
	 * 生成集合类型
	 * @param collectionClass 集合类型
	 * @param elementClass 元素类型
	 * @return JSON 转换时所需的 JavaType
	 */
	public static JavaType getCollectionType(Class collectionClass, Class elementClass) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
	}

	/**
	 * 读取 JSON 字符串， 转换为节点树
	 * @param json JSON 字符串
	 * @return JsonNode
	 */
	public static JsonNode jsonToNode(String json) {
		try {
			return mapper.readTree(json);
		} catch (IOException e) {
			throw new JsonOperateException("读取 JSON 失败", e);
		}
	}

	/**
	 * 读取 JSON 输入流， 转换为节点树
	 * @param inputStream JSON 输入流
	 * @return JsonNode
	 */
	public static JsonNode jsonToNode(InputStream inputStream) {
		try {
			return mapper.readTree(inputStream);
		} catch (IOException e) {
			throw new JsonOperateException("读取 JSON 失败", e);
		}
	}

	/**
	 * JSON 字符串转换为特定类型对象
	 * @param json JSON 字符串
	 * @param clazz 对象类
	 * @param <T> 对象类型
	 * @return 特定类型对象
	 */
	public static <T> T jsonToObject(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new JsonOperateException("JSON 转换对象失败", e);
		}
	}

	/**
	 * JSON 字符串转换为特定类型对象
	 * @param inputStream JSON 输入流
	 * @param clazz 对象类
	 * @param <T> 对象类型
	 * @return 特定类型对象
	 */
	public static <T> T jsonToObject(InputStream inputStream, Class<T> clazz) {
		try {
			return mapper.readValue(inputStream, clazz);
		} catch (IOException e) {
			throw new JsonOperateException("JSON 转换对象失败", e);
		}
	}

	/**
	 * JSON 字符串转换为特定类型对象
	 * @param bytes JSON 字节数组
	 * @param clazz 对象类
	 * @param <T> 对象类型
	 * @return 特定类型对象
	 */
	public static <T> T jsonToObject(byte[] bytes, Class<T> clazz) {
		try {
			return mapper.readValue(bytes, clazz);
		} catch (IOException e) {
			throw new JsonOperateException("JSON 转换对象失败", e);
		}
	}

	/**
	 * JSON 字符串转换为特定集合类型
	 * @param json JSON 字符串
	 * @param collectionClass 集合类
	 * @param elementClass 元素类
	 * @param <C> 集合类型
	 * @param <E> 元素类型
	 * @return 特定集合类型
	 */
	public static <C, E> C jsonToCollection(String json, Class<C> collectionClass, Class<E> elementClass) {
		try {
			return mapper.readValue(json, getCollectionType(collectionClass, elementClass));
		} catch (IOException e) {
			throw new JsonOperateException("JSON 转换集合失败", e);
		}
	}

	/**
	 * JSON 字符串转换为特定集合类型
	 * @param inputStream JSON 输入流
	 * @param collectionClass 集合类
	 * @param elementClass 元素类
	 * @param <C> 集合类型
	 * @param <E> 元素类型
	 * @return 特定集合类型
	 */
	public static <C, E> C jsonToCollection(InputStream inputStream, Class<C> collectionClass, Class<E> elementClass) {
		try {
			return mapper.readValue(inputStream, getCollectionType(collectionClass, elementClass));
		} catch (IOException e) {
			throw new JsonOperateException("JSON 转换集合失败", e);
		}
	}

	/**
	 * 对象转换为字符串
	 * @param object 输入对象
	 * @return String JSON 字符串
	 */
	public static String objectToJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new JsonOperateException("对象转换 JSON 失败", e);
		}
	}
}
