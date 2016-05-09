package com.example.safephonetest;

import java.util.List;

import com.example.safephone.business.ContactsManager;
import com.example.safephone.entity.ContactInfo;

import android.test.AndroidTestCase;
//测试需要添加类库
//测试联系人的测试类
public class TestContactManager extends AndroidTestCase {
//测试
	public void testgetAllContactInfo(){
	List<ContactInfo> data=ContactsManager.getAllContactsInfo(getContext());
	for(ContactInfo temp:data){
		System.out.println(temp.getName().toString());
	}
}
}
