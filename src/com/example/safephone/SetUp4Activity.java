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
	private CheckBox checkbox;// ��ѡ��

	/*
	 * @OnClick(R.id.step4_check) // ���õ�������¼� public void test_CheckBox(View
	 * view) { // ִ�е�ҵ�� }
	 */

	// ��״̬�ı�ļ���
	@OnCompoundButtonCheckedChange(R.id.step4_check)
	public void test_CheckBox(CompoundButton buttonView, boolean isChecked) {
		// ִ�е�ҵ��
		if (isChecked) {
			CacheUtils.putBoolean(getApplicationContext(), CacheUtils.IS_PROTECT, true);
			ToastUtils.show(getApplicationContext(), "�����ֻ�����");
		} else {
			CacheUtils.putBoolean(getApplicationContext(), CacheUtils.IS_PROTECT, false);
			ToastUtils.show(getApplicationContext(), "�ر��ֻ�����");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup4);
		ViewUtils.inject(this);
		setTitle("4  �����Ƿ�������");
		// ��ʼ���ؼ�
		if (CacheUtils.getBoolean(getApplicationContext(), CacheUtils.IS_PROTECT)) {
			checkbox.setChecked(true);
		} else {
			checkbox.setChecked(false);
		}
	}


	@Override
	public void nextActivity() {
		// TODO Auto-generated method stub
		ToastUtils.show(getApplicationContext(), "�������");
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
