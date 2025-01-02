package bb.j2ray.tools;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpTool {
	private final static MultiThreadedHttpConnectionManager manager;
	private final static HttpClient httpClient;
	private static final int DEFAULT_TIMEOUT = 3000;
	private static final int DEFAULT_CONNECTS = 100000;

	static {
		manager = new MultiThreadedHttpConnectionManager();
		httpClient = new HttpClient(manager);

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(DEFAULT_TIMEOUT);
		params.setSoTimeout(DEFAULT_TIMEOUT);
		params.setMaxTotalConnections(DEFAULT_CONNECTS);
		manager.setParams(params);
	}

	public static String getRealURL(String rootURL,String uri){
		if(!uri.startsWith("https://")&&!uri.startsWith("http://")){
			uri=rootURL+"/"+uri;
		}
		return uri;
	}

	private static NameValuePair[] parseParamFromMap(Map<String, String> param) {

		NameValuePair[] nameValuePairs = new NameValuePair[param.size()];
		int i = 0;
		for (String key : param.keySet()) {
			NameValuePair nValuePair = new NameValuePair(key, param.get(key));
			nameValuePairs[i] = nValuePair;
			i++;
		}

		return nameValuePairs;
	}
	public static String post(String uri, Map<String, String> param) throws HttpException {

		PostMethod postMethod = new PostMethod(uri);
		postMethod.setRequestBody(parseParamFromMap(param));
		postMethod.getParams().setContentCharset("UTF-8");
		InputStream inputStream=null;
		try {
			httpClient.executeMethod(postMethod);
			inputStream = postMethod.getResponseBodyAsStream();
			BufferedReader readber = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			String tmpRes;
			StringBuilder stb=new StringBuilder();
			while ((tmpRes = readber.readLine()) != null) {
				stb.append(tmpRes);
			}
			readber.close();
			return stb.toString();

		} catch (IOException e) {
			throw new HttpException(-8000,uri,e);
		} finally {
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			postMethod.releaseConnection();
		}

	}


	public static byte[] download(String uri) throws HttpException {

		GetMethod postMethod = new GetMethod(uri);
		try {
			httpClient.executeMethod(postMethod);
			return postMethod.getResponseBody();
		} catch (IOException e) {
			throw new HttpException(-8000,uri,e);
		} finally {
			postMethod.releaseConnection();
		}
	}


	public static String httpsPost(String uri, String data) throws HttpException {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(uri);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			((HttpsURLConnection) conn).setHostnameVerifier(new XHostnameVerifier());
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(data);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (IOException e) {
			throw new HttpException(-8000,uri,e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
	}


	public static String httpsPost(String uri, Map<String, String> paramMap) throws HttpException {
		// 创建 HttpClient 实例
		HttpClient httpClient = new HttpClient();

		// 配置 HTTPS 请求
		ProtocolSocketFactory factory = new SSLProtocolSocketFactory();
		Protocol.registerProtocol("https", new Protocol("https", factory, 443));

		// 创建 POST 请求
		PostMethod postMethod = new PostMethod(uri);

		// 设置请求体
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		for(String kk:paramMap.keySet()){
			postMethod.setParameter(kk, paramMap.get(kk));
		}

		try {
			// 执行请求
			int statusCode = httpClient.executeMethod(postMethod);

			// 处理响应
			if (statusCode == HttpStatus.SC_OK) {
				return postMethod.getResponseBodyAsString();
			} else {
				throw new HttpException(-8000,"HTTP POST Request ErrorCode="+statusCode);
			}
		} catch (Exception e) {
		    throw new HttpException(-8000,"Https POST Error",e);
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}

	}


	public static String post(String uri, String data) throws HttpException {

		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(uri);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(data);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (IOException e) {
			throw new HttpException(-8000,uri,e);
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
			}
		}
	}


	public static String get(String uri,Map<String,String> header) throws HttpException {
		GetMethod postMethod = new GetMethod(uri);
		String tmpRes = "";
		InputStream inputStream = null;
		StringBuilder stb = new StringBuilder();
		try {

			for(Map.Entry<String,String> entry:header.entrySet()){
				postMethod.setRequestHeader(entry.getKey(),entry.getValue());
			}

			httpClient.executeMethod(postMethod);
			inputStream = postMethod.getResponseBodyAsStream();
			BufferedReader readber = new BufferedReader(new InputStreamReader(inputStream));

			while ((tmpRes = readber.readLine()) != null) {
				stb.append(tmpRes);
			}
			readber.close();
			return stb.toString();
		} catch (IOException e) {
			throw new HttpException(-8000,uri,e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			postMethod.releaseConnection();
		}

	}


	public static String get(String uri) throws HttpException {
		GetMethod postMethod = new GetMethod(uri);
		String tmpRes = "";
		InputStream inputStream = null;
		StringBuilder stb = new StringBuilder();
		try {

			httpClient.executeMethod(postMethod);
			inputStream = postMethod.getResponseBodyAsStream();
			BufferedReader readber = new BufferedReader(new InputStreamReader(inputStream));

			while ((tmpRes = readber.readLine()) != null) {
				stb.append(tmpRes);
			}
			readber.close();
			return stb.toString();
		} catch (IOException e) {
			throw new HttpException(-8000,uri,e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			postMethod.releaseConnection();
		}
	}


	public static String get(String uri, String charter) throws HttpException {
		GetMethod postMethod = new GetMethod(uri);
		String tmpRes = "";
		InputStream inputStream = null;
		StringBuilder stb = new StringBuilder();
		try {
			httpClient.executeMethod(postMethod);
			inputStream = postMethod.getResponseBodyAsStream();
			BufferedReader readber = new BufferedReader(new InputStreamReader(inputStream, charter));

			while ((tmpRes = readber.readLine()) != null) {
				stb.append(tmpRes);
			}
			readber.close();
			return stb.toString();
		} catch (IOException e) {
			throw new HttpException(-8000,uri,e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {

				}
			}
			postMethod.releaseConnection();
		}
	}

	public void setTimeOut(int timeOut) {
		manager.getParams().setConnectionTimeout(timeOut);
		manager.getParams().setSoTimeout(timeOut);
	}

	public static void reset() {
		manager.getParams().setConnectionTimeout(DEFAULT_TIMEOUT);
		manager.getParams().setSoTimeout(DEFAULT_TIMEOUT);
	}
}