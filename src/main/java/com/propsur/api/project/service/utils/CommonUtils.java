package com.propsur.api.project.service.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public  class CommonUtils {

	public static final String JDBC_DATE_FORMAT = "yyyy.MM.dd";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String SQL_DATE_FORMAT = "yy-MM-dd";
	public static final String SQL_DATE_FORMAT1 = "yyyy-MM-dd";
	public static  final Integer STATUS_FLAG = 1;
	
	public static Character FROM_DEVICE;
	
	public static int getOTP() {
		return Integer.valueOf((int)(Math.random()*900000)+1000);
	}
	
	public static Date getStringToDate(String date)
	{
		Date retDate = null;
		try
		{		    
			retDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}  
	   
		return retDate;  
	}
	
	public static String getUserDevice(String userAgent) {
		String os="";
		String browser = "";
		 if (userAgent.toLowerCase().indexOf("windows") >= 0 )
         {
             os = "Windows";
             FROM_DEVICE='D';
         } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
         {
             os = "Mac";
             FROM_DEVICE='D';
         } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
         {
             os = "Unix";
             FROM_DEVICE='D';
         } else if(userAgent.toLowerCase().indexOf("android") >= 0)
         {
             os = "Android";
             FROM_DEVICE='M';
         } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
         {
             os = "IPhone";
             FROM_DEVICE='M';
         }else{
             os = "UnKnown, More-Info: "+userAgent;
             FROM_DEVICE='U';
         }
		 String  user =   userAgent.toLowerCase();
		 //===============Browser===========================
	        if (user.contains("msie"))
	        {
	            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
	            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
	        } else if (user.contains("safari") && user.contains("version"))
	        {
	            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
	        } else if ( user.contains("opr") || user.contains("opera"))
	        {
	            if(user.contains("opera"))
	                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
	            else if(user.contains("opr"))
	                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
	        } else if (user.contains("chrome"))
	        {
	            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
	        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
	        {
	            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
	            browser = "Netscape-?";

	        } else if (user.contains("firefox"))
	        {
	            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
	        } else if(user.contains("rv"))
	        {
	            browser="IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
	        } else
	        {
	            browser = "UnKnown, More-Info: "+userAgent;
	        }
	        
	        return os.concat("-").concat(browser);
	}


	
	public static String getExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        String extension = "";
		String extensionNew = "";
		int index = fileName.lastIndexOf(".");

		if (index > 0) {

			extension = fileName.substring(index + 1); // return ---> "java"
			extensionNew = fileName.substring(index); // return ---> ".java"
		}

		return extension;
		//System.out.println("File extension is: " + extension);
		//System.out.println("File extension new  is: " + extensionNew);
    }

	//Round of for above .50
	public static double getRoundOfVal(double value,double compareVal) {
		
		double decimalpoints = Math.abs(value - Math.floor(value));
		decimalpoints = Double.parseDouble(new DecimalFormat("##.####").format(decimalpoints));
		if (decimalpoints > compareVal)
	         return Math.round(value);
	     else
	        return Math.floor(value);
	}
	
	public static BigDecimal getDecimalRoundOffVal(int decimalScale , BigDecimal amtVal) {
		 amtVal = amtVal.setScale(decimalScale, BigDecimal.ROUND_HALF_DOWN);
		 return amtVal;
	}
}
