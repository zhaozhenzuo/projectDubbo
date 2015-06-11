package com.test.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.test.framework.annotation.Column;
import com.test.framework.annotation.ID;
import com.test.framework.annotation.Table;

public class GeneratorUtil {

	private static final ResourceBundle generatorBundle = ResourceBundle.getBundle("config.generator");

	private static final String START_KEY = "start";

	private static final String END_KEY = "end";

	private static final String INSERT_STATEMENT = "insert";

	// private static final String SEARCH_VO_STATEMENT = "selectVoBySo";

	private static final String SEARCH_PO_STATEMENT = "selectPoBySo";

	private static final String SEARCH_COUNT_STATEMENT = "selectCount";

	private static final String UPDATE_STATEMENT = "update";

	private static final String DELETE_STATEMENT = "delete";

	private static final String MAPPER_SQL_FIRST_BLANK = "  ";

	private static final String MAPPER_SQL_SENCOND_BLANK = "		";

	private static String tableName;

	private static String PO_CLASS_NAME;
	
	private static String VO_CLASS_NAME;

	private static String VO_CLASS_NAME_FULL;

	private static String targetClassName;
	
	/**
	 * 里面每个元素代表一个属性值： key为 po属性名:po属性类型 value为 po属性名对应数据库字段名
	 */
	private static final Map<String, String> propertyToColumnMap = new LinkedHashMap<String, String>();

	private static final String DECIMAL_SPLIT = ":";

	private static final String BLANK_STR = "  ";

	private static final java.util.List<String> tablePrimayKeyOrIndexList = new ArrayList<String>();

	public static String getModleName(String source) {
		String[] arr = source.split("\\.");
		String modleName = arr[CodeInfoClass.groupLevel];
		return modleName;
	}
	
	private static String getVoClassName(String targetClassName){
		String[] arr =targetClassName.split("\\.");
		StringBuilder builder=new StringBuilder();
		//group name
		builder.append(CodeInfoClass.groupName);
		
		//model name
		builder.append(".").append(arr[CodeInfoClass.groupLevel]);
		
		//vo name
		builder.append(".vo.").append(arr[arr.length-1]).append("Vo");
		return builder.toString();
	}
	
	public static void doGenerateSql() throws IOException {
		InputStreamReader inReader = new InputStreamReader(System.in);

		BufferedReader br = new BufferedReader(inReader);

		char cn = '\n';

		int r;

		System.out.println("请输入class文件:");

		StringBuffer strBuffer = new StringBuffer();

		while ((r = (char) br.read()) != cn) {
			strBuffer.append((char) r);
		}

		targetClassName = strBuffer.toString();
		
		init(targetClassName);
		
		//vo class full name
		VO_CLASS_NAME_FULL=getVoClassName(targetClassName);

		StringBuffer buffer = new StringBuffer();

		buffer.append(generatorStart(targetClassName));

		buffer.append(generatorSearchPoBySo(targetClassName));

		buffer.append(generatorSearchCountBySo(targetClassName));

		buffer.append(generatorInsert(targetClassName));

		buffer.append(generatorDelete(targetClassName));

		buffer.append(generatorUpdate(targetClassName));

		buffer.append(generateSearchVos());

		buffer.append(generateSelectPoByPrimaryKey());

		buffer.append(generatePoResultMapStr());

		buffer.append(generateVoResultMapStr());

		buffer.append(generatorEnd(targetClassName));

		System.out.println(buffer.toString());

		System.out.println("--------------table--------------------");
		System.out.println(generateTable());
		System.out.println(getPrimaryKeyOrIndex());
	}

	private static StringBuffer getPrimaryKeyOrIndex() {
		StringBuffer buffer = new StringBuffer();
		for (String sql : tablePrimayKeyOrIndexList) {
			buffer.append(sql + ";");
			buffer.append("\n");
		}
		return buffer;
	}

	private static String generateTable() {
		String src = generatorBundle.getString("create_table");

		StringBuffer resContent = new StringBuffer();
		int count = 0;
		for (Entry<String, String> entry : propertyToColumnMap.entrySet()) {
			if (count > 0) {
				resContent.append("," + "\n");
			}

			String columnName = entry.getValue();
			String[] keys = entry.getKey().split("\\" + DECIMAL_SPLIT);
			String columnType = gettypeInTableByTypeStr(keys[1]);
			resContent.append(BLANK_STR + columnName + BLANK_STR + columnType);
			count++;
		}

		return MessageFormat.format(src, new Object[] { tableName, resContent.toString() }) + "\n";
	}

