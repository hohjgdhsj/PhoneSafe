package com.example.safephone.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.safephone.R;
import com.example.safephone.page.BasePage;
import com.example.safephone.page.SetupPage1;
import com.example.safephone.page.SetupPage2;
import com.example.safephone.page.SetupPage3;
import com.example.safephone.page.SetupPage4;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProtectSetupFragment extends Fragment {
	private Context context;
	private View rootView;// Ƭ�εĽ�����ͼ�ĸ��ڵ����
	private List<BasePage> pageList;

	@ViewInject(R.id.title_bar)
	private TextView titlebar;// ������
	@ViewInject(R.id.setup_vp)
	private ViewPager mViewPage;// viewpager
	@ViewInject(R.id.dot_lly)
	private LinearLayout dot_lly;// �ĸ��������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getActivity();// ÿ��Ƭ�ζ������и�activity
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_protect_setup, container, false);
		ViewUtils.inject(this, rootView);

		// ���ز���
		// 1.��ʼ����

		// 2.��ʼ��Viewpager
		initpage();
		// װ������
		mViewPage.setAdapter(new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return pageList.size();
			}

			// ��ʼ��
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(pageList.get(position).getRootView());
				return pageList.get(position).getRootView();
			}

			// ����
			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView((View) object);
			}
		});
		// ��viewpage���ü���
		mViewPage.setOnPageChangeListener(new OnPageChangeListener() {
			// ��ҳ�汻ѡ�е�ʱ��ص��÷���
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				// 1.��������ҳ������
				//Ҫ����BasePage.initData()
				// 2.��Ƭ�εı���
				// 3.�����ĸ����״̬

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		// 3.�����ĸ���

		return rootView;
	}

	private void initpage() {
		pageList = new ArrayList<BasePage>();
		BasePage page1 = new SetupPage1(context);
		BasePage page2 = new SetupPage2(context);
		BasePage page3 = new SetupPage3(context);
		BasePage page4 = new SetupPage4(context);
		pageList.add(page1);
		pageList.add(page2);
		pageList.add(page3);
		pageList.add(page4);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

}
