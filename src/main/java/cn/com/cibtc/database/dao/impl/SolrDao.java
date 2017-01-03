/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.dao.impl;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import cn.com.cibtc.database.dao.IDao;
import cn.com.cibtc.database.handler.ConditionAnalysisFactory;
import cn.com.cibtc.database.model.condition.solr.SolrCondition;

/**
 * <p>标题：SolrDao </p>
 * <p>
 *    功能描述：solr数据操作Dao
 * </p>
 * <p>创建日期：2016年9月14日下午2:55:11</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class SolrDao<FieldEnum extends Enum<FieldEnum>,Model,PK> implements IDao<FieldEnum, Model, PK> {
	
	private HttpSolrServer solrServer;

	public QueryResponse search(SolrCondition<FieldEnum> solrCondition) throws SolrServerException {
		QueryResponse response = solrServer.query((SolrQuery) ConditionAnalysisFactory.parseSql(solrCondition));
		return response;
	}
	
	public void delete(SolrCondition<FieldEnum> solrCondition) throws SolrServerException, IOException {
		String query = ((SolrQuery) ConditionAnalysisFactory.parseSql(solrCondition)).getQuery();
		solrServer.deleteByQuery(query);
		solrServer.commit();
	}
	
	public void delete(SolrCondition<FieldEnum> solrCondition, int commitWithinMs) throws SolrServerException, IOException {
		String query = ((SolrQuery) ConditionAnalysisFactory.parseSql(solrCondition)).getQuery();
		solrServer.deleteByQuery(query, commitWithinMs);
		solrServer.commit();
	}
	
	/* ==================== getter && setter =========================== */
	public HttpSolrServer getSolrServer() {
		return solrServer;
	}

	public void setSolrServer(HttpSolrServer solrServer) {
		this.solrServer = solrServer;
	}
}
