package com.sky.spider.advance.berkeleyDB;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

/**
 * BerkeleyDB java的简单使用
 * @author Xiaohua 
 *
 */
public class Test {
	
	//此数据库的好处
	//大数据的支持，可以支持millions的记录，并且更可能是硬件出现瓶颈而不是je
	//数据库环境支持，数据库环境可以一次配置多个数据库或者一个数据库；环境也可以管理并发和事务
	//多线程和多进程的支持
	//支持事务
	//支持内存缓存
	//支持索引
	//日志记录
	private static String dbEnv = "D://123";
 
	public static void main(String[] args) {
		Environment myDbEnvironment = null;
		Database myDatabase = null;
		try {
			EnvironmentConfig envConfig = new EnvironmentConfig();// 配置环境变量
			envConfig.setAllowCreate(true);
			File f=new File(dbEnv);
			if(!f.exists()){
				f.mkdirs();
			}
			myDbEnvironment = new Environment(f, envConfig);
 
		} catch (DatabaseException dbe) {
		}
		try {
			DatabaseConfig dbConfig = new DatabaseConfig();// 打开数据库
			dbConfig.setAllowCreate(true);
			myDatabase = myDbEnvironment.openDatabase(null, "myDatabase",
					dbConfig);
 
		} catch (DatabaseException dbe2) {
 
		}
//存储数据
		String aKey = "key7";
		String aData = "data8";
		try {
			DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes("UTF-8"));
			DatabaseEntry theData = new DatabaseEntry(aData.getBytes("UTF-8"));
			myDatabase.put(null, theKey, theData);
//			myDbEnvironment.sync();
			System.out.println(myDatabase.count());
		} catch (Exception e) {
 
		}
		
		
		//查询
		DatabaseEntry key;
		String value = "";
		try {
			key = new DatabaseEntry(aKey.toString().getBytes("UTF-8"));
			DatabaseEntry data = new DatabaseEntry();
			if(myDatabase.get(null,key,data,LockMode.DEFAULT) == OperationStatus.SUCCESS)
				value = new String(data.getData(),"UTF-8");
			
			System.out.println("取到："+ value);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		
		
		
 
		// 关闭,应该会自动提交
		try {
			if (myDatabase != null) {
				myDatabase.close();
			}
			if (myDbEnvironment != null) {
				myDbEnvironment.cleanLog(); //  在关闭环境前清理下日志
				myDbEnvironment.close();
			}
		} catch (DatabaseException dbe) {
 
		}
	}
	
	

}
