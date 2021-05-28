package cn.dlut.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

/**
 * 
 * 
 */

public class Vnet_HttpRequest {
	
	/**
	 * 
	 * 
	 */

	public static String readContentFromGet(String GET_URL, String HEADER_NAME, String HEADER_VALUE) throws IOException {
		String getURL = GET_URL;
		System.out.println("getURL======"+getURL);
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestProperty("Content-Type",
				"application/json");
		connection.setRequestProperty(HEADER_NAME, HEADER_VALUE);
		connection.setConnectTimeout(10000);

		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		String line = "";
		String result = "";   
		while ((line = reader.readLine()) != null){
			result = result + line;
        }
		reader.close();
		connection.disconnect();	
		System.out.println("Get Success");
		return result;
	}
	
	
	/**
	 * HttpRequest.readContentFromPut()
	 * @param PUT_URL
	 */
	public static String readContentFromPut(String PUT_URL, String HEADER_NAME, String HEADER_VALUE)
			throws IOException {
		URL putUrl = new URL(PUT_URL);
//		System.out.println("putURL======"+putUrl);
		HttpURLConnection connection = (HttpURLConnection) putUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("PUT");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty(HEADER_NAME, HEADER_VALUE);
		connection.setConnectTimeout(10000);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write("Resource content");
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		String line = "";
		String result = "";   
		while ((line = reader.readLine()) != null){
			result = result + line;
        }
		reader.close();
		connection.disconnect();
		return result;
	}
	
		
	/**
	 * HttpRequest.readContentFromPost()
	 * @param POST_URL
	 * @param content
	 */
	public static String readContentFromPost(String POST_URL, String content, String HEADER_NAME, String HEADER_VALUE)
			throws IOException {
		URL postUrl = new URL(POST_URL);
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestProperty("Content-Type",
				"application/json");
		connection.setRequestProperty(HEADER_NAME, HEADER_VALUE);
		connection.setConnectTimeout(10000);

		connection.connect();
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		out.writeBytes(content);
		out.flush();
		out.close();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		String line = "";
		String result = "";   //Post returned string
		while ((line = reader.readLine()) != null){
			result = result + line;
        }
		reader.close();
		connection.disconnect();
		return result;
	}
	public static String rpcFromPost(String POST_URL, String content)
	throws IOException {
		URL postUrl = new URL(POST_URL);
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestProperty("Content-Type","application/json");
		connection.setConnectTimeout(10000);
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		out.writeBytes(content);
		out.flush();
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		String line = "";
		String result = "";   //Post returned string
		while ((line = reader.readLine()) != null){
			result = result + line;
        }
		reader.close();
		connection.disconnect();
		return result;
	}
	public static Boolean readContentFromDelete(String DELETE_URL, String HEADER_NAME, String HEADER_VALUE)
			throws IOException {

		Boolean result = false;
		URL deleteUrl = new URL(DELETE_URL);
		
		HttpURLConnection connection = (HttpURLConnection) deleteUrl.openConnection();
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty(HEADER_NAME, HEADER_VALUE);
		connection.setRequestMethod("DELETE");
		if (connection.getResponseCode() == 204) {
			result = true;
		} 
		
		connection.disconnect();
		return result;
	}

	
	
	
	public static void main(String args[]) {
		try {
			 String POST_URL1 = "http://192.168.0.131:12345";
			 String content_showovs="{\"id\":\"1\",\"jsonrpc\":\"2.0\",\"method\":\"cmd\",\"params\":{\"cmd\":\"ovs-vsctl show\"}}";
			 System.out.println( Vnet_HttpRequest.rpcFromPost(POST_URL1, content_showovs));
//			 String POST_URL = "http://192.168.0.86:35357/v2.0/tokens";
//			 String GET_URL = "http://192.168.0.86:9696/v2.0/";
//			 String content = "{\"auth\":{\"tenantName\":\"admin\",\"passwordCredentials\":{\"username\":\"admin\",\"password\":\"0000\"}}}"; 
//			 String result = HttpRequest.readContentFromPost(POST_URL,content);
//			 System.out.println(result);
//			 JSONObject jsonObj = (JSONObject) ((JSONObject) JSONObject.fromString(result).get("access")).get("token");
//			 String HEADER_NAME = "X-Auth-Token";
//			 String HEADER_VALUE = jsonObj.getString("id");
//			 String result2 = HttpRequest.readContentFromGet(GET_URL, HEADER_NAME, HEADER_VALUE);
//			 System.out.println(result2);
//			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}