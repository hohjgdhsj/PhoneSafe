package com.example.safephone;

import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {
	private TextView titleTextView;

	// 下一个Activity
	public void next(View view) {
		nextActivity();
	}

	// 上一个Activity
	public void back(View view) {
		preActivity();
	}

	public abstract void nextActivity();

	public abstract void preActivity();

	// 设置标题
	public void setTitle(String title) {
		titleTextView = (TextView) findViewById(R.id.title_bar);
		titleTextView.setText(title);
	}

	// 监听按键 按返回键 不销毁当前的avtivity，而是返回上一个activity
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 监控返回键
			preActivity();
			return true;// 就不会调用 onbanckpressed 从而不会销毁当前activity
		}
		return super.onKeyDown(keyCode, event);
	}
}
