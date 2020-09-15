package com.xx.action;


import org.openqa.selenium.WebDriver;

import com.xx.page.LoginPage;

public class CommonLogin {

	 private static WebDriver driver;
	 static String sheetname;
	 static LoginPage loginpage;
	 static String[][] locator;
	 
	 public static  void login(String url,String username,String password)throws Exception{
 
		 loginpage=new LoginPage(getDriver(),locator);
 
		// loginpage.openurl(url);
		 Thread.sleep(2000);
		 loginpage.clear_namepwd();
		 loginpage.setLoginName(username);
		 loginpage.setLoginPwd(password);
		 loginpage.clickCommitBtn();
		 Thread.sleep(2000);
		 
	 }
	/* public static void logout() throws Exception{
		 loginpage.logout();
		 
	 }*/
	 public static WebDriver getDriver(){
		 return driver;
	 }
	 public static void setDriver(WebDriver driver1){
		 CommonLogin.driver=driver1;
	 }
	 public static void setLocators(String[][] locators){
		 CommonLogin.locator=locators;
	 }
	 
}
