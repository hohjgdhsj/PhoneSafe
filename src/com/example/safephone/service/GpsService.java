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
		// �õ�λ�÷���
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// ȡ�����еĶ�λ��ʽ
		List<String> allProviders = locationManager.getAllProviders();
		for (String provider : allProviders) {
			// �����ǰ�ֻ�֧�ֵ����ж�λ��ʽ
			System.out.println();
		}
		Criteria criteria = new Criteria();// ���ö�λ����
		criteria.setAltitudeRequired(true);// ֧�ֺ��ζ�λ����������gps��λ
		// ��ȡ��õĶ�λ��ʽ
		String bestProvider = locationManager.getBestProvider(criteria, true);// enable�Ƿ����
																				// һ��Ϊtrue
		// ����λ�ø���
		/**
		 * 1������ʽ 2�����µ�ʱ����������Ϊ��λ 3����С�ľ��� 4��λ�ü�����
		 */
		locationManager.requestLocationUpdates(bestProvider, 2000, 10, new MyLocationListener());
	}

	private class MyLocationListener implements LocationListener {
		// λ�øı䣬�ص��÷���
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			// ȡ�þ��Ⱥ�γ��
			double latitude = location.getLatitude();
			double longtitude = location.getLongitude();
			System.out.println("γ��" + longtitude + "����" + latitude);
		//���Ա���
			CacheUtils.putString(GpsService.this, "latitude", ""+latitude);
			CacheUtils.putString(GpsService.this, "longtitude", ""+longtitude);
		}

		// ״̬�ı䣬�ص��÷��� ����wifi �Ƿ����
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		// ��λ������ʱ�ص�
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		// ����ʱ�ص�
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
