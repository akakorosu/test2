package com.bonc.common.des;

public class DesTest {

	public static void main(String[] args) throws Exception {
		// 待加密内容
		String str = "123";
		System.out.println("原始内容：" + str);
		// 加密
		String encryptResult = DesUtil.encrypt(str);
		System.out.println("加密后：" + encryptResult);
		// 直接将如上内容解密
		String decryResult = "";
		try {
			// 解密
			decryResult = DesUtil.decrypt(encryptResult);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("解密后：" + decryResult);
	}
}