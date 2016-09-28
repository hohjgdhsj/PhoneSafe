package com.example.safephone;

import javax.security.auth.PrivateCredentialPermission;

import com.example.safephone.fragment.ProtectFragment;
import com.example.safephone.fragment.ProtectSetupFragment;
import com.example.safephone.utils.CacheUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LostFindActivity extends Activity {
	private Context context;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_lost_find);
		//片段管理器
		FragmentManager fragmentManager=getFragmentManager();
		if (CacheUtils.getBoolean(context, CacheUtils.SAFE_PHONE_SET)) {
			ProtectFragment protextFragment=new ProtectFragment();
			// 添加展示手机防盗界面片段
			fragmentManager.beginTransaction().replace(R.id.content, protextFragment,"ProtectFragment").commit();
		} else {
			// 进入手机防盗设置片段界面
			ProtectSetupFragment protextsetupFragment=new ProtectSetupFragment();
			// 添加展示手机防盗界面片段
			fragmentManager.beginTransaction().replace(R.id.content, protextsetupFragment,"ProtectsetupFragment").commit();
	
		}
	}

	
}
