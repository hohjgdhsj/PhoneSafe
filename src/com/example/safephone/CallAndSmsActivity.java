package com.example.safephone;

import java.util.List;

import com.example.safephone.db.BlackNumDao;
import com.example.safephone.entity.BlackNumInfo;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CallAndSmsActivity extends Activity {
	@ViewInject(R.id.blacknum_tv)
	private ListView mBlacklistview;

	@ViewInject(R.id.progress_pd)
	private ProgressBar mProgressBar;

	private BlackNumDao blackNumDao;
	private List<BlackNumInfo> allBlackNums;// �������б�����
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		ViewUtils.inject(this);
		context = this;
		/**
		 * ��listviewչʾ����
		 * 
		 */
		allBlackNums = blackNumDao.getAllBlackNums();
		mBlacklistview.setAdapter(new BlackNumAdapter());
	}

	// �Զ���������
	private class BlackNumAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return allBlackNums.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return allBlackNums.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 1���õ�ǰ�б��� 2װ������
			BlackNumInfo blackNumInfo = new BlackNumInfo();
			HolderView holderView=null;
			if (convertView == null) {
				convertView=LayoutInflater.from(context).inflate(R.layout.black_num_item,  parent, false);
				holderView.numtv=(TextView) convertView.findViewById(R.id.num_tv);
				holderView.modetv=(TextView) convertView.findViewById(R.id.mode_tv);
				holderView.deleteiv=(ImageView) findViewById(R.id.delete_iv);
			convertView.setTag(holderView);
			}
			else{
				holderView=(HolderView) convertView.getTag();
				
			}
			return convertView;
		}
	}
	//��holderview �������Ŀռ䣬ȡ��findviewbyid
public static class HolderView{
	TextView numtv;
	TextView modetv;
	ImageView deleteiv;
	
}
}
