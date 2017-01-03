/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.dao;

import cn.com.cibtc.database.model.UpdateField;
import cn.com.cibtc.database.model.condition.BaseCondition;

/**
 * <p>标题：IDao </p>
 * <p>
 *    功能描述：数据库动态条件基础Dao<br/>
 *    UpdateField：更新字段模型 extends{@link UpdateField}<br/>
 *    Condition：动态条件对象模型继承{@link BaseCondition}<br/>
 *    Model:返回值数据模型类型<br/>
 *    PK:主键类型<br/>
 * </p>
 * <p>创建日期：2016年1月22日上午11:52:20</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public interface IDao<FieldEnum extends Enum<FieldEnum>,Model,PK> {

}
