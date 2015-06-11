package com.test.framework.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.test.framework.basemapper.BaseMapper;
import com.test.framework.domain.BaseBo;

public class ObjectGerateUtil {

	private static final Logger logger = Logger.getLogger(ObjectGerateUtil.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T insertAndReturnTargetClassInstance(BaseBo sourceObj, Class<T> targetClass,
			BaseMapper baseMapperInf) {
		boolean flag = baseMapperInf.insert(sourceObj);
		if (!flag) {
			return null;
		}

		return copyAndGenerateObj(baseMapperInf.selectPoByPrimaryKey(sourceObj.getId()), targetClass);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T updateAndReturnTargetClassInstance(BaseBo sourceObj, Class<T> targetClass,
			BaseMapper baseMapperInf) {
		int count = baseMapperInf.update(sourceObj);
		if (count<=0) {
			return null;
		}

		return copyAndGenerateObj(baseMapperInf.selectPoByPrimaryKey(sourceObj.getId()), targetClass);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T, Element> List<T> getFiledValueListFromListObject(List<Element> sourceObjList, String fieldName,
			Class<T> filedClass) {
		List<T> fieldValueList = new ArrayList<T>();
		if (sourceObjList == null || sourceObjList.size() <= 0 || StringUtils.isEmpty(fieldName) || filedClass == null) {
			logger.error("param is null,getFiledValueListFromListObject");
			return fieldValueList;
		}

		try {
			Class objClass = sourceObjList.get(0).getClass();
			Field field = getFieldFromClassWithSuper(objClass, fieldName);
			for (Element element : sourceObjList) {
				T fieldValue = (T) field.get(element);
				fieldValue = (T) field.get(element);
				fieldValueList.add(fieldValue);
			}

		} catch (IllegalArgumentException e) {
			logger.error("getFiledValueListFromListObject err", e);
		} catch (IllegalAccessException e) {
			logger.error("getFiledValueListFromListObject err", e);
		} catch (SecurityException e) {
			logger.error("getFiledValueListFromListObject err", e);
		} catch (Exception e) {
			logger.error("getFiledValueListFromListObject err", e);
		}

		return fieldValueList;
	}

	/**
	 * 获取对应claz的field,如果claz本身找不到则查找父类
	 * 
	 * @param claz
	 * @param fiedname
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Field getFieldFromClassWithSuper(Class claz, String fiedname) {
		Class searchClaz;
		Field field = null;
		for (searchClaz = claz; searchClaz != Object.class; searchClaz = searchClaz.getSuperclass()) {
			try {
				field = searchClaz.getDeclaredField(fiedname);
				if (field != null) {
					break;
				}
			} catch (NoSuchFieldException e) {
			} catch (SecurityException e) {
			}
		}
		field.setAccessible(true);
		return field;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <KEY, ELEMENT> Map<KEY, ELEMENT> groupMapByKeyAndSingleValue(String keyName, Class<KEY> keyClass,
			List<ELEMENT> objectList) {
		Map<KEY, ELEMENT> groupMap = new HashMap<KEY, ELEMENT>();
		if (objectList == null || objectList.size() <= 0) {
			return groupMap;
		}

		// 获取field对象
		try {
			Class elementCalss = objectList.get(0).getClass();
			Field keyField = getFieldFromClassWithSuper(elementCalss, keyName);
			for (ELEMENT vo : objectList) {
				KEY keyValue = (KEY) keyField.get(vo);
				groupMap.put(keyValue, vo);
			}
		} catch (IllegalArgumentException e) {
			logger.error("err groupListByKey", e);
		} catch (IllegalAccessException e) {
			logger.error("err groupListByKey", e);
		} catch (SecurityException e) {
			logger.error("err groupListByKey", e);
		} catch (Exception e) {
			logger.error("err groupListByKey", e);
		}
		return groupMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <KEY, ELEMENT> Map<KEY, List<ELEMENT>> groupMapByByKeyAndListValue(String keyName,
			Class<KEY> keyClass, List<ELEMENT> objectList) {
		Map<KEY, List<ELEMENT>> groupMap = new HashMap<KEY, List<ELEMENT>>();
		if (objectList == null || objectList.size() <= 0) {
			return groupMap;
		}

		// 获取field对象
		try {
			Class elementCalss = objectList.get(0).getClass();
			Field keyField = getFieldFromClassWithSuper(elementCalss, keyName);
			for (ELEMENT vo : objectList) {
				KEY keyValue = (KEY) keyField.get(vo);
				List<ELEMENT> tempList = groupMap.get(keyValue);
				if (tempList == null) {
					tempList = new ArrayList<ELEMENT>();
					groupMap.put(keyValue, tempList);
				}
				tempList.add(vo);
			}
		} catch (IllegalArgumentException e) {
			logger.error("err groupListByKey", e);
		} catch (IllegalAccessException e) {
			logger.error("err groupListByKey", e);
		} catch (SecurityException e) {
			logger.error("err groupListByKey", e);
		} catch (Exception e) {
			logger.error("err groupListByKey", e);
		}
		return groupMap;
	}

	/**
	 * 将sourceObj的值拷入，新创建targetClass类型对象中并返回此对象
	 * 
	 * @param sourceObj
	 * @param targetClass
	 * @return
	 */
	public static <T> T copyAndGenerateObj(Object sourceObj, Class<T> targetClass) {
		T targetObj = null;
		try {
			targetObj = targetClass.newInstance();
		} catch (InstantiationException e) {
			logger.error("gerate class InstantiationException err", e);
		} catch (IllegalAccessException e) {
			logger.error("gerate class IllegalAccessException err", e);
		}

		BeanUtils.copyProperties(sourceObj, targetObj);
		return targetObj;
	}

}
