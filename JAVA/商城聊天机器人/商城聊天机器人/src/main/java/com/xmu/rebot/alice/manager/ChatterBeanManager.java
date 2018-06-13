package com.xmu.rebot.alice.manager;

import java.util.Date;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xmu.rebot.alice.service.AliceService;

import bitoflife.chatterbean.ChatterBean;
import bitoflife.chatterbean.TimedChatterBean;



/** 
* @ClassName: ChatterBeanManager 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王崇菲
* @date 2018年3月15日 下午1:18:42 
* @version V1.0 
*/
@Component
public class ChatterBeanManager {

	@Autowired
	private AliceService aliceService;

	private ConcurrentHashMap<String, TimedChatterBean> chatterBeans = new ConcurrentHashMap<String, TimedChatterBean>();

	public ChatterBean getChatterBeanByUser(String User) {

		// 如果内存中有
		if (chatterBeans.containsKey(User)) {
			chatterBeans.get(User).setDate(new Date());

		} else {

			chatterBeans.put(User, new  TimedChatterBean(new Date(),this.aliceService.getChatterBean()));

		}

		return chatterBeans.get(User).getChatterBean();
		// 如果内存中没有但是数据库中有
		// 如果以上两处都没有
	}
	
	public ConcurrentHashMap<String, TimedChatterBean> getChatterBeans()
	{
		return this.chatterBeans;
	}
	/**
	 * 
	* @Title: freeChatterBeanByUser 
	* @Description: 释放过期的chatterBean
	* @param @param user    入参
	* @return void    返回类型
	* @author 王崇菲
	* @throws
	* @date 2018年3月15日 下午1:24:14 
	* @version V1.0
	 */
	public void freeChatterBeanByUser(String user)
	{
		
		 Map<String, Object> properties=chatterBeans.get(user).getChatterBean().getAliceBot().getContext().getProperties();
		 
		 for(Map.Entry<String, Object> entry:properties.entrySet())
		 {
			 System.out.println(entry.getKey()+","+entry.getValue());
		 }
		 
		
		this.chatterBeans.remove(user);
	}
	
	public void freeChatterBeanByUser(Vector<String> users)
	{
		for(String removedUser:users)
			this.freeChatterBeanByUser(removedUser);
	}

}
