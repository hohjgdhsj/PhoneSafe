package com.example.safephone;

import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {
	private TextView titleTextView;

	// ��һ��Activity
	public void next(View view) {
		nextActivity();
	}

	// ��һ��Activity
	public void back(View view) {
		preActivity();
	}

	public abstract void nextActivity();

	public abstract void preActivity();

	// ���ñ���
	public void setTitle(String title) {
		titleTextView = (TextView) findViewById(R.id.title_bar);
		titleTextView.setText(title);
	}

	// �������� �����ؼ� �����ٵ�ǰ��avtivity�����Ƿ�����һ��activity
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// ��ط��ؼ�
			preActivity();
			return true;// �Ͳ������ onbanckpressed �Ӷ��������ٵ�ǰactivity
		}
		return super.onKeyDown(keyCode, event);
	}
}
