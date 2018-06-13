package com.xmu.rebot.wechat.builder;



import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.thoughtworks.xstream.io.path.Path;
import com.xmu.rebot.wechat.service.WeixinService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
public class ImageBuilder extends AbstractBuilder {
	
	String path="test.jpg";

  @Override
  public WxMpXmlOutMessage build(String url, WxMpXmlMessage wxMessage,
      WeixinService service) {
	  
	  
	  
	  WxMpMaterialServiceImpl wxMpMaterialService=new WxMpMaterialServiceImpl(service);
	  String media_id="";
	try {
		media_id = wxMpMaterialService.mediaUpload("image", "jpg", new URL(url).openStream()).getMediaId();
	} catch (WxErrorException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  

    WxMpXmlOutImageMessage m = WxMpXmlOutMessage.IMAGE().mediaId(media_id)
        .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
        .build();

    return m;
  }
  
  
 

}
