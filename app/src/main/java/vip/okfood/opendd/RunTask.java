package vip.okfood.opendd;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;


import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * function:RunTask
 *
 * <p></p>
 * Created by Leo on 2019/12/30.
 */
@SuppressWarnings("WeakerAccess")
public class RunTask implements Runnable {
    private static final AtomicReference<RunTask> INSTANCE = new AtomicReference<>();

    public static RunTask instance() {
        for(; ; ) {
            RunTask instance = INSTANCE.get();
            if(instance != null) return instance;
            instance = new RunTask();
            if(INSTANCE.compareAndSet(null, instance)) return instance;
        }
    }

    private RunTask() {}

    private int[] time;

    public void start() {
        time = SPUtil.getTime();
        MyApp.uiHandler.post(this);
    }

    public void stop() {
        MyApp.uiHandler.removeCallbacks(this);
    }

    @Override
    public void run() {
        try {
            PowerManager mPowerManager = (PowerManager) MyApp.context.getSystemService(Context.POWER_SERVICE);
            if(mPowerManager != null) {
                PowerManager.WakeLock mWakeLock = mPowerManager.newWakeLock(
                        PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP,
                        UUID.randomUUID().toString());
                mWakeLock.acquire(3000);
            }
        } catch(Throwable e) {e.printStackTrace();}
        Toast.makeText(MyApp.context, "running...", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();
        int      month    = calendar.get(Calendar.MONTH);
        int      day      = calendar.get(Calendar.DATE);
        int      hour     = calendar.get(Calendar.HOUR_OF_DAY);
        int      minute   = calendar.get(Calendar.MINUTE);
        int      second   = calendar.get(Calendar.SECOND);
        String   timeNow  = String.format(Locale.getDefault(), "%02d-%02d %02d:%02d:%02d", month, day, hour, minute, second);
        Log.i("RunTask", "running...now("+timeNow+")"+
                ", target-time: "+String.format(Locale.getDefault(), "%02d:%02d:00", time[0], time[1]));
        if(hour == time[0] && minute == time[1]) {
            Log.i("RunTask", "time is OK!!!");
            launch();
            stop();
        } else {
            MyApp.uiHandler.postDelayed(this, 5_000);
        }
    }

    private void launch() {
        PackageManager packageManager = MyApp.context.getPackageManager();
        Intent         intent         = packageManager.getLaunchIntentForPackage("com.alibaba.android.rimet");
        if(intent == null) {
            Toast.makeText(MyApp.context, "未安装", Toast.LENGTH_LONG).show();
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyApp.context.startActivity(intent);
        }
    }
}
