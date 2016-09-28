package com.example.safephone.service;

import java.util.List;

import com.example.safephone.utils.CacheUtils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * 监听来电信息 并且显示来电号码的归属地
 */

public class AddressService extends Service {
	private TelephonyManager telephonyManager;// 电话管理器
	private MyPhoneStateListener myPhoneStateListener;

	@Override
	public void onCreate() {
		telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		myPhoneStateListener = new MyPhoneStateListener();
		// listener the phone state
		telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
//注销来电显示号码归属地查询的服务
		telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_NONE);
	
	}

	private class MyPhoneStateListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE://空闲状态

				break;
			case TelephonyManager.CALL_STATE_OFFHOOK://挂起（通话）状态

				break;
			case TelephonyManager.CALL_STATE_RINGING://响铃状态

				break;
			default:
				break;
			}
		}
	}

}
