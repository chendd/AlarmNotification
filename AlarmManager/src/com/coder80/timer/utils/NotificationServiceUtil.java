package com.coder80.timer.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import com.coder80.timer.service.TimerNotificationService;
/**
 * Created by coder80 on 2014/10/31.
 */

public class NotificationServiceUtil {

    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceInfos = activityManager.getRunningServices(NotificationConstants.RETRIVE_SERVICE_COUNT);

        if(null == serviceInfos || serviceInfos.size() < 1) {
            return false;
        }

        for(int i = 0; i < serviceInfos.size(); i++) {
            if(serviceInfos.get(i).service.getClassName().contains(className)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 设置定时推送
     * @param context
     * @param time 多少毫秒后推送
     * @param content 推送内容
     * @param id 推送id
     */
    public static void invokeTimerNotification(Context context,int time , String content , int id){
        Log.i("ServiceUtil-AlarmManager", "invokeTimerPOIService wac called.." );
        PendingIntent alarmSender = null;
        Intent startIntent = new Intent(context, TimerNotificationService.class);
        startIntent.putExtra("content", content);
        startIntent.putExtra("id", id);
        startIntent.setAction(NotificationConstants.POI_SERVICE_ACTION);
        try {
            alarmSender = PendingIntent.getService(context, id, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        } catch (Exception e) {
        	
        }
        AlarmManager am = (AlarmManager) context.getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+time, alarmSender);
    }
    
    /**
     * 取消定时推送
     * @param context
     * @param id 推送id
     */
    public static void cancleNotification(Context context,int id){
    	String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nm = (NotificationManager) context.getSystemService(ns);
        nm.cancel(id);
        cancleAlarmManager(context,id);
    }
    
    /**
     * 关掉定时器(在cancleNotification里调用了)
     * @param context
     * @param id
     */
    public static void cancleAlarmManager(Context context, int id){
        Intent intent = new Intent(context,TimerNotificationService.class);
    	intent.setAction(NotificationConstants.POI_SERVICE_ACTION);
    	intent.putExtra("id", id);
        PendingIntent pendingIntent=PendingIntent.getService(context, id, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm=(AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);
        alarm.cancel(pendingIntent);
    }
    
}
