package com.example.safephone.view;

import com.example.safephone.R;
import com.example.safephone.utils.CacheUtils;
import com.example.safephone.utils.Logutil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingStyleView extends RelativeLayout {
	// 把自定义组合控件xml实例化为对象，并且添加到当前对象中
	// 自定义方法 操纵
	public TextView text1;
	public TextView tilte;
	public CheckBox check1;
	private static final String TAG = "SettingView";
	private View rootView;
	private String destitle;// 自己设置的值
	private String deson;
	private String desoff;

	public SettingStyleView(Context context) {
		super(context);
		Logutil.i(TAG, "context");
		init();
	}

	public SettingStyleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
		Logutil.i(TAG, "defStyleAttr");
	}

	// 布局xml 实例化 调用该构造方法
	public SettingStyleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Logutil.i(TAG, "attrs");
		init();
		// 取得自定义的属性值
		/*
		 * int length=attrs.getAttributeCount(); for (int index = 0; index <
		 * length; index++) { Logutil.i(TAG, attrs.getAttributeValue(index));
		 * 
		 * }
		 */
		// 拿到自己设置的内容
		destitle = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.example.safephone", "setting_title");
		deson = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.example.safephone", "des_on");
		desoff = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.example.safephone", "des_off");
		// 初始化 自定义空间的属性
		tilte.setText(destitle);
		if (getChecked()) {
			check1.setChecked(true);
			text1.setText(deson);
		} else {
			check1.setChecked(false);
			text1.setText(desoff);
		}
	}

	// 初始化自定义组合控件的界面
	private void init() {
		// TODO Auto-generated method stub
		rootView = View.inflate(getContext(), R.layout.setting_view, this);
		text1 = (TextView) rootView.findViewById(R.id.setting_text1);
		tilte = (TextView) rootView.findViewById(R.id.text_tv);
		check1 = (CheckBox) rootView.findViewById(R.id.setting_check1);
	}

	// 设置组合控件的标题
	public void setTitle(String title) {
		tilte.setText(destitle);
	}

	// 设置描述
	public void setDes(String des) {
		text1.setText(des);
	}

	// 设置复选框
	public void setChecked(boolean ischecked) {
		check1.setChecked(ischecked);
		if (ischecked) {
			text1.setText(deson);
		} else {
			text1.setText(desoff);
		}
	}

	// 取得组合控件的状态
	public boolean getChecked() {
		return check1.isChecked();
	}
}
