package com.sky.spider.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitUtil {

	/**
	 * 创建webClient
	 * 
	 * @ClassName:HtmlUnitUtil.java
	 * @ClassDescribe:
	 * @auth:sky
	 * @createDate:2018年1月24日 下午1:45:36
	 * @updateAuth:
	 * @updateDate:
	 * @version
	 * @return
	 */
	public static WebClient createWebClient() {

		WebClient webClient = new WebClient();

		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);

		return webClient;
	}

	/**
	 * 通过代理创建WebClient
	 * 
	 * @ClassName:HtmlUnitUtil.java
	 * @ClassDescribe:
	 * @auth:sky
	 * @createDate:2018年1月24日 下午2:07:03
	 * @updateAuth:
	 * @updateDate:
	 * @version
	 * @return
	 */
	public static WebClient createWebClientWithproxy() {

		WebClient webClient = new WebClient();

		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);

		// 设置代理
		ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
		proxyConfig.setProxyHost("proxy.abuyun.com");
		proxyConfig.setProxyPort(Integer.valueOf("9020"));
		webClient.getOptions().setProxyConfig(proxyConfig);
		DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient
				.getCredentialsProvider();
		credentialsProvider.addCredentials("HQPNV182R256008D", "0D60F07D5A744504");
		webClient.setCredentialsProvider(credentialsProvider);

		return webClient;
	}

	/**
	 * 访问网站，并返回响应页面
	 * 
	 * @ClassName:HtmlUnitUtil.java
	 * @ClassDescribe:
	 * @auth:sky
	 * @createDate:2018年1月24日 下午1:45:58
	 * @updateAuth:
	 * @updateDate:
	 * @version
	 * @param webClient
	 * @param url
	 * @return
	 */
	public static Document visit(WebClient webClient, String url) {

		Document document = null;
		// 获取页面
		HtmlPage page;
		try {

			webClient.waitForBackgroundJavaScript(5000);
			page = webClient.getPage(url);
			String pageXml = page.asXml();
			document = Jsoup.parse(pageXml);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return document;

	}

}
