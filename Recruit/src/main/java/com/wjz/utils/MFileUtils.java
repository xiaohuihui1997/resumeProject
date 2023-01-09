package com.wjz.utils;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Random;

public class MFileUtils {

	private static Random r = new Random();

	// 存储的根目录
	//	public static final String FILE_PATH = "D:/Book/";
	public static final String cover="D:\\resumeFile\\images\\cover\\";
	public static final String oldCover="D:\\resumeFile";

	public static final String FILE_PATH = "D:/resumeFile/resume/";
	public static final String FILE_PATH9 = "\\resume\\";
	public static int moneyType=0;
	public static int adminType=0;


	// 根据文件名生成一个新的文件名
	/**
	 * 当前时间的毫秒数+随机时间
	 *
	 * @param fileName
	 * @return
	 */
	public static String MakeFileName(String fileName) {
		//创建一个文件名
		return fileName = System.currentTimeMillis() + "-" + (r.nextInt(8888) + 1000)
				+ fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 删除文件
	 */
	public static boolean deleteFile(String fileName) {

		return new File(oldCover + fileName).delete();
	}

	public static void setSession(HttpSession session,boolean r,String msg){
		session.setAttribute("r",r);
		session.setAttribute("msg",msg);
	}

}
