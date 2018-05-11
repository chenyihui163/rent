package com.rent.utils;

import java.util.Date;

import java.util.UUID;



/*
 *  生成一个唯一的6位字符编码（到2063年前一直生效）的JAVA 方法
 */
public class unRepeatCode {
	/*
	 *  private static final int ORDER_DEFAULT_LENGTH = 20;  
  
    public static String order() {  
    return order(ORDER_DEFAULT_LENGTH);  
    }  
    //获取一个指定长度的订单号  
    private static String order(int length) {  
	 */
	 
	public static Integer Random()
	{
		//这参数10代表你生成10位随机串，当然你也可以设置为20 30,40等，  
		//String random = RandomStringUtils.randomNumeric(10);  
	   int num =(int) (Math.random()*10000000+50000000);
	   return num;
   }
	
    /** 
     * 20位末尾的数字id 
     */  
	 private static Date date = new Date();  
	  private static StringBuilder buf = new StringBuilder();  
	  private static int seq = 0;  
	  private static final int ROTATION = 99999;  

    public static Integer getRandom() {  
          
    	  if (seq > ROTATION) seq = 0;  
    	    buf.delete(0, buf.length());  
    	    date.setTime(System.currentTimeMillis());  
    	    String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);  
    	    return Integer.parseInt(str);
    }  
    
    public static String[] chars = new String[] { "1", "2", "3", "4", "5",  
        "6", "7", "8", "9" };  


    public static Integer generateShortUuid() {  
    StringBuffer shortBuffer = new StringBuffer();  
   String uuid = UUID.randomUUID().toString().replace("-", "");  
   for (int i = 0; i < 8; i++) {  
    String str = uuid.substring(i * 4, i * 4 + 4);  
    int x = Integer.parseInt(str, 16);  
    shortBuffer.append(chars[x % 0x3E]);  
     }  
     return Integer.valueOf(shortBuffer.toString());  

} 
	
}
