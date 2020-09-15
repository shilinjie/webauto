package com.xx.page;
 
import org.openqa.selenium.WebDriver;

import com.xx.common.ElementAction;
import com.xx.common.LocatorInfo;



public class CommonPage {
	WebDriver driver;
	String[][] Locators;
	ElementAction elementaction;
	
	public CommonPage(WebDriver driver, String[][] Locators) throws Exception {
		this.driver = driver;
		this.Locators = Locators;
		elementaction = new ElementAction(driver,Locators);
	}
	
	//通用_提醒框_文字
	LocatorInfo common_message_text=new LocatorInfo("common_message_text","");
	
	//通用_提醒框_确定按钮
	LocatorInfo common_message_ok=new LocatorInfo("common_message_ok","");
	
	//通用_鼠标悬浮时的tip
		LocatorInfo common_tip=new LocatorInfo("common_tip","");
	
	//通用右侧主div的标题
		LocatorInfo common_divname=new LocatorInfo("common_divname","");
		
	//通用_右下角上滑提示_文字
		LocatorInfo common_message1_text=new LocatorInfo("common_message1_text","");
		
		

		
	//返回提醒框的文字
	public String get_messgeText(){
		return elementaction.getTextValue(common_message_text);
	}
	 
	//返回tip提醒层的文字
	public String get_TipText(){
			return elementaction.getTextValue(common_tip);
		}

	
	
	public void click_ok() throws Exception{
		elementaction.click(common_message_ok);
	}
	
	/*
	 * 获取右侧主div的标题，如收件箱、草稿箱等
	 * 史林洁
	 * 2017-08-16
	 */
	public String get_maindivname() throws Exception{
		return elementaction.getTextValue(common_divname);
	}
	
	
	/*
	 * 获取右下方上滑提示的文字
	 * 史林洁
	 * 2017-09-07
	 */
	public String get_messge1Text() throws Exception{
		return elementaction.getTextValue(common_message1_text);
	}
}
