/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.util;

import cn.com.cibtc.database.model.condition.BaseCondition;

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
	public static final boolean isConditionAvailable(BaseCondition<?> condition){
		boolean available=true;
		if(condition==null)
			available=false;
		else if(condition.isConditionNull()&&!condition.isMatchAll()){
			available=false;
		}
		return available;
	}
}
