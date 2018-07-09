package com.base.utils;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang.StringUtils;

import com.hxt.hProxyMessage.model.HProxyMessage;
import com.hxt.hProxyMessage.model.Location;

public class CommUtil {
	private static final java.text.SimpleDateFormat dateFormat = new

	java.text.SimpleDateFormat("yyyy-MM-dd");

	private static String imageURL = "";// 系统图片服务器地址，后期可对其进行扩展

	public static String first2low(String str) {
		String s = "";
		s = str.substring(0, 1).toLowerCase() + str.substring(1);
		return s;
	}

	public static String first2upper(String str) {
		String s = "";
		s = str.substring(0, 1).toUpperCase() + str.substring(1);
		return s;
	}

	public static void setImageURL(String imageUrl) {
		imageURL = imageUrl;
	}

	/**
	 * 用来处理一行一条数据
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static List<String> str2list(String s) throws IOException {
		List<String> list = new ArrayList<String>();
		s = CommUtil.null2String(s);
		if (!s.equals("")) {
			StringReader fr = new StringReader(s);
			BufferedReader br = new BufferedReader(fr);
			String aline = "";
			while ((aline = br.readLine()) != null) {
				list.add(aline);
			}
		}
		return list;
	}

	public static java.util.Date formatDate(String s) {
		java.util.Date d = null;
		try {
			d = dateFormat.parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	public static java.util.Date formatDate(String s, String format) {
		java.util.Date d = null;
		try {
			SimpleDateFormat dFormat = new java.text.SimpleDateFormat(format);
			d = dFormat.parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	public static String firstCharUpper(String s) {
		String src = null;
		StringBuilder target = new StringBuilder();
		if (s != null && (src = s.trim()).length() > 0) {
			String[] ds = src.split("_");
			for (int i = 0; i < ds.length; i++) {
				String d = ds[i];
				String p = "";
				String f = "";
				if (d.length() > 0) {
					p = d.substring(0, 1).toUpperCase();
				}
				if (d.length() > 2) {
					f = d.substring(1);
				}
				target.append(p);
				target.append(f);
			}
		}
		return target.toString();
	}

	public static String formatTime(String format, Object v) {
		if (v == null)
			return null;
		if (v.equals(""))
			return "";
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	public static String formatLongDate(Object v) {
		if (v == null || v.equals(""))
			return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(v);
	}

	public static String formatShortDate(Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(v);
	}

	public static String formatCNDate(Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		return df.format(v);
	}

	public static String decode(String s) {
		String ret = s;
		try {
			ret = URLDecoder.decode(s.trim(), "UTF-8");
		} catch (Exception e) {
		}
		return ret;
	}

	public static String encode(String s) {
		String ret = s;
		try {
			ret = URLEncoder.encode(s.trim(), "UTF-8");
		} catch (Exception e) {
		}
		return ret;
	}

	public static String convert(String str, String coding) {
		String newStr = "";
		if (str != null)
			try {
				newStr = new String(str.getBytes("ISO-8859-1"), coding);
			} catch (Exception e) {
				return newStr;
			}
		return newStr;
	}

	public static boolean isImg(String extend) {
		boolean ret = false;
		List<String> list = new java.util.ArrayList<String>();
		list.add("jpg");
		list.add("jpeg");
		list.add("bmp");
		list.add("gif");
		list.add("png");
		list.add("tif");
		list.add("tbi");
		for (String s : list) {
			if (s.equals(extend))
				ret = true;
		}
		return ret;
	}

	/**
	 * 图片水印，一般使用gif png格式，其中png质量较好
	 * 
	 * @param pressImg
	 *            水印文件
	 * @param targetImg
	 *            目标文件
	 * @param pos
	 *            水印位置，使用九宫格控制
	 * @param alpha
	 *            水印图片透明度
	 */
	public final static void waterMarkWithImage(String pressImg,
			String targetImg, int pos, float alpha) {
		try {
			// 目标文件
			Image theImg = Toolkit.getDefaultToolkit().getImage(targetImg);
			theImg.flush();
			BufferedImage bis = toBufferedImage(theImg);
			int width = theImg.getWidth(null);
			int height = theImg.getHeight(null);
			bis = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bis.createGraphics();
			g.drawImage(theImg, 0, 0, width, height, null);

			// 水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha / 100));
			int width_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			int x = 0;
			int y = 0;
			if (pos == 1) {

			}
			if (pos == 2) {
				x = (width - width_biao) / 2;
				y = 0;
			}
			if (pos == 3) {
				x = width - width_biao;
				y = 0;
			}
			if (pos == 4) {
				x = width - width_biao;
				y = (height - height_biao) / 2;
			}
			if (pos == 5) {
				x = width - width_biao;
				y = height - height_biao;
			}
			if (pos == 6) {
				x = (width - width_biao) / 2;
				y = height - height_biao;
			}
			if (pos == 7) {
				x = 0;
				y = height - height_biao;
			}
			if (pos == 8) {
				x = 0;
				y = (height - height_biao) / 2;
			}
			if (pos == 9) {
				x = (width - width_biao) / 2;
				y = (height - height_biao) / 2;
			}
			g.drawImage(src_biao, x, y, width_biao, height_biao, null);
			// 水印文件结束
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			ImageIO.write(bis, "JPEG", out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取图片为bufferedimage,修正图片读取ICC信息丢失而导致出现红色遮罩
	 * 
	 * @param image
	 * @return
	 */
	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		// boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			/*
			 * if (hasAlpha) { transparency = Transparency.BITMASK; }
			 */

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			// int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
			/*
			 * if (hasAlpha) { type = BufferedImage.TYPE_INT_ARGB; }
			 */
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	/**
	 * @param filePath
	 *            需要添加水印的图片的路径
	 * @param outPath
	 *            添加水印后的输出路径
	 * @param markContent
	 *            水印的文字
	 * @param markContentColor
	 *            水印文字的颜色
	 * @param font
	 *            文字字体 大小等
	 * @param left
	 *            水印的位置，距离图片左上角位置
	 * @param top
	 *            水印的位置，距离图片顶部位置
	 * @param qualNum
	 *            图片质量
	 * @return
	 */
	public static boolean waterMarkWithText(String filePath, String outPath,
			String text, String markContentColor, Font font, int pos,
			float qualNum) {
		Image theImg = Toolkit.getDefaultToolkit().getImage(filePath);
		theImg.flush();
		BufferedImage bis = toBufferedImage(theImg);
		int width = bis.getWidth(null);
		int height = bis.getHeight(null);
		BufferedImage bimage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimage.createGraphics();
		if (font == null) {
			font = new Font("黑体", Font.BOLD, 30);
			g.setFont(font);
		} else {
			g.setFont(font);
		}
		g.setColor(getColor(markContentColor));
		g.setBackground(Color.white);
		g.drawImage(theImg, 0, 0, null);
		FontMetrics metrics = new FontMetrics(font) {
		};
		Rectangle2D bounds = metrics.getStringBounds(text, null);
		int widthInPixels = (int) bounds.getWidth();
		int heightInPixels = (int) bounds.getHeight();
		int left = 0;
		int top = heightInPixels;
		if (pos == 1) {

		}
		if (pos == 2) {
			left = width / 2;
			top = heightInPixels;
		}
		if (pos == 3) {
			left = width - widthInPixels;
			top = heightInPixels;
		}
		if (pos == 4) {
			left = width - widthInPixels;
			top = height / 2;
		}
		if (pos == 5) {
			left = width - widthInPixels;
			top = height - heightInPixels;
		}
		if (pos == 6) {
			left = width / 2;
			top = height - heightInPixels;
		}
		if (pos == 7) {
			left = 0;
			top = height - heightInPixels;
		}
		if (pos == 8) {
			left = 0;
			top = height / 2;
		}
		if (pos == 9) {
			left = width / 2;
			top = height / 2;
		}
		g.drawString(text, left, top); // 添加水印的文字和设置水印文字出现的内容
		g.dispose();
		try {
			FileOutputStream out = new FileOutputStream(outPath);
			ImageIO.write(bimage, "JPEG", out);
			out.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean createFolder(String folderPath) {
		boolean ret = true;
		try {
			java.io.File myFilePath = new java.io.File(folderPath);
			if (!myFilePath.exists() && !myFilePath.isDirectory()) {
				ret = myFilePath.mkdirs();
				if (!ret) {
					System.out.println("创建文件夹出错");
				}
			}
		} catch (Exception e) {
			System.out.println("创建文件夹出错");
			ret = false;
		}
		return ret;
	}

	public static List toRowChildList(List list, int perNum) {
		// System.out.println("执行toRowChildList");
		List l = new java.util.ArrayList();
		if (list == null)
			return l;
		// System.out.println("照片："+list.size());
		// System.out.println("perNum:"+perNum);
		for (int i = 0; i < list.size(); i += perNum) {
			List cList = new ArrayList();
			for (int j = 0; j < perNum; j++)
				if (i + j < list.size())
					cList.add(list.get(i + j));
			l.add(cList);
		}
		return l;
	}

	public static List copyList(List list, int begin, int end) {
		List l = new ArrayList();
		if (list == null)
			return l;
		if (end > list.size())
			end = list.size();
		for (int i = begin; i < end; i++) {
			l.add(list.get(i));
		}
		return l;
	}

	public static boolean isNotNull(Object obj) {
		if (obj != null && !obj.toString().equals("")) {
			return true;
		} else
			return false;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错 ");
			e.printStackTrace();
		}
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param path
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean deleteFolder(String path) {
		boolean flag = false;
		File file = new File(path);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(path);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(path);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param path
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String path) {
		boolean flag = false;
		File file = new File(path);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param path
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String path) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		File dirFile = new File(path);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static char randomChar() {
		char[] chars = new char[] { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D',
				'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J',
				'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P',
				'q', 'Q', 'r', 'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V',
				'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z' };
		int index = (int) (Math.random() * 52) - 1;
		if (index < 0) {
			index = 0;
		}
		return chars[index];
	}

	public static String[] splitByChar(String s, String c) {
		String[] list = s.split(c);
		return list;
	}

	public static String substringfrom(String s, String from) {
		if (s.indexOf(from) < 0)
			return "";
		return s.substring(s.indexOf(from) + from.length());
	}

	public static int null2Int(Object s) {
		int v = 0;
		if (s != null)
			try {
				v = Integer.parseInt(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	public static float null2Float(Object s) {
		float v = 0.0f;
		if (s != null)
			try {
				v = Float.parseFloat(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	public static double null2Double(Object s) {
		double v = 0.0;
		if (s != null)
			try {
				v = Double.parseDouble(null2String(s));
			} catch (Exception e) {
			}
		return v;
	}

	public static boolean null2Boolean(Object s) {
		boolean v = false;
		if (s != null)
			try {
				v = Boolean.parseBoolean(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	public static String null2String(Object s) {
		return s == null ? "" : s.toString().trim();
	}

	public static Long null2Long(Object s) {
		Long v = -1l;
		if (s != null)
			try {
				v = Long.parseLong(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	public static String getTimeInfo(long time) {
		int hour = (int) time / (1000 * 60 * 60);
		long balance = time - hour * 1000 * 60 * 60;
		int minute = (int) balance / (1000 * 60);
		balance = balance - minute * 1000 * 60;
		int seconds = (int) balance / 1000;
		String ret = "";
		if (hour > 0)
			ret += hour + "小时";
		if (minute > 0)
			ret += minute + "分";
		else if (minute <= 0 && seconds > 0)
			ret += "零";
		if (seconds > 0)
			ret += seconds + "秒";
		return ret;
	}

	public static int indexOf(String s, String sub) {
		return s.trim().indexOf(sub.trim());
	}

	public static Map cal_time_space(Date begin, Date end) {
		long l = end.getTime() - begin.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long second = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		Map map = new HashMap();
		map.put("day", day);
		map.put("hour", hour);
		map.put("min", min);
		map.put("second", second);
		return map;
	}

	public static final String randomString(int length) {
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
				+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		if (length < 1) {
			return "";
		}
		Random randGen = new Random();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final String randomInt(int length) {
		if (length < 1) {
			return null;
		}
		Random randGen = new Random();
		char[] numbersAndLetters = ("0123456789").toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}

	/**
	 * 计算两个时间间隔
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long getDateDistance(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 浮点数除法运算，保证数据的精确度
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double div(Object a, Object b) {
		double ret = 0.0;
		if (!null2String(a).equals("") && !null2String(b).equals("")) {
			BigDecimal e = new BigDecimal(null2String(a));
			BigDecimal f = new BigDecimal(null2String(b));
			if (null2Double(f) > 0)
				ret = e.divide(f, 3, BigDecimal.ROUND_DOWN).doubleValue();
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret));
	}

	/**
	 * 浮点数据减法运算，保证数据的精确度
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double subtract(Object a, Object b) {
		double ret = 0.0;
		BigDecimal e = new BigDecimal(CommUtil.null2Double(a));
		BigDecimal f = new BigDecimal(CommUtil.null2Double(b));
		ret = e.subtract(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret));
	}

	/**
	 * 浮点数据加法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double add(Object a, Object b) {
		double ret = 0.0;
		BigDecimal e = new BigDecimal(CommUtil.null2Double(a));
		BigDecimal f = new BigDecimal(CommUtil.null2Double(b));
		ret = e.add(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret));
	}

	/**
	 * 浮点数乘法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double mul(Object a, Object b) {// 乘法
		BigDecimal e = new BigDecimal(CommUtil.null2Double(a));
		BigDecimal f = new BigDecimal(CommUtil.null2Double(b));
		double ret = e.multiply(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret));
	}

	public static String formatMoney(Object money) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(money);
	}

	public static int M2byte(float m) {
		float a = m * 1024 * 1024;
		return (int) a;
	}

	public static boolean convertIntToBoolean(int intValue) {
		return (intValue != 0);
	}

	/**
	 * 根据图片绝对地址获取图片路径
	 * 
	 * @param path
	 *            :图片绝对地址
	 * @author hezeng
	 * @date 20160506
	 * @return
	 */
	public static String getImgURL(String path) {
		String src = "";
		if (path != null && !path.equals("")) {
			src = imageURL + "/" + path;
		}
		return src;
	}

	/**
	 * 根据图片绝对地址获取图片路径，返回大图或者小图
	 * 
	 * @param path
	 * @param type
	 *            :传入middle或者small
	 * @author hezeng
	 * @date 20160506
	 * @return
	 */
	public static String getImgURL(String path, String type) {
		String src = "";
		if (path != null && !path.equals("")) {
			src = imageURL + "/" + path;
		}
		if (type != null && !type.equals("")) {
			String str2 = path.substring(path.indexOf("."), path.length());
			src = src + "_" + type + str2;
		}
		return src;
	}

	public static int parseDate(String type, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (type.equals("y")) {
			return cal.get(Calendar.YEAR);
		}
		if (type.equals("M")) {
			return cal.get(Calendar.MONTH) + 1;
		}
		if (type.equals("d")) {
			return cal.get(Calendar.DAY_OF_MONTH);
		}
		if (type.equals("H")) {
			return cal.get(Calendar.HOUR_OF_DAY);
		}
		if (type.equals("m")) {
			return cal.get(Calendar.MINUTE);
		}
		if (type.equals("s")) {
			return cal.get(Calendar.SECOND);
		}
		return 0;
	}

	// 读取远程url图片,得到宽高
	public static int[] readImgWH(String imgurl) {
		boolean b = false;
		try {
			// 实例化url
			URL url = new URL(imgurl);
			// 载入图片到输入流
			java.io.BufferedInputStream bis = new BufferedInputStream(
					url.openStream());
			// 实例化存储字节数组
			byte[] bytes = new byte[100];
			// 设置写入路径以及图片名称
			OutputStream bos = new FileOutputStream(new File(
					"C:\\thetempimg.gif"));
			int len;
			while ((len = bis.read(bytes)) > 0) {
				bos.write(bytes, 0, len);
			}
			bis.close();
			bos.flush();
			bos.close();
			// 关闭输出流
			b = true;
		} catch (Exception e) {
			// 如果图片未找到
			b = false;
		}
		int[] a = new int[2];
		if (b) {// 图片存在
			// 得到文件
			java.io.File file = new java.io.File("C:\\thetempimg.gif");
			BufferedImage bi = null;
			boolean imgwrong = false;
			try {
				// 读取图片
				bi = javax.imageio.ImageIO.read(file);
				try {
					// 判断文件图片是否能正常显示,有些图片编码不正确
					int i = bi.getType();
					imgwrong = true;
				} catch (Exception e) {
					imgwrong = false;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			if (imgwrong) {
				a[0] = bi.getWidth(); // 获得 宽度
				a[1] = bi.getHeight(); // 获得 高度
			} else {
				a = null;
			}
			// 删除文件
			file.delete();
		} else {// 图片不存在
			a = null;
		}
		return a;
	}

	/**
	 * 前台判定是否存在文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean fileExist(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * 计算分割后的数组长度
	 * 
	 * @param s
	 * @param c
	 * @return
	 */
	public static int splitLength(String s, String c) {
		int v = 0;
		if (!s.trim().equals("")) {
			v = s.split(c).length;
		}
		return v;
	}

	/**
	 * 计算file文件大小，可以是单个文件也可以是文件夹
	 * 
	 * @param file
	 * @return
	 */
	static int totalFolder = 0;
	static int totalFile = 0;

	public static double fileSize(File folder) {
		if (folder.exists()) {
			totalFolder++;
			// System.out.println("Folder: " + folder.getName());
			long foldersize = 0;
			File[] filelist = folder.listFiles();
			for (int i = 0; i < filelist.length; i++) {
				if (filelist[i].isDirectory()) {
					foldersize += fileSize(filelist[i]);
				} else {
					totalFile++;
					foldersize += filelist[i].length();
				}
			}
			return div(foldersize, 1024);
		} else
			return 0;
	}

	/**
	 * 计算文件夹下文件数量
	 * 
	 * @param file
	 * @return
	 */
	public static int fileCount(File file) {
		if (file == null) {
			return 0;
		}
		if (!file.isDirectory()) {
			return 1;
		}
		int fileCount = 0;
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				fileCount++;
			} else if (f.isDirectory()) {
				fileCount++;
				fileCount += fileCount(file); // 如果遇到目录则通过递归调用继续统计
			}
		}
		return fileCount;
	}

	/**
	 * 根据html颜色代码返回java Color
	 * 
	 * @param color
	 * @return
	 */
	public static Color getColor(String color) {
		if (color.charAt(0) == '#') {
			color = color.substring(1);
		}
		if (color.length() != 6) {
			return null;
		}
		try {
			int r = Integer.parseInt(color.substring(0, 2), 16);
			int g = Integer.parseInt(color.substring(2, 4), 16);
			int b = Integer.parseInt(color.substring(4), 16);
			return new Color(r, g, b);
		} catch (NumberFormatException nfe) {
			return null;
		}
	}

	/**
	 * 根据种子a随机出一组长度为length不重复的整型数组
	 * 
	 * @param a
	 * @param length
	 * @return
	 */
	public static Set<Integer> randomInt(int a, int length) {
		Set<Integer> list = new TreeSet<Integer>();
		int size = length;
		if (length > a) {
			size = a;
		}
		while (list.size() < size) {
			Random random = new Random();
			int b = random.nextInt(a);
			list.add(b);
		}
		return list;
	}

	/**
	 * 格式化数字，保留对应的小数位
	 * 
	 * @param obj
	 * @param len
	 * @return
	 */
	public static Double formatDouble(Object obj, int len) {
		Double ret = 0.0;
		String format = "0.0";
		for (int i = 1; i < len; i++) {
			format = format + "0";
		}
		DecimalFormat df = new DecimalFormat(format);
		return Double.valueOf(df.format(obj));
	}

	/**
	 * 判断字符是否为中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为乱码
	 * 
	 * @param strName
	 * @return
	 */
	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {

				if (!isChinese(c)) {
					count = count + 1;
					System.out.print(c);
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 去掉IP字符串前后所有的空格
	 * 
	 * @param IP
	 * @return
	 */
	public static String trimSpaces(String IP) {//
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}

	/**
	 * 判断是否是一个IP
	 * 
	 * @param IP
	 * @return
	 */
	public static boolean isIp(String IP) {//
		boolean b = false;
		IP = trimSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = IP.split("\\.");
			if (Integer.parseInt(s[0]) < 255)
				if (Integer.parseInt(s[1]) < 255)
					if (Integer.parseInt(s[2]) < 255)
						if (Integer.parseInt(s[3]) < 255)
							b = true;
		}
		return b;
	}

	/**
	 * 
	 * @param str
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String generic_star(String str, int begin, int end) {
		if (str.length() > begin && str.length() >= end) {
			return str.replaceAll(str.substring(begin, end), "********");
		} else {
			return str;
		}
	}

	/**
	 * 检测字符串是否包含数字、字母、大写字母、特殊符号
	 * 
	 * @param str
	 * @return 返回1表示含有数字，返回2表示含有数字+小写字母，返回3表示含有数字+小写字母+大写字母，返回4表示含有数字+小写字母+大写字母+
	 *         特殊符号
	 */
	public static int checkInput(String str) {
		int num = 0;
		num = Pattern.compile("\\d").matcher(str).find() ? num + 1 : num;
		num = Pattern.compile("[a-z]").matcher(str).find() ? num + 1 : num;
		num = Pattern.compile("[A-Z]").matcher(str).find() ? num + 1 : num;
		num = Pattern.compile("[-.!@#$%^&*()+?><]").matcher(str).find() ? num + 1
				: num;
		return num;
	}

	/**
	 * 对象转数组
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] object2Byte(Object obj) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 数组转对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object byte2Object(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 
	 * @param s
	 *            8位字符
	 * @return 16位数字串
	 */
	public static long StringTolong(String s) {
		s = s.toUpperCase();
		StringBuffer stringBuffer = new StringBuffer();
		for (byte b : s.getBytes()) {
			stringBuffer.append(b);
		}
		return Long.valueOf(stringBuffer.toString());
	}

	/**
	 * 十进制数字串,转字符串
	 * 
	 * @param l
	 *            16位
	 * @return 小写字符串
	 */
	public static String LongToString(Long l) {
		String lstr = l + "";
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 16;) {
			i += 2;
			String letterNum = lstr.substring(i - 2, i);
			stringBuffer.append(String.valueOf((char) Integer
					.valueOf(letterNum).intValue()));
		}
		return stringBuffer.toString().toLowerCase();
	}

	/**
	 * 计算两个日期相隔天数
	 * 
	 * @param smdate
	 *            小日期
	 * @param bdate
	 *            大日期
	 * @return
	 */
	public static long getBetweenDate(Date smdate, Date bdate) {
		long between_days = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return between_days;
	}

	/**
	 * 拼接二维码到海报
	 * 
	 * @param pressImg
	 *            二维码
	 * @param targetImg
	 *            海报模板
	 * @param x
	 *            坐标x轴
	 * @param y
	 *            坐标Y轴
	 * @param w
	 *            生成二维码宽度
	 * @param h
	 *            生成二维码高度
	 * @param alpha
	 */
	public final static void poster(String pressImg, String targetImg,
			String outPath, Integer x, Integer y, Integer w, Integer h,
			float alpha) {
		try {
			// 目标文件
			Image theImg = Toolkit.getDefaultToolkit().getImage(targetImg);
			theImg.flush();
			BufferedImage bis = toBufferedImage(theImg);
			int width = theImg.getWidth(null);
			int height = theImg.getHeight(null);
			bis = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bis.createGraphics();
			g.drawImage(theImg, 0, 0, width, height, null);

			// 水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha / 100));
			if (w == null) {
				w = src_biao.getWidth(null);
			}
			if (h == null) {
				h = src_biao.getHeight(null);
			}

			if (x == null) {
				x = (width - w) / 2;
			}
			if (y == null) {
				y = height - h - 15; // 下方15像素处
			}
			g.drawImage(src_biao, x, y, w, w, null);
			// 水印文件结束
			g.dispose();
			FileOutputStream out = new FileOutputStream(outPath);
			ImageIO.write(bis, "JPEG", out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成授权证书
	 * 
	 * @param qcodePath
	 * @param filePath
	 * @param outPath
	 * @param text
	 * @param markContentColor
	 * @param pos
	 * @param qualNum
	 * @return
	 */
	public static boolean authImage(String filePath,
			String outPath, List<Location> list, String markContentColor) {
		try {
			Image theImg = Toolkit.getDefaultToolkit().getImage(filePath);
			theImg.flush();
			BufferedImage bis = toBufferedImage(theImg);
			int width = bis.getWidth(null);
			int height = bis.getHeight(null);
			BufferedImage bimage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bimage.createGraphics();
			g.setColor(getColor(markContentColor));
			g.setBackground(Color.white);
			g.drawImage(theImg, 0, 0, null);
			
			for(Location l:list){
				Font font = new Font("宋体", Font.PLAIN, 12);// 大名字
				g.setFont(font);
				if(StringUtils.isNotBlank(l.getName())){
					g.drawString(l.getName(), l.getX(), l.getY());// 大名字
				}
			}

			g.dispose();
			FileOutputStream out = new FileOutputStream(outPath);
			ImageIO.write(bimage, "JPEG", out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean authImage1(String filePath,
			String outPath, String text, String markContentColor) {
		try {
			Image theImg = Toolkit.getDefaultToolkit().getImage(filePath);
			theImg.flush();
			BufferedImage bis = toBufferedImage(theImg);
			int width = bis.getWidth(null);
			int height = bis.getHeight(null);
			BufferedImage bimage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bimage.createGraphics();
			g.setColor(getColor(markContentColor));
			g.setBackground(Color.white);
			g.drawImage(theImg, 0, 0, null);
			
			Font font = new Font("宋体", Font.ROMAN_BASELINE, 12);// 大名字
			g.setFont(font);
			g.drawString(text, 245, 146);// 大名字
			
			g.dispose();
			FileOutputStream out = new FileOutputStream(outPath);
			ImageIO.write(bimage, "JPEG", out);
			out.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {

		String filePath = "E:/ewm.jpg";// 二维码
		String auth_temple = "E:/123.jpg";// 授权模板

		String outPath = "E:/bb_new.jpg";// 输出
		String text = "张立波Keeny_bob";// 文字
		String markContentColor = "#000000";// 颜色
		int pos = 6;// 位置
		float qualNum = 100f;// 质量
		authImage1(auth_temple, outPath, "015810742305655001002016225260101653000", markContentColor);

		// poster(filePath, haibao,outPath1, 220, 735, 200, 200, qualNum);//海报
	}
}
