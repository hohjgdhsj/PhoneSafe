package com.example.safephone.receiver;

import com.example.safephone.utils.CacheUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.util.Log;

public class BootCompliteReceiver extends BroadcastReceiver {
	// 运行在主线程且声明周期比较短
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// 取的当前sim卡的串号
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String phonenumber = telephonyManager.getDeviceId();
		if (!phonenumber.equals(CacheUtils.getString(context, CacheUtils.SIM))) {
			// 不等于 就发短信给安全号码
			String safe_phone_number = CacheUtils.getString(context, CacheUtils.SAFE_NUMBER);
			// 发送当前位置
			double lng = 0;
			double lat = 0;
			LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			Location loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (loc != null) {
				lat = loc.getLatitude();
				lng = loc.getLongitude();
			}
			// 发短信
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(safe_phone_number, null, "i am lost" + lat + lng, null, null);
			// 发短信需要扣费
		}
	}

}
