/*
 * Copyright (c) 2014 中国国际图书贸易集团公司 
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition;

import java.io.Serializable;

/**
 * <p>标题：Field </p>
 * <p>
 *    功能描述：字段基类
 * </p>
 * <p>创建日期：2015年12月17日上午11:54:22</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class Field implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 字段名称
	 */
	private String fieldName;
	
	
	public Field(String fieldName){
		this.fieldName=fieldName;
	}
	/**
	 * @return the keyName
	 */
	public String getFieldName() {
		return fieldName;
	}
}
