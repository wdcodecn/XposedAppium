package com.zhenxi.Superappium.utils;

import static com.zhenxi.Superappium.SuperAppium.TAG;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CLogUtils {


	//�涨ÿ����ʾ�ĳ���
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
	 * log��� 4*1024 ���� ��� ���� ���Խ�� �������
	 *
	 * @param TAG
	 * @param msg
	 */
	private static void InfiniteLog(String TAG, String msg) {
		int strLength = msg.length();
		int start = 0;
		int end = LOG_MAXLENGTH;
		for (int i = 0; i < 100; i++) {
			//ʣ�µ��ı����Ǵ��ڹ涨����������ظ���ȡ�����
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
