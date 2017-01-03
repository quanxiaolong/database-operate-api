/*
 * Copyright (c) 2014 中国国际图书贸易集团公司 
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.com.cibtc.database.dao.ICommonDao;
import cn.com.cibtc.database.model.UpdateField;
import cn.com.cibtc.database.model.condition.DynamicCondition;
import cn.com.cibtc.database.util.ConditionUtil;

/**
 * <p>标题：MybatisDao </p>
 * <p>
 *    功能描述：Mybatis操作MySql基础Dao<br/>
 *    UpdateField：更新字段模型 extends{@link UpdateField}<br/>
 *    Condition：动态条件对象模型继承{@link DynamicCondition}<br/>
 *    Model:返回值数据模型类型<br/>
 *    PK:主键类型
 * </p>
 * <p>创建日期：2016年1月22日上午11:53:36</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class MybatisDao2<FieldEnum extends Enum<FieldEnum>,Model,PK> extends SqlSessionDaoSupport implements ICommonDao<FieldEnum,Model,PK>{
	
	private String nameSpace;
	/**
	 * 通过ID 获取 数据模型信息
	 * @date 2016年1月22日下午1:55:36
	 * @author QuanXiaolong
	 * @param id
	 * @return
	 */
	public Model searchById(@Param("id")PK id){
		if(id==null)
			return null;
		Map<String, Object> parameter=null;
		String statement=this.nameSpace+"searchById";
		return this.getSqlSession().selectOne(statement,parameter);
	}

	/**
	 * 通过IDS 集合获取 数据模型信息
	 * @date 2016年1月22日下午1:55:59
	 * @author QuanXiaolong
	 * @param ids
	 * @return
	 */
	public List<Model> searchWithBatch(@Param("ids")List<PK> ids){
		if(CollectionUtils.isEmpty(ids))
			return Collections.emptyList();
		Map<String, Object> parameter=null;
		String statement=this.nameSpace+"searchWithBacth";
		return this.getSqlSession().selectList(statement, parameter);
	}
	
	

	/**
	 * 通过动态条件获取 记录个数
	 * @date 2016年1月22日下午1:57:19
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public Integer searchCount(@Param("condition")DynamicCondition<FieldEnum> condition){
		if(!ConditionUtil.isConditionAvailable(condition))
			return 0;
		Map<String, Object> parameter=null;
		String statement=this.nameSpace+"searchCount";
		return this.getSqlSession().selectOne(statement, parameter);
			
	}

	
	/**
	 * 通过动态条件获取 数据模型
	 * @date 2016年1月22日下午1:57:47
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public List<Model> search(@Param("condition")DynamicCondition<FieldEnum> condition){
		if(!ConditionUtil.isConditionAvailable(condition))
			return Collections.emptyList();
		Map<String, Object> parameter=null;
		String statement=this.nameSpace+"search";
		return this.getSqlSession().selectList(statement, parameter);
	}

	public Model searchOne(DynamicCondition<FieldEnum> condition) {
		if(!ConditionUtil.isConditionAvailable(condition))
			return null;
		Map<String, Object> parameter=null;
		String statement=this.nameSpace+"search";
		return this.getSqlSession().selectOne(statement, parameter);
	}
	/**
	 * 保存数据
	 * @date 2016年1月22日下午1:58:23
	 * @author QuanXiaolong
	 * @param model
	 * @return
	 */
	public Integer insert(@Param("model")Model model){
		if(model==null)
			return 0;
		String statement=this.nameSpace+"insert";
		Map<String, Object> parameter=null;
		return this.getSqlSession().insert(statement,parameter);
	}

	/**
	 * 批量保存数据
	 * @date 2016年1月22日下午1:58:53
	 * @author QuanXiaolong
	 * @param models
	 */
	public void insertWithBatch(@Param("models")List<Model> models)
	{
		if(CollectionUtils.isEmpty(models))
			return;
		Map<String, Object> parameter=null;
		String statement=this.nameSpace+"insertWithBacth";
		this.getSqlSession().insert(statement,parameter);
	}

	/**
	 * 根据动态条件更新 模型数据
	 * TODO  修改更新字段类型以及增加更新字段验证
	 * @date 2016年1月22日下午1:59:06
	 * @author QuanXiaolong
	 * @param condition
	 * @param model
	 * @return
	 */
	public Integer update(@Param("updateField")UpdateField<FieldEnum> updateField,@Param("model")Model model,@Param("condition")DynamicCondition<FieldEnum> condition){
		if(!ConditionUtil.isConditionAvailable(condition))
			return 0;
		Map<String, Object> parameter=null;
		String statement=this.nameSpace+"update";
		return this.getSqlSession().update(statement, parameter);
	}

	/**
	 * 根据动态条件 删除数据
	 * @date 2016年1月22日下午2:01:00
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public Integer delete(@Param("condition")DynamicCondition<FieldEnum> condition){
		if(!ConditionUtil.isConditionAvailable(condition))
			return 0;
		Map<String, Object> parameter=null;
		String statement=this.nameSpace+"delete";
		return this.getSqlSession().delete(statement, parameter);
	}

	/**
	 * 通过主键ID 删除数据模型信息
	 * @date 2016年1月22日下午2:03:58
	 * @author QuanXiaolong
	 * @param id
	 * @return
	 */
	public Integer deleteById(@Param("id")PK id){
		if(id==null)
			return 0;
		Map<String, Object> parameter=null;
		String statement=this.nameSpace+"deleteById";
		return this.getSqlSession().delete(statement, parameter);
	}
}
