package com.example.safephone.page;

import com.example.safephone.R;
import com.lidroid.xutils.ViewUtils;

import android.content.Context;
import android.view.View;

public class SetupPage1 extends BasePage {

	public SetupPage1(Context context) {
		super(context);//���ø���Ĺ��췽��
	}

	@Override
	public View initView() {
		rootView = View.inflate(context, R.layout.layout_setup1, null);
		ViewUtils.inject(this,rootView);
		return rootView;
	}

	@Override
	public View initData() {

		return null;
	}

}
