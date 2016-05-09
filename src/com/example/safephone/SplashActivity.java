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
	private static final int MSG_SHOW_DIALOG = 1;// ��ʾ����Ҫ�õĶԻ�����
	private static final int MSG_ENTER_HOME = 2;// ����������ı��
	private static final int MSG_SERVER_ERROR = 3;// ���ʷ���˴���ı��
	private TextView bTextView;// �汾��ʾ��
	private Context context;// ������
	private int newVersipnCode;// ����˰汾��
	private String APK_URL;// apk���ص�ַ
	private String des;// ����˰汾����
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
		// ��ʼ��
		initView();
		// �����û��������������Ƿ���Ҫ���� ,Ĭ���Ǹ��°汾
		if (CacheUtils.getBoolean(context, CacheUtils.APK_UPDATE, true)) {
			checkupdate();// ������
		} else {
			new Thread() {

				public void run() {
					SystemClock.sleep(2000);// ��������
					// �ŵ�ui��������ȥ
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

	// ��������Ƿ����°汾 ���ӷ����� ���������߳�
	private void checkupdate() {
		new Thread() {
			// ��������
			public void run() {
				long startTime = System.currentTimeMillis();// ��������Ŀ�ʼʱ��
				Message message = new Message();
				try {
					// �������� ��������
					URL url = new URL(Constants.SERVER_VERSION_URL);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(5000);
					int resultCode = connection.getResponseCode();
					// ��������
					if (resultCode == HttpURLConnection.HTTP_OK) {
						InputStream iStream = connection.getInputStream();
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iStream));
						String jsonResult = bufferedReader.readLine();
						// ����json����
						JSONObject jsonObject = new JSONObject(jsonResult);
						newVersipnCode = jsonObject.getInt("code");
						APK_URL = jsonObject.getString("apkurl");
						des = jsonObject.getString("des");// �汾����
						// �ȽϷ���˵İ汾��
						if (newVersipnCode > getVersionCode()) {
							// ��ʾ���°汾���Ƿ�Ҫ����
							message.what = MSG_SHOW_DIALOG;

							// Dialog dialog=new
							// Dialog(context).setTitle("�Ƿ����").show();
						} else {
							// û���°汾 ֱ�ӽ���home����
							message.what = MSG_ENTER_HOME;
							// enterHome();
						}
					} else {
						// ���ʷ�����쳣 ����homeactivity
						message.what = MSG_SERVER_ERROR;
						// enterHome();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// ���ʷ�����쳣 ����homeactivity
					message.what = MSG_SERVER_ERROR;
					e.printStackTrace();
				} finally {
					long duration = System.currentTimeMillis() - startTime;// ������������ʱ��
					if (duration < 2000) {
						SystemClock.sleep(2000 - duration);
					}
					// ��ui�̷߳���Ϣ
					handler.sendMessage(message);
				}
			};
		}.start();

	}

	private void initView() {
		// TODO Auto-generated method stub
		bTextView = (TextView) findViewById(R.id.banbentext);
		bTextView.setText("�汾�ţ�" + getVersionCode());
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		textView=(TextView) findViewById(R.id.textprogress);
	}

	private int getVersionCode() {
		// TODO Auto-generated method stub
		PackageManager packageManager = getPackageManager();
		/**
		 * ȡ�ð��� package����ǰӦ�õİ���
		 * 
		 */
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
			return packageInfo.versionCode;// ȡ�ð汾��
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// ��ת����һ��ҳ�棺homeActivity
	private void enterHome() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	// ��ʾ�Ƿ�Ҫ�����ĶԻ���
	private void showUpdateDialog() {
		// TODO Auto-generated method stub
		ToastUtils.show(this, "���µİ汾" + newVersipnCode + "���Ը���");

		new AlertDialog.Builder(context).setTitle("�°汾��" + newVersipnCode).setIcon(R.drawable.ic_logo).setMessage(des)
				.setPositiveButton("����", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						downloadAPK();
					}

				}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// ����homeActivity
						enterHome();
					}
				}).create().show();

	}

	// ����apk
	private void downloadAPK() {
		// TODO Auto-generated method stub
		// ��xutils -httpUtilsģ����ʵ��
		HttpUtils httpUtils = new HttpUtils();
		/**
		 * ���ط��� url��Ҫ���ص��ʼ����url��ַ
		 * target�����ص����ص�Ŀ���ַ�����ļ��ĵط������ص�/mnt/sdcard/android/data/
		 * <project>/cache:�ⲿ�洢��˽�л���·�� RequestCallBack:��������ķ���
		 */
		httpUtils.download(APK_URL, getExternalCacheDir().getAbsolutePath() + "/safe.apk", new RequestCallBack<File>() {
			// ��������ʧ�� ,Ҳ�����߳�����
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				// �������
				System.out.println("download-onFailure");
				ToastUtils.show(getApplicationContext(), "����apkʧ��");
				enterHome();
			}

			// ������ʳɹ� ������200 �÷��������߳�����
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
				// ���ý���
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
		intent.setAction(Intent.ACTION_VIEW);// �鿴
		intent.addCategory(intent.CATEGORY_DEFAULT);// ���Ҫ���ʵ����activity�����
		/**
		 * ����Ҫ��������� data:uri:��ʽ������ type:���ݵ�MIME����
		 */
		intent.setDataAndType(Uri.fromFile(new File(getExternalCacheDir(), "safe.apk")),
				"application/vnd.android.package-archive");
		startActivityForResult(intent, 6);// Ҫ�󷵻ؽ��
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// ��װ��Ϻ���ת��HomeActivity����
		if (requestCode == 6 && resultCode == RESULT_OK) {
			enterHome();
		}
	}
}
