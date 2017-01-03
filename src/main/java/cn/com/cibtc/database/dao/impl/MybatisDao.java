/*
 * Copyright (c) 2014 中国国际图书贸易集团公司 
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.cibtc.database.dao.ICommonDao;
import cn.com.cibtc.database.model.UpdateField;
import cn.com.cibtc.database.model.condition.DynamicCondition;

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
public interface MybatisDao<FieldEnum extends Enum<FieldEnum>,Model,PK> extends ICommonDao<FieldEnum,Model,PK>{
	
	/**
	 * 通过ID 获取 数据模型信息
	 * @date 2016年1月22日下午1:55:36
	 * @author QuanXiaolong
	 * @param id
	 * @return
	 */
	public Model searchById(@Param("id")PK id);

	/**
	 * 通过IDS 集合获取 数据模型信息
	 * @date 2016年1月22日下午1:55:59
	 * @author QuanXiaolong
	 * @param ids
	 * @return
	 */
	public List<Model> searchWithBatch(@Param("ids")List<PK> ids);

	/**
	 * 通过动态条件获取 记录个数
	 * @date 2016年1月22日下午1:57:19
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public Integer searchCount(@Param("condition")DynamicCondition<FieldEnum> condition);

	
	/**
	 * 通过动态条件获取 多个数据模型
	 * @date 2016年1月22日下午1:57:47
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public List<Model> search(@Param("condition")DynamicCondition<FieldEnum> condition);
	
	/**
	 * 通过动态条件获取 单个数据模型
	 * @date 2016年1月22日下午1:57:47
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public Model searchOne(@Param("condition")DynamicCondition<FieldEnum> condition);

	/**
	 * 保存数据
	 * @date 2016年1月22日下午1:58:23
	 * @author QuanXiaolong
	 * @param model
	 * @return
	 */
	public Integer insert(@Param("model")Model model);

	/**
	 * 批量保存数据
	 * @date 2016年1月22日下午1:58:53
	 * @author QuanXiaolong
	 * @param models
	 */
	public void insertWithBatch(@Param("models")List<Model> models);

	/**
	 * 根据动态条件更新 模型数据
	 * TODO  传入字段集合，对指定字段进行更新
	 * @date 2016年1月22日下午1:59:06
	 * @author QuanXiaolong
	 * @param condition
	 * @param model
	 * @return
	 */
	public Integer update(@Param("updateField")UpdateField<FieldEnum> updateField,@Param("model")Model model,@Param("condition")DynamicCondition<FieldEnum> condition);

	/**
	 * 根据动态条件 删除数据
	 * @date 2016年1月22日下午2:01:00
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public Integer delete(@Param("condition")DynamicCondition<FieldEnum> condition);

	/**
	 * 通过主键ID 删除数据模型信息
	 * @date 2016年1月22日下午2:03:58
	 * @author QuanXiaolong
	 * @param id
	 * @return
	 */
	public Integer deleteById(@Param("id")PK id);
}
