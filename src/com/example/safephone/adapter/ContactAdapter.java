package com.example.safephone.adapter;

import java.util.List;

import com.example.safephone.ContactActivity;
import com.example.safephone.R;
import com.example.safephone.entity.ContactInfo;

import android.content.Context;
import android.provider.ContactsContract.Contacts.Data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {
private Context context;
private List<ContactInfo> mdata;
	public ContactAdapter(Context context,List<ContactInfo> mdata) {
		this.context=context;
		this.mdata=mdata;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mdata==null?0:mdata.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mdata==null?null:mdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//设置列表项的布局 装备列表项的数据
		
		ContactInfo contactInfo=mdata.get(position);
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.list_contact_item, parent,false);
		}TextView name=(TextView) convertView.findViewById(R.id.contact_name);
		TextView num=(TextView) convertView.findViewById(R.id.number_contact);
		name.setText(contactInfo.getName());
		num.setText(contactInfo.getNum());
		return convertView;
	}

}
