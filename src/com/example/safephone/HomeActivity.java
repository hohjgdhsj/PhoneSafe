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
	private GridView gridView;// girdview����ͼ
	private Context context;// ������
	private AlertDialog dialog;
	private AlertDialog dialog2 ;
	private static SharedPreferences mSharedPreferences;
	private static final String SAFE_PASSWORD = "safe_password";
	private EditText other_pwd;// �ɿ�ԭ����������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		context = this;
		/**
		 * 1.������Ĳ��֣���GridViewչʾ�������� 2.��ʼ�����ݣ��̶������� 3.׼�������� �Զ��������� 4.�������
		 */
		gridView = (GridView) findViewById(R.id.home_gv);
		gridView.setAdapter(new HomeAdapter(this));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				switch (position) {
				case 0:// �ֻ�����
					if (TextUtils.isEmpty(CacheUtils.getString(context, SAFE_PASSWORD))) {
						// �����ʼ��������Ľ���
						showStupDialog();
					} else {
						// ��ʾ��֤����ĶԻ���
						showAuthorDialog();
					}
					break;
				case 1:// ͨ����ʿ
					intent.setClass(getApplicationContext(), CallAndSmsActivity.class);
					startActivity(intent);
					break;
				case 2:// ����ܼ�
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 3:// ���̹���
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 4:// ����ͳ��
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 5:// �ֻ�ɱ��
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 6:// ��������
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;
				case 7:// �߼�����
					intent.setClass(getApplicationContext(), AdvacedToolsActivity.class);
					startActivity(intent);
					break;
				case 8:// ��������
					intent.setClass(getApplicationContext(), SettingActivity.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}
		});
	}

	// ��ʾ��֤����ĶԻ���
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
					ToastUtils.show(context, "����Ϊ��");
					return;
				}

				if (MD5.getMD5(password).equals(CacheUtils.getString(getApplicationContext(), SAFE_PASSWORD))) {
					// ��֤�ɹ�
					ToastUtils.show(getApplicationContext(), "������ȷ");
					
					  Intent intent=new Intent(); 
					  intent.setClass(context,LostFindActivity.class); 
					  dialog2.dismiss();
					  startActivity(intent);
					
				} else {
					ToastUtils.show(getApplicationContext(), "���벻��ȷ");
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

	// ��ʼ�������õĶԻ�
	@SuppressLint("NewApi")
	protected void showStupDialog() {
		// �Ի���
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
					ToastUtils.show(context, "������˺�Ϊ��");
					return;
				}
				if (password.equals(password2)) {
					CacheUtils.putString(getApplicationContext(), SAFE_PASSWORD, MD5.getMD5(password));
				} else {
					ToastUtils.show(getApplicationContext(), "�������벻һ��");
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

	// ��ʾ����
	public void mima(View view) {

		if (other_pwd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
			other_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		} else {
			other_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
		}
		other_pwd.setSelection(other_pwd.length());
	}
}
