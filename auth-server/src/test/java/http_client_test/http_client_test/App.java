package http_client_test.http_client_test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.security.crypto.codec.Base64;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        HttpClient http = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8080/oauth/token?username=user&password=3009873b-bd17-4064-9137-4eba7d0e20b7&grant_type=password&client_id=client&client_secret=secret&scope=app");
        post.setHeader("Content-type", "application/json; charset=utf-8");
        HttpResponse response = http.execute(post);
        
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
        
    }
    
    @Test
    public void testRefreshToken() throws ClientProtocolException, IOException {
    	HttpClient http = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8888/oauth/token?grant_type=refresh_token&client_id=fort&client_secret=fort&refresh_token=ba152423-63fb-4419-9267-8eae3870ae35");
        post.setHeader("Content-type", "application/x-www-form-urlencoded");
        HttpResponse response = http.execute(post);
        
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }
    
    @Test
    public void testPassword() throws ClientProtocolException, IOException {
    	HttpClient http = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8888/oauth/token?username=admin&password=admin&grant_type=password&client_id=fort&client_secret=fort&scope=app&custom=custom");
        post.setHeader("Content-type", "application/x-www-form-urlencoded");
        HttpResponse response = http.execute(post);
        
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }
    
    @Test
    public void testClient() throws ClientProtocolException, IOException {
    	HttpClient http = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8888/oauth/token?grant_type=client_credentials&client_id=fort&client_secret=fort&scope=app");
        post.setHeader("Content-type", "application/json; charset=utf-8");
        //post.setHeader("Authorization", "Bearer 443d2b7b-e845-4c30-85c4-c28a12232284");
        HttpResponse response = http.execute(post);
        
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }
    
    @Test
    public void testResource() throws ClientProtocolException, IOException {
    	HttpClient http = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:9999/user/userList?keyword=user&pageLimit=1");
        //HttpPost post = new HttpPost("http://localhost:9001/private/data");
        //post.setHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Authorization", "Bearer f68c689b-ee12-4ff0-b930-37ff8a37238a");
        post.setHeader("content-type", "application/json");
        //post.setEntity(new ByteArrayEntity("{'_id':'5a375216d1c469191481a0ef','username':'zzg','password':'1234','roletype':'','mail':'','phone':'','description':''}".getBytes("UTF-8")));
        HttpResponse response = http.execute(post);
        
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }
    
    @Test
    public void testCheckToken() throws ClientProtocolException, IOException {
    	HttpClient http = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://fort:fort@localhost:8888/oauth/check_token?token=86d794c1-041a-445f-9013-bbe18cf2ca73");
        post.setHeader("Content-type", "application/json; charset=utf-8");
        //post.setHeader("Authorization", "Bearer 79d6fcae-03d6-4713-adbe-280ed2961333");
        HttpResponse response = http.execute(post);
        
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }
    
    @Test
    public void testCode() throws ClientProtocolException, IOException {
    	HttpClient http = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8888/oauth/token?code=lIFjKv&client_id=fort&client_secret=fort&grant_type=authorization_code&redirect_uri=https://www.baidu.com");
        post.setHeader("Content-type", "application/json; charset=utf-8");
        //post.setHeader("Authorization", "Bearer 79d6fcae-03d6-4713-adbe-280ed2961333");
        HttpResponse response = http.execute(post);
        
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }
}
