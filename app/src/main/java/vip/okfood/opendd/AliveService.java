package vip.okfood.opendd;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


import androidx.core.app.NotificationCompat;


/**
 * function:
 *
 * <p>
 * Created by Leo on 2019/2/12.
 */
public class AliveService extends Service {

    private NotificationManager mNotificationManager;
    private Notification        mNotification;

    private static final int    ID_NOTIFY  = 0;
    private static final String CHANNEL_ID = "alive-opendd";

    @Override
    public void onCreate() {
        super.onCreate();
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel             = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(notificationManager != null) notificationManager.createNotificationChannel(channel);
        }
    }

    private NotificationManager getManager() {
        if(mNotificationManager == null) {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    private Notification getNotification() {
        if(mNotification == null) {
            mNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.app_name))
                    .setOngoing(true)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setAutoCancel(false)
                    .setLocalOnly(true)
                    .setShowWhen(false)
                    .setContentIntent(PendingIntent.getActivity(this, 100, new Intent(this, MainActivity.class), 0))
                    .build();
        }
        return mNotification;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getManager().notify(ID_NOTIFY, getNotification());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getManager().cancel(ID_NOTIFY);
        mNotificationManager = null;
        mNotification = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
