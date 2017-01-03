/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.dao;

import java.util.List;

import cn.com.cibtc.database.model.UpdateField;
import cn.com.cibtc.database.model.condition.BaseCondition;

/**
 * <p>标题：ICommonDao </p>
 * <p>
 *    功能描述：公用方法DAO<br/>
 *    UpdateField：更新字段模型 extends{@link UpdateField}<br/>
 *    Condition：动态条件对象模型继承{@link BaseCondition}<br/>
 *    Model:返回值数据模型类型<br/>
 *    PK:主键类型<br/>
 *    01.通过ID 数组获取 数据模型信息<br/>
 *    02.通过IDS 数组获取 数据模型信息<br/>
 *    03.动态获取Count<br/>
 *    04.动态获取 数据模型信息<br/>
 *    05.保存模型数据信息<br/>
 *    06.批量保存 数据模型信息<br/>
 *    07.动态修改模型数据信息<br/>
 *    08.动态删除数据模型信息<br/>
 *    09.通过主键删除数据模型信息
 * </p>
 * <p>创建日期：2016年1月28日上午9:59:18</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public interface ICommonDao <FieldEnum extends Enum<FieldEnum>,Model,PK> extends IDao<FieldEnum,Model, PK> {
	/**
	 * 通过ID 获取 数据模型信息
	 * @date 2016年1月22日下午1:55:36
	 * @author QuanXiaolong
	 * @param id
	 * @return
	 */
	public Model searchById(PK id);

	/**
	 * 通过IDS 集合获取 数据模型信息
	 * @date 2016年1月22日下午1:55:59
	 * @author QuanXiaolong
	 * @param ids
	 * @return
	 */
	public List<Model> searchByIds(List<PK> ids);

	/**
	 * 通过动态条件获取 记录个数
	 * @date 2016年1月22日下午1:57:19
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public Integer searchCount(BaseCondition<FieldEnum> condition);

	
	/**
	 * 通过动态条件获取 多数据模型
	 * @date 2016年1月22日下午1:57:47
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public List<Model> search(BaseCondition<FieldEnum> condition);
	
	/**
	 * 通过动态条件获取 单个数据模型
	 * @date 2016年2月16日下午1:29:14
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public Model searchOne(BaseCondition<FieldEnum> condition);

	/**
	 * 保存数据
	 * @date 2016年1月22日下午1:58:23
	 * @author QuanXiaolong
	 * @param model
	 * @return
	 */
	public Integer insert(Model model);

	/**
	 * 批量保存数据
	 * @date 2016年1月22日下午1:58:53
	 * @author QuanXiaolong
	 * @param models
	 */
	public void insertBatch(List<Model> models);

	/**
	 * 根据Id更新模型数据
	 * @param updateField
	 * @param model
	 * @param id
	 * @return
	 */
	public Integer updateById(UpdateField<FieldEnum> updateField,Model model,PK id);
	/**
	 * 根据动态条件更新 模型数据
	 * @date 2016年1月22日下午1:59:06
	 * @author QuanXiaolong
	 * @param condition
	 * @param model
	 * @return
	 */
	public Integer update(UpdateField<FieldEnum> updateField,Model model,BaseCondition<FieldEnum> condition);

	/**
	 * 根据动态条件 删除数据
	 * @date 2016年1月22日下午2:01:00
	 * @author QuanXiaolong
	 * @param condition
	 * @return
	 */
	public Integer delete(BaseCondition<FieldEnum> condition);

	/**
	 * 通过主键ID 删除数据模型信息
	 * @date 2016年1月22日下午2:03:58
	 * @author QuanXiaolong
	 * @param id
	 * @return
	 */
	public Integer deleteById(PK id);
}
