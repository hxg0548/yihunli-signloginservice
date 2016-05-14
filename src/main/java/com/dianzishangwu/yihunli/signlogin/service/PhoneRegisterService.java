package com.dianzishangwu.yihunli.signlogin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jboss.logging.Logger;

import com.dianzishangwu.yihunli.signlogin.domain.PhoneRegisterException;

public class PhoneRegisterService {

	private String submitAccount="cf_jason";
	private String submitPassword="19900516!";
	private String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	
	Map<String,Integer> phoneNumbers = new HashMap<String,Integer>();
	
	public PhoneRegisterService(){
		phoneNumbers.put("15989663989", 123456);
	}

	public String sendSms(String phoneNumber) throws  HttpException, IOException, PhoneRegisterException, DocumentException{
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(postUrl); 

		client.getParams().setContentCharset("UTF-8");

		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		
		int mobile_code = (int)((Math.random()*9+1)*100000);
	
	    String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。"); 

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", submitAccount), 
			    new NameValuePair("password", submitPassword), //密码可以使用明文密码或使用32位MD5加密
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			    new NameValuePair("mobile", phoneNumber), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);			
		
		client.executeMethod(method);	
		
		String SubmitResult =method.getResponseBodyAsString();
				
		Document doc = DocumentHelper.parseText(SubmitResult); 
		Element root = doc.getRootElement();

		String code = root.elementText("code");	
		String msg = root.elementText("msg");	
		String smsid = root.elementText("smsid");	
				
		Logger logger = Logger.getLogger(this.getClass());
		
	    if("2".equals(code)){
			 phoneNumbers.put(phoneNumber, mobile_code);
			 logger.info("[手机号码注册]保存手机号码和验证码:" + phoneNumber + "," + mobile_code);
		}else{
			 logger.info("[手机号码注册]短信发送失败,错误码:" + code);
			 return "-2";
		}
		 
		return code;
	}
	
	public boolean validatePhone(String phoneNumber,Integer code) throws PhoneRegisterException{
		 
		 Integer validatCode = phoneNumbers.get(phoneNumber);
		 Logger.getLogger(this.getClass()).info("手机注册的验证码:"+validatCode);
		 if(validatCode == null){
			 throw new PhoneRegisterException("验证码不正确,请重新输入.");
		 }
		 
		 if(String.valueOf(validatCode).equals(String.valueOf(code))){
			 phoneNumbers.remove(validatCode);
			 return true;
		 }else{
			 return false;
		 }
		 
	}

}
