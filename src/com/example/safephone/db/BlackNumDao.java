package com.example.safephone.db;

import java.util.ArrayList;
import java.util.List;

import com.example.safephone.entity.BlackNumInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * ���������ʵĹ�����
 * 
 * @author Jay-Tang
 *
 */
public class BlackNumDao {
	public static final String TABLE = "t_info";
	public static final String NUM = "num";
	public static final String MODE = "mode";
	private BlackNumOpenHelper blackNumOpenHelper;
	// ���÷��ʷ�ʽ
public static final int CALL=0;
public static final int SMS=1;
public static final int ALL=2;
	// �ڹ��췽���ﴫ��������
	public BlackNumDao(Context context) {
		blackNumOpenHelper = new BlackNumOpenHelper(context);
	}

	/**
	 * 
	 * ��Ӻ���������
	 */
	public void add(String num, int mode) {
		SQLiteDatabase database = blackNumOpenHelper.getWritableDatabase();// ��ͬ����
		ContentValues values = new ContentValues();
		values.put(NUM, num);
		values.put(MODE, mode);
		long id = database.insert(TABLE, null, values);
		database.close();
	}

	// ɾ��
	public void delete(String num) {
		SQLiteDatabase database = blackNumOpenHelper.getWritableDatabase();// ��ͬ����
		database.delete(TABLE, NUM + "=?", new String[] { num });
		database.close();
	}
	// ��ѯ���ط�ʽ
	public void queryBlackNumMode(String num) {
		int mode=-1;
		SQLiteDatabase database = blackNumOpenHelper.getWritableDatabase();// ��ͬ����
		Cursor c=database.query(TABLE, new String[]{MODE},NUM+"=?",  new String[]{num}, null, null, null);
		if(c.moveToNext()){
		//	mode=c.getInt(0);
			//��������������ѯ
			mode=c.getInt(c.getColumnIndex(MODE));
			System.out.println(mode);
		}
		c.close();
		database.close();
	}
	// ���º����������ط�ʽ
	public void updateBalckNumMode(String num,int mode){
		SQLiteDatabase database = blackNumOpenHelper.getWritableDatabase();// ��ͬ����
		ContentValues values=new ContentValues();
		values.put(MODE, mode);
		database.update(TABLE, values, NUM+"=?", new String[]{num});
	database.close();
	}
	// ��ѯ���к�����
	public List<BlackNumInfo> getAllBlackNums(){
		List<BlackNumInfo> data = null;
		SQLiteDatabase database=blackNumOpenHelper.getReadableDatabase();
		Cursor c=database.query(TABLE, new String[]{NUM,MODE}, null, null, null, null, "num odesc");
		if(c.getCount()>0){
			data=new ArrayList<BlackNumInfo>();
		}
		while(c.moveToNext()){
			String num=c.getString(0);
			int mode=c.getInt(1);
			BlackNumInfo blackNumInfo=new BlackNumInfo(num, mode);
			data.add(blackNumInfo);		
			}
		c.close();
		database.close();
		return data;
	}
}
