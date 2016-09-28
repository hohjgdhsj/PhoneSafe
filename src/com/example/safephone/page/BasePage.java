package com.example.safephone.page;

import android.content.Context;
import android.view.View;

public abstract class BasePage {
public Context context;
public View rootView;//����ҳ�Ľ�����ͼ����

public BasePage(Context context) {
this.context=context;
rootView=initView();//�ڹ������ʱ����ʼ������
}
public abstract View initView();//��ʼ������
public abstract View initData();//��ʼ������
public View getRootView(){//���ص�������ҳ�Ľ�����ͼ����
	return rootView;
}
}
