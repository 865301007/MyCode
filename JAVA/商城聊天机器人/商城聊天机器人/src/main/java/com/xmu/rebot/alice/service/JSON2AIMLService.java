package com.xmu.rebot.alice.service;

import java.io.IOException;
import java.util.Set;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

@Service
public class JSON2AIMLService {
	
	
	public String json2aiml(String json)
	{
		String aiml="<aiml><category";
		JSONObject jo=com.alibaba.fastjson.JSON.parseObject(json, Feature.OrderedField);
		
		aiml+=this.writeAIMLfromJSON(jo);
		//this.writeAIMLfromJSON(jo);
		aiml+="</category></aiml>";
		
		//System.out.println(aiml);
		return aiml;
	}
	
	public Vector<String> json2aiml(Vector<String> jsons)
	{
		Vector<String> aimls=new Vector<String>();
		for(String json:jsons)
		{
			aimls.add(this.json2aiml(json));
		}
		return aimls;
	}
	
	/**
	 * 
	 * @param aiml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @Description: ����������д�����
	 */

	
	public String writeAIMLfromJSON(JSONObject jo)
	{
		String result="";
		
		if(jo.containsKey("attributes"))   //如果标签含有属性
		{
			JSONObject attributes=jo.getJSONObject("attributes");
			for(String key :attributes.keySet())
				result+=" "+key+"=\""+attributes.getString(key)+"\" ";
		}
		result+=">";
	
		Set<String> keys=jo.keySet();
		
		for(String key :keys)
		{
			String init_key=key.substring(0,key.indexOf("--")==-1?key.length():key.indexOf("--"));
			
			if(key.equals("attributes")) continue;
			
			if(key.startsWith("text"))
			{
				result+=jo.getString(key);
			}
			else
			{
				result+="<"+init_key+this.writeAIMLfromJSON(jo.getJSONObject(key)); //
				result+="</"+init_key+">";
			}
		}
		
		return result;
	}
	
	

}
