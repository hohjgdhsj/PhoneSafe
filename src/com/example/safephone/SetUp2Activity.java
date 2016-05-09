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
	// 注解式声明 ,取代用findviewbyid来引用
	@ViewInject(R.id.update4)
	private SettingView bindsv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 首先要引入当前activity关联的布局控件对象，再注入到已经声明注解的控件中
		setContentView(R.layout.activity_setup2);
		// 采用Xutil框架的viewUtils 模块注入 控件的注入
		ViewUtils.inject(this);
		setTitle("2 请绑定sim卡");
		// 对绑定sim控件 SettingView初始化
		if (CacheUtils.getString(getApplicationContext(), CacheUtils.SIM).equals("")) {
			bindsv.setChecked(false);
		} else {
			bindsv.setChecked(true);
		}
		// 对SettingView控件设置点击监听事件，实现对sim卡绑定和解密
		bindsv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bindsv.getChecked()) {
					bindsv.setChecked(false);
					CacheUtils.putString(getApplicationContext(), CacheUtils.SIM, "");
				} else {
					bindsv.setChecked(true);
					// 获的系统提供服务来取得电话管理服务
					TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
					String phonenumber = telephonyManager.getDeviceId();//取得当前sim号
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
			ToastUtils.show(getApplicationContext(), "请先绑定sim卡");
		}
	}
}
