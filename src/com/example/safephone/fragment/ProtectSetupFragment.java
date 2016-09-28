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
	private View rootView;// 片段的界面视图的根节点对象
	private List<BasePage> pageList;

	@ViewInject(R.id.title_bar)
	private TextView titlebar;// 标题栏
	@ViewInject(R.id.setup_vp)
	private ViewPager mViewPage;// viewpager
	@ViewInject(R.id.dot_lly)
	private LinearLayout dot_lly;// 四个点的容器

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getActivity();// 每个片段都必须有个activity
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_protect_setup, container, false);
		ViewUtils.inject(this, rootView);

		// 加载步骤
		// 1.初始标题

		// 2.初始化Viewpager
		initpage();
		// 装配数据
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

			// 初始化
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(pageList.get(position).getRootView());
				return pageList.get(position).getRootView();
			}

			// 销毁
			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView((View) object);
			}
		});
		// 对viewpage设置监听
		mViewPage.setOnPageChangeListener(new OnPageChangeListener() {
			// 当页面被选中的时候回调该方法
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				// 1.加载设置页的数据
				//要调用BasePage.initData()
				// 2.该片段的标题
				// 3.更新四个点的状态

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

		// 3.设置四个点

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
