package com.example.safephone.utils;

import android.R.string;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * ͨsharedpreference�������ݵ�xml�ļ���
 *
 *
 * @author Jay-Tang
 *
 */
public class CacheUtils {
	public static final String CONFIG_SP = "config_sp";// config_sp.xml�ļ�
														// ���λ�ã�/data/data<����>/shared_prefes
	public static final String APK_UPDATE = "apk_update";// ���汾��Ϣ
	public static final String APK_SUO = "apk_suo";// ����Ƿ�����
	public static final String IS_FIRST_USE = "is_first_use";//�Ƿ��һ����
	public static final String SAFE_PHONE_SET="safe_phone_set";//�Ƿ�����
	public static final String SIM="";//sim�������кţ�����
	public static final String SAFE_NUMBER = "safe_number";//��Ű�ȫ�����ֵ
	public static final String IS_PROTECT = "is_protect";
	private static SharedPreferences mSharedPreferences;
	private static SharedPreferences getPreferencs(Context context) {
		if (mSharedPreferences == null) {
			return context.getSharedPreferences(CONFIG_SP, Context.MODE_PRIVATE);
		}
		return mSharedPreferences;
	}

	// ���沼������
	public static void putBoolean(Context context, String key, boolean value) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		sPreferences.edit().putBoolean(key, value).commit();
	}

	// ȡ�������ݣ����ص�Ĭ��ֵ��false
	public static boolean getBoolean(Context context, String key) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		return sPreferences.getBoolean(key, false);
	}

	// ȡ�������ݣ����ص�Ĭ��ֵ��false
	public static boolean getBoolean(Context context, String key, boolean value) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		return sPreferences.getBoolean(key, value);
	}

	// �����ַ���
	public static void putString(Context context, String key, String value) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		sPreferences.edit().putString(key, value).commit();
	}

	// ȡ�������ݣ����ص�Ĭ��ֵ��null
	public static String getString(Context context, String key) {
		// TODO Auto-generated method stub
		SharedPreferences sPreferences = getPreferencs(context);
		return sPreferences.getString(key, null);
	}
	// ȡ�ַ������� ,���ص��Ǵ��ݹ�����ֵ
		public static String getString(Context context,String key,String defvalue) {
			SharedPreferences sp=getPreferencs(context);
			return sp.getString(key, defvalue); 
		}
		
}
