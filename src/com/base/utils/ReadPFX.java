package com.base.utils;

import java.io.*;
import java.util.*;
import java.security.*;
import java.security.cert.Certificate;

import com.unionpay.acp.sdk.SDKUtil;

public class ReadPFX {
	public ReadPFX() {
	}

	// 转换成十六进制字符串
	public static String Byte2String(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	public static byte[] StringToByte(int number) {
		int temp = number;
		byte[] b = new byte[4];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = new Integer(temp & 0xff).byteValue();// 将最高位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	public static PrivateKey GetPvkformPfx(String strPfx, String strPassword) {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(strPfx);
			// If the keystore password is empty(""), then we have to set
			// to null, otherwise it won't work!!!
			char[] nPassword = null;
			if ((strPassword == null) || strPassword.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = strPassword.toCharArray();
			}
			ks.load(fis, nPassword);
			fis.close();
			Enumeration enumas = ks.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements())// we are readin just one certificate.
			{
				keyAlias = (String) enumas.nextElement();
			}
			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			Certificate cert = ks.getCertificate(keyAlias);
			PublicKey pubkey = cert.getPublicKey();
			System.out.println("cert class = " + cert.getClass().getName());
			System.out.println("cert = " + cert);
			System.out.println("public key = " + pubkey);
			System.out.println("private key = " + prikey);
			return prikey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) throws Exception {
		validate(null, null);
	}

	// 字节数组转十六进制
	public static String byte2Hex(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(0x00ff & b[i]);
			if (hex.length() < 2) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public static boolean validate(Map<String, String> resData, String encoding) {
		String stringSign = (String) resData.get("signature");
		SDKUtil.sign(resData, encoding);
		try {
			System.out.println("-------stringSign-------------"+stringSign);
			System.out.println("-------signature-------------"+resData.get("signature"));
		    return resData.get("signature").equals(stringSign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
