package com.xmu.rebot.wechat.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmu.rebot.wechat.Config.WxMpConfig;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;


/** 
* @ClassName: WeixinMenuService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王崇菲
* @date 2018年3月28日 下午2:57:18 
* @version V1.0 
*/
@Service
public class WeixinMenuService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WxMpConfig wxMpConfig;
	
	@Autowired
	WxMpService wxMpService;
	
	
	/**
	 * 
	* @Title: init 
	* @Description: 此方法原本是为了添加自定义菜单，但是由于权限不够已经废弃 
	* @param     入参
	* @return void    返回类型
	* @author 王崇菲
	* @throws
	* @date 2018年4月2日 下午3:42:25 
	* @version V1.0
	 */
	@PostConstruct
	public void  init() {
		// TODO Auto-generated constructor stub
	
		WxMenu wxMenu=new WxMenu();
		WxMenuButton button=new WxMenuButton();
		
		button.setAppId(wxMpConfig.getAppid());

		button.setKey(this.wxMpConfig.getAesKey());
		button.setName("商城");
		button.setType("view");
		button.setUrl("http://39.108.15.155/JavaEE/");
		List<WxMenuButton> buttons=new ArrayList<>();
		buttons.add(button);
		
		
		wxMenu.setButtons(buttons);
		
		try {
			wxMpService.getMenuService().menuCreate(wxMenu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.logger.info("创建菜单成功!");
		
	}

}
