package com.example.safephone;

import com.example.safephone.utils.CacheUtils;
import com.example.safephone.utils.ToastUtils;
import com.example.safephone.view.SettingView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SetUp2Activity extends BaseActivity {
	// ע��ʽ���� ,ȡ����findviewbyid������
	@ViewInject(R.id.update4)
	private SettingView bindsv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ����Ҫ���뵱ǰactivity�����Ĳ��ֿؼ�������ע�뵽�Ѿ�����ע��Ŀؼ���
		setContentView(R.layout.activity_setup2);
		// ����Xutil��ܵ�viewUtils ģ��ע�� �ؼ���ע��
		ViewUtils.inject(this);
		setTitle("2 ���sim��");
		// �԰�sim�ؼ� SettingView��ʼ��
		if (CacheUtils.getString(getApplicationContext(), CacheUtils.SIM).equals("")) {
			bindsv.setChecked(false);
		} else {
			bindsv.setChecked(true);
		}
		// ��SettingView�ؼ����õ�������¼���ʵ�ֶ�sim���󶨺ͽ���
		bindsv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bindsv.getChecked()) {
					bindsv.setChecked(false);
					CacheUtils.putString(getApplicationContext(), CacheUtils.SIM, "");
				} else {
					bindsv.setChecked(true);
					// ���ϵͳ�ṩ������ȡ�õ绰�������
					TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
					String phonenumber = telephonyManager.getDeviceId();//ȡ�õ�ǰsim��
					ToastUtils.show(getApplicationContext(), phonenumber);
					CacheUtils.putString(getApplicationContext(), CacheUtils.SIM, phonenumber);
				}
			}
		});
	}



	@Override
	public void preActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), SetUp1Activity.class);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		finish();
	}

	@Override
	public void nextActivity() {
		// TODO Auto-generated method stub
		if(bindsv.getChecked()){
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), SetUp3Activity.class);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		finish();
		}else{
			ToastUtils.show(getApplicationContext(), "���Ȱ�sim��");
		}
	}
}
