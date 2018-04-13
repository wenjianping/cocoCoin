package com.cplatform.surf.coco.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	public static String hash128(String content) {
		return hash128(content.getBytes(Charset.forName("utf-8")));
	}

	public static String hash128(byte[] content) {
		String encName = "SHA-512";
		return encrypt(content, encName);
	}

	public static String hash64(String content) {
		String encName = "SHA-256";
		return encrypt(content.getBytes(), encName);
	}
	
	public static String hash64(byte[] content) {
		String encName = "SHA-256";
		return encrypt(content, encName);
	}


	public static String md5(String content) {
		String encName = "MD5";
		return encrypt(content.getBytes(), encName);
	}

	private static String encrypt(byte[] content, String encName) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = content;
		try {

			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = ByteUtil.bytes2Hex(md.digest()); // to HexString
		}
		catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	public static void main(String args[]) {
		String s = hash128("汪业培1");
		System.out.println(s);
		s = hash128("汪业培1");
		System.out.println(s);
		System.out.println(hash64("汪业培1"));
		System.out.println(md5("汪业培1"));
	}
}
