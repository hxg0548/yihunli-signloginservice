package com.dianzishangwu.yihunli.signlogin.service;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.dianzishangwu.yihunli.domain.user.Address;
import com.dianzishangwu.yihunli.domain.user.Individual;
import com.dianzishangwu.yihunli.domain.user.Merchant;
import com.dianzishangwu.yihunli.domain.user.UserEntity;
import com.dianzishangwu.yihunli.service.UserEntityService;
import com.dianzishangwu.yihunli.signlogin.domain.IndividualSignUpRequest;
import com.dianzishangwu.yihunli.signlogin.domain.MerchantSignUpRequest;
import com.dianzishangwu.yihunli.signlogin.domain.PhoneRegisterException;
import com.dianzishangwu.yihunli.signlogin.domain.SignUpRequest;

public class AccountService 
{
     private  PhoneRegisterService phoneRegisterService;
     private  UserEntityService userEntityService;
     
	 public PhoneRegisterService getPhoneRegisterService() {
		return phoneRegisterService;
	 }
	 public void setPhoneRegisterService(PhoneRegisterService phoneRegister) {
		this.phoneRegisterService = phoneRegister;
	 }
	 public UserEntityService getUserEntityService() {
		return userEntityService;
	 }
	 public void setUserEntityService(UserEntityService userEntityRegister) {
		this.userEntityService = userEntityRegister;
	 }
     
	 public String sendSms(String phone) throws HttpException, IOException, PhoneRegisterException{
		 UserEntity userEntity = userEntityService.findByPhone(phone);
         if(userEntity != null) return "-1";
         String code = "";
		 try {
			code = phoneRegisterService.sendSms(phone);
		 } catch (DocumentException e) {
            Logger.getLogger(this.getClass()).error("[短信发送]发生异常:"+e.getMessage(),e);
		 }
         return code;
	 }
	 
	 public boolean validatePhone(String phoneNumber,int code) throws PhoneRegisterException{
		 if(userEntityService.findByPhone(phoneNumber) != null) return false;
         return phoneRegisterService.validatePhone(phoneNumber, code);
	 }
	 
     public Serializable saveIndividual(IndividualSignUpRequest signUp){
    	 
 		SignUpRequest userSignUp = new SignUpRequest();
 		userSignUp.setPhoneNumber(signUp.getPhoneNumber());
 		this.deleteUserEntity(userSignUp);
 		
    	 Individual userEntity = new Individual();
    	 userEntity.setEmail(signUp.getEmail());
    	 userEntity.setPassword(signUp.getPassword());
    	 userEntity.setPhoneNumber(signUp.getPhoneNumber());
    	 userEntity.setUserName(signUp.getName());
    	 userEntity.setImage(signUp.getImage());
    	 userEntity.setEntityType(Enum.valueOf(UserEntity.ENTITY.class, signUp.getType()));
    	 return userEntityService.save(userEntity);
     }
     
     public Serializable saveUserEntity(SignUpRequest signUpRequest) throws PhoneRegisterException{
    	 UserEntity userEntity = userEntityService.findByPhone(signUpRequest.getPhoneNumber());
    	 if(userEntity != null) throw new PhoneRegisterException("该手机号码已经注册.");
    	 userEntity = new UserEntity();
    	 userEntity.setPhoneNumber(signUpRequest.getPhoneNumber());
    	 return userEntityService.save(userEntity);
     }
     
     public UserEntity findUserEntityFromPhone(String phone){
    	 return userEntityService.findByPhone(phone);
     }
     
     public UserEntity findUserEntityFromUserName(String userName){
    	 return userEntityService.findByUserName(userName);
     }
     
     public void updateIndividual(IndividualSignUpRequest signUpRequest,Serializable entityId){
    	 Individual individual = (Individual) userEntityService.get(entityId);
    	 individual.setEmail(signUpRequest.getEmail());
    	 individual.setRealName(signUpRequest.getRealName());
    	 Address address = new Address();
    	 address.setProvince(signUpRequest.getProvince());
    	 address.setCity(signUpRequest.getCity());
    	 address.setDistrict(signUpRequest.getDistrict());
    	 address.setDetailAddress(signUpRequest.getDetailAddress());
    	 individual.setAddress(address);
    	 individual.setLogo(signUpRequest.getLogo());
    	 userEntityService.update(individual);
     }
     
     
     public void updateMerchant(MerchantSignUpRequest signUpRequest,Serializable entityId){
    	 Merchant merchant = (Merchant)userEntityService.get(entityId);
    	 merchant.setEmail(signUpRequest.getEmail());
    	 merchant.setCompanyIntroduce(signUpRequest.getCompanyIntroduce());
    	 merchant.setCompanyName(signUpRequest.getCompanyName());
    	 Address address = new Address();
    	 address.setProvince(signUpRequest.getProvince());
    	 address.setCity(signUpRequest.getCity());
    	 address.setDistrict(signUpRequest.getDistrict());
    	 address.setDetailAddress(signUpRequest.getDetailAddress());
    	 merchant.setAddress(address);
    	 merchant.setServiceTel(signUpRequest.getServiceTel());
    	 merchant.setBusinessScope(signUpRequest.getBusinessScope());
    	 merchant.setLogo(signUpRequest.getLogo());
    	 merchant.setAccountSubject(signUpRequest.getAccountSubject());
    	 userEntityService.update(merchant);
     }
     
     public void updateUserEntity(UserEntity userEntity){
    	 userEntityService.update(userEntity);
     }
     
     public Serializable saveMerchant(MerchantSignUpRequest signUp){
    	 
  		SignUpRequest userSignUp = new SignUpRequest();
  		userSignUp.setPhoneNumber(signUp.getPhoneNumber());
  		this.deleteUserEntity(userSignUp);
  		
    	 Merchant userEntity = new Merchant();
    	 userEntity.setEmail(signUp.getEmail());
    	 userEntity.setPassword(signUp.getPassword());
    	 userEntity.setPhoneNumber(signUp.getPhoneNumber());
    	 userEntity.setUserName(signUp.getName());
    	 userEntity.setBusinessScope(signUp.getBusinessScope());
    	 userEntity.setCompanyIntroduce(signUp.getCompanyIntroduce());
    	 userEntity.setCompanyName(signUp.getCompanyName());
    	 userEntity.setEntityType(Enum.valueOf(UserEntity.ENTITY.class, signUp.getType()));
    	 return userEntityService.save(userEntity);
     }
     
     public void deleteUserEntity(SignUpRequest signUp){
    	 UserEntity userEntity = userEntityService.findByPhone(signUp.getPhoneNumber());
    	 if(userEntity != null)  userEntityService.delete(userEntity);    	 
     }
     
     public UserEntity findUserEntity(Serializable id){
    	 return userEntityService.get(id);
     }

}
