package com.example.safephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SetUp1Activity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup1);
		setTitle("1  ��ӭʹ���ֻ�����");
	}


	@Override
	public void nextActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), SetUp2Activity.class);
		startActivity(intent);
		/**
		 * ��дActivity��ƽ�ƶ��� 
		 * enterAnim ����Ķ��� 
		 * exitAnim �˳��Ķ���
		 */
		overridePendingTransition(R.anim.next_enter, R.anim.next_exit);
		finish();
	}


	@Override
	public void preActivity() {
		// TODO Auto-generated method stub
		
	}

}
