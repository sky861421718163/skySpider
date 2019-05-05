package com.sky.spider.domain;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

public class HttpEntity {

	private HttpGet get;
	private CloseableHttpClient client;

	public HttpGet getGet() {
		return get;
	}

	public void setGet(HttpGet get) {
		this.get = get;
	}

	public CloseableHttpClient getClient() {
		return client;
	}

	public void setClient(CloseableHttpClient client) {
		this.client = client;
	}
}