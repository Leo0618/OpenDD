package vip.okfood.opendd;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/8/2 0002.
 * SharedPreferences管理类.
 */

public class SPUtil {
    private static SharedPreferences sp;

    private static SharedPreferences getSp() {
        if(sp == null) {
            Context context = MyApp.context;
            sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        }
        return sp;
    }

    /**
     * 获取boolean 数据
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return getSp().getBoolean(key, defValue);
    }

    /**
     * 存boolean缓存
     */
    public static void putBoolean(String key, boolean value) {
        getSp().edit().putBoolean(key, value).apply();
    }

    /**
     * 获取String 数据
     */
    public static String getString(String key, String defValue) {
        return getSp().getString(key, defValue);
    }

    /**
     * 存String缓存
     */
    public static void putString(String key, String value) {
        getSp().edit().putString(key, value).apply();
    }


    /**
     * 获取Long 数据
     */
    public static Long getLong(String key, Long defValue) {
        return getSp().getLong(key, defValue);
    }

    /**
     * 存long缓存
     */
    public static void putLong(String key, Long value) {
        getSp().edit().putLong(key, value).apply();
    }


    /**
     * 存int缓存
     */
    public static void putInt(String key, int value) {
        getSp().edit().putInt(key, value).apply();
    }

    /**
     * 取int缓存
     */
    public static int getInt(String key, int defValue) {
        return getSp().getInt(key, defValue);
    }

    public static void saveTime(int hour, int minute) {
        putInt("hour", hour);
        putInt("minute", minute);
    }

    public static int[] getTime() {
        int[] result = new int[2];
        result[0] = getInt("hour", 9);
        result[1] = getInt("minute", 5);
        return result;
    }
}
