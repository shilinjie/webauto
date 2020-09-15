package com.xx.common;

public class StrToLoc {
/*
	public Locator[] strtoloc(Locator[] loc,String [][] operator){
		int len=operator.length;
		Locator[] locorder=new Locator[len];
		
		for(int i=0;i<operator.length;i++){
			for(int j=0;j<loc.length;j++){
				String a=loc[j].getLocatorInfo().toString();
				if(loc[j].getLocatorInfo().toString().equals(operator[i][2])){

					locorder[i]=loc[j];
					
				}
			}
		}
		return locorder;
			
	}*/
	/*
	 * 用于将步骤写在excel中时使用
	 * 
	 */
	
	public LocatorInfo[] strtoloc(LocatorInfo[] loc,String [][] cases){
		int len=cases.length;
		LocatorInfo[] locorder=new LocatorInfo[len];
		
		for(int i=0;i<cases.length;i++){
			for(int j=0;j<loc.length;j++){
				String a=loc[j].getLocatorInfo().toString();
				if(loc[j].getLocatorInfo().toString().equals(cases[i][2])){

					locorder[i]=loc[j];
					
				}
			}
		}
		return locorder;
			
	}
}
