package com.example.safephone;

import com.example.safephone.utils.CacheUtils;
import com.example.safephone.utils.ToastUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class SetUp3Activity extends BaseActivity {
	@ViewInject(R.id.phonenumber)
	private EditText Phone_edt;// 安全号码的编辑框
	@ViewInject(R.id.choose_contact)
	private TextView choose_contact;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup3);
		ViewUtils.inject(this);
		setTitle("3  设置安全号码");
		// 设置 安全号码的初始化
		Phone_edt.setText(CacheUtils.getString(getApplicationContext(), CacheUtils.SAFE_NUMBER));
		// 选择联系人
		choose_contact.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ToastUtils.show(getApplicationContext(), "选择联系人");
			Intent intent=new Intent();
			intent.setClass(getApplicationContext(), ContactActivity.class);
			startActivityForResult(intent, 4);
		}
	});
	}
//取得返回的结果
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if(data!=null&&requestCode==4){
		//设置安全号码到文本框
		Phone_edt.setText(data.getStringExtra("num"));
	}
	}
	// 选择联系人
/*	public void selectcontact(View view) {
		ToastUtils.show(getApplicationContext(), "选择联系人");
		Intent intent=new Intent();
		intent.setClass(getApplicationContext(), ContactActivity.class);
		startActivityForResult(intent, 4);
	}
*/	

	@Override
	public void nextActivity() {
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(Phone_edt.getText().toString().trim())) {
			ToastUtils.show(getApplicationContext(), "安全号码没有设置");
		}
		// 有值就用sharedpreference来存 安全号码
		else {
			CacheUtils.putString(getApplicationContext(), CacheUtils.SAFE_NUMBER,
					Phone_edt.getText().toString().trim());
			ToastUtils.show(getApplicationContext(), "安全号码已经设置" + Phone_edt.getText().toString().trim());

		}
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), SetUp4Activity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		finish();
	}

	@Override
	public void preActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), SetUp2Activity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		finish();
	}
}
