package com.example.safephone;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import com.example.safephone.utils.CacheUtils;
import com.example.safephone.utils.Constants;
import com.example.safephone.utils.ToastUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private static final int MSG_SHOW_DIALOG = 1;// 提示升级要用的对话框标记
	private static final int MSG_ENTER_HOME = 2;// 进入主界面的标记
	private static final int MSG_SERVER_ERROR = 3;// 访问服务端错误的标记
	private TextView bTextView;// 版本显示的
	private Context context;// 上下文
	private int newVersipnCode;// 服务端版本号
	private String APK_URL;// apk下载地址
	private String des;// 服务端版本描述
	private ProgressBar progressBar1;
	private TextView textView;
	HttpUtils client = new HttpUtils();
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_SHOW_DIALOG:
				showUpdateDialog();
				break;
			case MSG_ENTER_HOME:
				enterHome();
				break;
			case MSG_SERVER_ERROR:
				enterHome();
				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		context = this;
		// 初始化
		initView();
		// 依据用户的配置来检验是否需要更新 ,默认是更新版本
		if (CacheUtils.getBoolean(context, CacheUtils.APK_UPDATE, true)) {
			checkupdate();// 检测更新
		} else {
			new Thread() {

				public void run() {
					SystemClock.sleep(2000);// 休眠两秒
					// 放到ui队列里面去
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							enterHome();
						}

					});
				};
			}.start();

		}
	}

	// 检测服务端是否有新版本 连接服务器 ，开辟子线程
	private void checkupdate() {
		new Thread() {
			// 访问网络
			public void run() {
				long startTime = System.currentTimeMillis();// 连接网络的开始时间
				Message message = new Message();
				try {
					// 建立连接 设置请求
					URL url = new URL(Constants.SERVER_VERSION_URL);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(5000);
					int resultCode = connection.getResponseCode();
					// 访问正常
					if (resultCode == HttpURLConnection.HTTP_OK) {
						InputStream iStream = connection.getInputStream();
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iStream));
						String jsonResult = bufferedReader.readLine();
						// 解析json数据
						JSONObject jsonObject = new JSONObject(jsonResult);
						newVersipnCode = jsonObject.getInt("code");
						APK_URL = jsonObject.getString("apkurl");
						des = jsonObject.getString("des");// 版本描述
						// 比较服务端的版本号
						if (newVersipnCode > getVersionCode()) {
							// 提示有新版本，是否要更新
							message.what = MSG_SHOW_DIALOG;

							// Dialog dialog=new
							// Dialog(context).setTitle("是否更新").show();
						} else {
							// 没有新版本 直接进入home界面
							message.what = MSG_ENTER_HOME;
							// enterHome();
						}
					} else {
						// 访问服务端异常 进入homeactivity
						message.what = MSG_SERVER_ERROR;
						// enterHome();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// 访问服务端异常 进入homeactivity
					message.what = MSG_SERVER_ERROR;
					e.printStackTrace();
				} finally {
					long duration = System.currentTimeMillis() - startTime;// 访问网络所耗时间
					if (duration < 2000) {
						SystemClock.sleep(2000 - duration);
					}
					// 向ui线程发消息
					handler.sendMessage(message);
				}
			};
		}.start();

	}

	private void initView() {
		// TODO Auto-generated method stub
		bTextView = (TextView) findViewById(R.id.banbentext);
		bTextView.setText("版本号：" + getVersionCode());
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		textView=(TextView) findViewById(R.id.textprogress);
	}

	private int getVersionCode() {
		// TODO Auto-generated method stub
		PackageManager packageManager = getPackageManager();
		/**
		 * 取得包名 package：当前应用的包名
		 * 
		 */
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
			return packageInfo.versionCode;// 取得版本号
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// 跳转到下一个页面：homeActivity
	private void enterHome() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	// 显示是否要升级的对话框
	private void showUpdateDialog() {
		// TODO Auto-generated method stub
		ToastUtils.show(this, "有新的版本" + newVersipnCode + "可以更新");

		new AlertDialog.Builder(context).setTitle("新版本号" + newVersipnCode).setIcon(R.drawable.ic_logo).setMessage(des)
				.setPositiveButton("升级", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						downloadAPK();
					}

				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// 进入homeActivity
						enterHome();
					}
				}).create().show();

	}

	// 下载apk
	private void downloadAPK() {
		// TODO Auto-generated method stub
		// 用xutils -httpUtils模块来实现
		HttpUtils httpUtils = new HttpUtils();
		/**
		 * 下载方法 url：要下载的问价你的url地址
		 * target：下载到本地的目标地址（存文件的地方）下载到/mnt/sdcard/android/data/
		 * <project>/cache:外部存储的私有缓存路径 RequestCallBack:网络请求的返回
		 */
		httpUtils.download(APK_URL, getExternalCacheDir().getAbsolutePath() + "/safe.apk", new RequestCallBack<File>() {
			// 访问网络失败 ,也在主线程运行
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				// 下载完毕
				System.out.println("download-onFailure");
				ToastUtils.show(getApplicationContext(), "下载apk失败");
				enterHome();
			}

			// 网络访问成功 返回码200 该方法在主线程运行
			@Override
			public void onSuccess(ResponseInfo<File> arg0) {
				// TODO Auto-generated method stub
				System.out.println("download-onsuccess");
				initallApk();
			}

			/**
			 * 
			 * 
			 * @param total
			 * @param current
			 * @param isUploading
			 */
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				// TODO Auto-generated method stub
				super.onLoading(total, current, isUploading);
				// 设置进度
				progressBar1.setVisibility(View.VISIBLE);
				progressBar1.setMax((int)total);
				progressBar1.setProgress((int)current);
				textView.setText(((int)(current/total)*100)+"%");
			}
		});
	}

	private void initallApk() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);// 查看
		intent.addCategory(intent.CATEGORY_DEFAULT);// 添加要访问的类别，activity的类别
		/**
		 * 设置要传输的数据 data:uri:格式的数据 type:数据的MIME类型
		 */
		intent.setDataAndType(Uri.fromFile(new File(getExternalCacheDir(), "safe.apk")),
				"application/vnd.android.package-archive");
		startActivityForResult(intent, 6);// 要求返回结果
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// 安装完毕后，跳转到HomeActivity界面
		if (requestCode == 6 && resultCode == RESULT_OK) {
			enterHome();
		}
	}
}
