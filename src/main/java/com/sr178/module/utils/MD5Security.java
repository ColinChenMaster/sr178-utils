package com.sr178.module.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5Security {
	
	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		int t;
		for (int i = 0; i < 16; i++) {
			t = bytes[i];
			if (t < 0)
				t += 256;
			sb.append(hexDigits[(t >>> 4)]);
			sb.append(hexDigits[(t % 16)]);
		}
		return sb.toString();
	}
	
	public static String md5_16_Big(String input) throws Exception {
		return code(input, 16).toUpperCase();
	}
	

	
	public static String md5_16_Small(String str){
		try {
			return code(str,16).toLowerCase();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static String md5_32_Big(String input) throws Exception {
		return code(input, 32).toUpperCase();
	}
	
	
	public static String md5_32_Small(String str){
		try {
			return code(str,32).toLowerCase();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	private static String code(String input, int bit) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance(System.getProperty(
					"MD5.algorithm", "MD5"));
			if (bit == 16)
				return bytesToHex(md.digest(input.getBytes("utf-8")))
						.substring(8, 24);
			return bytesToHex(md.digest(input.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Could not found MD5 algorithm.", e);
		}
	}
}

