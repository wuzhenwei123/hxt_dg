package com.base.payment.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.base.utils.FileUploadConstants;

public class Test {
	public static void main(String[] args) throws UnknownHostException, Exception {
		PaymentClient client = new PaymentClientImpl();
		String uesrno = randomNum(18);
		System.out.println(uesrno);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		client.add("015810742305655001002016225260101653000", "100000000000000001", "张", "64090001", "00100", "201581000018", "6225260101653000", "张伟浩");
//		client.update("015810742305655001002016225260101653000", "0000911202139876", "张", "00100", "201581000018", "6225260101653000", "张伟浩");
		client.revoke("015810742305655001002016225260101653000", "0000911202139876", "张", "00100", "201581000018", "6225260101653000", "张伟浩");
//		String sqe = client.getSQE();
//		System.out.println("---------sqe-------------"+sqe);
//		client.daishou("100000000000000001", "201581000018", "6227020160830115459", "201581000018", null, "6225260101653000", "张伟浩", "00100", "015810742305655001002016225260101653000", "CNY", "1000",sqe);
//		String dataSource = "100301" + client.autoFill(FileUploadConstants.ORGANIZATION_CODE, 12) + sdf.format(new Date()).substring(0, 8) + "2016091215133337";
//		System.out.println("---------dataSource-------------"+dataSource);
//		client.correct("201581000018", null, dataSource);

//		String s = "00000473100403120220160911201919512016091100000000946943596327825840742305655               2015810000186227020160830115459                906581000018            56812378945678553100               天裕有限公司                                                00100                                                            SCNY000000000010000000000000000000                                                            0000000000";
//		System.out.println(length(s));
//		System.out.println(length("100403120220160911201655212016091100000000804511035125246882742305655               2015810000186227020160830115459                906581000018            56812378945678553100               天裕有限公司                                                00100                                                            SCNY000000000010000000000000000000                                                            0000000000CT2O4004      00b381edfce8ca6d39bbc0244be68e35bd"));
	}

	public static String randomNum(int c) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < c; i++) {
			int r = random.nextInt(10);
			buffer.append(r);
		}
		return buffer.toString();
	}

	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param String
	 *            s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}
}
