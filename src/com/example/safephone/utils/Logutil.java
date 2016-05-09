package com.example.safephone.utils;

import android.util.Log;

public class Logutil {
	private static boolean is_debug = true;

	public static void i(String tag, String msg) {
		if (is_debug) {
			Log.i(tag, msg);
		}
	}
	public static void v(String tag, String msg) {
		if (is_debug) {
			Log.v(tag, msg);
		}
	}
	public static void d(String tag, String msg) {
		if (is_debug) {
			Log.d(tag, msg);
		}
	}
}
