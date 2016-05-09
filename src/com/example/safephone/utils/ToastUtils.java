package com.example.safephone.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
public static void show(Context context,String rString){
	Toast.makeText(context, rString, Toast.LENGTH_SHORT).show();
	
}
}
