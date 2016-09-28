package com.example.safephone.page;

import com.example.safephone.R;
import com.lidroid.xutils.ViewUtils;

import android.content.Context;
import android.view.View;

public class SetupPage2 extends BasePage {

	public SetupPage2(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		rootView = View.inflate(context, R.layout.layout_setup2, null);
		ViewUtils.inject(this,rootView);
		return rootView;
	}

	@Override
	public View initData() {

		return null;
	}

}
