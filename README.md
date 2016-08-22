# AlarmNotification
android 本地定时推送。
    具体如下NotificationServiceUtil的接口
    
    /**
     * 设置定时推送
     * @param context
     * @param time 多少毫秒后推送
     * @param content 推送内容
     * @param id 推送id
     */
    public static void invokeTimerNotification(Context context,int time , String content , int id)
    
    /**
     * 取消定时推送
     * @param context
     * @param id 推送id
     */
    public static void cancleNotification(Context context,int id)
    
    /**
     * 关掉定时器(在cancleNotification里调用了)
     * @param context
     * @param id
     */
    public static void cancleAlarmManager(Context context, int id)
