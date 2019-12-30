package vip.okfood.opendd;

import android.text.TextUtils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Administrator on 2017/8/2 0002.
 * 日期时间工具类.
 */

public class DateTimeUtil {

    /** 日期格式：yyyy-MM-dd HH:mm:ss **/
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /** 日期格式：yyyy-MM-dd HH:mm **/
    public static final String DF_YYYY_MM_DD_HH_MM    = "yyyy-MM-dd HH:mm";
    /** 日期格式：yyyy-MM-dd HH:mm **/
    public static final String DF_MM_DD_HH_MM_SS      = "MM-dd HH:mm:ss";
    /** 日期格式：yyyy-MM-dd **/
    public static final String DF_YYYY_MM_DD          = "yyyy-MM-dd";
    /** 日期格式：yyyy-MM **/
    public static final String DF_YYYY_MM             = "yyyy-MM";
    /** 日期格式：MM-dd **/
    public static final String DF_MM_DD               = "MM-dd";
    /** 日期格式：yyyy-MM-dd **/
    public static final String DF_YYYY_MM_DD_DOT      = "yyyy.MM.dd";
    /** 日期格式：HH:mm:ss **/
    public static final String DF_HH_MM_SS            = "HH:mm:ss";
    /** 日期格式：mm:ss **/
    public static final String MM_SS                  ="mm:ss";
    /** 日期格式：HH:mm **/
    public static final String DF_HH_MM               = "HH:mm";
    /** 日期格式：MM月dd日 **/
    public static final String DF_MM_YUE_DD           = "MM月dd日";
    /** 日期格式：MM月dd HH:mm **/
    public static final String DF_MM_DD_HH_MM         = "MM月dd日 HH:mm";
    /** 日期格式：yyyy年MM月dd日 **/
    public static final String DF_YYYYMMDD            = "yyyy年MM月dd日";
    /** 日期格式：yyyy年 **/
    public static final String DF_YYYY_N              = "yyyy年";
    /** 日期格式：yyyy年 **/
    public static final String DF_YYYY                = "yyyy";
    /** 日期格式：dd **/
    public static final String DF_DD                  = "dd";
    /** 日期格式：yyyy-MM-dd HH:mm:ss **/
    public static final String DF_YYYY_MM_DD_HH       = "yyyy-MM-dd HH";
    public static final String MM_dd_HH_mm            = "MM-dd HH:mm";
    /** 1秒 */
    public final static long   second                 = 1000;
    /** 1分钟 */
    public final static long   minute                 = 60 * 1000;
    /** 1小时 */
    public final static long   hour                   = 60 * minute;
    /** 1天 */
    public final static long   day                    = 24 * hour;
    /** 1周 */
    public final static long   week                   = 7 * day;
    /** 1月 */
    public final static long   month                  = 31 * day;
    /** 1年 */
    public final static long   year                   = 12 * month;


    /** Log输出标识 **/
    private static final String TAG = "DateTimeUtil";

    /**
     * 获取当前时间,单位为秒
     *
     * @return 返回当前时间 秒单位
     */
    public static long getCurrentTimeSeconds() {
        return getCurrentDate().getTime() / 1000;
    }

    /**
     * 获取当前时间,单位为毫秒
     *
     * @return 返回当前时间 毫秒单位
     */
    public static long getCurrentTimeMills() {
        return getCurrentDate().getTime();
    }

    /**
     * 获取系统当前日期
     *
     * @return 返回当前日期
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取间隔时间
     *
     * @param currentDate 参考时间(当前时间)
     * @param otherDate   比较时间 (>参考时间)
     * @return 格式化后的间隔时间 eg: 1年3个月5天7小时10分钟 、 3个月10分钟
     */
    public static String getIntervalTime(Date currentDate, Date otherDate) {
        String intervalTime = "";
        if (currentDate == null || otherDate == null) {
            return intervalTime;
        }
        long diff = otherDate.getTime() - currentDate.getTime();
        if (diff > year) {
            intervalTime += (diff / year) + "年";
            diff = diff % year;
        }
        if (diff > month) {
            intervalTime += (diff / month) + "个月";
            diff = diff % month;
        }
        if (diff > day) {
            intervalTime += (diff / day) + "天";
            diff = diff % day;
        }
        if (diff > hour) {
            intervalTime += (diff / hour) + "小时";
            diff = diff % hour;
        }
        if (diff > minute) {
            intervalTime += (diff / minute) + "分钟";
        }
        return intervalTime;
    }

    /**
     * 格式化倒计时显示字符串(显示精确到秒)
     *
     * @param time 时间
     * @return 格式化后的倒计时字符串 eg: 1天10分钟30秒
     */
    public static String formatTimeFriendly2Sec(long time) {
        time = time - new Date().getTime();
        if (time <= 1000L) {
            return "00:00:00";
        }
        String result = "";
        if (time > day) {
            result += (time / day) + "天";
            time = time % day;
        }
        if (time > hour) {
            result += (time / hour) + "小时";
            time = time % hour;
        }
        if (time > minute) {
            result += (time / minute) + "分钟";
            time = time % minute;
        }
        if (time > second) {
            result += (time / second) + "秒";
        }
        return result;
    }

