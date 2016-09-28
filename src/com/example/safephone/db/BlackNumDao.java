package com.example.safephone.db;

import java.util.ArrayList;
import java.util.List;

import com.example.safephone.entity.BlackNumInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 黑名单访问的工具类
 * 
 * @author Jay-Tang
 *
 */
public class BlackNumDao {
	public static final String TABLE = "t_info";
	public static final String NUM = "num";
	public static final String MODE = "mode";
	private BlackNumOpenHelper blackNumOpenHelper;
	// 设置访问方式
public static final int CALL=0;
public static final int SMS=1;
public static final int ALL=2;
	// 在构造方法里传入上下文
	public BlackNumDao(Context context) {
		blackNumOpenHelper = new BlackNumOpenHelper(context);
	}

	/**
	 * 
	 * 添加黑名单号码
	 */
	public void add(String num, int mode) {
		SQLiteDatabase database = blackNumOpenHelper.getWritableDatabase();// 加同步锁
		ContentValues values = new ContentValues();
		values.put(NUM, num);
		values.put(MODE, mode);
		long id = database.insert(TABLE, null, values);
		database.close();
	}

	// 删除
	public void delete(String num) {
		SQLiteDatabase database = blackNumOpenHelper.getWritableDatabase();// 加同步锁
		database.delete(TABLE, NUM + "=?", new String[] { num });
		database.close();
	}
	// 查询拦截方式
	public void queryBlackNumMode(String num) {
		int mode=-1;
		SQLiteDatabase database = blackNumOpenHelper.getWritableDatabase();// 加同步锁
		Cursor c=database.query(TABLE, new String[]{MODE},NUM+"=?",  new String[]{num}, null, null, null);
		if(c.moveToNext()){
		//	mode=c.getInt(0);
			//根据索引号来查询
			mode=c.getInt(c.getColumnIndex(MODE));
			System.out.println(mode);
		}
		c.close();
		database.close();
	}
	// 更新黑名单的拦截方式
	public void updateBalckNumMode(String num,int mode){
		SQLiteDatabase database = blackNumOpenHelper.getWritableDatabase();// 加同步锁
		ContentValues values=new ContentValues();
		values.put(MODE, mode);
		database.update(TABLE, values, NUM+"=?", new String[]{num});
	database.close();
	}
	// 查询所有黑名单
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
