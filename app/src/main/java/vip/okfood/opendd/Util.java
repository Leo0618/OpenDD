package vip.okfood.opendd;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;


import java.security.MessageDigest;

/**
 * function:
 *
 * <p></p>
 * Created by lzj on 2019/3/18.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Util {
    private static final String TAG = "Util";

    /** 获取主线程的handler */
    public static Handler getHandler() {
        return MyApp.uiHandler;
    }

    /** 在主线程中延时一定时间执行runnable */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /** 在主线程执行runnable */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /** 从主线程looper里面移除runnable */
    public static void removeCallbacksFromMainLooper(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }


    public static boolean isInstalled(Context context, String packageName) {
        boolean result = false;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if(packageInfo != null) {
                result = true;
            }
        } catch(Exception e) {e.printStackTrace();}
        return result;
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String getVersionName(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo    packageInfo    = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 检测辅助功能是否开启
     *
     * @param context 上下文
     * @param clazz   服务类名，eg: AccServiceWX.class
     */
    public static boolean isAccessibilitySettingsOn(Context context, Class<?> clazz) {
        try {
            int accessibilityEnabled = 0;
            // TestService为对应的服务
            final String service = context.getPackageName()+"/"+clazz.getCanonicalName();
            try {
                accessibilityEnabled = Settings.Secure.getInt(context.getApplicationContext().getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
            } catch(Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
            if(accessibilityEnabled == 1) {
                String settingValue = Settings.Secure.getString(context.getApplicationContext().getContentResolver(),
                        Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
                // com.z.buildingaccessibilityservices/com.z.buildingaccessibilityservices.TestService
                if(settingValue != null) {
                    mStringColonSplitter.setString(settingValue);
                    while(mStringColonSplitter.hasNext()) {
                        String accessibilityService = mStringColonSplitter.next();
                        if(accessibilityService.equalsIgnoreCase(service)) {
                            return true;
                        }
                    }
                }
            }
        } catch(Exception e) {e.printStackTrace();}
        return false;
    }


    /**
     * MD5 加密
     */
    public static String md5(String origin) {
        return md5(origin, "UTF-8");
    }

    /**
     * MD5加密
     *
     * @param str         加密字符串
     * @param charsetname 字符集
     *
     * @return 加密后的字符串, 异常则返回null
     */
    public static String md5(String str, String charsetname) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(charsetname));
            byte[]        byteDigest = md.digest();
            int           i;
            StringBuilder buf        = new StringBuilder();
            for(byte aByteDigest : byteDigest) {
                i = aByteDigest;
                if(i < 0) i += 256;
                if(i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
