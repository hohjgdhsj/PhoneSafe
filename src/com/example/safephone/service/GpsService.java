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

public class GpsService extends Service {
	public LocationManager locationManager;

	@Override
	public void onCreate() {
		// 拿到位置服务
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// 取得所有的定位方式
		List<String> allProviders = locationManager.getAllProviders();
		for (String provider : allProviders) {
			// 输出当前手机支持的所有定位方式
			System.out.println();
		}
		Criteria criteria = new Criteria();// 设置定位设置
		criteria.setAltitudeRequired(true);// 支持海拔定位，基本就是gps定位
		// 获取最好的定位方式
		String bestProvider = locationManager.getBestProvider(criteria, true);// enable是否可用
																				// 一般为true
		// 请求位置更新
		/**
		 * 1，请求方式 2，最新的时间间隔，以秒为单位 3，最小的距离 4，位置监听器
		 */
		locationManager.requestLocationUpdates(bestProvider, 2000, 10, new MyLocationListener());
	}

	private class MyLocationListener implements LocationListener {
		// 位置改变，回调该方法
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			// 取得精度和纬度
			double latitude = location.getLatitude();
			double longtitude = location.getLongitude();
			System.out.println("纬度" + longtitude + "精度" + latitude);
		//可以保存
			CacheUtils.putString(GpsService.this, "latitude", ""+latitude);
			CacheUtils.putString(GpsService.this, "longtitude", ""+longtitude);
		}

		// 状态改变，回调该方法 比如wifi 是否可用
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		// 定位不可用时回调
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		// 可用时回调
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

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
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
