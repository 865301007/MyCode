package com.xmu.rebot.alice.service;

import java.util.Date;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xmu.rebot.alice.manager.ChatterBeanManager;

import bitoflife.chatterbean.TimedChatterBean;
import bsh.This;


/** 
* @ClassName: TimerService 
* @Description: 提供定时任务 
* @author 王崇菲
* @date 2018年4月14日 下午3:11:32 
* @version V1.0 
*/
@Service
public class TimerService {
	private final Logger logger=LoggerFactory.getLogger(This.class);
	
	@Autowired
	private ChatterBeanManager chatterBeanManager;
	/**
	 * 
	* @Title: freeChatterBean 
	* @Description: 释放五分钟前的chatterBean
	* @param     入参
	* @return void    返回类型
	* @author 王崇菲
	* @throws
	* @date 2018年3月15日 下午1:43:31 
	* @version V1.0
	 */
	//此处是执行定时任务，目前处于调试阶段先不用
	//@Scheduled(fixedRate=30000)
	public void freeChatterBean()
	{
		
		logger.debug("执行定时任务");
		ConcurrentHashMap<String, TimedChatterBean> chatterBeans=this.chatterBeanManager.getChatterBeans();
		Date currentTime=new Date();
		
		Vector<String> removedUsers=new Vector<String>();
		for(Map.Entry<String, TimedChatterBean> entry:chatterBeans.entrySet())
		{	
			if(currentTime.getTime()-entry.getValue().getDate().getTime()>=30000)
			{
				removedUsers.add(entry.getKey());
			}
		}
		
		this.chatterBeanManager.freeChatterBeanByUser(removedUsers);
		
	}
}
