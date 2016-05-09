package com.example.safephone.receiver;

import com.example.safephone.utils.ToastUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.telephony.gsm.SmsManager;
import android.view.SurfaceHolder.BadSurfaceTypeException;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
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
if("#*location*#".equals(content)){
	ToastUtils.show(context, "GPS���ٶ�λ");
	abortBroadcast();//�ضϹ㲥
}else if("#*alarm*#".equals(content)){
	ToastUtils.show(context, "���ű�������");
	abortBroadcast();//�ضϹ㲥
}else if("#*wipe*#".equals(content)){
	ToastUtils.show(context, "Զ�̲�������");
	abortBroadcast();//�ضϹ㲥
}else if("#*lockscreen*#".equals(content)){
	ToastUtils.show(context, "Զ������");
	abortBroadcast();//�ضϹ㲥
}
			
			
		}
	}

}
