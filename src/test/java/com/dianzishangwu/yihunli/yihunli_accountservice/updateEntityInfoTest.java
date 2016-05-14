package com.dianzishangwu.yihunli.yihunli_accountservice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dianzishangwu.yihunli.domain.user.UserEntity;
import com.dianzishangwu.yihunli.signlogin.service.AccountService;

public class updateEntityInfoTest {

	private AccountService service;
	@Before
	public void before(){
		service = (AccountService) new ClassPathXmlApplicationContext("yihunli-service.xml","yihunli-account.xml").getBean("accountService");
	}
	
	@Test
	public void updateEntityTest(){
		UserEntity userEntity = service.findUserEntityFromUserName("teluofusiji");
		assertEquals("8512691@qq.com",userEntity.getEmail());
	}
}
