package com.charilog.lib;

import java.security.MessageDigest;

public class CommonLib {
	public static String encryptSHA256(String plainText) {
		String encrypted = null;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(plainText.getBytes());
			byte[] cipher = messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : cipher) {
				sb.append(String.format("%02x", (b & 0xff)));
			}
			encrypted = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encrypted;
	}
}
