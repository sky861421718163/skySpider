package com.sky.spider.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.xerces.impl.dv.util.Base64;

import com.sky.spider.domain.HttpEntity;
import com.sky.spider.domain.ProxyIp;
import com.sky.spider.domain.ResponseResult;

public class HttpRequestClient {

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @ClassDescribe:
	 * @author:sky
	 * @createDate:2017年8月10日 上午11:45:42
	 * @updateAuthor:
	 * @updateDate:
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 响应结果
	 */
	public static String sendPostForm(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 使用代理ip发送请求
	 * 
	 * @ClassName:HttpRequestUtils.java
	 * @ClassDescribe:
	 * @auth:sky
	 * @createDate:2017年11月15日 下午3:41:33
	 * @updateAuth:
	 * @updateDate:
	 * @version
	 * @param url
	 *            请求url
	 * @param param
	 *            请求参数
	 * @param proxyType
	 *            代理ip类型 http?https?
	 * @param proxyIp
	 *            代理ip
	 */
	public static String sendGet(String url, String param, ProxyIp proxyIp) {
		String result = "";

		// 创建代理服务器
		InetSocketAddress addr = new InetSocketAddress(proxyIp.getIp(), proxyIp.getPort());
		Proxy proxy = new Proxy(IpUtil.int2Type(proxyIp.getType()), addr); // http
																			// 代理

		String urlNameString = url + "?" + param;
		URL realUrl = null;
		try {
			realUrl = new URL(urlNameString);
		} catch (MalformedURLException e1) {

			e1.printStackTrace();
			return "error_1"; // url格式错误
		}
		// 打开和URL之间的连接
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) realUrl.openConnection(proxy);
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(3000);
		} catch (IOException e) {

			e.printStackTrace();
			return "error_2"; // 使用代理 ip 错误
		}
		// 设置通用的请求属性
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

		// 建立实际的连接
		int responseCode = 0;
		try {
			connection.connect();
			responseCode = connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
			return "error_3"; // 打开连接错误
		}

