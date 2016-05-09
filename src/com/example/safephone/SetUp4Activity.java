package com.example.safephone;

import com.example.safephone.utils.CacheUtils;
import com.example.safephone.utils.ToastUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class SetUp4Activity extends BaseActivity {
	@ViewInject(R.id.step4_check)
	private CheckBox checkbox;// 复选框

	/*
	 * @OnClick(R.id.step4_check) // 设置点击监听事件 public void test_CheckBox(View
	 * view) { // 执行的业务 }
	 */

	// 绑定状态改变的监听
	@OnCompoundButtonCheckedChange(R.id.step4_check)
	public void test_CheckBox(CompoundButton buttonView, boolean isChecked) {
		// 执行的业务
		if (isChecked) {
			CacheUtils.putBoolean(getApplicationContext(), CacheUtils.IS_PROTECT, true);
			ToastUtils.show(getApplicationContext(), "开启手机防盗");
		} else {
			CacheUtils.putBoolean(getApplicationContext(), CacheUtils.IS_PROTECT, false);
			ToastUtils.show(getApplicationContext(), "关闭手机防盗");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup4);
		ViewUtils.inject(this);
		setTitle("4  设置是否开启保护");
		// 初始化控件
		if (CacheUtils.getBoolean(getApplicationContext(), CacheUtils.IS_PROTECT)) {
			checkbox.setChecked(true);
		} else {
			checkbox.setChecked(false);
		}
	}


	@Override
	public void nextActivity() {
		// TODO Auto-generated method stub
		ToastUtils.show(getApplicationContext(), "设置完成");
		CacheUtils.putBoolean(getApplicationContext(), CacheUtils.SAFE_PHONE_SET, true);
		finish();
	}

	@Override
	public void preActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), SetUp3Activity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		finish();
	}
}
