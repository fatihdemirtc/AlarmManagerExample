package com.mobilhanem.alarmmanagerexample;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.app.Notification;
import         android.app.PendingIntent;
import         android.content.Intent;
import         android.app.NotificationManager;

import static android.content.Context.NOTIFICATION_SERVICE;


public class AlarmReceiver extends BroadcastReceiver {
    Notification notification;
    NotificationManager notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        //region Alarmı öttür
        //        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        if (alarmUri == null)
//        {
//            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }
//        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//        ringtone.play();
        //endregion

        // Bildirim başlatılıyor
        Intent notificationIntent = new Intent(context, Main2Activity.class);
        PendingIntent intent2 = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("New notification")
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setContentIntent(intent2);

        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());

        Log.i("MyTestService", "Test");

        int interval = 1; // minutes

        Intent receiverIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 123456789, receiverIntent, 0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) Log.i("MyTestService", "alarmManager es null");


        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+interval*60*1000, interval*60*1000, sender);
        Log.i("MyTestService", "End Test");
    }


    }

