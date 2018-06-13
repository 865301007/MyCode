package com.xmu.rebot.alice.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Vector;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

@Service
public class ElasticsearchService {
	
	

	public ElasticsearchService()
	{
		
	}
	
	@SuppressWarnings("resource")
	public Vector<String> getAll()
	{
		TransportClient client=null;
		try {
			client=new PreBuiltTransportClient(Settings.EMPTY)
			.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<String> categorys=new Vector<String>();
		SearchResponse response=client.prepareSearch().setIndices("aiml").setTypes("category").get();
		//System.out.println(response);
		for (SearchHit hit:response.getHits().getHits())
		{
			categorys.add(hit.getSourceAsString());
		}
		client.close();
		return categorys;
	}
	
	@SuppressWarnings("resource")
	public String getJoke()
	{
		TransportClient client=null;
		try {
			client=new PreBuiltTransportClient(Settings.EMPTY)
			.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String joke="";
		Random random=new Random();
		int total=5602;//5602是目前笑话总数，这里为了节省查询时间直接在这里给出，后期可根据具体情况做优化
		SearchResponse response=client.prepareSearch().setIndices("jokes").setTypes("joke").setFrom(Math.abs(random.nextInt()%total)).setSize(1).get();
		JSONObject joke_json=com.alibaba.fastjson.JSON.parseObject(response.getHits().getHits()[0].getSourceAsString(), Feature.OrderedField);
		joke="《"+joke_json.getString("title")+"》\n"+joke_json.getString("content");
		client.close();
		return joke;
	}
	
	@SuppressWarnings("resource")
	public String getAnswer(String question) {
		
		TransportClient client=null;
		try {
			client=new PreBuiltTransportClient(Settings.EMPTY)
			.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
			SearchResponse response=client.prepareSearch().setIndices("xhj").setTypes("xhj").setQuery(QueryBuilders.matchQuery("question", question)).get();
			int count=response.getHits().getHits().length;
			Random random=new Random();
			JSONObject answer=(JSONObject) JSON.parse(response.getHits().getAt(Math.abs(random.nextInt()%count)).getSourceAsString());
			
			client.close();
			return (answer.getString("answer"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "未找到答案";
	}
	


}
