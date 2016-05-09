package com.example.safephone.utils;

import android.R.string;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 通sharedpreference缓存数据到xml文件中
 *
 *
 * @author Jay-Tang
 *
 */
public class CacheUtils {
	public static final String CONFIG_SP = "config_sp";// config_sp.xml文件
														// 存放位置：/data/data<包名>/shared_prefes
	public static final String APK_UPDATE = "apk_update";// 检测版本信息
	public static final String APK_SUO = "apk_suo";// 检测是否上锁
	public static final String IS_FIRST_USE = "is_first_use";//是否第一次用
	public static final String SAFE_PHONE_SET="safe_phone_set";//是否上锁
	public static final String SIM="";//sim卡的序列号，串号
	public static final String SAFE_NUMBER = "safe_number";//存放安全号码的值
	public static final String IS_PROTECT = "is_protect";
	private static SharedPreferences mSharedPreferences;
	private static SharedPreferences getPreferencs(Context context) {
		if (mSharedPreferences == null) {
			return context.getSharedPreferences(CONFIG_SP, Context.MODE_PRIVATE);
		}
		return mSharedPreferences;
	}

	// 保存布尔数据
	public static void putBoolean(Context context, String key, boolean value) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		sPreferences.edit().putBoolean(key, value).commit();
	}

	// 取布尔数据，返回的默认值是false
	public static boolean getBoolean(Context context, String key) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		return sPreferences.getBoolean(key, false);
	}

	// 取布尔数据，返回的默认值是false
	public static boolean getBoolean(Context context, String key, boolean value) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		return sPreferences.getBoolean(key, value);
	}

	// 保存字符串
	public static void putString(Context context, String key, String value) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		sPreferences.edit().putString(key, value).commit();
	}

	// 取布尔数据，返回的默认值是null
	public static String getString(Context context, String key) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		return sPreferences.getString(key, null);
	}
	// 取字符串数据 ,返回的是传递过来的值
		public static String getString(Context context,String key,String defvalue) {
			SharedPreferences sp=getPreferencs(context);
			return sp.getString(key, defvalue); 
		}
		
}
