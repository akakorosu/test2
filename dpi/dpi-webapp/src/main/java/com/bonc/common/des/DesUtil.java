package com.bonc.common.des;

import java.nio.charset.Charset;

import com.bonc.text.utils.classesManager.Initializer;

public class DesUtil {
	
	private static final String SKEY = "snqazwsx"; 
	
	//private static final String SKEY = Initializer.getClassLoader2();
	
   private static final Charset CHARSET = Charset.forName("UTF-8");
    

    /**
     * 加密
     * @param srcStr
     * @param charset
     * @param sKey
     * @return
     * @throws Exception 
     */
    public static String encrypt(String srcStr) {
    	try {
			return Des.encrypt(srcStr, SKEY, CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    /**
     * 解密
     *
     * @param hexStr
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String decrypt(String hexStr) {
    	try {
    		return Des.decrypt(hexStr, SKEY, CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    public static void main(String[] args) {
		String a="我爱中国aa";
		System.out.println(a.toUpperCase());
				
	}
    
}