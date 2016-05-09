package com.example.safephone.business;

import java.util.ArrayList;
import java.util.List;

import com.example.safephone.entity.ContactInfo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * ��ϵ�˵�ҵ����
 * 
 * @author Jay-Tang ��ȡϵͳͨ�������ṩ���ṩ����ϵ������
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


/**��ϵ�˵�ҵ����
 * 
 * @author Administrator
 *
 */
public class ContactsManager {
	public static List<ContactInfo> getAllContactsInfo(Context context){
		List<ContactInfo> contactsList=new ArrayList<ContactInfo>();
		/**��ȡϵͳͨ�������ṩ���ṩ�� ��ϵ������
		 * 1. ���ݷ�����
		 * 2. uri1 �� content://com.android.contacts/raw_contacts
		 *    uri2 �� content://com.android.contacts/data
		 * 3.����cursor����
		 */
		//1.���ݷ�����
		ContentResolver contentResolver = context.getContentResolver();
		
		//2. ׼��Ҫ���ʵ�uri
		String uri1="content://com.android.contacts/raw_contacts";
		String uri2="content://com.android.contacts/data";
		/**
		 * 3.1  �������  :�Ȳ���  raw_contacts��
		 */
		Cursor c = contentResolver.query(Uri.parse(uri1), new String[]{"contact_id"}, null, null, null);
		while(c.moveToNext()){ //��������ϵ�ˣ��ٲ�ѯ����ϵ�˵���ϸ��Ϣ
			String contactId=c.getString(0);
			if(contactId!=null){ //��ĳ����ϵ�˱�ɾ�� ��contact_id����Ϊnull
			//������ϵ�˵�id����ѯ����ϵ�˵���ϸ��Ϣ���� ��ѯ view_data()
			Cursor dataCursor = contentResolver.query(Uri.parse(uri2), 
					new String[]{"mimetype","data1"}, " raw_contact_id=?", new String[]{contactId}, null);
			ContactInfo contactInfo=new ContactInfo();
			while(dataCursor.moveToNext()){
				//ȡ�� mimetype    data1����
				String mimetype = dataCursor.getString(dataCursor.getColumnIndex("mimetype"));
				String data1 = dataCursor.getString(dataCursor.getColumnIndex("data1"));
				//�ж� data1 ����������
				if("vnd.android.cursor.item/phone_v2".equals(mimetype)){
					contactInfo.setNum(data1);
				}else if("vnd.android.cursor.item/name".equals(mimetype)){
					contactInfo.setName(data1);
				}
			}
			dataCursor.close();
			contactsList.add(contactInfo);
		}
		}
		
		c.close();
		
		
		return contactsList;
	}

}
/*
public class ContactsManager {
	public static List<ContactInfo> getAllContactsInfo(Context context) {
		List<ContactInfo> list = new ArrayList<ContactInfo>();
		// ���ݷ�����
		ContentResolver contentResolver = context.getContentResolver();
		// ׼��Ҫ���ʵ�uri
		String uri1 = "content://aom.android.contacts/raw_contacts";
		String uri2 = "content://aom.android.contacts/data";
		// ����������Ȳ���raw_contacts��
		Cursor cursor = contentResolver.query(Uri.parse(uri1), new String[] { "contact_id" }, null, null, null);
		// �½�һ��Contactinfo����
		ContactInfo contactInfo = new ContactInfo();
		while (cursor.moveToNext()) {// ��������ϵ�� �ٸ���id������ϸ��Ϣ
			String contactId = cursor.getString(0);
			Cursor cursor2 = contentResolver.query(Uri.parse(uri2), new String[]{"mimetype","data1"}, " raw_contact_id=?", new String[]{contactId}, null);
			
			// �����ֵ �ͽ�������
			while (cursor2.moveToNext()) {
				String type = cursor2.getString(cursor2.getColumnIndex("mimetype"));
				String data = cursor2.getString(cursor2.getColumnIndex("data1"));
				if("vnd.android.cursor.item/phone_v2".equals(type)){
					contactInfo.setNum(data);
				}else if("vnd.android.cursor.item/name".equals(type)){
					contactInfo.setName(data);
				}
			}
			cursor2.close();
			list.add(contactInfo);
		}
		cursor.close();
		return list;

	}
}
*/