package com.zhenxi.Superappium.utils;

import static com.zhenxi.Superappium.SuperAppium.TAG;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CLogUtils {


	//规定每段显示的长度
	private static int LOG_MAXLENGTH = 2000;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);


	public static String getNow() {
		return dateFormat.format(new Date()) + " ";
	}

	public static void e(String msg) {
		InfiniteLog(TAG, msg);
	}


	public static void e(String TAG, String msg) {
		InfiniteLog(TAG, msg);
	}

	/**
	 * log最多 4*1024 长度 这个 方法 可以解决 这个问题
	 *
	 * @param TAG
	 * @param msg
	 */
	private static void InfiniteLog(String TAG, String msg) {
		int strLength = msg.length();
		int start = 0;
		int end = LOG_MAXLENGTH;
		for (int i = 0; i < 100; i++) {
			//剩下的文本还是大于规定长度则继续重复截取并输出
			if (strLength > end) {
				Log.e(TAG + i, getNow() + msg.substring(start, end));
				start = end;
				end = end + LOG_MAXLENGTH;
			} else {
				Log.e(TAG, getNow() + msg.substring(start, strLength));
				break;
			}
		}
	}


}
