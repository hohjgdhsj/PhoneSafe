package com.example.safephone.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BlackNumOpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "black_num.db";
	private static final int version = 1;

	public BlackNumOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table t_info(id integer primary key autoincrement,num text,mode integer)");
	}

	// 版本更新
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("版本更新");
	}

}
