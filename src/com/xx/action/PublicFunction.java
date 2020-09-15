package com.xx.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import com.xx.common.BaseData;
import com.xx.common.DateString;
import com.xx.common.Excel;
import com.xx.common.ReadDB;

import jxl.JXLException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class PublicFunction {

	String mainfile_txt=BaseData.mainfile_txt;
	String mainfile_sql=BaseData.mainfile_sql;
	String mainfile_docx=BaseData.mainfile_docx;
	String casefile=BaseData.casefile;
	Excel excel=new Excel();
	File casefilepath;
	String [][] casemap;
	WebDriver driver;
	
	
	/*
	 * 实现：获取docx文件的绝对路径
	 * 返回值：String型绝对路径
	 * 作者：slj
	 * 时间：2017-11-22
	 */
	public String getDocxFilePath() throws IOException, JXLException{
		
		File file=new File(mainfile_docx);	
		return file.getAbsolutePath();
	}
	
	
	
	/*
	 * 实现：获取sql文件的绝对路径
	 * 返回值：String型绝对路径
	 * 作者：slj
	 * 时间：2017-11-21
	 */
	public String getSqlFilePath() throws IOException, JXLException{
		
		File file=new File(mainfile_sql);	
		return file.getAbsolutePath();
	}
	
	
	/*
	 * 实现：获取txt文件的绝对路径
	 * 返回值：String型绝对路径
	 * 作者：slj
	 * 时间：2017-11-21
	 */
	public String getTxtFilePath() throws IOException, JXLException{
		
		File file=new File(mainfile_txt);	
		return file.getAbsolutePath();
	}
	
	/*
	 * 实现：时间戳
	 * 返回值：Sting型的时间串
	 * 作者：slj
	 * 时间：2017-11-21
	 */
	public String getDate() throws IOException, JXLException{
		
		DateString datestring=new DateString();
		return datestring.getDateString();
	}
	
	
	

	/*
	 * 实现：读取excel用例表，将用例读取到数组中
	 * 返回值：String型用例数组
	 * 作者：slj
	 * 时间：2017-11-21
	 */
	public String[][] getCaseMap(String sheetname) throws IOException, JXLException{
		
		 
		 casefilepath=new File(casefile);
		return casemap=excel.readExcel(casefilepath.getAbsolutePath(),sheetname);
	}
	
	/*
	 * 实现：读取数据库中的元素，放数组中
	 * 返回值：String型元素数组
	 * 作者：slj
	 * 时间：2017-11-21
	 */
	public String[][] getLocatorMap() throws SQLException{
		
		ReadDB readLocator=new ReadDB();
		return readLocator.readLocators();
	}	
	
	/*
	 * 实现：根据用例名称，计算步骤个数
	 * 参数：cases实际用例集合，casename用例名
	 * 返回值：步骤个数
	 * 
	 */
	public int getstepcount(String[][] cases,String casename){
		int count=0;
		while(count<cases.length-1&cases[count][cases[0].length-1].equals(casename)){
			count++;
			}
		return count+1;
	}
	
	
	/*
	 * 实现：根据用例名称，将实际测试过程中的用例集合返回
	 * 参数：cases实际用例集合，casename用例名
	 * 返回值：实际使用用例数组
	 * 
	 */
	public String[][] getstepcases(String[][] cases,String casename){
		int row=0,i=0;
		//实际步数
		int stepcount=this.getstepcount(cases,casename);
		String  cases_real[][]=new String[stepcount][cases[0].length];
		 for(int j=0;j<cases.length;j++,row++){
			 for(int col=0;col<cases[0].length;col++){
				 if(cases[i][cases[0].length-1].equals(casename)&row<stepcount)
					 cases_real[row][col]=cases[j][col];
			 }
		  }
		 return cases_real;
		
	}
	 
	
	/*
	 * 实现：打开高版本浏览器
	 * 作者：slj
	 * 时间：2019-8-12
	 */
	public  WebDriver openBrowser(String  url) throws SQLException, BiffException, WriteException, IOException{
		System.setProperty("webdriver.gecko.driver","file/geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", BaseData.firefoxstr);
		ProfilesIni pi=new ProfilesIni();
		FirefoxProfile profile = pi.getProfile("Selenium");
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
		driver = new FirefoxDriver(options); 
		driver.get(url);
		return driver;
		 
	}	
	
	/*
	 * 实现：关闭浏览器，回收浏览器资源，将结果写进excel
	 * 作者：slj
	 * 时间：2017-11-21
	 */
	public void end(WebDriver driver,String casesheetname) throws SQLException, BiffException, WriteException, IOException{
		
		driver.close();
		driver.quit();
		excel.UpdateCell(casefilepath.getAbsolutePath(), casesheetname, casemap);
		 
	}	
	

	
	
	
}
