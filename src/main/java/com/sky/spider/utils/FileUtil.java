package com.sky.spider.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {

	/**
	 * 向文件内写内容
	 *@ClassName:FileUtil.java
	 *@ClassDescribe:
	 *@auth:sky
	 *@createDate:2018年1月24日 上午11:06:50
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 *@param filePath
	 *@param inputStr
	 */
	public static void WriteStringToFile(String filePath, String inputStr) {

		FileUtil.judeFileExists(new File(filePath));

		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filePath, true));
			pw.print(inputStr);
			pw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 判断文件是否存在
	 *@ClassName:FileUtil.java
	 *@ClassDescribe:
	 *@auth:sky
	 *@createDate:2018年1月24日 上午11:06:40
	 *@updateAuth:
	 *@updateDate:
	 *@version
	 *@param file
	 */
	public static void judeFileExists(File file) {

		if (file.exists()) {
			//System.out.println("file exists");
		} else {
			System.out.println("文件不存在，创建...");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	

	

}