    /**
     * 格式化倒计时显示字符串(显示精确到分)
     *
     * @param time 时间
     * @return 格式化后的倒计时字符串 eg: 1天10分钟
     */
    public static String formatTimeFriendly2Minute(long time) {
        time = time - new Date().getTime();
        if (time <= 1000L) {
            return "00:00";
        }
        String result = "";
        if (time > day) {
            result += (time / day) + "天";
            time = time % day;
        }
        if (time > hour) {
            result += (time / hour) + "小时";
            time = time % hour;
        }
        if (time > minute) {
            result += (time / minute) + "分钟";
        }
        return result;
    }

    /**
     * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
     */
    public static String formatFriendly(Date date) {
        if (date == null) {
            return "";
        }
        long diff = new Date().getTime() - date.getTime();
        long r;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 判断是否为今年
     */
    public static boolean isThisYear(long time) {
        Date   newDate  = new Date(time);
        String dateTime = formatDate(newDate, DF_YYYY_N);
        String nowTime  = formatDate(new Date(), DF_YYYY_N);
        return !(TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(nowTime)) && dateTime.equalsIgnoreCase(nowTime);
    }

    /**
     * 判断是否为当天
     */
    public static boolean isToday(long time) {
        Date   newDate  = new Date(time);
        String dateTime = formatDate(newDate, DF_YYYY_MM_DD);
        String nowTime  = formatDate(new Date(), DF_YYYY_MM_DD);
        return !(TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(nowTime)) && dateTime.equalsIgnoreCase(nowTime);
    }

    /**
     * 判断指定时间是否为昨天
     */
    public static boolean isYeasterday(long time) {
        Date newDate = new Date(time);
        newDate.setDate(newDate.getDate() + 1);
        String dateTime = formatDate(newDate, DF_YYYY_MM_DD);
        String nowTime  = formatDate(new Date(), DF_YYYY_MM_DD);
        return !(TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(nowTime)) && dateTime.equalsIgnoreCase(nowTime);
    }

    /**
     * 格式化时间
     */
    public static String formatDate(long dateL, String formater) {
        return new SimpleDateFormat(formater).format(new Date(dateL*1000));
    }

    /**
     * 格式化日期
     */
    public static String formatDate(Date date, String formater) {
        return new SimpleDateFormat(formater).format(date);
    }

    /**
     * 将日期字符串转成日期
     */
    public static Date parseDate(String strDate, String formater) {
        DateFormat dateFormat = new SimpleDateFormat(formater);
        Date       returnDate = null;
        try {
            returnDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnDate;
    }

    /**
     * 验证日期是否比当前日期早
     *
     * @param target1 比较时间1
     * @param target2 比较时间2
     * @return true 则代表target1比target2晚或等于target2，否则比target2早
     */
    public static boolean compareDate(Date target1, Date target2) {
        boolean flag = false;
        try {
            String target1DateTime = DateTimeUtil.formatDate(target1, DF_YYYY_MM_DD_HH_MM_SS);
            String target2DateTime = DateTimeUtil.formatDate(target2, DF_YYYY_MM_DD_HH_MM_SS);
            if (target1DateTime.compareTo(target2DateTime) <= 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 对日期进行增加操作
     *
     * @param target 需要进行运算的日期
     * @param hour   小时
     */
    public static Date addDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }
        return new Date(target.getTime() + (long) (hour * 60 * 60 * 1000));
    }

    /**
     * 对日期进行相减操作
     *
     * @param target 需要进行运算的日期
     * @param hour   小时
     */
    public static Date subDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }
        return new Date(target.getTime() - (long) (hour * 60 * 60 * 1000));
    }

    /**
     * 获取日期字符串对应的星期
     *
     * @param strDate 日期字符串
     * @return 1-7：对应周日-周六，国外周日为开始，周六为结束
     */
    public static int getDayOfWeekFromStrDate(String strDate, String formater) {
        Date     date = parseDate(strDate, formater);
        Calendar c    = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    //获取明天的结束时间
     public static Date getEndDayOfTomorrow() {
               Calendar cal = new GregorianCalendar();
               cal.setTime(getDayEnd());
                cal.add(Calendar.DAY_OF_MONTH, 1);
               return cal.getTime();
            }

    //获取当天的结束时间
      public static java.util.Date getDayEnd() {
                 Calendar cal = new GregorianCalendar();
                 cal.set(Calendar.HOUR_OF_DAY, 23);
                 cal.set(Calendar.MINUTE, 59);
                 cal.set(Calendar.SECOND, 59);
                 return cal.getTime();
             }


             public static Long getOldData(int dataType, int delayTime){
                 Calendar c = Calendar.getInstance();
                 c.setTime(new Date());
                switch (dataType){
                    case 0:
                        //天
                        c.add(Calendar.DATE, - delayTime);
                        break;
                    case 1:
                        //月
                        c.add(Calendar.MONTH, - delayTime);
                        break;
                    case 2:
                        //年
                        c.add(Calendar.YEAR, - delayTime);
                        break;
                }
                 Date d = c.getTime();
                 return d.getTime();
             }

    public static long getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     *
     * @return
     */
    public static long getLongCurrent(){
        long current= System.currentTimeMillis();//当前时间毫秒数
        return current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
    }
    public static String getSeconds(Long second){
        Long         temp;
        StringBuffer sb =new StringBuffer();
        temp = second/3600;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=second%3600/60;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=second%3600%60;
        sb.append((temp<10)?"0"+temp:""+temp);
        return sb.toString();

    }
/*
获取前后几分钟的时间
 */
    public static Long getTimeByMinute(int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, minute);

        return calendar.getTime().getTime()/1000;

    }
    /**
     * 获取一小时为单位
     */
    public static Long getTimeByHour(int hour) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return  calendar.getTime().getTime()/1000;

    }
    /**
     * 获取一天为单位的时间间隔
     *
     */
    public static Long getTimeByDay(int day) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);

        return  calendar.getTime().getTime()/1000;

    }
    /**
     * 获取一周为单位的时间间隔
     *
     */
    public static Long getTimeByWeek(int week) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + week*7);

        return  calendar.getTime().getTime()/1000;

    }

    /**
     * 计算时间差
     * @param toData
     * @param fromData
     * @return
     */
    public static int diffHoursTime(Long toData, Long fromData){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String           fromDate     = simpleFormat.format(new Date(fromData));
        String           toDate       = simpleFormat.format(new Date(toData));
        long             from         = 0;
        long             to           =0;
        try {
            from = simpleFormat.parse(fromDate).getTime();
            to  = simpleFormat.parse(toDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  (int) ((to - from)/(1000 * 60 * 60));
    }
    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
    /**
     * 根据K线图类别获取起始时间戳
     */
    /*** 取点数量 */
    private static final int COUNT_POINT = 100;
    public static Long getFromTime(int itemPos) {
        Long result;
        long countTimeMills = 0L;
        switch(itemPos) {
            case 0://分时
                countTimeMills = minute*COUNT_POINT;
                break;
            case 1://10分
                countTimeMills = minute*10*COUNT_POINT;
                break;
            case 2://时K
                countTimeMills =hour*COUNT_POINT;
                break;
            case 3://日K
                countTimeMills = day*COUNT_POINT;
                break;
            case 4://周K
                countTimeMills = week*COUNT_POINT;
                break;
        }
        result = (DateTimeUtil.getCurrentTimeMills()-countTimeMills)/1000;
        return result;
    }
    private static final int MARKET_COUNT_POINT = 100;
    public static Long getFromeTime(int itemPos) {
        Long result;
        long countTimeMills = 0L;
        switch(itemPos) {
            case 0://分时
            case 1:
                countTimeMills = minute*MARKET_COUNT_POINT;
                break;
            case 2://10分
                countTimeMills = minute*10*MARKET_COUNT_POINT;
                break;
            case 3://1时K
                countTimeMills =hour*MARKET_COUNT_POINT;
                break;
            case 4://日K
                countTimeMills = day*MARKET_COUNT_POINT;
                break;
            case 5://5分K
                countTimeMills = minute*5*MARKET_COUNT_POINT;
                break;
            case 6://30分
                 countTimeMills = minute*30*MARKET_COUNT_POINT;
                 break;
            case 7://4H
                 countTimeMills = hour*4*MARKET_COUNT_POINT;
                  break;
             case 8://周K
                  countTimeMills = week*MARKET_COUNT_POINT;
                   break;
        }
        result = (DateTimeUtil.getCurrentTimeMills()-countTimeMills)/1000;
        return result;
    }
    public static Long getFromeTime(int itemPos, Long time) {
        Long result;
        long countTimeMills = 0L;
        switch(itemPos) {
            case 0://分时
            case 1:
                countTimeMills = minute*COUNT_POINT;
                break;
            case 2://10分
                countTimeMills = minute*10*COUNT_POINT;
                break;
            case 3://1时K
                countTimeMills =hour*COUNT_POINT;
                break;
            case 4://日K
                countTimeMills = day*COUNT_POINT;
                break;
            case 5://5分K
                countTimeMills = minute*5*COUNT_POINT;
                break;
            case 6://30分
                countTimeMills = minute*30*COUNT_POINT;
                break;
            case 7://4分
                countTimeMills = minute*4*COUNT_POINT;
                break;
            case 8://周K
                countTimeMills = week*COUNT_POINT;
                break;
        }
        result = (time*1000-countTimeMills)/1000;
        return result;
    }
}
