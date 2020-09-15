package com.xx.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadDB {

	
		 String driverName;    //描述数据库驱动类型
		 
		 String dbURL;    //描述数据库连接地址
		 
		 String userName;    //描述数据库连接用户名
		
		 String userPwd;     //描述数据库连接密码
		 
		 Connection dbConn;    //用于操作数据库
		 
	
		 
		 /*
			 * slj
			 * 连接数据库
			 * 2017-06-21
			  
			 public void connectDB(){
				 
				 driverName = BaseData.DatabaseDriver;
				 
				// driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";    //设置数据库driver，采用数据库是SQL server	
				 
				 //dbURL="jdbc:sqlserver://192.168.50.166;DatabaseName=selenium";    //设置数据库连接地址
				 dbURL = BaseData.dbURL;
				 
				  
				 
				 // userName="sa";  
				 // userPwd="root";	
				userName = BaseData.userName;
				userPwd = BaseData.userPwd;
				 
				 try
				 	{
					 
					 Class.forName(driverName);    //载入驱动
					 
					 dbConn=DriverManager.getConnection(dbURL,userName,userPwd);    //连接数据库
					 
					 System.out.println("mysql连接数据库成功");

				 	}
				 catch(Exception e)
				 	{
				 		e.printStackTrace();
				 		System.out.print("mysql连接失败，请检查数据库服务是否正常运行");
				 	}   
			 }*/
		 
		 /*
		 * slj
		 * 连接数据库
		 * 2017-06-21
		 */
		 public void connectDB(){
			 
			 driverName = BaseData.DatabaseDriver;
			 
			// driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";    //设置数据库driver，采用数据库是SQL server	
			 
			 //dbURL="jdbc:sqlserver://192.168.50.166;DatabaseName=selenium";    //设置数据库连接地址
			 dbURL = BaseData.dbURL;
			 
			  
			 
			 // userName="sa";  
			 // userPwd="root";	
			userName = BaseData.userName;
			userPwd = BaseData.userPwd;
			 
			 try
			 	{
				 
				 Class.forName(driverName);    //载入驱动
				 
				 dbConn=DriverManager.getConnection(dbURL,userName,userPwd);    //连接数据库
				 
				 System.out.println("连接数据库成功");

			 	}
			 catch(Exception e)
			 	{
			 		e.printStackTrace();
			 		System.out.print("连接失败，请检查数据库服务是否正常运行");
			 	}   
		 }

		 /*
		  * slj
		  * 关闭数据库连接
		  * 2017-06-21
		  */
		 
		 public void closeConn()  
		    {  
		        try {  
		        	dbConn.close();  
		        	dbConn = null;  
		        } catch (Exception ex) {  
		            System.out.println(ex.getMessage());  
		            dbConn=null;   
		        }
		    }
		 
		 /*
		  * slj
		  * 将数据库中相应项目下的所有页面元素定位信息读取到二维数组中返回
		  * 返回值：elements是二维数组，保存元素使用
		  * 2017-06-21
		  */
		 
		 public String[][] readLocators() throws SQLException{
				
		     this.connectDB();
		     String projectname = BaseData.projectname;
			
		     /*将结果存在一个可以自由移动游标的ResultSet中，再处理，st用于执行数据库*/
		     
			Statement st =dbConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			/*执行查询语句，结果放在resset中*/
			//sqlserver版本
			//ResultSet resset = st.executeQuery("select  cast(l.id as nvarchar)id,l.name,value,[type],[desc],cast(status as nvarchar) status,p.name as project,l.createtime from tb_locator as l  left join  tb_project as p on p.id=l.projectid where l.status=0 order by l.createtime desc");
			String querystr="select  cast(l.id as char)id,lname,lvalue,ltype,ldesc,cast(lstatus as char) lstatus,pname as project,l.createtime from locators as l  left join  projects as p on p.id=l.projectid where lstatus=0 and projectid=(select id from projects where pname=\'"+projectname+"\')order by l.createtime desc";
			ResultSet resset = st.executeQuery(querystr);
			int rowCount = 0;    //计算行数 
			while(resset.next()){
			    rowCount++;
			   }
			
			
			int i=0;    //用于遍历数组行，便于赋值
			/*将页面元素存储在elements中，包括：编号，名称，值，类型，描述四个值*/
			
			String  elements[][]=new String[rowCount][5];
			
	 		resset.beforeFirst();    //将指针放在第一条数据之前
	 		
	 		/*遍历resset结果集，将对应列中的值，存在元素数组中*/
	 		
	 		while(resset.next()) {
	            elements[i][0]=resset.getString("id");
	 			elements[i][1]=resset.getString("lname");
	 			elements[i][2]=resset.getString("lvalue");
	 			elements[i][3]=resset.getString("ltype");
	 			elements[i][4]=resset.getString("ldesc");
	 			elements[i][4]=resset.getString("project");
	 			i++;
	 		}
	 		this.closeConn();
	 		return elements;
	 	}
}
