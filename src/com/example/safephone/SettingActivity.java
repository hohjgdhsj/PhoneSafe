package com.example.safephone;

import com.example.safephone.utils.CacheUtils;
import com.example.safephone.view.SettingView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SettingActivity extends Activity {
	private SettingView settingview;
	private SettingView settingview2;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		context = this;
		settingview = (SettingView) findViewById(R.id.update2);
		settingview2 = (SettingView) findViewById(R.id.update3);
		 diyinitview();
	}

	private void diyinitview() {

		// TODO Auto-generated method stub
		settingview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (settingview.getChecked()) {
					settingview.setChecked(false);
					CacheUtils.putBoolean(context, CacheUtils.APK_UPDATE, false);
				} else {
					settingview.setChecked(true);
					CacheUtils.putBoolean(context, CacheUtils.APK_UPDATE, true);
				}

			}
		});
		settingview2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (settingview2.getChecked()) {
					settingview2.setChecked(false);
					CacheUtils.putBoolean(context, CacheUtils.APK_UPDATE, false);
					// settingview.text1.setText("要更新");
				} else {
					settingview2.setChecked(true);
					CacheUtils.putBoolean(context, CacheUtils.APK_UPDATE, true);
					// settingview.text1.setText("不要更新");
				}

			}
		});

	}


}
