package com.example.safephonetest;

import java.util.List;
import java.util.Random;

import com.example.safephone.db.BlackNumDao;
import com.example.safephone.entity.BlackNumInfo;

import android.test.AndroidTestCase;

public class TestBlackNumDao extends AndroidTestCase {
	/*
	 * public void testAdd(){ BlackNumDao bNumDao=new BlackNumDao(getContext());
	 * Random random=new Random(); for(int i=0;i<=50;i++){
	 * bNumDao.add(""+132326237+i*5+random.nextInt(), random.nextInt(3)); } }
	 */
/*	public void testdeletet() {
		BlackNumDao bNumDao = new BlackNumDao(getContext());
		bNumDao.delete("11223232");
	}
*/
	// ²éÑ¯À¹½Ø·½Ê½
	public void querymode() {
		BlackNumDao bNumDao = new BlackNumDao(getContext());
		bNumDao.queryBlackNumMode("13232623755-894051249");
	}

	public void updateBalckNumMode() {
		BlackNumDao bNumDao = new BlackNumDao(getContext());
		bNumDao.updateBalckNumMode("13232623755-894051249", 0);
	}

	public void getAllBlackNums() {
		BlackNumDao bNumDao = new BlackNumDao(getContext());
		List<BlackNumInfo> data = bNumDao.getAllBlackNums();
		for(BlackNumInfo temp:data){
			System.out.println(temp.getNum());
		}
	}
}
