package com.xmu.rebot.wechat.Config;

import org.springframework.context.annotation.Configuration;



/** 
* @ClassName: WxMpConfig 
* @Description: TODO(������һ�仰��������������) 
* @author �����
* @date 2018��3��6�� ����3:29:58 
* @version V1.0 
*/
@Configuration
public class WxMpConfig {
  //@Value("#{wxProperties.wx_token}")
  private String token="hello";

  //@Value("#{wxProperties.wx_appid}")
  private String appid="wxd66e8df74b6dfe5e";

  //@Value("#{wxProperties.wx_appsecret}")
  private String appsecret="8ac218718c00d5d05318812f90fcdef1";

  //@Value("#{wxProperties.wx_aeskey}")
  private String aesKey="9J38Ke9mqNv2m0vcHe1iqhJt3O8xZFp2WMFKp4J2kZk";

  public String getToken() {
    return this.token;
  }

  public String getAppid() {
    return this.appid;
  }

  public String getAppsecret() {
    return this.appsecret;
  }

  public String getAesKey() {
    return this.aesKey;
  }

}
