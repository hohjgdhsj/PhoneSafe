package com.example.safephone.adapter;

import com.example.safephone.R;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {
	private Context context;

	private int[] iconsId = { R.drawable.safe, R.drawable.callmsgsafe, R.drawable.app, R.drawable.taskmanager,
			R.drawable.netmanager, R.drawable.trojan, R.drawable.sysoptimize, R.drawable.atools, R.drawable.settings };// 图片id数组
	private String[] names = { "手机防盗", "通信卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心" };

	public HomeAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return names[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(R.layout.grideview, null);
		ImageView imageView = (ImageView) convertView.findViewById(R.id.item_icon);
		TextView textView = (TextView) convertView.findViewById(R.id.item_text);
		imageView.setImageResource(iconsId[position]);
		textView.setText(names[position]);

		return convertView;
	}

}
