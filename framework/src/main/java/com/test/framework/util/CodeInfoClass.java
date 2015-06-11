package com.test.framework.util;

public class CodeInfoClass {

	// 全局项目名称
	public static final String groupName = "com.test";

	// 全局项目名称层级数
	public static final int groupLevel = groupName.split("\\.").length;

	// mapper文件存放路径，groupName+modelName+mapperConfigLocation构成完整路径
	public static final String mapperConfigLocation = "dao";

	public static final String mapperSuffix = "Mapper";

	public static final int failInt = Integer.MIN_VALUE;

	public static final long failLong = Integer.MIN_VALUE;

	public static final Object failObject = null;

	public static final String USER_ID = "userid";

	public static final String USER_NAME = "username";

	// 不存在
	public static final int NOT_EXIST = 100;

	// 已经存在
	public static final int HAS_EXIST = 101;
	
	public static final int COMMON_FAIL = 102;

	public static final int SUCCESS = 103;
	
	//参数错误
	public static final int PARAM_ERROR=104;

}
