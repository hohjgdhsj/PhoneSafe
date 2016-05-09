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
		// 取得短信的内容
		Object[] objects = (Object[]) intent.getExtras().get("pdus");// pdus:protocol
																		// data
																		// unit
																		// :国际标准的单元数据
																		// 国籍标准
																		// 一条短信包含70个汉字
		for (Object obj : objects) {
			byte[] pdu = (byte[]) obj;// 把每一个目标对象转化为字节数组，该字节数组封装了该短信的内容
			SmsMessage smsMessage = SmsMessage.createFromPdu(pdu);// 通过字节数组来生成一条短信
			String number = smsMessage.getMessageBody();// 取得短信内容
			String content = smsMessage.getOriginatingAddress();// 取得发送短信的地址，即发送者电话号码
			System.out.println("号码" + number + "内容" + content);
if("#*location*#".equals(content)){
	ToastUtils.show(context, "GPS跟踪定位");
	abortBroadcast();//截断广播
}else if("#*alarm*#".equals(content)){
	ToastUtils.show(context, "播放报警音乐");
	abortBroadcast();//截断广播
}else if("#*wipe*#".equals(content)){
	ToastUtils.show(context, "远程擦除数据");
	abortBroadcast();//截断广播
}else if("#*lockscreen*#".equals(content)){
	ToastUtils.show(context, "远程锁屏");
	abortBroadcast();//截断广播
}
			
			
		}
	}

}
