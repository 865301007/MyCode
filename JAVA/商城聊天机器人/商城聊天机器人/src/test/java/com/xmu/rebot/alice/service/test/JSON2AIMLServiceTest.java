package com.xmu.rebot.alice.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.xmu.config.RootConfig;
import com.xmu.config.WebConfig;
import com.xmu.rebot.alice.service.JSON2AIMLService;

import bsh.This;



/** 
* @ClassName: JSON2AIMLServiceTest 
* @Description: TODO 测试JSON2AIMLService类
* @author 王崇菲
* @date 2018年4月14日 下午2:36:33 
* @version V1.0 
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
@WebAppConfiguration
public class JSON2AIMLServiceTest {
	
	@Autowired
	private JSON2AIMLService json2aimlService;
	
	@Test
	public void json2aimlTest1()
	{
		String json=""
				+ "{\r\n" + 
				"    \"pattern\": {\r\n" + 
				"      \"text0\": \"RANDOM TEST\"\r\n" + 
				"    },\r\n" + 
				"    \"template\": {\r\n" + 
				"      \"random\": {\r\n" + 
				"        \"li--1\": {\r\n" + 
				"          \"text\": \"random1\"\r\n" + 
				"        },\r\n" + 
				"        \"li--2\": {\r\n" + 
				"          \"text\": \"random2\"\r\n" + 
				"        },\r\n" + 
				"        \"li--3\": {\r\n" + 
				"          \"text\": \"random3\"\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  }";
		String aiml="<aiml><category><pattern>RANDOM TEST</pattern><template><random><li>random1</li><li>random2</li><li>random3</li></random></template></category></aiml>";
		assertEquals(aiml, this.json2aimlService.json2aiml(json)); 
		
	}
	
	
	@Test
	public void json2aimlTest2()
	{
		String json="{\r\n" + 
				"    \"pattern\": {\r\n" + 
				"      \"text0\": \"SECOND BLOCK CONDITION TEST\"\r\n" + 
				"    },\r\n" + 
				"    \"template\": {\r\n" + 
				"      \"think\": {\r\n" + 
				"        \"set\": {\r\n" + 
				"          \"attributes\": {\r\n" + 
				"            \"name\": \"block.condition\"\r\n" + 
				"          },\r\n" + 
				"          \"text1\": \"on\"\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"text2\": \"The block condition is      \",\r\n" + 
				"      \"condition\": {\r\n" + 
				"        \"attributes\": {\r\n" + 
				"          \"name\": \"block.condition\",\r\n" + 
				"          \"value\": \"OFF\"\r\n" + 
				"        },\r\n" + 
				"        \"text3\": \"not      \"\r\n" + 
				"      },\r\n" + 
				"      \"text4\": \"working.    \"\r\n" + 
				"    }\r\n" + 
				"  }";
		//System.out.println(json2aimlService.json2aiml(json));
		String aiml="<aiml><category><pattern>SECOND BLOCK CONDITION TEST</pattern><template><think><set name=\"block.condition\" >on</set></think>The block condition is      <condition name=\"block.condition\"  value=\"OFF\" >not      </condition>working.    </template></category></aiml>";
		assertEquals(aiml, this.json2aimlService.json2aiml(json)); 
		
	}

	
	

}
