package com.example.util;

import java.lang.reflect.Method;

/**
 * 反射类获取到方法
 */
public class ReflectUtil {

	/**
	 *
	 * @param clazz 对象类型
	 * @param methodName 方法名
	 * @param type 参数类型
	 * @return
	 */
	public static Method getMethod (Class<?> clazz,String methodName,Class<?> type) {
		Method method = null;
		try {
			method = clazz.getMethod(methodName, type);
		} catch (Exception e) {
			method = null;
		}
		
		return method;
	}
	
	public static Method getMethod (Class<?> clazz,String methodName,Class<?>[] type) {
		Method method = null;
		try {
			method = clazz.getMethod(methodName, type);
		} catch (Exception e) {
			method = null;
		}
		
		return method;
	}

	public static Method getMethod (Class<?> clazz,String methodName) {
		Method method = null;
		try {
			method = clazz.getMethod(methodName);
		} catch (Exception e) {
			method = null;
		}
		
		return method;
	}
}
