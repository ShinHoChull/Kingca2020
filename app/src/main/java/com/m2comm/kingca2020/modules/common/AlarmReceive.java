package com.m2comm.kingca2020.modules.common;


import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.m2comm.kingca2020.R;
import com.m2comm.kingca2020.views.ContentsActivity;


public class AlarmReceive extends BroadcastReceiver {

    Globar g;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        g = new Globar(context);

        String message = intent.getStringExtra("msg") + " The session starts in 10 minutes.";
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;

        if (cn.getPackageName().equals(context.getPackageName())) {
            Log.d("activitySameSame","okSameSame");

            Bundle bun = new Bundle();

            Intent popupIntent = new Intent(context, ServiceAlertActivity.class);
            popupIntent.putExtra("title", "알림");
            popupIntent.putExtra("content", message);
            popupIntent.putExtra("paramUrl", this.g.urls.get("myschedule"));
            popupIntent.putExtras(bun);
            PendingIntent pie= PendingIntent.getActivity(context, 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
            try {
                pie.send();
            } catch (PendingIntent.CanceledException e) {

                Log.d("serviceError",e.toString());
            }
            return;
        }


        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent2 = new Intent(context, ContentsActivity.class);
        intent.putExtra("paramUrl", this.g.urls.get("myschedule"));
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification.Builder builder = new Notification.Builder(context, this.g.code)
                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText(message)
                    .setSmallIcon(R.drawable.icon)
                    .setAutoCancel(true);
            builder.setContentIntent(contentIntent);
            mNotificationManager.notify((int) (Math.random() * 99999999), builder.build());
        }else {



            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.icon)
                            .setContentTitle(context.getString(R.string.app_name))
                            .setAutoCancel(true)
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText(message))
                            .setContentText(message);
            builder.setContentIntent(contentIntent);
            mNotificationManager.notify((int) (Math.random() * 99999999), builder.build());
        }


    }

}