package com.example.safephone;

import android.app.Activity;
import android.app.DownloadManager.Query;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdvacedToolsActivity extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
}
private void query() {
	// TODO Auto-generated method stub

}
//½øÈë»ð¼ý¶¯»­activity
public void rocket(View view){
	Intent intent =new Intent(this,RocketActivity.class);
startActivity(intent);
}

}
