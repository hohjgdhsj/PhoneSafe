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
	// ���������߳����������ڱȽ϶�
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// ȡ�ĵ�ǰsim���Ĵ���
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String phonenumber = telephonyManager.getDeviceId();
		if (!phonenumber.equals(CacheUtils.getString(context, CacheUtils.SIM))) {
			// ������ �ͷ����Ÿ���ȫ����
			String safe_phone_number = CacheUtils.getString(context, CacheUtils.SAFE_NUMBER);
			// ���͵�ǰλ��
			double lng = 0;
			double lat = 0;
			LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			Location loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (loc != null) {
				lat = loc.getLatitude();
				lng = loc.getLongitude();
			}
			// ������
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(safe_phone_number, null, "i am lost" + lat + lng, null, null);
			// ��������Ҫ�۷�
		}
	}

}