		if (responseCode == 200) {
			BufferedReader in = null;
			// 定义 BufferedReader输入流来读取URL的响应
			try {

				in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
				return "error_4"; // 读取返回信息失败

			} catch (IOException e) {

				e.printStackTrace();
				return "error_4";// 读取返回信息失败

			} // 使用finally块来关闭输入流
			finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();

					return "error_5";// 关闭urlConnection错误
				}
			}

		}

		if ("".equals(result)) {
			result = "error_3";
		}
		return result;
	}

	public static String sendGet(String url, String param) {

		String user = "H549681BA0CGPECD";
		String pwd = "23617E0B65C13DDF";

		// Authenticator.setDefault(new ProxyAuthenticator("H549681BA0CGPECD",
		// "23617E0B65C13DDF"));
		// 创建代理服务器地址对象
		InetSocketAddress addr = new InetSocketAddress("proxy.abuyun.com", 9020);
		// 创建HTTP类型代理对象
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

		String result = "";

		String urlNameString = url + "?" + param;
		URL realUrl = null;
		try {
			realUrl = new URL(urlNameString);
		} catch (MalformedURLException e1) {

			e1.printStackTrace();
			return "error_1"; // url格式错误
		}
		// 打开和URL之间的连接
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) realUrl.openConnection(proxy);

			String headerKey = "Proxy-Authorization";
			String auth = "Basic " + Base64.encode((user + ":" + pwd).getBytes());
			connection.setRequestProperty(headerKey, auth);

		} catch (IOException e) {

			e.printStackTrace();
			return "error_2"; // 使用代理 ip 错误
		}
		// 设置通用的请求属性
		connection.setRequestProperty("Proxy-Switch-Ip", "yes");

		// connection.setRequestProperty("accept", "*/*");
		// connection.setRequestProperty("connection", "Keep-Alive");
		// connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;
		// MSIE 6.0; Windows NT 5.1;SV1)");

		// 建立实际的连接
		int responseCode = 0;
		try {
			connection.connect();
			responseCode = connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
			return "error_3"; // 打开连接错误
		}

		if (responseCode == 200) {
			BufferedReader in = null;
			// 定义 BufferedReader输入流来读取URL的响应
			try {

				in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
				return "error_4"; // 读取返回信息失败

			} catch (IOException e) {

				e.printStackTrace();
				return "error_4";// 读取返回信息失败

			} // 使用finally块来关闭输入流
			finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();

					return "error_5";// 关闭urlConnection错误
				}
			}

		}

		if (result.contains("提交")) {
			return "error_2";
		}

		return result;
	}

	public static String sendGetByABuYun(String url, String param, String headerPath)
			throws ClientProtocolException, IOException {

		param = urlEncode(param, HTTP.UTF_8);

		String requestUrl = url + "?" + param;

		// 代理服务器
		final String proxyHost = "proxy.abuyun.com";
		final Integer proxyPort = 9020;

		// 代理隧道验证信息
		final String proxyUser = "H549681BA0CGPECD";
		final String proxyPass = "23617E0B65C13DDF";

		// 设置HttpHost
		HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");

		// 设置credsProvider
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(proxyUser, proxyPass));

		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

		// 将代理设置到HttpClient中
		AuthCache authCache = new BasicAuthCache();
		authCache.put(proxy, new BasicScheme());

		HttpGet get = new HttpGet(requestUrl);

		// localContext
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);

		get = HttpRequestClient.setRequestHeader(get, headerPath);

		// 请求配置
		RequestConfig config = RequestConfig.custom().setProxy(new HttpHost(proxyHost, proxyPort))
				.setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(5000).build();
		get.setConfig(config);

		CloseableHttpResponse res = client.execute(get);
		InputStream content = res.getEntity().getContent();
		System.out.println(readInputStream(content));

		return "";
	}

	public static HttpEntity getProxy(String url) throws ClientProtocolException, IOException {

		// 代理服务器
		final String proxyHost = "proxy.abuyun.com";
		final Integer proxyPort = 9020;

		// 代理隧道验证信息
		final String proxyUser = "H549681BA0CGPECD";
		final String proxyPass = "23617E0B65C13DDF";

		// 设置HttpHost
		HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");

		// 设置credsProvider
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(proxyUser, proxyPass));

		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

		// 将代理设置到HttpClient中
		AuthCache authCache = new BasicAuthCache();
		authCache.put(proxy, new BasicScheme());

		HttpGet get = new HttpGet(url);

		// localContext
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);

		// 请求配置
		RequestConfig config = RequestConfig.custom().setProxy(new HttpHost(proxyHost, proxyPort))
				.setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(5000).build();
		get.setConfig(config);
		HttpEntity he = new HttpEntity();
		he.setClient(client);
		he.setGet(get);
		return he;
	}

	/**
	 * 将输入流转为字符串
	 * 
	 * @author huangying 2016年12月27日10:08:06
	 * @param is
	 *            输入流
	 * @return 转化后的字符串
	 */
	public static String readInputStream(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length = 0;
			byte[] buffer = new byte[1024];
			while ((length = is.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
			is.close();
			baos.close();
			// 或者用这种方法
			// byte[] result=baos.toByteArray();
			// return new String(result);
			return baos.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @ClassName:HttpRequestUtils.java
	 * @ClassDescribe:
	 * @auth:sky
	 * @createDate:2017年11月15日 下午3:41:33
	 * @updateAuth:
	 * @updateDate:
	 * @version
	 * @param urlStr
	 */
	public static void sendgetByProxy(String urlStr) {

		BufferedReader in2 = null;
		String result = "";
		try {
			URL url = new URL(urlStr);
			// 创建代理服务器
			InetSocketAddress addr = new InetSocketAddress("218.92.220.67", 8080);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理

			HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);// proxy

			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setReadTimeout(8000);
			connection.setConnectTimeout(8000);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");

			InputStream in = connection.getInputStream();
			in2 = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = in2.readLine()) != null) {
				// 遍历抓取到的每一行并将其存储到result里面
				result += line + "\n";
			}

			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String urlEncode(String source, String encode) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "0";
		}
		return result;
	}

	public static HttpGet setRequestHeader(HttpGet httpGet, String headerPath) {

		Map<String, String> map = requestHeaderToMap(headerPath);

		for (String keyStr : map.keySet()) {

			String valStr = map.get(keyStr);// 得到每个key多对用value的值
			httpGet.setHeader(keyStr, valStr);
			// httpGet.addHeader(keyStr, valStr);

			System.out.println(keyStr + "--" + valStr);

		}

		return httpGet;
	}

	public static Map<String, String> requestArgsToMap(String pramPath) {

		Map<String, String> resultMap = new HashMap<>();

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String reStr = "";
		try {
			String str = "";

			fis = new FileInputStream(pramPath);
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {

				int index = str.indexOf(":");
				String str1 = str.substring(0, index);
				String str2 = str.substring(index + 1);

				resultMap.put(str1, str2);

			}

		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件失败");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			reStr = URLEncoder.encode(reStr, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 * 将header 转化为map
	 * 
	 * @ClassName:FileUtil.java
	 * @ClassDescribe:
	 * @auth:sky
	 * @createDate:2017年11月20日 上午9:07:18
	 * @updateAuth:
	 * @updateDate:
	 * @version
	 * @param pramPath
	 * @return
	 */
	public static Map<String, String> requestHeaderToMap(String pramPath) {

		Map<String, String> resultMap = new HashMap<>();

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String reStr = "";
		try {
			String str = "";

			fis = new FileInputStream(pramPath);
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				String[] strArr = str.split(":");
				resultMap.put(strArr[0], strArr[1]);

			}

		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件失败");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			reStr = URLEncoder.encode(reStr, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 * 
	 * @ClassName:HttpRequestUtils.java
	 * @ClassDescribe:
	 * @auth:sky
	 * @createDate:2018年1月26日 上午10:59:52
	 * @updateAuth:
	 * @updateDate:
	 * @version
	 * @param url
	 *            访问地址（包含传递的参数 ）
	 * @param headerMap
	 *            请求头 headers
	 * @param resultType
	 *            响应结果 1：html 2: json
	 * @return
	 */
	public static ResponseResult sendGet(String url, Map<String, String> headerMap, int resultType) {

		ResponseResult responseResult = new ResponseResult();

		String result = "";
		URL realUrl = null;
		int responseCode = 200;
		try {
			realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = null;
			connection = (HttpURLConnection) realUrl.openConnection();
			
			for (String keyStr : headerMap.keySet()) {
				String valStr = headerMap.get(keyStr);// 得到每个key多对用value的值
				connection.setRequestProperty(keyStr, valStr);

			}
			
			connection.connect();
			responseCode = connection.getResponseCode();
			responseResult.setResponesCode(responseCode);
			if (responseCode == 200) {

				BufferedReader in = null;
				in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "ISO-8859-1"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				responseResult.setResponesStr(result);
			}

		} catch (Exception e) {

			e.printStackTrace();
			responseResult.setResponesCode(responseCode);
			responseResult.setResponesStr(e.toString());

			return responseResult;
		}

		return responseResult;
	}
	
	//canuse
	public static ResponseResult sendGetforZip(String url, Map<String, String> headerMap, int resultType) {

		ResponseResult responseResult = new ResponseResult();

		String result = "";
		URL realUrl = null;
		int responseCode = 200;
		try {
			realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = null;
			connection = (HttpURLConnection) realUrl.openConnection();
			
			for (String keyStr : headerMap.keySet()) {
				String valStr = headerMap.get(keyStr);// 得到每个key多对用value的值
				connection.setRequestProperty(keyStr, valStr);

			}
			
			connection.connect();
			responseCode = connection.getResponseCode();
			responseResult.setResponesCode(responseCode);
			if (responseCode == 200) {

				BufferedReader in = null;
				//in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "ISO-8859-1"));
				
				InputStream urlStream = new GZIPInputStream(connection.getInputStream());  
				in = new BufferedReader(new InputStreamReader(urlStream,"utf-8")); 
				
				
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				responseResult.setResponesStr(result);
				
				String sessionId = "";  
		        String cookieVal = "";  
		        String key = null; 
				
				
				 //取cookie  
		        for(int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++){  
		            if(key.equalsIgnoreCase("set-cookie")){  
		                cookieVal = connection.getHeaderField(i);  
		                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));  
		                sessionId = sessionId + cookieVal + ";";  
		            }  
		        }  
		        
		        System.err.println(sessionId);
		        responseResult.setResponesCookie(sessionId);
			}

		} catch (Exception e) {

			e.printStackTrace();
			responseResult.setResponesCode(responseCode);
			responseResult.setResponesStr(e.toString());

			return responseResult;
		}

		return responseResult;
	}
	
	//canuse
	public static ResponseResult sendGetforZipWithProxy(String url, Map<String, String> headerMap, int resultType) {

		String user = "HQPNV182R256008D";
		String pwd = "0D60F07D5A744504";
		ResponseResult responseResult = new ResponseResult();

		String result = "";
		URL realUrl = null;
		int responseCode = 200;
		try {
			realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = null;
			// 创建代理服务器地址对象
			InetSocketAddress addr = new InetSocketAddress("proxy.abuyun.com", 9020);
			// 创建HTTP类型代理对象
			Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
			connection = (HttpURLConnection) realUrl.openConnection(proxy);
			String headerKey = "Proxy-Authorization";
			String auth = "Basic " + Base64.encode((user + ":" + pwd).getBytes());
			connection.setRequestProperty(headerKey, auth);
			connection.setRequestProperty("Proxy-Switch-Ip", "yes");
			
			for (String keyStr : headerMap.keySet()) {
				String valStr = headerMap.get(keyStr);// 得到每个key多对用value的值
				connection.setRequestProperty(keyStr, valStr);

			}
			
			connection.connect();
			responseCode = connection.getResponseCode();
			responseResult.setResponesCode(responseCode);
			if (responseCode == 200) {

				BufferedReader in = null;
				//in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "ISO-8859-1"));
				
				InputStream urlStream = new GZIPInputStream(connection.getInputStream());  
				in = new BufferedReader(new InputStreamReader(urlStream,"utf-8")); 
				
				
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				responseResult.setResponesStr(result);
				
				String sessionId = "";  
		        String cookieVal = "";  
		        String key = null; 
				
				
				 //取cookie  
		        for(int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++){  
		            if(key.equalsIgnoreCase("set-cookie")){  
		                cookieVal = connection.getHeaderField(i);  
		                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));  
		                sessionId = sessionId + cookieVal + ";";  
		            }  
		        }  
		        
		        //System.err.println(sessionId);
		        responseResult.setResponesCookie(sessionId);
			}

		} catch (Exception e) {

			e.printStackTrace();
			responseResult.setResponesCode(responseCode);
			responseResult.setResponesStr(e.toString());

			return responseResult;
		}

		return responseResult;
	}

}