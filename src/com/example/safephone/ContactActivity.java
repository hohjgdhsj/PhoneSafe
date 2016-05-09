package com.example.safephone;

import java.util.List;

import com.example.safephone.adapter.ContactAdapter;
import com.example.safephone.business.ContactsManager;
import com.example.safephone.entity.ContactInfo;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ContactActivity extends Activity {
	@ViewInject(R.id.list_contact)
	private ListView list_contact;
	private Context context;
	public List<ContactInfo> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		context = this;
		/**
		 * ͨ��listviewչʾ�������� ������ ����Դ
		 */
		// 1.ע��
		ViewUtils.inject(this);
		// 2.����Դ
		data = ContactsManager.getAllContactsInfo(this);
		// 3.����������
		list_contact.setAdapter(new ContactAdapter(this, data));
		// 4.��������б���
		list_contact.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				//������б���ʱ���ѵ�ǰ�б����ֵ���ظ�������
				Intent intent=new Intent();
				intent.putExtra("num", data.get(position).getNum());
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
}
