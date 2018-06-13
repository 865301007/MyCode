package com.xmu.rebot.alice.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import bitoflife.chatterbean.ChatterBean;
import bitoflife.chatterbean.aiml.AIMLParser;
import bitoflife.chatterbean.aiml.AIMLParserConfigurationException;
import bitoflife.chatterbean.aiml.AIMLParserException;
import bsh.This;

/** 
* @ClassName: AliceService 
* @Description: 提供alice服务 
* @author 王崇菲
* @date 2018年3月9日 下午2:58:34 
* @version V1.0 
*/

@Service
public class AliceService {
	private final Logger logger=LoggerFactory.getLogger(This.class);
	
	
	private AIMLParser parser;
	
	@Autowired
	private ElasticsearchService elasticsearchService;
	
	@Autowired
	private JSON2AIMLService json2aimlService;
	public AliceService() {
		// TODO Auto-generated constructor stub
		
		try {
			parser=new AIMLParser();
			} catch (AIMLParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	public ChatterBean getChatterBean() 
	{
		ChatterBean chatterBean=null;
		Resource res = new ClassPathResource("Bots/properties.xml");
		try {
			chatterBean = new ChatterBean(res.getFile().getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Vector<String> categorys=json2aimlService.json2aiml(elasticsearchService.getAll());
		for(String category:categorys)
		{
			try {
				parser.parse(chatterBean.getAliceBot().getGraphmaster(),new ByteArrayInputStream(category.getBytes()));
			} catch (AIMLParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return chatterBean;
	}
	
	public AIMLParser getParser()
	{
		return this.parser;
	}

}
