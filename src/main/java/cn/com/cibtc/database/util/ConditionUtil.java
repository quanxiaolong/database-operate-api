/*
 * Copyright (c) 2014 中国国际图书贸易集团公司 
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.util;

import cn.com.cibtc.database.enums.RelationEnums.Compare;
import cn.com.cibtc.database.model.condition.ConditionBlock;
import cn.com.cibtc.database.model.condition.DynamicCondition;

/**
 * <p>标题：SearchConditionUtil </p>
 * <p>
 *    功能描述：动态条件处理工具
 * </p>
 * <p>创建日期：2015年12月24日下午5:09:42</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class ConditionUtil {
	
	/**
	 * 判断查询条件是否有效
	 * @date 2015年12月24日下午5:12:46
	 * @param condition
	 * @return
	 */
	public static final boolean isConditionAvailable(DynamicCondition<?> condition){
		boolean available=true;
		if(condition==null)
			available=false;
		else if(condition.isConditionNull()&&!condition.isMatchAll()){
			available=false;
		}
		return available;
	}
	
	/**
	 * 创建Id查询动态条件
	 * @date 2016年2月22日上午11:01:03
	 * @author QuanXiaolong
	 * @param fieldId 数据库字段枚举 Id
	 * @param id 
	 * @return
	 */
	public static final <T extends Enum<T>> DynamicCondition<T> createConditionID(T fieldId,String id){
		DynamicCondition<T> condition = new DynamicCondition<T>();
		ConditionBlock<T> block =new ConditionBlock<T>();
		block.addCompareCondition(fieldId, Compare.EQUAL, id);
		condition.addCondationBlock(block);
		return condition;
	}
}
