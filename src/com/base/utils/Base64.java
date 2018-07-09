package com.base.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.*;

public class Base64 {
    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b).replace("\n", "").replace("\r", "");
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static BASE64Encoder encoder = new BASE64Encoder();

    /**
     * base64编码
     *
     * @param str
     *            字符数组
     * @return
     * @throws IOException
     */
    public static String encodeBytes(String str) throws IOException
    {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new BASE64Encoder().encode(b).replace("\n", "").replace("\r", "");
    }

    /**
     * BASE64 编码
     *
     * @param str
     * @return
     */
    public static String encodeBufferBase64(String str)
    {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s == null?null:encoder.encodeBuffer(b).trim();
    }

    /**
     * base64解码
     *
     * @param str
     *            字符数组
     * @return
     * @throws IOException
     */
    public static String decodeBytes(String str) throws IOException
    {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String(new BASE64Decoder().decodeBuffer(new String(b)));
    }

}