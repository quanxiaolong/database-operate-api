/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.handler;

import cn.com.cibtc.database.model.condition.BaseCondition;

/**
 * <p>标题：IConditionAnalysis </p>
 * <p>
 *    功能描述：条件对象解析处理
 * </p>
 * <p>创建日期：2016年2月3日上午10:54:55</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public interface IConditionAnalysis {
	
	/**
	 * 解析条件，返回sql
	 * @param condition
	 * @return
	 */
	public <FieldEnum extends Enum<FieldEnum>> Object parseSql(BaseCondition<FieldEnum> condition);
}
