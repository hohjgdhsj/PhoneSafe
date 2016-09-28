package com.example.safephone.page;

import com.example.safephone.R;
import com.lidroid.xutils.ViewUtils;

import android.content.Context;
import android.view.View;

public class SetupPage3 extends BasePage {

	public SetupPage3(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		rootView = View.inflate(context, R.layout.layout_setup3, null);
		ViewUtils.inject(this,rootView);
		return rootView;
	}

	@Override
	public View initData() {

		return null;
	}

}
