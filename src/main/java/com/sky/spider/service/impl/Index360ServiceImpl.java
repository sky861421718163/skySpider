package com.sky.spider.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.spider.dao.DbDao;
import com.sky.spider.domain.BaseMonitorMain;
import com.sky.spider.domain.ResponseResult;
import com.sky.spider.domain.TempMonitorMain;
import com.sky.spider.service.Index360Service;
import com.sky.spider.utils.FileUtil;
import com.sky.spider.utils.HttpRequestClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class Index360ServiceImpl implements Index360Service {

	@Autowired
	private DbDao dbDao;

	@Override
	public void collect360Index() {

		long staStartTime = System.currentTimeMillis(); // 计算开始时间

		Map<String, String> map = new HashMap<String, String>();

		map.put("fdType", "1");

		List<BaseMonitorMain> monitorList = dbDao.getMonitorForPV(map);

		/*
		 * List<BaseMonitorMain> monitorList = new ArrayList<>();
		 * BaseMonitorMain m = new BaseMonitorMain(); m.setId(1L);
		 * m.setFdName("WEMEC威迈"); 高美 monitorList.add(m);
		 * 
		 * BaseMonitorMain m = new BaseMonitorMain(); m.setId(1L);
		 * m.setFdName("建国3");WEMEC威迈 高美 monitorList.add(m);
		 * 
		 */

		List<TempMonitorMain> successList = new ArrayList<>(); // 爬取成功的监测对象

		List<TempMonitorMain> errorList = new ArrayList<>(); // 爬取失败的监测对象

		BaseMonitorMain monitorMain = null;

		String visitUrl = "";
		String monitorName = "";
		ResponseResult responseResult = null;
		// headers
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("User-Agent",
				"Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/602.4.6 (KHTML, like Gecko) Version/10.0 Mobile/14D27 Safari/602.1");
		headerMap.put("Accept", "application/json");
		headerMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		headerMap.put("Accept-Encoding", "gzip, deflate, br");
		headerMap.put("Referer", "https://trends.so.com/?src=index.so.com");
		headerMap.put("X-Requested-With", "XMLHttpRequest");
		headerMap.put("Connection", "keep-alive");

		
			for (int i = 0; i < monitorList.size(); i++) {

				monitorMain = monitorList.get(i);
				System.out.println(monitorList.size());
				System.out.println(i);
				System.out.println("即将爬");
				System.out.println(monitorMain.getFdName());

				monitorName = monitorMain.getFdName();

				TempMonitorMain tempMonitorMain = new TempMonitorMain();

				tempMonitorMain.setId(monitorMain.getId());
				tempMonitorMain.setName(monitorName);

				visitUrl = "https://trends.so.com/index/overviewJson?area=全国&q=" + monitorName;


				responseResult = HttpRequestClient.sendGet(visitUrl, headerMap, 2);

				String responesStr  = responseResult.getResponesStr();
				int responseCode = responseResult.getResponesCode();
				System.out.println("1----"+responesStr);
				if (!"null".equals(responesStr) && null != responesStr){
					
					String 	resCode = JSONObject.fromObject(responesStr).getString("status");
					
					if (!resCode.equals("7001")){
						
						JSONObject jsonResult = JSONObject.fromObject(responseResult.getResponesStr());
						System.out.println("----"+jsonResult);
						JSONArray dataArr = jsonResult.getJSONArray("data");
						JSONObject data = (JSONObject) dataArr.get(0);

						JSONObject resultData = data.getJSONObject("data");

						String resultIndex = resultData.getString("month_index");
						
						System.out.println(resultIndex);
						
						tempMonitorMain.setIndex360(resultIndex);
						 successList.add(tempMonitorMain);
					}
					
				}
				

				

			}

			// 写入文件中：

			StringBuffer sBuffer = new StringBuffer();

			sBuffer.append("---------采集到有效数据的监测对象：\n");

			Iterator<TempMonitorMain> suIt = successList.iterator();
			while (suIt.hasNext()) {

				sBuffer.append(suIt.next() + "\n");
			}

			FileUtil.WriteStringToFile("d:/360月指数采集结果.txt", sBuffer.toString());

			long staEndTime = System.currentTimeMillis(); // 获取结束时间

			System.err.println("爬取结束...爬取花费时间：" + (staEndTime - staStartTime) / 1000 + "秒");

		

	}

}
