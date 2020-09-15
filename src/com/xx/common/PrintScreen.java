package com.xx.common;

import java.io.File;
import java.io.IOException;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class PrintScreen {

	public void saveScreenFile(WebDriver driver,String filepath) throws IOException{
		File scr=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File file=new File(filepath);
		file.delete();
		//FileUtils.copyFile(scr,file);
	}
}
