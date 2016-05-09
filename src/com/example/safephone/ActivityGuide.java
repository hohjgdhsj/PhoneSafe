package com.example.safephone;

import java.util.ArrayList;
import java.util.List;

import com.example.safephone.utils.CacheUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat.MediaItem.Flags;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityGuide extends Activity {
	private List<ImageView> mPageList;
	private Context context;
	private Button btn;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guidelayout);
		ViewPager mGuideViewPager = (ViewPager) findViewById(R.id.viewpager);
		btn = (Button) findViewById(R.id.guide_enter_btn);
		// page list
		context = this;
		// init page
		initpage();
		// 2.initdata by adapter
		mGuideViewPager.setAdapter(new PagerAdapter() {
			// is the view from object or from fragment
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			// view items count
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mPageList == null ? 0 : mPageList.size();
			}

			// DESTORY the view items
			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				// TODO Auto-generated method stub
				container.removeView(mPageList.get(position));
			}

			/**
			 * init view items pass position container ViewGroup ViewPager
			 */
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(mPageList.get(position));
				return mPageList.get(position);
			}
		});
		// 3、对viewpage进行监听
		mGuideViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			//当页面被选择则回调该方法
			@Override
			public void onPageSelected(int position) {
				if(position==(mPageList.size()-1)){
					btn.setVisibility(View.VISIBLE);
				}else{
					btn.setVisibility(View.GONE);
				}
			}
			//页面滚动回调该方法
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			//当页面滚动状态改变
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}

	//
	private void initpage() {
		// TODO Auto-generated method stub
		mPageList = new ArrayList<ImageView>();
		ImageView imageView = new ImageView(context);
		imageView.setBackgroundResource(R.drawable.guide_1);
		mPageList.add(imageView);
		ImageView imageView2 = new ImageView(context);
		imageView2.setBackgroundResource(R.drawable.guide_2);
		mPageList.add(imageView2);
		ImageView imageView3 = new ImageView(context);
		imageView3.setBackgroundResource(R.drawable.guide_3);
		mPageList.add(imageView3);

	}

	public void entersystem(View view) {
		CacheUtils.putBoolean(context,"is_first_use",false);
		Intent intent = new Intent(this, SplashActivity.class);
		startActivity(intent);
		finish();
	}
}
