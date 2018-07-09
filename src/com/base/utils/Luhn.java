package com.base.utils;
import java.util.Scanner;

//信用卡号校验算法
public class Luhn {
	public static void main(String[] args) {
		System.out.println("Please input your credit card number:");
		Scanner input = new Scanner(System.in);
		int sumOdd = 0;
		int sumEven = 0;
		String number = input.next();
		int length = number.length();
		int[] wei = new int[length];
		for (int i = 0; i < number.length(); i++) {
			wei[i] = Integer.parseInt(number.substring(length - i - 1, length
					- i));// 从最末一位开始提取，每一位上的数值
			System.out.println("第" + i + "位数字是：" + wei[i]);
		}
		for (int i = 0; i < length / 2; i++) {
			sumOdd += wei[2 * i];
			if ((wei[2 * i + 1] * 2) > 9)
				wei[2 * i + 1] = wei[2 * i + 1] * 2 - 9;
			else
				wei[2 * i + 1] *= 2;
			sumEven += wei[2 * i + 1];
		}
		System.out.println("奇数位的和是：" + sumOdd);
		System.out.println("偶数位的和是：" + sumEven);
		if ((sumOdd + sumEven) % 10 == 0)
			System.out.println("Recept.");
		else
			System.out.println("Can not recept.");
	}
}

