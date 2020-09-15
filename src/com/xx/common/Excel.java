package com.xx.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import jxl.Cell;
import jxl.CellType;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Excel {

    /*
     * 实现：读取指定路径下，指定sheet中的excel表格中每一行，将放到数组中，返回
     * 参数：filePath：文件路径
     * 参数：sheetname:excel中的sheet名字
     * 返回值：cases用例数组
     * 作者：slj
     * 时间：2017-06-21
     */

    public  String[][] readExcel(String filePath,String sheetname) throws IOException, JXLException{
		
    	/*输入流，声明读取变量is 用于读取excel表格*/	
        InputStream is=null;    
        
        /*构建Workbook对象*/
        Workbook workbook=null;   
        
        /*传入filepath中描述的路径，读取到is中*/
        is=new FileInputStream(filePath);    
        
        /*将is中读取到的excel表格赋值给workbook*/
        workbook=Workbook.getWorkbook(is);    
        
       /*声明变量sheet，将workbook中的sheet名为sheetname的表格读取到sheet中*/
        Sheet sheet=workbook.getSheet(sheetname);    
        
        /*声明一个数组，用于存放sheet中的值，长度根据sheet中的实际长度定义*/
        String cases[][] = new String[sheet.getRows()][sheet.getColumns()];     
        
        /*通过两层循环，将sheet中的数据读取到数组中*/
		for(int i=0;i<sheet.getRows();i++){
			for(int j=0;j<sheet.getColumns();j++){
				
				/*声明一个单元格变量，通过getCell(j,i)得到单元格中的值，读取每列每行，getCell(j,i)前面的j是列，i是行*/
				Cell cellA1=sheet.getCell(j,i);
				
				/*如果当前单元格的格式是label(文本类型)类型的，就读取到数组中，注意：excel中所有内容需要保存成文本类型，包括数字*/
				if (cellA1.getType().equals(CellType.LABEL)){
					
					/*获取string类型单元格的数据*/
					cases[i][j]=cellA1.getContents();
				}
			}
		}
		
		workbook.close();    //将excel表关闭
		is.close();    //将输入流关闭
		return cases;
   }

    
    /*
     * 实现：修改excel中结果列的值
     * 参数：filepath:被修改excel存储路径
     * 参数：sheetname:表格中的sheet名称
     * 返回值：result，返回与excel表格中内容一致的二维数组
     * 作者：slj
     * 时间：2017-08-09
     */

	public void UpdateCell( String filepath,String sheetname,String[][] result) throws BiffException, IOException, WriteException{
		
		CopyFile copyfile=new CopyFile();
		String dest=getFilename();
	    copyfile.copyFile(filepath, dest);
		//构建Workbook对象，保存已有表格，只读
		Workbook  workbook = null;
		
		//构建WritableWorkbook对象，将已有表格赋值一份，可写类型
		WritableWorkbook wworkbook = null;
		
		//输入流，声明读取变量is 用户读取excel表格
		InputStream is=new FileInputStream(filepath);
		
		//将流中数据保存在只读的workbook中
		workbook=Workbook.getWorkbook(is);
		
		//将workbook中数据，复制一份给可写的wworkbook 
		wworkbook=Workbook.createWorkbook(new File(filepath),workbook);
		
		//声明变量wsheet，将wworkbook中的sheet名为sheetname的表格读取到wsheet中
		 WritableSheet wsheet =wworkbook.getSheet(sheetname);
		 

		 for(int i=1;i<result.length;i++)
		 {	
			 for(int j=1;j<wsheet.getRows();j++)
			 {
//				 String t1=result[i][result[0].length-3];
//				 String t2=wsheet.getWritableCell(wsheet.getColumns()-3, j).getContents();
			
				 //判断数组倒数第三列第i行，是否与待写sheet中的倒数第三列，第j行相等，相等则重写预期结果和时间两列
				 if(result[i][result[0].length-3].equals(wsheet.getWritableCell(wsheet.getColumns()-3, j).getContents())){
					//获取可写副本中的预期结果
					 WritableCell cell_expectancy =wsheet.getWritableCell(result[0].length-2, i);
					//获取可写副本中的时间
					 WritableCell cell_time =wsheet.getWritableCell(result[0].length-1, i);
			 
					 //保存预期结果单元格的样式
					 CellFormat cf_expectancy = cell_expectancy.getCellFormat();
					//保存时间单元格的样式
					 CellFormat cf_time = cell_time.getCellFormat();
					 
					 //修改预期结果，第一个参数：列，第二个参数：行，第三个参数：修改的值
					 Label lbl_expectancy = new Label(result[0].length-2, i, result[i][result[0].length-2]);
					//修改时间，第一个参数：列，第二个参数：行，第三个参数：修改的值
					 Label lbl_time = new Label(result[0].length-1, i, result[i][result[0].length-1]);
			
					 //恢复预期结果单元格样式
					 lbl_expectancy.setCellFormat(cf_expectancy);
					//恢复时间单元格样式
					 lbl_time.setCellFormat(cf_time);
			
					 //保存新的预期结果值
					 wsheet.addCell(lbl_expectancy);
					//保存新的时间值
					 wsheet.addCell(lbl_time);
					 break;
				 }
			 }	 
		}
		//将修改保存到wworkbook中
		wworkbook.write();
		System.out.println("写入excel成功");
		//释放wworkbook
		wworkbook.close();
	     	 
	}
	
	/*
	 * 实现：用于备份用例excel，拼凑目标文件备份的路径及标题_年月日时分秒
	 * 返回：路径+拼凑好的标题
	 * 作者：slj
	 * 时间：2017-08-11
	 */
	public String getFilename(){
		
		DateString datestring=new DateString();
		
		return BaseData.copypath+datestring.getDateString()+".xls";

	}
}
