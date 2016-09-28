package com.example.safephone.fragment;

import com.example.safephone.R;
import com.example.safephone.utils.CacheUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProtectFragment extends Fragment {
	private Context context;
	private View rootView;// 片段的界面视图的根节点对象
	@ViewInject(R.id.safe_num_tv)
	private TextView safeNum_tv;
	@ViewInject(R.id.protected_iv)
	private ImageView lockIv;
	@OnClick(R.id.reset)
	public void resetup(View view){
		//进入到设置片段
		ProtectSetupFragment protextsetupFragment=new ProtectSetupFragment();
		getFragmentManager().beginTransaction().replace(R.id.content, protextsetupFragment,"protextsetupFragment").commit();
	}  

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_protect, container, false);
		ViewUtils.inject(this, rootView);
		return rootView;	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	// 重新设置安全号码
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// 初始胡手机安全号码
		if (CacheUtils.getString(context, CacheUtils.SAFE_NUMBER) != null) {
			safeNum_tv.setText(CacheUtils.getString(context, CacheUtils.SAFE_NUMBER));
			safeNum_tv.setEnabled(false);
		}
		// 初始化是否开启手机防盗功能
		if (CacheUtils.getBoolean(context, CacheUtils.IS_PROTECT)) {
			lockIv.setImageResource(R.drawable.lock);
		} else {
			lockIv.setImageResource(R.drawable.unlock);
		}
	}
}
