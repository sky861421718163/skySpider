package com.sky.spider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sky.spider.dao.DbDao;
import com.sky.spider.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private DbDao dbDao;

	@Transactional
	@Override
	public void save() {
		
		dbDao.insertOrder("order1");
		
		dbDao.insertMsg("msg1");
	}
	
	

}
