/*
 * Copyright (c) 2014 中国国际图书贸易集团公司 
 * All rights reserved.
 *  
 */
package demo;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.cibtc.database.dao.impl.MybatisDao;
import cn.com.cibtc.database.model.AbstractUpdateField;
import cn.com.cibtc.database.model.condition.AbstractCondition;
import cn.com.cibtc.database.util.ConditionUtil;

/**
 * <p>标题： BaseService</p>
 * <p>
 *    功能描述：service-- {@link AbstractService}
 * </p>
 * <p>创建日期：2015年12月28日 上午10:27:58</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class DemoService<UpdateField extends AbstractUpdateField<?>,Condition extends AbstractCondition<?, ?>,Model,PK>{

	private MybatisDao<UpdateField,Condition,Model,PK> baseDao;

	public Model search(PK id) {
		if(id == null) return null;
		return this.baseDao.searchById(id);
	}

	public List<Model> search(List<PK> ids) {
		if(CollectionUtils.isEmpty(ids)) return Collections.emptyList();
		return this.baseDao.searchWithBacth(ids);
	}

	public int searchCount(Condition condition) {
		if(!ConditionUtil.isConditionAvailable(condition))
			return 0;
		return this.baseDao.searchCount(condition);
	}

	public List<Model> search(Condition condition) {
		if(!ConditionUtil.isConditionAvailable(condition))
			return Collections.emptyList();
		return this.baseDao.search(condition);
	}
	
	public Model searchOne(Condition condition){
		
		if(!ConditionUtil.isConditionAvailable(condition))
			return null;
		
		Model model=null;
		if(condition!=null){
			List<Model>listMember= this.baseDao.search(condition);
			if(listMember!=null&&listMember.size()>0)
				model=listMember.get(0);
		}
		return model;
	}

	public Model save(Model model) {
		this.baseDao.insert(model);
		return model;
	}

	public void save(List<Model> models) {
		this.baseDao.insertWithBacth(models);
	}

//	public int edit(Condition condition, Model model) {
//		if(!ConditionUtil.isConditionAvailable(condition))
//			return 0;
//		return this.baseDao.update(condition, model);
//	}

	public Boolean delete(Condition condition) {
		if(!ConditionUtil.isConditionAvailable(condition))
			return false;
		int result = this.baseDao.delete(condition);
		return result > 0;
	}

	public Boolean delete(PK id) {
		if(id == null) return false;
		int result = this.baseDao.deleteById(id);
		return result > 0;
	}

	/**
	 * @param baseDao The baseDao to set.
	 */
	public void setBaseDao(MybatisDao<UpdateField,Condition, Model, PK> baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 获取指定类型的Dao
	 * @date 2015年12月31日上午10:24:50
	 * @param subClass
	 * @return
	 */
	public <T> T getDao(Class<T> subClass){
		return subClass.cast(this.baseDao);
	}
}
