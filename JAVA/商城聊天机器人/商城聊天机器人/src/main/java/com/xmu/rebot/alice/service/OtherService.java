package com.xmu.rebot.alice.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.hibernate.dialect.Sybase11Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import bsh.This;


/** 
* @ClassName: OtherService 
* @Description: 调用外部接口的service 
* @author 王崇菲
* @date 2018年4月14日 下午3:08:26 
* @version V1.0 
*/
@Service
public class OtherService {
	
	private final Logger logger=LoggerFactory.getLogger(This.class);
	
	public String getQAnswer(String question) {
		String answer="";
		try {
			URL url = new URL("http://api.qingyunke.com/api.php?key=free&appid=0&msg="+question);
			 HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
	            httpUrlConn.setDoInput(true);  
	            httpUrlConn.setRequestMethod("GET");  
	            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	            //获取输入流  
	            httpUrlConn.getInputStream();
	            
	            answer = IOUtils.toString(httpUrlConn.getInputStream(), "UTF-8");
	            JSONObject answer_json=com.alibaba.fastjson.JSONObject.parseObject(answer);
	            answer=answer_json.getString("content");
	            
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return answer;
		
	}
	
	public String getAnswer(String question)
	{
		String userID="001";
		return getAnswer(question,userID);
		
	}
	
	public String getAnswer(String question,String userID)
	{
		String url="http://openapi.tuling123.com/openapi/api/v2";
		String apikey="d38891d7ab524688919dce4f3a95abbd";
		@SuppressWarnings("resource")
		String data="{\"perception\": {\"inputText\": {\"text\": \""+question+"\"}},\"userInfo\": {\"apiKey\": \""+apikey+"\",\"userId\": \""+userID+"\"}}";
		this.logger.debug(data);
		String answer="";
		try {
			String response=sendPost(url,data);
			JSONObject jsonObject=JSON.parseObject(response);
			this.logger.debug(jsonObject.toString());
			answer=jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("values").getString("text");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return answer;
	}
	

	
	public String getImageAnswer(String Imageurl,String userID)
	{
		String url="http://openapi.tuling123.com/openapi/api/v2";
		@SuppressWarnings("resource")
		String data="{\r\n" + 
				"	\"reqType\":1,\r\n" + 
				"    \"perception\": {\r\n" + 
				"        \"inputImage\": {\r\n" + 
				"            \"url\": \""+Imageurl+"\"\r\n" + 
				"        }\r\n" + 
				"    },\r\n" + 
				"    \"userInfo\": {\r\n" + 
				"        \"apiKey\": \"d38891d7ab524688919dce4f3a95abbd\",\r\n" + 
				"        \"userId\": \""+userID+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		this.logger.debug(data);
		String answer="";
		try {
			String response=sendPost(url,data);
			JSONObject jsonObject=JSON.parseObject(response);
			this.logger.debug(jsonObject.toString());
			answer=jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("values").getString("image");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return answer;
	}
	
	private String sendPost(String url,String Params)throws IOException{
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response="";
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(
                    conn.getOutputStream());
            out.write(Params);
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response+=lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();

            
        } catch (Exception e) {
        System.out.println("发送 POST 请求出现异常！"+e);
        e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
        try{
            if(out!=null){
                out.close();
            }
            if(reader!=null){
                reader.close();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

        return response;
    }

}
