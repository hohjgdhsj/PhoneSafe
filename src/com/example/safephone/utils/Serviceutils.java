package com.example.safephone.utils;

import java.util.List;

import javax.xml.transform.Templates;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.text.TextDirectionHeuristic;

public class Serviceutils {
public static boolean isServiceRunning(Context context){
	ActivityManager activityManager=(ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
	//ȡ���������еķ��������ٸ���
	List<RunningServiceInfo> runningServiceInfos=activityManager.getRunningServices(5);
	for(RunningServiceInfo service:runningServiceInfos){
		System.out.println(service.service.getClassName());
	}
	return false;
}
}
