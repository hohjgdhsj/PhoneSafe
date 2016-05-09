package com.example.safephone;

import javax.security.auth.PrivateCredentialPermission;

import com.example.safephone.utils.CacheUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LostFindActivity extends Activity {
	private Context context;
	@ViewInject(R.id.safe_num_tv)
	private TextView safeNum_tv;
	@ViewInject(R.id.protected_iv)
	private ImageView lockIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
		if (CacheUtils.getBoolean(context, CacheUtils.SAFE_PHONE_SET)) {
			// �����򵼵Ľ���
			setContentView(R.layout.activity_lost_find);
			//ע�� ֮��Ҫע��
			ViewUtils.inject(this);
		} else {
			// �����򵼵Ľ���
			Intent intent = new Intent();
			intent.setClass(context, SetUp1Activity.class);
			startActivity(intent);
			finish();
		}
	}

	// ��������������
	public void resetup(View view) {
		Intent intent = new Intent();
		intent.setClass(this, SetUp1Activity.class);
		overridePendingTransition(R.anim.next_enter, R.anim.next_exit);
		startActivity(intent);
		finish();
	}

	// �������ð�ȫ����
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// ��ʼ���ֻ���ȫ����
		if (CacheUtils.getString(getApplicationContext(), CacheUtils.SAFE_NUMBER) != null) {
			 safeNum_tv.setText(CacheUtils.getString(getApplicationContext(),
			 CacheUtils.SAFE_NUMBER));
			 safeNum_tv.setEnabled(false);
		}
		// ��ʼ���Ƿ����ֻ���������
		if (CacheUtils.getBoolean(context, CacheUtils.IS_PROTECT)) {
			lockIv.setImageResource(R.drawable.lock);
		} else {
			lockIv.setImageResource(R.drawable.unlock);
		}
	}
}
