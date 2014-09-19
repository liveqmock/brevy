package com.brevy.fw.common.util;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 主要用来调试(复杂)对象，输出对象的详细内容
 * @author modify by caobin
 * @date 2013-7-24
 * @version 1.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ObjectDebugUtils {

	/**
	 * @Description 解析复杂类型
	 * @param scope
	 * @param parentObject
	 * @param visitedObjs
	 * @return
	 * @author modify by caobin
	 */
	private static String complexTypeToString(String scope, Object parentObject, List visitedObjs) {
		StringBuffer buffer = new StringBuffer("");
		try {
			Class cl = parentObject.getClass();
			while (cl != null) {
				processFields(cl.getDeclaredFields(), scope, parentObject, buffer, visitedObjs);
				cl = cl.getSuperclass();
			}
		} catch (IllegalAccessException iae) {
			buffer.append(iae.toString());
		}
		return (buffer.toString());
	}

	/**
	 * @Description 反射字段处理
	 * @param fields
	 * @param scope
	 * @param parentObject
	 * @param buffer
	 * @param visitedObjs
	 * @throws IllegalAccessException
	 * @author modify by caobin
	 */
	private static void processFields(Field[] fields, String scope, Object parentObject, StringBuffer buffer, List visitedObjs) 
			throws IllegalAccessException {
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals("__discriminator") || fields[i].getName().equals("__uninitialized"))continue;
			if (!fields[i].isAccessible())fields[i].setAccessible(true);
			if (!Modifier.isStatic(fields[i].getModifiers())) {
				buffer.append(typeToString(scope.concat(".").concat(fields[i].getName()), fields[i].get(parentObject), visitedObjs));
			} 
		}
	}

	/**
	 * @Description 集合类型判断
	 * @param obj
	 * @return
	 * @author modify by caobin
	 */
	public static boolean isCollectionType(Object obj) {
		
		return (
				obj.getClass().isArray() 
				|| 
				obj instanceof Collection 
				|| 
				obj instanceof Map 
				|| 
				obj instanceof Set
		);
	}


	/**
	 * @Description 判断是否复杂类型
	 * @param obj
	 * @return
	 * @author modify by caobin
	 */
	public static boolean isComplexType(Object obj) {
		return !(obj.getClass().isPrimitive() || obj instanceof String);
	}

	/**
	 * @Description 解析集合类型
	 * @param scope
	 * @param obj
	 * @param visitedObjs
	 * @return
	 * @author modify by caobin
	 */
	private static String collectionTypeToString(String scope, Object obj,
			List visitedObjs) {
		StringBuffer buffer = new StringBuffer("");
		if(obj == null){
			buffer.append(scope.concat("[]: null\n"));
		}else if (obj.getClass().isArray()) {
			if (Array.getLength(obj) > 0) {
				for (int j = 0; j < Array.getLength(obj); j++) {
					Object x = Array.get(obj, j);
					buffer.append(typeToString(scope.concat("[").concat(String.valueOf(j)).concat("]"), x, visitedObjs));
				}
			} else {
				buffer.append(scope.concat("[]: empty\n"));
			}
		} else {
			boolean isCollection = (obj instanceof Collection);
			boolean isHashTable = (obj instanceof Hashtable);
			boolean isHashMap = (obj instanceof HashMap);
			boolean isHashSet = (obj instanceof HashSet);
			boolean isAbstractMap = (obj instanceof AbstractMap);
			boolean isMap = isAbstractMap || isHashMap || isHashTable;
			if (isMap) {
				Set keySet = ((Map) obj).keySet();
				Iterator iterator = keySet.iterator();
				int size = keySet.size();
				if (size > 0) {
					while (iterator.hasNext()) {
						Object key = iterator.next();
						Object x = ((Map) obj).get(key);
						buffer.append(typeToString(scope.concat("[\"").concat(String.valueOf(key)).concat("\"]"), x, visitedObjs));
					}
				} else {
					buffer.append(scope.concat("[]: empty\n"));
				}
			} else if (isCollection || isHashSet) {
				Iterator iterator = null;
				int size = 0;		
				if (isCollection) {
					iterator = ((Collection) obj).iterator();
					size = ((Collection) obj).size();
				} else if (isHashTable) {
					iterator = ((Hashtable) obj).values().iterator();
					size = ((Hashtable) obj).size();
				} else if (isHashSet) {
					iterator = ((HashSet) obj).iterator();
					size = ((HashSet) obj).size();
				} else if (isHashMap) {
					iterator = ((HashMap) obj).values().iterator();
					size = ((HashMap) obj).size();
				}
				if (size > 0) {
					for (int j = 0; iterator.hasNext(); j++) {
						Object x = iterator.next();
						buffer.append(typeToString(scope.concat("[").concat(String.valueOf(j)).concat("]"), x, visitedObjs));
					}
				} else {
					buffer.append(scope.concat("[]: empty\n"));
				}			
			}
		}
		return (buffer.toString());
	}

	/**
	 * @Description 输出传递(复杂)对象的明细内容
	 * @param scope
	 * @param obj
	 * @param visitedObjs
	 * @return
	 * @author modify by caobin
	 */
	
	private static String typeToString(String scope, Object obj, List visitedObjs) {
		if (obj == null) {
			return (scope.concat(": null\n"));
		} else if (isCollectionType(obj)) {
			return collectionTypeToString(scope, obj, visitedObjs);
		} else if (isComplexType(obj)) {
			if (!visitedObjs.contains(obj)) {
				visitedObjs.add(obj);
				return complexTypeToString(scope, obj, visitedObjs);
			} else {
				return (scope.concat(": <already visited>\n"));
			}
		} else {
			return (scope.concat(": ").concat(obj.toString()).concat("\n"));
		}
	}


	/**
	 * @Description 输出传递(复杂)对象的明细内容
	 * @param scope 前缀域
	 * @param obj 需要解析的对象
	 * @return
	 * @author modify by caobin
	 */
	public static String typeToString(String scope, Object obj) {
		if(scope == null){
			scope = "";
		}
		if (obj == null) {
			return (scope.concat(": null\n"));
		} else if (isCollectionType(obj)) {
			return collectionTypeToString(scope, obj, new ArrayList());
		} else if (isComplexType(obj)) {
			return complexTypeToString(scope, obj, new ArrayList());
		} else {
			return (scope.concat(": ").concat(obj.toString()).concat("\n"));
		}
	}
}
