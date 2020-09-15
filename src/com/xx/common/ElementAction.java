package com.xx.common;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementAction {
	
	WebDriver driver;
	String[][] Locators;
	FindWebElement findwebelement;
	public ElementAction(WebDriver driver,String[][] Locators) throws Exception{
		this.driver= driver;
		this.Locators = Locators;
		findwebelement = new FindWebElement(driver,Locators);
	}
	 /*****************无步骤，单独处理操作************************************************************************* 
    /*
     * 实现：给元素赋值，一般指输入框，上传按钮等
     * 参数：locatorInfo：定位信息
     * 		value：赋值内容
     * 作者：slj
     * 时间：2017-06-21
     */
    
	public void sendValue(LocatorInfo locatorInfo, String value) throws Exception {
		WebElement e = findwebelement.findElement(driver, locatorInfo);
		e.sendKeys(value);
	}
    
    /*
     * 实现：点击元素
     * 参数：locatorInfo：定位信息
     * 作者：slj
     * 时间：2017-06-21
     */
    
	public void click(LocatorInfo locatorInfo) throws Exception {
 		WebElement e = findwebelement.findElement(driver, locatorInfo);
 		e.click();
 	}
    
    /*
     * 实现：获取元素text的值
     * 参数：locatorInfo：定位信息
     * 返回值：String，将元素text的值返回
     * 作者：slj
     * 时间：2017-06-21
     */
    
	public String getTextValue(LocatorInfo locatorInfo){
    	WebElement e=findwebelement.findElement(driver,locatorInfo);
    	return e.getText();
    } 
    
    /*
     * 实现：清空元素值，一般指输入框
     * 参数：locatorInfo：定位信息
     * 作者：slj
     * 时间：2017-06-21
     */
    
	public void cleanText(LocatorInfo locatorInfo){
    	WebElement e=findwebelement.findElement(driver,locatorInfo);
    	e.clear();
    }
   
	/********************带步骤的元素操作处理*******************
	 * 
	 * @param locator
	 * @param type  
	 * @param value
	 * @throws Exception
	 */
	public void excstep(LocatorInfo locator,String type,String value) throws Exception{
		switch(type){
		case "input":
    		sendValue(locator,value);
    		break;
		case "click":
    		click(locator);
    		break;
		case "gettext":
    		getTextValue(locator);
    		break;
		}
		
	}
	

	
	public void setps(String[][] cases,LocatorInfo[] locMap) throws Exception{
		StrToLoc strtoloc=new StrToLoc();
		LocatorInfo[] loc_real  = new LocatorInfo[cases.length];
		for(int i=0;i<cases.length ;i++){
			loc_real=strtoloc.strtoloc(locMap, cases);
		}
		
		for(int i=0;i<cases.length ;i++){
			excstep(loc_real[i],cases[i][1],cases[i][3]);
		}
	}
	
	
	
    
    /*
     * 实现： 按照传入的属性，返回元素的属性值，例如value是maxlength，就返回locatorInfo的最大长度
     * 参数：locatorInfo：元素定位信息
     * 		value：元素属性
     * 返回值：String，属性具体值
     * 作者：slj
     * 时间：2017-06-21
     */
	public String getElementValue(LocatorInfo locatorInfo,String value){
    	WebElement e = findwebelement.findElement(driver,locatorInfo);
    	return  e.getAttribute(value);
    }
    
//    /*
//     * 返回div的背景色
//     * locatorInfo：元素定位信息
//     * 返回值：String，类似：rgba(250, 116, 116, 0.1)
//     * slj
//     * 2017-06-21
//     */
//    public String getDivBgc(Locator locatorInfo){
//    	WebElement e=findElement(driver,locatorInfo);
//		return e.getCssValue("background-color");	
//    }
    
    /*
     * 实现：返回传入的css属性的值
     * 参数：locatorInfo：元素定位信息
     * 返回值：String，类似：rgba(250, 116, 116, 0.1)，样式的值
     * 作者：slj
     * 时间：2017-06-21
     */
	protected String getCssValue(LocatorInfo locatorInfo,String str){
    	WebElement e = findwebelement.findElement(driver,locatorInfo);
		return e.getCssValue(str);	
    }
    
    
    /*
     * 实现：判断元素是否可见
     * 参数：locatorInfo：元素定位信息
     * 返回值：布尔类型，true
     * 作者：slj
     * 时间：2017-06-21
     */
	public boolean isExist(LocatorInfo locatorInfo){
    	WebElement e = findwebelement.findElement(driver,locatorInfo);
    	return e.isDisplayed();
    }
    
    
    /*
     * 实现：判断元素是否存在与html中
     * 参数：locatorInfo：元素定位信息
     * 返回值：布尔类型，true存在，false不存在
     * 作者：slj
     * 时间：2017-06-21
     */
	public boolean isExistInHtml(LocatorInfo locatorInfo) throws IOException{
 	   locatorInfo = findwebelement.getLocator(locatorInfo.getLocatorInfo());
 	   WebElement e;
     	switch(locatorInfo.getByType()){
     		case "xpath":
     			try{	

     				e=driver.findElement(By.xpath(locatorInfo.getLocatorInfo()));
     				return true; 
     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     		case "id":
     			try{

     				e=driver.findElement(By.id(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }	
     		
     		case "name":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.name(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }	
     			
     		case "cssSelector":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.cssSelector(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		case "className":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.className(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		case "tagName":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.tagName(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		case "linkText":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.linkText(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		case "partialLinkText":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.partialLinkText(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		default:
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.xpath(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     	}  	 
           
     }
    
    
    /*
     * 实现：鼠标悬停
     * 参数：locatorInfo：元素定位信息
     * 作者：slj
     * 时间：2017-06-21
     */
	protected void mouseOver(LocatorInfo locatorInfo) throws IOException {
 		WebElement e = findwebelement.findElement(driver, locatorInfo);
 		Actions actions = new Actions(driver);
 		actions.moveToElement(e).perform();
 	}
     
     
    /*
     * 实现：页面滚动条滚动至locatorInfo元素出现
     * 参数：locatorInfo：元素定位信息
     * 作者：slj
     * 时间：2017-06-21
     */
	protected  void scrollTo(WebDriver driver, LocatorInfo locatorInfo) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement e = findwebelement.findElement(driver,locatorInfo);
        js.executeScript("var q=document.documentElement.scrollTop=0", e);
    }
    

    /*
     * 实现：侧滑层中的垂直滚动条，滚动至locatorInfo元素出现
     * 参数：locatorInfo：元素定位信息
     * 作者：slj
     * 时间：2017-06-21
     */
	protected  void divScrollTo(WebDriver driver, LocatorInfo locatorInfo) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement e = findwebelement.findElement(driver,locatorInfo);
        js.executeScript("arguments[0].scrollIntoView(true);", e);
    }
    
    
    /*
	public WebDriver getDriver() {
		return driver;
	}
    
	public void waitForPageLoad() {
		 getDriver().manage().timeouts()
				.pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	*/

}
