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
	// ���Զ�����Ͽؼ�xmlʵ����Ϊ���󣬲�����ӵ���ǰ������
	// �Զ��巽�� ����
	public TextView text1;
	public TextView tilte;
	public CheckBox check1;
	private static final String TAG = "SettingView";
	private View rootView;
	private String destitle;// �Լ����õ�ֵ
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

	// ����xml ʵ���� ���øù��췽��
	public SettingStyleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Logutil.i(TAG, "attrs");
		init();
		// ȡ���Զ��������ֵ
		/*
		 * int length=attrs.getAttributeCount(); for (int index = 0; index <
		 * length; index++) { Logutil.i(TAG, attrs.getAttributeValue(index));
		 * 
		 * }
		 */
		// �õ��Լ����õ�����
		destitle = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.example.safephone", "setting_title");
		deson = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.example.safephone", "des_on");
		desoff = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.example.safephone", "des_off");
		// ��ʼ�� �Զ���ռ������
		tilte.setText(destitle);
		if (getChecked()) {
			check1.setChecked(true);
			text1.setText(deson);
		} else {
			check1.setChecked(false);
			text1.setText(desoff);
		}
	}

	// ��ʼ���Զ�����Ͽؼ��Ľ���
	private void init() {
		// TODO Auto-generated method stub
		rootView = View.inflate(getContext(), R.layout.setting_view, this);
		text1 = (TextView) rootView.findViewById(R.id.setting_text1);
		tilte = (TextView) rootView.findViewById(R.id.text_tv);
		check1 = (CheckBox) rootView.findViewById(R.id.setting_check1);
	}

	// ������Ͽؼ��ı���
	public void setTitle(String title) {
		tilte.setText(destitle);
	}

	// ��������
	public void setDes(String des) {
		text1.setText(des);
	}

	// ���ø�ѡ��
	public void setChecked(boolean ischecked) {
		check1.setChecked(ischecked);
		if (ischecked) {
			text1.setText(deson);
		} else {
			text1.setText(desoff);
		}
	}

	// ȡ����Ͽؼ���״̬
	public boolean getChecked() {
		return check1.isChecked();
	}
}
