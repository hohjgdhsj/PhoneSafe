package com.example.safephone;

import com.example.safephone.adapter.HomeAdapter;
import com.example.safephone.utils.CacheUtils;
import com.example.safephone.utils.MD5;
import com.example.safephone.utils.ToastUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

@SuppressLint("InflateParams")
public class HomeActivity extends Activity {
	private GridView gridView;// girdview的视图
	private Context context;// 上下文
	private AlertDialog dialog;
	private AlertDialog dialog2 ;
	private static SharedPreferences mSharedPreferences;
	private static final String SAFE_PASSWORD = "safe_password";
	private EditText other_pwd;// 可看原密码的输入框

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		context = this;
		/**
		 * 1.网格项的布局，用GridView展示批量数据 2.初始化数据，固定的数据 3.准备适配器 自定义适配器 4.点击监听
		 */
		gridView = (GridView) findViewById(R.id.home_gv);
		gridView.setAdapter(new HomeAdapter(this));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				switch (position) {
				case 0:// 手机防盗
					if (TextUtils.isEmpty(CacheUtils.getString(context, SAFE_PASSWORD))) {
						// 进入初始设置密码的界面
						showStupDialog();
					} else {
						// 显示验证密码的对话框
						showAuthorDialog();
					}
					break;
				case 1:// 通信卫士
					intent.setClass(getApplicationContext(), CallAndSmsActivity.class);
					startActivity(intent);
					break;
				case 2:// 软件管家
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 3:// 进程管理
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 4:// 流量统计
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 5:// 手机杀毒
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 6:// 缓存清理
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 7:// 高级工具
					intent.setClass(getApplicationContext(), AdvacedToolsActivity.class);
					startActivity(intent);
					break;
				case 8:// 设置中心
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});
	}

	// 显示验证密码的对话框
	protected void showAuthorDialog() {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(context).inflate(R.layout.safe_confirm_dialog, null);
		other_pwd = (EditText) view.findViewById(R.id.other_pwd_et);
		Button btnok = (Button) view.findViewById(R.id.ok_btn);
		Button btncancle = (Button) view.findViewById(R.id.quxiao_btn);
		btnok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String password = other_pwd.getText().toString().trim();

				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(password)) {
					ToastUtils.show(context, "密码为空");
					return;
				}

				if (MD5.getMD5(password).equals(CacheUtils.getString(getApplicationContext(), SAFE_PASSWORD))) {
					// 验证成功
					ToastUtils.show(getApplicationContext(), "密码正确");
					
					  Intent intent=new Intent(); 
					  intent.setClass(context,LostFindActivity.class); 
					  dialog2.dismiss();
					  startActivity(intent);
					
				} else {
					ToastUtils.show(getApplicationContext(), "密码不正确");
					return;
				}

			}
		});
		btncancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog2.dismiss();
			}
		});
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(view);
		dialog2 = builder.create();
		dialog2.show();
	}

	// 初始密码设置的对话
	@SuppressLint("NewApi")
	protected void showStupDialog() {
		// 对话框
		View view = LayoutInflater.from(context).inflate(R.layout.safe_setup_dialog, null);
		final EditText pwd = (EditText) view.findViewById(R.id.pwd_et);
		final EditText confrim_pwd = (EditText) view.findViewById(R.id.confrim_pwd_et);
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(view);
		Button btnset = (Button) view.findViewById(R.id.setup_btn);
		Button btncancle = (Button) view.findViewById(R.id.cancel_btn);
		btnset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String password = pwd.getText().toString().trim();
				String password2 = confrim_pwd.getText().toString().trim();
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
					ToastUtils.show(context, "密码或账号为空");
					return;
				}
				if (password.equals(password2)) {
					CacheUtils.putString(getApplicationContext(), SAFE_PASSWORD, MD5.getMD5(password));
				} else {
					ToastUtils.show(getApplicationContext(), "两次密码不一致");
					return;
				}

			}
		});
		btncancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		// dialog.setCancelable(false);
		dialog = builder.create();
		dialog.show();
	}

	// 显示密码
	public void mima(View view) {

		if (other_pwd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
			other_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		} else {
			other_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
		}
		other_pwd.setSelection(other_pwd.length());
	}
}
