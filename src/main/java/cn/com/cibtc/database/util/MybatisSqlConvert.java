/*
 * Copyright (c) 2014 中国国际图书贸易集团公司 
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.cibtc.database.model.condition.field.SqlField;

/**
 * <p>标题： MybatisSqlConvert</p>
 * <p>
 *    功能描述：转化sql为Mybatis支持的格式
 * </p>
 * <p>创建日期：2017年6月16日 下午4:06:45</p>
 * <p>作者：陶聪</p>
 * <p>版本：2.0</p>
 */
public class MybatisSqlConvert {
	
	private static final String PLACEHOLDER_STR = "#{}";	// 占位符
	
	/* 正则筛选占位符：（去除占位符之间的空格、制表符） */
	private static final String PLACEHOLDER_REGEX = "\\#[\t ]*\\{[\t ]*\\}";
	
	/* 
	 * 替换占位符的字符串
	 * 格式：#{conditionBlock.sqlField.sqlMap.i}   i为占位符出现的index（从0开始）
	 */
	private static final String REPLACEMENT_PREFIX = "#{conditionBlock.sqlField.sqlMap.";
	private static final String REPLACEMENT_suffix = "}";

	/**
	 * 通过indexof方式替换占位符；占位符需书写严格
	 * @date 2017年6月21日 下午3:25:50
	 * @author 陶聪
	 * @param sqlField
	 * @return
	 */
	public static String convertSql(SqlField sqlField) {
		
		String sql = sqlField.getSqlWhere();
		Object[] params = sqlField.getParams();
		
		/* 替代占位符  */
		StringBuilder strBuild = new StringBuilder();
		if (sql != null) {
			int idx = 0;		// 占位符出现的位置
			int startSub = 0;	// 字符串开始匹配的位置
			int i= 0;			// 占位符出现的次数
			while ((idx = sql.indexOf(PLACEHOLDER_STR, startSub)) != -1) {
				strBuild.append(sql.substring(startSub, idx)).append(REPLACEMENT_PREFIX + i + REPLACEMENT_suffix);
				startSub = idx + PLACEHOLDER_STR.length();
				i++;
			}
			if (sql.length() > startSub) {
				strBuild.append(sql.substring(startSub, sql.length()));
			}
		}
		
		/* 将参数放入map */
		setSqlMap(sqlField, params);
			
		return strBuild.toString();
	}
	
	/**
	 * 通过正则方式替换占位符；支持占位符之间存在空格与制表符
	 * @date 2017年6月21日 下午3:26:42
	 * @author 陶聪
	 * @param sqlField
	 * @return
	 */
	public static String convertSqlByRegex(SqlField sqlField) {
		
		String sql = sqlField.getSqlWhere();
		Object[] params = sqlField.getParams();
		
		/* 替代占位符  */
		Pattern r = Pattern.compile(PLACEHOLDER_REGEX);
		Matcher m = r.matcher(sql);
		StringBuffer strBuild = new StringBuffer();
		if (sql != null) {
			int i = 0;
			int lastMatchIndex = 0;
			while (m.find()) {
				m.appendReplacement(strBuild, REPLACEMENT_PREFIX + i + REPLACEMENT_suffix);
				lastMatchIndex = m.end();
				i++;
			}
			if (sql.length() > lastMatchIndex) {
				strBuild.append(sql.substring(lastMatchIndex, sql.length()));
			}
		}
		
		/* 将参数放入map */
		setSqlMap(sqlField, params);
		
		return strBuild.toString();
	}
	
	private static void setSqlMap(SqlField sqlField, Object[] params) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (params != null) {
			for (int j = 0; j < params.length; j++) {
				map.put(String.valueOf(j), params[j]);
			}
		}	
		sqlField.setSqlMap(map);
	}
	
}
