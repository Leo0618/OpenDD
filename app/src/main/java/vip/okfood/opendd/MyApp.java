package vip.okfood.opendd;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * function:
 *
 * <p></p>
 * Created by Leo on 2019/12/30.
 */
public class MyApp extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public static Handler uiHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        uiHandler = new Handler(Looper.getMainLooper());
    }

}
