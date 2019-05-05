package com.sky.spider.dao;

import java.util.List;
import java.util.Map;

import com.sky.spider.domain.BaseMonitorMain;
import com.sky.spider.domain.MonitorKey;
import com.sky.spider.domain.MonitorMainTc;
import com.sky.spider.domain.WxCrawIndex;

public interface DbDao {

	/**
	 * 查询 所有的监测对象
	 * 
	 * @ClassDescribe:
	 * @author:sky
	 * @createDate:2017年7月14日 下午3:04:07
	 * @updateAuthor:
	 * @updateDate:
	 * @return
	 */
	List<BaseMonitorMain> getMonitorForPV(Map map);

	List<MonitorMainTc> getMonitorForTc(Map<String, String> map);

	/**
	 * 查询mci对象
	 *@ClassName:DbDao.java
	 *@ClassDescribe:
	 *@auth:SKY
	 *@createDate:2018年4月23日 下午1:37:37
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 *@return
	 */
	List<BaseMonitorMain> getMCIMonitor();

	List<MonitorKey> getMonitorKeys(Long id);

	void saveWxCrawIndex(WxCrawIndex wxCrawIndex);

	void saveWxCrawIndex_err(WxCrawIndex wxCrawIndex);

	List<BaseMonitorMain> getMCIMonitorZero();

	void updateWxCrawIndex(WxCrawIndex wxCrawIndex);
	
	void insertOrder(String order);
	void insertMsg(String msg);

	

}
