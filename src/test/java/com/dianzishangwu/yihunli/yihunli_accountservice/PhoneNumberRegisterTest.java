package com.dianzishangwu.yihunli.yihunli_accountservice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dianzishangwu.yihunli.signlogin.service.PhoneRegisterService;


public class PhoneNumberRegisterTest 
{
   
	private PhoneRegisterService phoneRegister;
	@Before
	public void prapre(){
		phoneRegister = new PhoneRegisterService();
	}
	
	//@Test
	public void testSendSms() throws Exception{
		assertEquals("2",phoneRegister.sendSms("15989663989"));		
	}
	
}