	private static String generateVoResultMapStr() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format(MAPPER_SQL_FIRST_BLANK + "<resultMap type=\"%s\" id=\"%s\">",
				VO_CLASS_NAME_FULL,convertFirstCharToLower(VO_CLASS_NAME) + "Mapper"));
		buffer.append("\n");

		for (Entry<String, String> entry : propertyToColumnMap.entrySet()) {
			String key = entry.getKey();
			String[] keyArr = key.split("\\" + DECIMAL_SPLIT);
			if (keyArr[0].equalsIgnoreCase("id")) {
				buffer.append(MAPPER_SQL_SENCOND_BLANK + "<id column=\"id\" property=\"id\"/>");
				buffer.append("\n");
			} else {
				buffer.append(MAPPER_SQL_SENCOND_BLANK
						+ String.format("<result column=\"%s\" property=\"%s\"/>", new Object[] { entry.getValue(),
								keyArr[0] }));
				buffer.append("\n");
			}
		}

		buffer.append(MAPPER_SQL_FIRST_BLANK + "</resultMap>");
		buffer.append("\n\n\n");
		return buffer.toString();
	}

	private static String generatePoResultMapStr() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format(MAPPER_SQL_FIRST_BLANK + "<resultMap type=\"%s\" id=\"%s\">", targetClassName,
				convertFirstCharToLower(PO_CLASS_NAME) + "Mapper"));
		buffer.append("\n");

		for (Entry<String, String> entry : propertyToColumnMap.entrySet()) {
			String key = entry.getKey();
			String[] keyArr = key.split("\\" + DECIMAL_SPLIT);
			if (keyArr[0].equalsIgnoreCase("id")) {
				buffer.append(MAPPER_SQL_SENCOND_BLANK + "<id column=\"id\" property=\"id\"/>");
				buffer.append("\n");
			} else {
				buffer.append(MAPPER_SQL_SENCOND_BLANK
						+ String.format("<result column=\"%s\" property=\"%s\"/>", new Object[] { entry.getValue(),
								keyArr[0] }));
				buffer.append("\n");
			}
		}

		buffer.append(MAPPER_SQL_FIRST_BLANK + "</resultMap>");
		buffer.append("\n\n\n");
		return buffer.toString();
	}

	private static String convertFirstCharToLower(String s) {
		String firstChar = s.substring(0, 1).toLowerCase();
		return firstChar + s.substring(1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void init(final String targetClassName) {

		Class clazz = loadClassByName(targetClassName);

		Table tableAno = (Table) clazz.getAnnotation(Table.class);

		PO_CLASS_NAME = clazz.getSimpleName();

		VO_CLASS_NAME = PO_CLASS_NAME + "Vo";

		tableName = tableAno.value();

		List<Field> fieldList = new ArrayList<Field>();
		addField(clazz, fieldList);

		addTheFiledToMap(fieldList);
	}

	@SuppressWarnings("rawtypes")
	private static void addField(Class clazz, List<Field> fieldList) {
		Class tempClass = clazz;
		while (tempClass != null && tempClass != Object.class) {
			Field[] fields = tempClass.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				List<Field> tempFieldList = Arrays.asList(fields);
				fieldList.addAll(tempFieldList);
			}
			tempClass = tempClass.getSuperclass();
		}
	}

	private static String generateSelectPoByPrimaryKey() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format(MAPPER_SQL_FIRST_BLANK + "<select id=\"selectPoByPrimaryKey\" resultMap=\"%s\">",
				new Object[] { convertFirstCharToLower(PO_CLASS_NAME) + "Mapper" }));
		buffer.append("\n");

		buffer.append(MAPPER_SQL_SENCOND_BLANK + String.format("select t.* from %s t", new Object[] { tableName }));
		buffer.append("\n");

		buffer.append(MAPPER_SQL_SENCOND_BLANK + "where t.id=#{id}");
		buffer.append("\n");

		buffer.append(MAPPER_SQL_FIRST_BLANK + "</select>");
		buffer.append("\n\n");
		return buffer.toString();
	}

	private static String generateSearchVos() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format(MAPPER_SQL_FIRST_BLANK + "<select id=\"selectVoBySo\" resultMap=\"%s\">",
				convertFirstCharToLower(VO_CLASS_NAME) + "Mapper"));
		buffer.append("\n");
		buffer.append(MAPPER_SQL_SENCOND_BLANK + String.format("select t.* from %s t", tableName));
		buffer.append("\n");
		buffer.append(MAPPER_SQL_SENCOND_BLANK + "<include refid=\"sqlWhere\" />");
		buffer.append("\n");
		buffer.append(MAPPER_SQL_FIRST_BLANK + "</select>");
		buffer.append("\n\n");
		return buffer.toString();
	}

	private static void addTheFiledToMap(List<Field> fields) {
		for (Field field : fields) {
			if (field.isAnnotationPresent(ID.class)) {
				ID id = field.getAnnotation(ID.class);
				try {
					propertyToColumnMap.put(field.getName() + DECIMAL_SPLIT + field.getType().getSimpleName(),
							id.value());
				} catch (Exception e) {
					e.printStackTrace();
				}

				// we also generate the primary key constraint
				String primayKeyConstraint = "alter table " + tableName + " add primary key(" + field.getName() + ")";
				String autoIncrementConstraint = "alter table " + tableName + " change " + field.getName() + " "
						+ field.getName() + " bigint(19) auto_increment";
				tablePrimayKeyOrIndexList.add(primayKeyConstraint);
				tablePrimayKeyOrIndexList.add(autoIncrementConstraint);
			} else if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);

				String fieldType = field.getType().getSimpleName();

				if (field.getType().isEnum()) {
					fieldType = String.class.getSimpleName();
				}

				propertyToColumnMap.put(field.getName() + DECIMAL_SPLIT + fieldType, column.value());
			}
		}
	}

	private static String generatorStart(String targetClassName) {
		String start = generatorBundle.getString(START_KEY);
		return MessageFormat.format(start, new Object[] { getSpaceName() }) + "\n\n";
	}

	private static String getSpaceName() {
		String res = CodeInfoClass.groupName + "." + getModleName(targetClassName) + "."
				+ CodeInfoClass.mapperConfigLocation + "." + PO_CLASS_NAME + CodeInfoClass.mapperSuffix;
		return res;
	}

	private static String generatorEnd(String targetClassName) {
		String end = generatorBundle.getString(END_KEY);
		return MessageFormat.format(end, new Object[] {}) + "\n";
	}

	private static String generatorSearchPoBySo(String targetClassName) {
		String src = generatorBundle.getString("select");
		return MessageFormat.format(src, new Object[] { SEARCH_PO_STATEMENT, targetClassName, tableName }) + "\n";
	}

	private static String generatorSearchCountBySo(String targetClassName) {
		String src = generatorBundle.getString("selectCount");
		return MessageFormat.format(src, new Object[] { SEARCH_COUNT_STATEMENT, "long", tableName }) + "\n";
	}

	private static String generatorDelete(String targetClassName) {
		String src = generatorBundle.getString(DELETE_STATEMENT);
		return MessageFormat.format(src, new Object[] { tableName, "#{id}" }) + "\n";
	}

	private static String generatorInsert(String targetClassName) {
		String src = generatorBundle.getString(INSERT_STATEMENT);
		StringBuffer columnContent = new StringBuffer();

		StringBuffer valuesContent = new StringBuffer();
		int count = 0;
		for (Entry<String, String> entry : propertyToColumnMap.entrySet()) {
			if (count > 0) {
				columnContent.append("," + "\n");
				valuesContent.append("," + "\n");
			}
			columnContent.append(BLANK_STR + entry.getValue());

			String[] keys = entry.getKey().split("\\" + DECIMAL_SPLIT);
			valuesContent.append(BLANK_STR + "#{" + keys[0] + ",");
			valuesContent.append(getStringByType(keys[1]));
			valuesContent.append("}");
			count++;
		}

		return MessageFormat.format(src, new Object[] { targetClassName, tableName, columnContent.toString(),
				valuesContent.toString() })
				+ "\n";
	}

	private static String getStringByType(String type) {
		String res = null;
		if (Long.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "jdbcType=BIGINT";
		} else if (String.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "jdbcType=VARCHAR";
		} else if (Date.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "jdbcType=TIMESTAMP";
		} else if (Boolean.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "jdbcType=VARCHAR";
		} else if (Enum.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "jdbcType=VARCHAR";
		} else if (Integer.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "jdbcType=INTEGER";
		} else {
			throw new RuntimeException("不支持的属性类型：" + type);
		}
		return res;
	}

	private static String gettypeInTableByTypeStr(String type) {
		String res = null;
		if (Long.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "bigint(19)";
		} else if (String.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "varchar(255)";
		} else if (Date.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "timestamp";
		} else if (Boolean.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "tinyint";
		} else if (Enum.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "varchar(50)";
		} else if (Integer.class.getSimpleName().equalsIgnoreCase(type)) {
			res = "int";
		} else {
			throw new RuntimeException("在mysql中不支持的属性类型：" + type);
		}
		return res;
	}

	private static String generatorUpdate(String targetClassName) {
		String src = generatorBundle.getString(UPDATE_STATEMENT);
		StringBuffer content = new StringBuffer();

		int count = 0;
		for (Entry<String, String> entity : propertyToColumnMap.entrySet()) {
			if (count > 0) {
				content.append("," + "\n");
			}

			String[] keys = entity.getKey().split("\\" + DECIMAL_SPLIT);
			content.append(BLANK_STR + entity.getValue() + "=#{" + keys[0] + ",");
			content.append(getStringByType(keys[1]) + "}");

			count++;
		}

		return MessageFormat.format(src, new Object[] { targetClassName, tableName, content.toString(),
				"where id=#{id,jdbcType=BIGINT}" })
				+ "\n";
	}

	public static Class loadClassByName(String targetClassName) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		try {
			Class target = cl.loadClass(targetClassName);
			return target;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
