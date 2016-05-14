package com.dianzishangwu.yihunli.yihunli_accountservice;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

import com.dianzishangwu.yihunli.signlogin.domain.IndividualSignUpRequest;
import com.dianzishangwu.yihunli.signlogin.domain.MerchantSignUpRequest;
import com.dianzishangwu.yihunli.signlogin.service.AccountService;


public class LoginUpRegisterTest
{
	ApplicationContext tx = null;
	
	@Before
	public void prepare(){
		tx = new ClassPathXmlApplicationContext("yihunli-account.xml");
	}

	//@Test
    public void testSaveUserEntityFromIndividualSignUpRequest()
    {
		IndividualSignUpRequest signUpRequest = new IndividualSignUpRequest();
		signUpRequest.setEmail("851269120@qq.com");
		signUpRequest.setImage("/userentity/indiviadual/hexiaogang.jpg");
		signUpRequest.setName("贺晓刚");
		signUpRequest.setPassword("123456");
		signUpRequest.setPhoneNumber("15989663989");
		
        AccountService servive = (AccountService) tx.getBean("accountService");
        Serializable serializable = servive.saveIndividual(signUpRequest);
        
        assertNotNull(serializable);
                
    }
	
	//@Test
    public void testSaveUserEntityFromMerchantSignUpRequest()
    {
		MerchantSignUpRequest signUpRequest = new MerchantSignUpRequest();
		signUpRequest.setEmail("851269120@qq.com");
		signUpRequest.setTradeMark("/userentity/indiviadual/hexiaogang.jpg");
		signUpRequest.setName("zantong");
		signUpRequest.setPassword("123456");
		signUpRequest.setPhoneNumber("15989663989");
		signUpRequest.setCompanyIntroduce("百年品牌");
		signUpRequest.setCompanyName("百年寒舍");
		signUpRequest.setServiceTel("4008208820");
        AccountService servive = new AccountService();
        Serializable serializable = servive.saveMerchant(signUpRequest);
        
        assertNotNull(serializable);
                
    }
	
}
