package com.example.safephone.receiver;

import com.example.safephone.R;
import com.example.safephone.service.GpsService;
import com.example.safephone.utils.CacheUtils;
import com.example.safephone.utils.ToastUtils;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.telephony.gsm.SmsManager;
import android.view.SurfaceHolder.BadSurfaceTypeException;

public class SmsReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//ȡ���豸��������
		DevicePolicyManager  devicePolicyManager=(DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
		// ȡ�ö��ŵ�����
		Object[] objects = (Object[]) intent.getExtras().get("pdus");// pdus:protocol
																		// data
																		// unit
																		// :���ʱ�׼�ĵ�Ԫ����
																		// ������׼
																		// һ�����Ű���70������
		for (Object obj : objects) {
			byte[] pdu = (byte[]) obj;// ��ÿһ��Ŀ�����ת��Ϊ�ֽ����飬���ֽ������װ�˸ö��ŵ�����
			SmsMessage smsMessage = SmsMessage.createFromPdu(pdu);// ͨ���ֽ�����������һ������
			String number = smsMessage.getMessageBody();// ȡ�ö�������
			String content = smsMessage.getOriginatingAddress();// ȡ�÷��Ͷ��ŵĵ�ַ���������ߵ绰����
			System.out.println("����" + number + "����" + content);
			if ("#*location*#".equals(content)) {
				// ToastUtils.show(context, "GPS���ٶ�λ");
				Intent locationIntent = new Intent();
				locationIntent.setClass(context, GpsService.class);
				context.startService(locationIntent);
				//��ȡ��ǰ�ֻ���λ��
				String latitude=CacheUtils.getString(context, "latitude");
				String longtitude=CacheUtils.getString(context, "longtitude");
				SmsManager smsManager=SmsManager.getDefault();
				smsManager.sendTextMessage(CacheUtils.getString(context, CacheUtils.SAFE_NUMBER), null, "����"+latitude+"γ��"+longtitude, null, null);
				abortBroadcast();// �ضϹ㲥
			} else if ("#*alarm*#".equals(content)) {
				// ToastUtils.show(context, "���ű�������");
				MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.cunzai);
				mediaPlayer.start();// ��������
				abortBroadcast();// �ضϹ㲥
			} else if ("#*wipe*#".equals(content)) {
				ComponentName componentName=new  ComponentName(context, MyAdminReceiver.class);
				if(devicePolicyManager.isAdminActive(componentName)){
					//devicePolicyManager.wipeData(0);//
				}//����������� ������
				ToastUtils.show(context, "Զ�̲�������");
				abortBroadcast();// �ضϹ㲥
			} else if ("#*lockscreen*#".equals(content)) {
				//����һ�����
				ComponentName componentName=new  ComponentName(context, MyAdminReceiver.class);
				if(devicePolicyManager.isAdminActive(componentName)){
					devicePolicyManager.lockNow();//�� �� 
				}//����������� ������
				ToastUtils.show(context, "Զ������");
				abortBroadcast();// �ضϹ㲥
			}

		}
	}

}
