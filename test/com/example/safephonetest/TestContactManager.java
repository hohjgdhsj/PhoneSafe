package com.example.safephonetest;

import java.util.List;

import com.example.safephone.business.ContactsManager;
import com.example.safephone.entity.ContactInfo;

import android.test.AndroidTestCase;
//������Ҫ������
//������ϵ�˵Ĳ�����
public class TestContactManager extends AndroidTestCase {
//����
	public void testgetAllContactInfo(){
	List<ContactInfo> data=ContactsManager.getAllContactsInfo(getContext());
	for(ContactInfo temp:data){
		System.out.println(temp.getName().toString());
	}
}
}
