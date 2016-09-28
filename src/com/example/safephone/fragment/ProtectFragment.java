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
	private View rootView;// Ƭ�εĽ�����ͼ�ĸ��ڵ����
	@ViewInject(R.id.safe_num_tv)
	private TextView safeNum_tv;
	@ViewInject(R.id.protected_iv)
	private ImageView lockIv;
	@OnClick(R.id.reset)
	public void resetup(View view){
		//���뵽����Ƭ��
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

	// �������ð�ȫ����
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// ��ʼ���ֻ���ȫ����
		if (CacheUtils.getString(context, CacheUtils.SAFE_NUMBER) != null) {
			safeNum_tv.setText(CacheUtils.getString(context, CacheUtils.SAFE_NUMBER));
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
