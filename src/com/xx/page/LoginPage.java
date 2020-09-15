package com.xx.page;

import java.io.IOException;
import java.sql.SQLException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import org.openqa.selenium.WebDriver;

import com.xx.action.PublicFunction;
import com.xx.common.ElementAction;
import com.xx.common.LocatorInfo;

 



public class LoginPage {
	
	WebDriver driver;
	String[][] Locators;
	String url;
	ElementAction elementaction;
	public LoginPage(WebDriver driver, String[][] Locators) throws Exception {
		elementaction = new ElementAction(driver,Locators);
		driver.manage().window().maximize();

	}
	
	//LocatorInfo的第一个参数，即：login_username,需要与元素数据中描述登录名框的lname一致
	LocatorInfo login_username=new LocatorInfo("login_username","");
	LocatorInfo login_pwd=new LocatorInfo("login_pwd","");
	LocatorInfo login_login=new LocatorInfo("login_submit","");
	 

	
	public void openurl( String url) throws BiffException, WriteException, SQLException, IOException{
		PublicFunction publicfunction = new PublicFunction();
		publicfunction.openBrowser( url);
		
		driver.manage().window().maximize();
		
	}
 
	public void setLoginName(String value) throws Exception{
		elementaction.sendValue(login_username,value);
	}

	public void setLoginPwd(String value) throws Exception{
		elementaction.sendValue(login_pwd,value);
	}
	//点击登录按钮
	public void clickCommitBtn() throws Exception{
		elementaction.click(login_login);
	}

	
	/*
	 * 实现：清空登录框和密码框
	 * 作者：slj
	 * 时间2017-09-28
	 */
	public void clear_namepwd(){
		elementaction.cleanText(login_username);
		elementaction.cleanText(login_pwd);
	}
	
	
}
