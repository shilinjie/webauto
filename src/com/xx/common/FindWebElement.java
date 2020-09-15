package com.xx.common;

import java.io.IOException;
 








import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;











 


public class FindWebElement {

	protected WebDriver driver;
    protected String[][] locatorMap;    //用于保存读取回来的元素
    ReadDB dbLocators=new ReadDB();
    
  /*
   * 为本类中driver和locatorMap赋值
   */
	public  FindWebElement(WebDriver driver,String[][] Locators) throws Exception{
    	
		this.driver=driver;
    	this.locatorMap=Locators;
    }
	
	/*
	 * 实现：按照传递过来的名称，去元素数组中查找相应行数，用数组中的第3项value和第四项type初始化一个定位实例，此时第一个参数的意义变成了value，是by后面的值
	 * 参数：locatorName：定位的名字
	 * 作者：slj
	 * 时间：2016-06-21
	 */
	protected LocatorInfo getLocator(String locatorName){
		LocatorInfo locatorinfo=null;
		for(int i=0;i<locatorMap.length;i++){
			//if(locatorMap[i][1].endsWith(locatorName)){
			if(locatorMap[i][1].equals(locatorName)){
				locatorinfo=new LocatorInfo(locatorMap[i][2],locatorMap[i][3]);
				break;
			}				
		}
		return locatorinfo;
	}
	
	/*
	 * 实现：根据传过来的定位信息中 bytype的值，在页面上查找，并返回
	 * 参数：driver：浏览器
	 * 	   locatorInfo：定位信息（包含定位标识和bytype）
	 * 返回值：return e：WebElement 类型，根据定位信息，确定查找方式，赋值给e返回
	 * 作者：slj
	 * 时间：2017-06-21
	 */
	protected WebElement getElement(WebDriver driver,LocatorInfo locatorInfo){
		WebElement e;
		/*按照定位信息中的定位标识，找到具体的value，再赋值给locatorInfo，此时的locatorInfo包括实际的value和bytype*/
		locatorInfo=getLocator(locatorInfo.getLocatorInfo());
		
		switch(locatorInfo.getByType()){
		case "xpath":
    		e=driver.findElement(By.xpath(locatorInfo.getLocatorInfo()));
    		break;
    	
		case "id":
    		e=driver.findElement(By.id(locatorInfo.getLocatorInfo()));
    		break;
    		
		case "name":
    		e=driver.findElement(By.name(locatorInfo.getLocatorInfo()));
    		break;
    		
		case "cssSelector":
    		e=driver.findElement(By.cssSelector(locatorInfo.getLocatorInfo()));
    		break;	
    		
		case "className":
    		e=driver.findElement(By.className(locatorInfo.getLocatorInfo()));
    		break;	
    		
		case "tagName":
    		e=driver.findElement(By.tagName(locatorInfo.getLocatorInfo()));
    		break;	
    		
		case "linkText":
    		e=driver.findElement(By.linkText(locatorInfo.getLocatorInfo()));
    		break;	
    		
		case "partialLinkText":
    		e=driver.findElement(By.partialLinkText(locatorInfo.getLocatorInfo()));
    		break;	
    	
		default:
            e = driver.findElement(By.id(locatorInfo.getLocatorInfo()));
		}
		return e;
	}
	
	/*
	 * 实现：按照传入的定位信息（locatorInfo），去浏览器中查找元素，10秒之内找到，赋值给element返回，没有就返回空
	 * 参数：driver：浏览器
	 * 		locatorInfo：定位信息
	 * 返回值：element，是浏览器中找到的元素，或没找到的空元素
	 * 作者： slj
	 * 时间：2017-06-21
	 */
	
	protected WebElement findElement(WebDriver driver, final LocatorInfo locatorInfo) {
//        WebElement element = (new WebDriverWait(driver, 10))
//                .until(new ExpectedCondition<WebElement>() {
//
//                    public WebElement apply(WebDriver driver) {
//                        return getElement(driver, locatorInfo);
//                    }
//		              });
//        return element=null;
    	try{
    		return getElement(driver,locatorInfo);	
    	}
    	 catch(Exception e){
    		 e.printStackTrace();
    		 System.out.println("没找到元素");
    		 return  null;
    	 }
    	
    }
	
 
}
