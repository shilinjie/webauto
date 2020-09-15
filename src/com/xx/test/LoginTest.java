package com.xx.test;
 
//暂时不用，待改
import java.io.IOException;
import java.sql.SQLException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.net.Urls;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.xx.action.PublicFunction;
import com.xx.action.UrlInfo;
import com.xx.page.LoginPage;

public class LoginTest {
	
	

	String[][] caseMap;
	String[][] locatorMap;
	WebDriver driver;
	String url;
	String caseFilePath;
	String caseSheetName;
	String mainfile_txt;
	String mainfile_sql;
	String mainfile_docx;
	String datestr;
	PublicFunction publicfunction;
	UrlInfo urls;
		
	/*
	 * 实现：初始化函数
	 * 		1. 将excel中文件读取到数组caseMap中待用
	 * 		2. 将数据库中元素读取到数组locatorMap中待用
	 * 		3. 获取本地sql，txt文件的绝对路径，作为上传的参数
	 * 		4. 打开浏览器，并登录
	 * 作者：slj
	 * 时间：2017-11-21
	 */
		@BeforeClass
		public void init() throws Exception{
		  publicfunction=new PublicFunction();
		  
		    urls=new UrlInfo();
			url=urls.get_url();
			
			//通过关键字“登录”，获取excle中“登录”sheet页中的用例，放在数组中
			caseSheetName="登录";
			caseMap=publicfunction.getCaseMap(caseSheetName);
			
			//获取元素数组
			locatorMap=publicfunction.getLocatorMap();
			
			//获取sql文件的绝对路径			
			mainfile_sql=publicfunction.getSqlFilePath();
			
			//获取txt文件的绝对路径			
			mainfile_txt=publicfunction.getTxtFilePath();
			
			//获取docx文件的绝对路径	
			mainfile_docx=publicfunction.getDocxFilePath();
			
			//设置时间戳
			datestr=publicfunction.getDate();
			
			driver = publicfunction.openBrowser(url);			

		}
		
		@AfterClass
		public void closeFirefox() throws BiffException, WriteException, IOException{
			try {
				publicfunction.end(driver, caseSheetName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	
	/*
	 * 编号：AUTO-1
	 * 步骤：测试正常登录
	 * 作者：slj
	 * 时间：2017-12-07
	 */
	@Test(priority = 1,enabled = true)
	public void login() throws Exception{
		Thread.sleep(10000);
		int flag=0;
		for(int i=0;i<caseMap.length;i++,flag=i){
			
			if("正常登录".equals(caseMap[i][caseMap[0].length-3])){
				if("true".equals(caseMap[i][caseMap[0].length-5])){
					LoginPage loginPage=new LoginPage(driver,locatorMap); 
					loginPage.setLoginName(caseMap[i][1]);
					loginPage.setLoginPwd(caseMap[i][2]);
					loginPage.clickCommitBtn();
					Thread.sleep(1000);
					 
					if(caseMap[i][caseMap[0].length-4].equals("")){
						caseMap[i][caseMap[0].length-2]="通过"; 
						caseMap[i][caseMap[0].length-1]=datestr;
						
					}
					else{
						//Assert.assertEquals("未通过，实际值："+loginPage.getErrorMsg()+"预期值：",caseMap[i][caseMap[0].length-4]);
						caseMap[i][caseMap[0].length-2]="未通过"; 
						caseMap[i][caseMap[0].length-1]=datestr;
					}
					break;
				}
				else{
					caseMap[i][caseMap[0].length-2]="不在测试范围"; 
					return;
				}
			}
				
		}
		if(flag==caseMap.length){
			System.out.print("没有找到正常登录用例");
				}
			
	}
	
	

}
