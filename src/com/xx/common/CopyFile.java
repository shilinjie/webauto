package com.xx.common;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class CopyFile {


    /*
     * 实现：将文件拷贝至目标文件夹，传入路径+文件名
     * 参数：sourcestr：源文件地址
     * 		deststr：目标文件地址
     * 作者：slj
     * 时间：2017-08-15
     */
	
	public void copyFile(String sourcestr,String deststr) throws IOException{
		File source=new File(sourcestr);
		File dest=new File(deststr);
		
		FileChannel inputchannel=null;
		FileChannel outputchannel=null;
		try{
			inputchannel=new FileInputStream(source).getChannel();
			outputchannel=new FileOutputStream(dest).getChannel();
			outputchannel.transferFrom(inputchannel, 0, inputchannel.size());
 		}
		finally{
			inputchannel.close();
			outputchannel.close();
		}
	}
	


}
