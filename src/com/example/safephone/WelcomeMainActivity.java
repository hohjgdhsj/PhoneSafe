package com.example.safephone;

import com.example.safephone.utils.CacheUtils;
import com.example.safephone.utils.ToastUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class WelcomeMainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ImageView imageView = (ImageView) findViewById(R.id.welcome_iv);
		/**
		 * animation conbine
		 */
		AnimationSet set = new AnimationSet(false);
		// 边旋转边放大
		RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(3000);// 设置时间为两秒
		rotateAnimation.setFillAfter(true);// 设置最终状态为填充效果
		set.addAnimation(rotateAnimation);// 将旋转动画添加到动画集合中
		/**
		 * 
		 */
		ScaleAnimation scaleAnimation=new ScaleAnimation(
				0, 1, 
				0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(3000);// 
		rotateAnimation.setFillAfter(true);// 设置最终状态为填充效果
		set.addAnimation(scaleAnimation);// 将缩放动画添加到动画集合中
		/**
		 * 第三个动画来自xml
		 */
		Animation animation=AnimationUtils.loadAnimation(this, R.anim.alpha);
		set.addAnimation(animation);// 将透明度动画添加到动画集合中
		set.setAnimationListener(new AnimationListener() {
			//begin
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			//repeat
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			//end
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				ToastUtils.show(getApplicationContext(), "欢迎来到德莱联盟");
				//the animation end enter the guideActivity
				
				if(CacheUtils.getBoolean(getApplicationContext(), "is_first_use", true)){Intent intent=new Intent();
				intent.setClass(getApplicationContext(), ActivityGuide.class);
				startActivity(intent);}
				else {
					Intent intent2 = new Intent();
					intent2.setClass(getApplicationContext(), SplashActivity.class);
					startActivity(intent2);
				}
				finish();
			}
		});
		imageView.startAnimation(set);		
	}

}
