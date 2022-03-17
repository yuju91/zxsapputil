package com.zxs.commonlyUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressLint("SimpleDateFormat")
public class GtDateUtil {

    public static final String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM = "yyyy-MM";
    public static final String yyyy年MM月dd日 = "yyyy年MM月dd日";
    public static final String yyyy年MM月dd日EEEE = "yyyy年MM月dd日 EEEE";
    public static final String yyyy年MM月dd日_HH_mm_ss = "yyyy年MM月dd日 HH:mm:ss";
    public static final String HH_mm = "HH:mm";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String yyyyMMdd = "yyyyMM/dd";

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getYear(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.MONTH);
    }

    public static int getDayOfMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonthInterval(Calendar start, Calendar end) {
        if (start.after(end)) {
            return Integer.MIN_VALUE;
        } else {
            return (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12 +
                    (end.get(Calendar.MONTH) - start.get(Calendar.MONTH));
        }
    }

    public static String getDateTime(Context context, long time) {
        if (time <= 0) {
            return "";
        }
        Date date = new Date(time);
        java.text.DateFormat dateFormat = DateFormat.getDateFormat(context);
        java.text.DateFormat timeFormat = DateFormat.getTimeFormat(context);
        return dateFormat.format(date) + " " + timeFormat.format(date);
    }

    public static String getDateTime(String regex, long time) {
        return new SimpleDateFormat(regex).format(time);
    }

    public static String getDateTime(long time) {
        if (time < 0) {
            return "";
        }
        return new SimpleDateFormat("yyyy.MM.dd  HH:mm").format(time);
    }


    /**
     * 获取时间含小时，显示
     */
    public static String getTime(long timeDiff) {
        String strTime = "00:00:00";
        if (timeDiff / 1000 / 3600 > 0) {
//            strTime = String.format("%1$s:%2$s:%3$", timeDiff / 3600,
//                    (timeDiff % 3600) / 60, (timeDiff % 3600) % 60);
            strTime = (timeDiff / 1000 / 3600) + ":"
                    + ((timeDiff / 1000 % 3600) / 60 < 10 ? "0" + (timeDiff / 1000 % 3600) / 60 : (timeDiff / 1000 % 3600) / 60)
                    + ":" + ((timeDiff / 1000 % 3600) % 60 < 10 ? "0" + (timeDiff / 1000 % 3600) % 60 : ((timeDiff / 1000 % 3600) % 60) < 10 ? ("0" + ((timeDiff / 1000 % 3600) % 60)) : ((timeDiff / 1000 % 3600) % 60));
        } else if ((timeDiff / 1000 / 60) >= 1) {
            if (timeDiff / 1000 / 60 < 59) {
                strTime = "00:" + (timeDiff / 1000 / 60 < 10 ? ("0" + (timeDiff / 1000 / 60)) : (timeDiff / 1000 / 60)) + ":" + ((timeDiff / 1000 % 60) < 10 ? "0" + (timeDiff / 1000 % 60) : (timeDiff / 1000 % 60));
            } else {//边界操作
                strTime = "1:" + "00:" + "00";
            }

        } else if (timeDiff / 1000 / 60 < 1) {
            strTime = "00:" + "00:" + (timeDiff / 1000 < 10 ? "0" + timeDiff / 1000 : timeDiff / 1000);
        } else {
            strTime = "00:" + "01:" + "00";
        }
        return strTime;
    }

    /**
     * 获取时间，显示
     */
    public static String getTimeMaxMinu(long timeDiff) {
        String strTime = "00:00:00";
        if (timeDiff / 1000 / 3600 > 0) {
//            strTime = String.format("%1$s:%2$s:%3$", timeDiff / 3600,
//                    (timeDiff % 3600) / 60, (timeDiff % 3600) % 60);
            strTime = (timeDiff / 1000 / 3600) + ":"
                    + ((timeDiff / 1000 % 3600) / 60 < 10 ? "0" + (timeDiff / 1000 % 3600) / 60 : (timeDiff / 1000 % 3600) / 60)
                    + ":" + ((timeDiff / 1000 % 3600) % 60 < 10 ? "0" + (timeDiff / 1000 % 3600) % 60 : (timeDiff / 1000 % 3600) % 60);
        } else if ((timeDiff / 1000 / 60) >= 1) {
            if (timeDiff / 1000 / 60 < 59) {
                strTime = (timeDiff / 1000 / 60 < 10 ? ("0" + (timeDiff / 1000 / 60)) : (timeDiff / 1000 / 60)) + ":" + (timeDiff / 1000 % 60);
            } else {//边界操作
                strTime = "1:" + "00:" + "00";
            }

        } else if (timeDiff / 1000 / 60 < 1) {
            strTime = "00:" + (timeDiff / 1000 < 10 ? "0" + timeDiff / 1000 : timeDiff / 1000);
        } else {
            strTime = "01:" + "00";
        }
        return strTime;
    }

    public static String getDate(Context context, long time) {
        java.text.DateFormat format = DateFormat.getDateFormat(context);
        return format.format(new Date(time));
    }

    public static String getDate(long time) {
        if (time < 0) {
            return "";
        }
        return new SimpleDateFormat("yyyy.MM.dd").format(time);
    }

    public static CharSequence getDateMonthDay(long time) {
        return DateFormat.format("MM.dd", time);
    }

    public static String getDate(Context context, int year, int monthOfYear, int dayOfMonth) {
        java.text.DateFormat format = DateFormat.getDateFormat(context);
        Date date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
        return format.format(date);
    }

    public static String getDate(int year, int month, int dayOfMonth) {
        return new SimpleDateFormat("yyyy.MM.dd").format(getTimeLong(year, month, dayOfMonth));
    }

    public static long getDateLong(int year, int month, int dayOfMonth) {
        return new GregorianCalendar(year, month, dayOfMonth).getTimeInMillis();
    }

    public static int getMonth(int month) {
        return month - 1;
    }

    public static String getMonth2Digits(int month) {
        return month % 10 == 0 ? "0" + month : "" + month;
    }

    public static String getDay2Digits(int day) {
        return getMonth2Digits(day);
    }

    public static String getTime(Context context, long time) {
        java.text.DateFormat format = DateFormat.getTimeFormat(context);
        return format.format(new Date(time));
    }

    public static long getTimeLong(String regex, String time) {
        try {
            return new SimpleDateFormat(regex).parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0l;
        }
    }

    public static String getNowDateTime() {
        return getTimeString(System.currentTimeMillis(), yyyy_MM_dd_HH_mm_ss);
    }

    /*上传图片的日期*/
    public static String getNowDateTimePic() {
        return getTimeString(System.currentTimeMillis(), yyyyMMdd);
    }

    public static String getTimeString(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(time));
    }

    public static String getTime(Context context, int hour, int minute) {
        java.text.DateFormat format = DateFormat.getTimeFormat(context);
        return format.format(new GregorianCalendar(0, 0, 0, hour, minute).getTimeInMillis());
    }

    public static String getTime(String regex, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return new SimpleDateFormat(regex).format(calendar.getTimeInMillis());
    }

    public static long getTimeLong(int year, int monthOfYear, int dayOfMonth) {
        return new GregorianCalendar(year, monthOfYear, dayOfMonth).getTimeInMillis();
    }

    public static long getTimeLong(int year, int monthOfYear, int dayOfMonth, int hour, int minute) {
        return new GregorianCalendar(year, monthOfYear, dayOfMonth, hour, minute).getTimeInMillis();
    }

    public static long getTimeLong(int year, int monthOfYear, int dayOfMonth, int hour, int minute, int second) {
        return new GregorianCalendar(year, monthOfYear - 1, dayOfMonth, hour, minute, second).getTimeInMillis();
    }

    public static String getDateSys(Context context, int year, int month, int day) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);
        return DateUtils.formatDateTime(context,
                mCalendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_SHOW_WEEKDAY
                        | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_ABBREV_MONTH
                        | DateUtils.FORMAT_ABBREV_WEEKDAY);
    }

    public static String getYearMonth(Context context, int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return DateUtils.formatDateTime(context, calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_NO_MONTH_DAY);
    }

    public static int getHour(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.MINUTE);
    }

    public static boolean isToday(long time) {
        Calendar target = Calendar.getInstance();
        target.setTimeInMillis(time);
        Calendar today = Calendar.getInstance();
        if (target.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                target.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }
        return false;
    }

//    public static String getTimeByDouble(double time) {
//        int i = (int) time;
//        return i + ":" + DataUtil.matchLength((int) (60 * (time - i)), 2);
//    }

    public static boolean compare_date(String start, String end, String pat) {


        SimpleDateFormat df = new SimpleDateFormat(pat);
        try {
            Date dt1 = df.parse(start);
            Date dt2 = df.parse(end);
            if (dt1.getTime() > dt2.getTime()) {
                return false;
            } else if (dt1.getTime() < dt2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }

}
