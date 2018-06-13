package com.xmu.rebot.wechat.builder;

import com.xmu.rebot.wechat.service.WeixinService;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutVoiceMessage;

public class VoiceBuilder extends AbstractBuilder {

	@Override
	public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WeixinService service) {
		// TODO Auto-generated method stub
		WxMpXmlOutVoiceMessage v = WxMpXmlOutMessage.VOICE().mediaId(content)
		        .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
		        .build();
		return v;
	}

}
