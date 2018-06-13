package com.xmu.rebot.wechat.handler;


import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xmu.rebot.alice.manager.ChatterBeanManager;
import com.xmu.rebot.alice.service.ElasticsearchService;
import com.xmu.rebot.alice.service.OtherService;
import com.xmu.rebot.wechat.builder.ImageBuilder;
import com.xmu.rebot.wechat.builder.TextBuilder;
import com.xmu.rebot.wechat.builder.VoiceBuilder;
import com.xmu.rebot.wechat.service.WeixinService;

import java.util.Map;


/** 
* @ClassName: MsgHandler 
* @Description: 处理消息
* @author 王崇菲
* @date 2018年3月6日 下午9:17:24 
* @version V1.0 
*/
@Component
public class MsgHandler extends AbstractHandler {
	

	@Autowired
	private ChatterBeanManager chatterBeanManger;
	
	@Autowired
	private ElasticsearchService elasticsearchService;
	
	@Autowired
	private OtherService otherService;
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) {
	  

    WeixinService weixinService = (WeixinService) wxMpService;
    
    if(wxMessage.getMsgType().equals("text"))
    {
    	   if(StringUtils.containsAny(wxMessage.getContent(), "笑话","故事","搞笑"))
    	    {
    	    	 return new TextBuilder().build(elasticsearchService.getJoke(), wxMessage, weixinService);
    	    }
    	   return new TextBuilder().build(this.otherService.getAnswer(wxMessage.getContent(),wxMessage.getFromUser()), wxMessage, weixinService);
    }
    	
    else if(wxMessage.getMsgType().equals("image"))
    	return new ImageBuilder().build(this.otherService.getImageAnswer(wxMessage.getUrl(), wxMessage.getFromUser()), wxMessage, weixinService);
    else if(wxMessage.getMsgType().equals("voice"))
    	return new VoiceBuilder().build(wxMessage.getMediaId(),wxMessage,weixinService);
    else 
    	return new TextBuilder().build("暂不支持此类消息", wxMessage, weixinService);
    /*
    if (!wxMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
      //TODO 可以选择将消息保存到本地
    }

 
    
    if(StringUtils.containsAny(wxMessage.getContent(), "笑话","故事","搞笑"))
    {
    	 return new TextBuilder().build(elasticsearchService.getJoke(), wxMessage, weixinService);
    }
 
  
    String content = this.chatterBeanManger.getChatterBeanByUser(wxMessage.getFromUser()).respond(wxMessage.getContent());
    
    return new TextBuilder().build(content, wxMessage, weixinService);

*/
  }
  

}
