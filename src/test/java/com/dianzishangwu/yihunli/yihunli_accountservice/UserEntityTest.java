package com.dianzishangwu.yihunli.yihunli_accountservice;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

import com.dianzishangwu.yihunli.domain.user.UserEntity;
import com.dianzishangwu.yihunli.service.UserEntityService;

public class UserEntityTest 
{

	private Session session;
	private SessionFactory sessionFactory;
	
	ApplicationContext ctx = null;
    @Before
    public void prapre()
    {
    	/*Configuration configuration = new Configuration().configure();
    	ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	
        session = sessionFactory.openSession();
*/    	
    	ctx = new ClassPathXmlApplicationContext("yihunli-account.xml");

    }
  
    /*@Test
    public void testUserEntity()
    {
    	Transaction tx = session.beginTransaction();
    	
    	Object identifier = null;
    	
        UserEntity userEntity = new Individual();
        userEntity.setEmail("851269120@qq.com");
        userEntity.setEntityType(UserEntity.ENTITY.INDIVIDUAL);
        userEntity.setPassword("123456");
        userEntity.setPhoneName("15989663989");
        userEntity.setUserName("teluofusiji");
        
        identifier = session.save(userEntity);
        
        assertEquals(2,identifier);
        
        Merchant merchant = new Merchant();
        merchant.setEmail("727272772@qq.com");
        merchant.setCompanyName("狗屎公司");
        merchant.setTradeMark("/1/2/3");
        
        identifier = session.save(merchant);
        
        assertEquals(3,identifier);
        
        tx.commit();
        
        session.close();
    }*/
    
    @Test
    public void testUserEntityOfUserName() {
        UserEntityService service = (UserEntityService) ctx.getBean("userEntityService");
        UserEntity userEntity = service.findByUserName("teluofusiji");  
        assertEquals("8512691@qq.com",userEntity.getEmail());
    }

}
