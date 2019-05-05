package com.sky.spider.advance.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class SolrUtil {
	
	private static String httpSolrClientUrl ="http://127.0.0.1:8983/solr/";
	private static SolrClient solr;
	
	public static HttpSolrClient connetHttpSolrClientServer(String coreName){
		HttpSolrClient server = new HttpSolrClient(httpSolrClientUrl + coreName);
		server.setSoTimeout(5000); 
		server.setConnectionTimeout(1000); 
		server.setDefaultMaxConnectionsPerHost(1000); 
		server.setMaxTotalConnections(5000);
		return server;
	}
	
	
	/**
	 *@ClassDescribe:向solr插入数据
	 *@author:chenxi
	 *@createDate:2017年9月28日 下午2:35:47
	 *@param coreName 核心名称
	 *@param input 数据封装
	 * @return
	 */
	public static boolean pushDataIntoSolr(String coreName, SolrInputDocument input) {
		boolean flag = false;
		try {
			solr = connetHttpSolrClientServer(coreName);
			solr.add(input);
			solr.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				solr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 *@ClassDescribe:根据id删除solr
	 *@author:chenxi
	 *@createDate:2017年12月19日 下午2:37:40
	 * @param coreName
	 * @param id
	 */
	public static boolean deleteDataIntoSolrById(String coreName, long id) {
		boolean flag = false;
		try {
			solr = connetHttpSolrClientServer(coreName);
			solr.deleteById(String.valueOf(id));
			solr.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				solr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
