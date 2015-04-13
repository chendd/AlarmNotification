package com.coder80.timer.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.coder80.timer.MainActivity;
import com.coder80.timer.R;
import com.coder80.timer.utils.NotificationServiceUtil;

/**
 * Created by coder80 on 2014/10/31.
 */
public class TimerNotificationService extends Service implements Runnable {
	private String TAG = TimerNotificationService.class.getSimpleName();
	private String content = "";
	private int id = 1;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		try {
			content = intent.getExtras().getString("content");
			id = intent.getExtras().getInt("id");
			uploadPOIInfo();
		} catch (Exception e) {

		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void uploadPOIInfo() {
		// simulation HTTP request to server
		new Thread(this).start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private boolean isRunningForeground(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String currentPackageName = cn.getPackageName();
		if (!TextUtils.isEmpty(currentPackageName)
				&& currentPackageName.equals(getPackageName())) {
			return true;
		}

		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!isRunningForeground(TimerNotificationService.this)) {
			String ns = Context.NOTIFICATION_SERVICE;
			NotificationManager nm = (NotificationManager) getSystemService(ns);
			CharSequence tickerText = "新消息";
			long when = System.currentTimeMillis();
			Notification notification = new Notification(
					R.drawable.ic_launcher, tickerText, when);
			notification.defaults = Notification.DEFAULT_SOUND;// 发出默认声音
			PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(
					this, MainActivity.class), 0);
			notification.setLatestEventInfo(this, tickerText, content, pi);

			nm.notify(id, notification);
		}
	}

}
