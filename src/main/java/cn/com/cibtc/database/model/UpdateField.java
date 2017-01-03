/*
 * Copyright (c) 2014 中国国际图书贸易集团公司 
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>标题：UpdateField </p>
 * <p>
 *    功能描述：更新字段基础类需要在业务类继承
 *    FieldEnum：可更新的字段集合
 * </p>
 * <p>创建日期：2016年1月28日下午2:30:17</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class UpdateField<FieldEnum extends Enum<FieldEnum>> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> listField = new ArrayList<String>();
	/**
	 * 添加更新字段
	 * @date 2016年1月28日下午2:36:20
	 * @param updateField
	 */
	public void addUpdateField(FieldEnum updateField){
		if(updateField!=null&&!listField.contains(updateField.toString()))
			this.listField.add(updateField.toString());
	}
	/**
	 * @return the listField
	 */
	public List<String> getListField() {
		return listField;
	}
	
}
