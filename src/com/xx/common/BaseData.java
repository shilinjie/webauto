package com.xx.common;

public class BaseData {
	
	//**************************数据库配置*******************//
	
	/*
	//设置数据库driver，采用数据库是SQL server	
	public static final String DatabaseDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//设置数据库连接地址
	public static final String dbURL="jdbc:sqlserver://192.168.50.166;DatabaseName=selenium";  
	
	//设置连接数据库用户名和密码
	public static final String userName = "sa";  
	public static final String userPwd = "root";
*/
	
	//设置数据库driver，采用数据库是MySQL	
		public static final String DatabaseDriver = "com.mysql.jdbc.Driver";
		
		//设置数据库连接地址
		public static final String dbURL="jdbc:mysql://localhost:3308/elements?useSSL=false";  
		
		//设置连接数据库用户名和密码
		public static final String userName = "root";  
		public static final String userPwd = "root";
	
		
//*************************被测试项目，数据库中project的name值*******************//
		public static final String projectname="项目1";

//*************************火狐存放地址*******************//
		public static final String firefoxstr="C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	
	
//*************************备份地址*******************//

	public static final String copypath = "E:\\Auto_eg\\file\\temp\\cases_";
	
	
	
//*************************用例及附件保存地址：相对地址*******************//
	
	public static final String mainfile_txt="file/sendmail_mainfile.txt";
	public static final String mainfile_sql="file/sendmail_mainfile.sql";
	public static final String mainfile_docx="file/sendmail_mainfile.docx";
	
	//用例地址
	public static final String casefile="file/cases.xls";
}
