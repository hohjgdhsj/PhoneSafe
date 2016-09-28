package com.example.safephone.page;

import android.content.Context;
import android.view.View;

public abstract class BasePage {
public Context context;
public View rootView;//设置页的界面视图对象

public BasePage(Context context) {
this.context=context;
rootView=initView();//在构造对象时，初始化界面
}
public abstract View initView();//初始化界面
public abstract View initData();//初始化界面
public View getRootView(){//返回的是设置页的界面视图对象
	return rootView;
}
}
