package org.apache.cordova.CustomPlugin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by xiaoshuai on 2016/12/16.
 */

public class NotificationPlugin extends CordovaPlugin {

    private static final String ACTION_TEST = "show_notification_test";
    private int ID_TEST=101;

    private static final String ACTION_CUSTOM = "show_notification_custom";
    private int ID_CUSTOM=202;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        NotificationManager nm = (NotificationManager) cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        if (ACTION_TEST.equals(action)) {
            //弹出默认通知
            nm.notify(++ID_TEST,buildNotification("测试标题","测试内容","通知来啦"));
            return true;
        }
        if(ACTION_CUSTOM.equals(action)){
            String title=args.getString(0);
            String content=args.getString(1);
            String firstTip=args.getString(2);

            nm.notify(++ID_CUSTOM,buildNotification(title,content,firstTip));
            return true;

        }
        return super.execute(action, args, callbackContext);
    }

    public PendingIntent getDefalutIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(cordova.getActivity(), 1, new Intent(), flags);
        return pendingIntent;
    }
	/**
	*构建通知栏
	*/
    private Notification buildNotification(String title ,String content,String firstTip){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(cordova.getActivity());
        mBuilder.setContentTitle(title)//设置通知栏标题
                .setContentText(content) //设置通知栏显示内容
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图
                //  .setNumber(number) //设置通知集合的数量
                .setTicker(firstTip) //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                //  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(android.R.mipmap.sym_def_app_icon);//设置通知小ICON
        return mBuilder.build();
    }
}
