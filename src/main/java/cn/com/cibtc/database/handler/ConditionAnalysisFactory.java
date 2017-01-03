/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import cn.com.cibtc.database.model.condition.BaseCondition;
import cn.com.cibtc.database.model.condition.solr.SolrCondition;

/**
 * <p>标题：ConditionAnalysisFactory </p>
 * <p>
 *    功能描述：条件对象解析工厂
 * </p>
 * <p>创建日期：2016年2月3日上午10:54:55</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class ConditionAnalysisFactory {
	
	/**
	 * 套件解析器集合
	 */
	private static Map<Class<?>,IConditionAnalysis> conditionHandler= new HashMap<>();

	/**
	 * 获取对象转换条件
	 * @return
	 */
	public static <FieldEnum extends Enum<FieldEnum>>  Object parseSql(BaseCondition<FieldEnum> condition){
		if(CollectionUtils.isEmpty(conditionHandler)){
			initHandler();
		}
		IConditionAnalysis handler = conditionHandler.get(condition.getClass());
		Object result =null;
		if(handler!=null){
			result=handler.parseSql(condition);
		}
		return result;
	}
	
	/**
	 * 初始化条件解析器
	 */
	private static void initHandler(){
		if(CollectionUtils.isEmpty(conditionHandler)){
			conditionHandler.put(SolrCondition.class,new ConditionSolrAnalysis());
		}
	}
}
