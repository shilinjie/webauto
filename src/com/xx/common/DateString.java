package com.xx.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

public class DateString {


	DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
	Date date=new Date();
	
	
	/*
     * 实现：将当前年月日时分秒拼接成字符串
     * 返回值：拼接好的时间YYYYMMDDHHMMSS
     * 作者：slj
     * 时间：2017-09-05
     */
	public String  getDateString(){
		//return year+""+mouth+""+date+""+hour+""+minute+""+second;
		return df.format(date);

		 
	}
}
