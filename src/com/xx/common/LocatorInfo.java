package com.xx.common;

public class LocatorInfo {
	
	private String locatorInfo;	//定位的名字或实际value
//		private int waitSec;
		//by类型 id，xpath等等
	private String byType;    //通过何种方式查找
		
    public LocatorInfo(String name,String byType){
    	this.locatorInfo=name;
    	this.byType=byType;
    }
	
 
    /*
     * 实现：获取具体定位信息，如//div[0]/等
     * 返回值：String类型，如//div[0]/
     * 作者：slj
     * 时间：2017-06-21
     */
    public String getLocatorInfo(){
		return locatorInfo;
	}
    
    /*
     * 实现：获取具体定位方法，如xpath，id等
     * 返回值：String类型，如xpath，id等
     * 作者：slj
     * 时间：2017-06-21
     */
    public String getByType(){
    	return byType;
    }

}
