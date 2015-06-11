package com.test.framework.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 资源文本工具类
 * @author hzzhaozhenzuo
 *
 */
public class ResourceTextUtil {
	
	public static ResourceBundle getResourceBundleByName(String fileName){
		return ResourceBundle.getBundle(fileName);
	}
	
	public static String getTextFromResourceByKey(ResourceBundle resourceBundle,String key,Object...params){
		String value=resourceBundle.getString(key);
		return MessageFormat.format(value, params);
	}
}
