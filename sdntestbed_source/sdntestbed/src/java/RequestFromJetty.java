

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import net.sf.json.JSONObject;

public class RequestFromJetty {
	static HttpHost  host = new HttpHost("192.168.0.106", 8443, "https");
	
	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		System.out.println("das=====");
		Map map=new HashMap();
		try {
			doPost("https://192.168.0.106:8080/control",map,"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	 

	
	
	 
	public static CloseableHttpClient createHttpsClient() {
	    X509TrustManager x509mgr = new X509TrustManager() {
	        @Override
	        public void checkClientTrusted(X509Certificate[] xcs, String string) {
	        }
	        @Override
	        public void checkServerTrusted(X509Certificate[] xcs, String string) {
	        }
	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            return null;
	        }
	    };
	 
	    SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			sslContext.init(null, new TrustManager[] { x509mgr }, null);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	 
	    return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
	
	private static void  doPost(String url, Map<String, String> headers, String body) throws IOException {
		CloseableHttpClient client= createHttpsClient();
		HttpPost post = new HttpPost(url);
		
	    for (Map.Entry<String, String> header : headers.entrySet()) {
	        post.setHeader(header.getKey(), header.getValue());
	    }
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isAddController", "yes");
		String JSONRPCStr = getJSONRPCString("addController", map);
	     List<NameValuePair> nvps = new ArrayList(); 
	        nvps.add(new BasicNameValuePair("JSONRPCString", JSONRPCStr));  
	        post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
	        
		
		

	    
	    HttpContext context = createBasicAuthContext("admin", "admin");
	    CloseableHttpResponse response = client.execute(host, post, context);
	    try {
	        // status = response.getStatusLine().getStatusCode();
	        // headers = response.getAllHeaders();
	 
	        // HttpEntity entity = response.getEntity();
	        // text = IOUtils.toString(entity.getContent(), "ISO-8859-1");
	    } finally {
	        response.close();
	    }
	}
	
	public static String getJSONRPCString(String method, Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jsonrpc", "2.0");
		map.put("method", method);
		map.put("params", params);
		map.put("id", "Coordinator");
		
		return JSONObject.fromObject(map).toString();
	}
	
	private static HttpClientContext createBasicAuthContext(String username, String password) {

	    CredentialsProvider credsProvider = new BasicCredentialsProvider();
	    Credentials defaultCreds = new UsernamePasswordCredentials(username, password);
	    credsProvider.setCredentials(new AuthScope("127.0.0.1", 8080), defaultCreds);
	 
	    AuthCache authCache = new BasicAuthCache();
	    BasicScheme basicAuth = new BasicScheme();
	    authCache.put(host, basicAuth);
	 
	    HttpClientContext context = HttpClientContext.create();
	    context.setCredentialsProvider(credsProvider);
	    context.setAuthCache(authCache);
	    return context;
	}

}
