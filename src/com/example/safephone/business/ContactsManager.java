package com.example.safephone.business;

import java.util.ArrayList;
import java.util.List;

import com.example.safephone.entity.ContactInfo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * 联系人的业务类
 * 
 * @author Jay-Tang 获取系统通过内容提供者提供的联系人数据
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


/**联系人的业务类
 * 
 * @author Administrator
 *
 */
public class ContactsManager {
	public static List<ContactInfo> getAllContactsInfo(Context context){
		List<ContactInfo> contactsList=new ArrayList<ContactInfo>();
		/**获取系统通过内容提供者提供的 联系人数据
		 * 1. 内容访问者
		 * 2. uri1 ： content://com.android.contacts/raw_contacts
		 *    uri2 ： content://com.android.contacts/data
		 * 3.遍历cursor数据
		 */
		//1.内容访问者
		ContentResolver contentResolver = context.getContentResolver();
		
		//2. 准备要访问的uri
		String uri1="content://com.android.contacts/raw_contacts";
		String uri2="content://com.android.contacts/data";
		/**
		 * 3.1  遍历结果  :先查下  raw_contacts表
		 */
		Cursor c = contentResolver.query(Uri.parse(uri1), new String[]{"contact_id"}, null, null, null);
		while(c.moveToNext()){ //假如有联系人，再查询该联系人的详细信息
			String contactId=c.getString(0);
			if(contactId!=null){ //当某个联系人被删除 ，contact_id被置为null
			//依据联系人的id来查询该联系人的详细信息，即 查询 view_data()
			Cursor dataCursor = contentResolver.query(Uri.parse(uri2), 
					new String[]{"mimetype","data1"}, " raw_contact_id=?", new String[]{contactId}, null);
			ContactInfo contactInfo=new ContactInfo();
			while(dataCursor.moveToNext()){
				//取得 mimetype    data1数据
				String mimetype = dataCursor.getString(dataCursor.getColumnIndex("mimetype"));
				String data1 = dataCursor.getString(dataCursor.getColumnIndex("data1"));
				//判断 data1 的数据类型
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
		// 内容访问者
		ContentResolver contentResolver = context.getContentResolver();
		// 准备要访问的uri
		String uri1 = "content://aom.android.contacts/raw_contacts";
		String uri2 = "content://aom.android.contacts/data";
		// 遍历结果：先查下raw_contacts表
		Cursor cursor = contentResolver.query(Uri.parse(uri1), new String[] { "contact_id" }, null, null, null);
		// 新建一个Contactinfo对象
		ContactInfo contactInfo = new ContactInfo();
		while (cursor.moveToNext()) {// 假如有联系人 再根据id来查详细信息
			String contactId = cursor.getString(0);
			Cursor cursor2 = contentResolver.query(Uri.parse(uri2), new String[]{"mimetype","data1"}, " raw_contact_id=?", new String[]{contactId}, null);
			
			// 如果有值 就将它保存
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