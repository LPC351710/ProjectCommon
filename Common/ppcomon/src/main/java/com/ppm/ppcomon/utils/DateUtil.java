package com.ppm.ppcomon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 跟日期相关的工具类
 */
public class DateUtil {

    public static final String FEED_DATE_FORMAT = "MM-dd HH:mm a";

    /**
     * 按格式把String转化成Date对象
     *
     * @param aMask   时间格式
     * @param strDate
     * @return
     */
    public static final Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        if (strDate == null || "".equals(strDate)) {
            return date;
        }
        df = new SimpleDateFormat(aMask);
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 按格式把Date转化成String对象
     *
     * @param aMask 时间格式
     * @param aDate
     * @return
     */
    public static final String convertDateToString(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return returnValue;
    }

    /**
     * 按格式把Date转化成Integer对象
     *
     * @param aMask 时间格式
     * @param aDate
     * @return
     */
    public static final Integer convertDateToInteger(String aMask, Date aDate) {
        String strDate = convertDateToString(aMask, aDate);
        Integer iDate = null;
        if (strDate != null && !"".equals(strDate)) {
            iDate = Integer.valueOf(strDate);
        }
        return iDate;
    }

    public static String millisToStr(long timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0800"));
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static String millisToStr(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0800"));
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    /**
     * 获得传入日期 date 偏移 offsetDay 天数的00:00:00
     *
     * @param date
     * @param offsetDay 偏移天数
     * @return
     */
    public static Date getOffsetDateBegin(Date date, int offsetDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, offsetDay);
        return c.getTime();
    }

    /**
     * 获取某天的开始时间戳
     *
     * @param format
     * @param dateStr
     * @return
     */
    public static long getDateBeginTimestamp(String format, String dateStr) {
        Date dt = convertStringToDate(format, dateStr);
        if (dt == null) {
            return 0l;
        } else {
            Date realDt = getOffsetDateBegin(dt, 0);
            return realDt.getTime();
        }

    }

    /**
     * 获取某天的结束时间戳
     *
     * @param format
     * @param dateStr
     * @return
     */
    public static long getDateEndTimestamp(String format, String dateStr) {
        Date dt = convertStringToDate(format, dateStr);
        if (dt == null) {
            return 0l;
        } else {
            Date realDt = getOffsetDateBegin(dt, 1);
            return realDt.getTime();
        }
    }

    /**
     * 传入时间距离当前时间的差距描述
     *
     * @param cmpDate
     * @return
     */
    public static String dataTime2NowDuration(Date cmpDate) {
        if (cmpDate == null) {
            return "未知";
        }

        Date now = new Date();
        long duration = now.getTime() - cmpDate.getTime();
        if (duration < (60 * 1000)) {
            return "刚刚";
        } else if (duration >= 60 * 1000 && duration < 3600 * 1000) {
            return duration / (60 * 1000) + "分钟前";
        } else if (duration >= 3600 * 1000 && duration < 3600 * 24 * 1000) {
            return duration / (3600 * 1000) + "小时前";
        } else if (duration >= 3600 * 24 * 1000 && duration < 3600 * 24 * 30 * 1000) {
            return duration / (3600 * 24 * 1000) + "天前";
        } else if (duration >= 3600 * 24 * 30 * 1000 && duration < 3600 * 24 * 30 * 12 * 1000) {
            return duration / (3600 * 24 * 30 * 1000) + "月前";
        } else {
            return "很久以前";
        }
    }

    /**
     * 判断是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean chkSameDay(Date date1, Date date2) {
        if ((date1 == null) || (date2 == null)) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date1).equals(sdf.format(date2));
    }

    /**
     * 得到date所在月份的第一天00:00:00
     *
     * @param date
     * @return
     */
    public static Date getMonthFirstDateOfOneDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * 得到date所在月份的最后一天00:00:00
     *
     * @param date
     * @return
     */
    public static Date getMonthLastDateOfOneDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);// 设为当前月的1号
        c.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        c.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * 得到 date 所在月份下月的第一天00:00:00
     *
     * @param date
     * @return
     */
    public static Date getNextMonthFirstDateOfOneDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);    // 加一个月
        c.set(Calendar.DATE, 1);    // 把日期设置为当月第一天

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * 得到 date 所在月份上月的第一天00:00:00
     *
     * @param date
     * @return
     */
    public static Date getBeforeMonthFirstDateOfOneDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);    // 加一个月
        c.set(Calendar.DATE, 1);    // 把日期设置为当月第一天

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }


    /**
     * 判断给定字符串时间是否为今日(效率不是很高，不过也是一种方法)
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean isYesterday(String day) {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = getDateFormat().parse(day);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    public static String getTimeStr(long timeMillis) {
        try {
            String date = DateUtil.millisToStr(timeMillis, "yyyy-MM-dd HH:mm:ss");
            if (isToday(date)) {
                return millisToStr(timeMillis, "HH:mm");
            } else if (isYesterday(date)) {
                return "昨天 " + millisToStr(timeMillis, "HH:mm");
            } else {
                return millisToStr(timeMillis, "MM-dd HH:mm");
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @param times   时间为： 秒
     * @param pattern 传null或者""则表示"yyyy-MM-dd HH:mm"的匹配 或yyyy年MM月dd日HH时mm分ss秒E
     * @return HH:mm
     */
    public static String getSimpleDate(long times, String pattern) {
        if (null == pattern || "".equals(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        return simpleDateFormat.format(times * 1000);
    }

    /**
     * @param seconds 秒为单位
     * @return
     */
    public static boolean isToday(long seconds) {
        String todayDate = getSimpleDate(System.currentTimeMillis() / 1000, "yyyy-MM-dd");
        String serverDate = getSimpleDate(seconds, "yyyy-MM-dd");
        if (todayDate.equals(serverDate)) {
            return true;
        }
        return false;
    }

    /**
     * 是否是昨天
     *
     * @param seconds 秒
     * @return
     */
    public static boolean isYesterday(long seconds) {
        String yesTodayDate = getSimpleDate((System.currentTimeMillis() / 1000 - 24 * 60 * 60), "yyyy-MM-dd");
        String serverDate = getSimpleDate(seconds, "yyyy-MM-dd");
        if (yesTodayDate.equals(serverDate)) {
            return true;
        }
        return false;
    }

    /**
     * @param seconds
     * @return
     */
    public static boolean isCurrentMonth(long seconds) {
        String todayMonthDay = getSimpleDate(System.currentTimeMillis() / 1000, "yyyy-MM");
        String monthDay = getSimpleDate(seconds, "yyyy-MM");
        if (todayMonthDay.equals(monthDay)) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前日期是周几<br>
     *
     * @param dt
     * @return 当前日期是周几
     */
    public static int getWeekOfDate(Date dt) {
        /*String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};*/
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;//0、1、2、3、4、5、6

        return w;
    }

    public static String getWeekStr(Date dt) {
        int dayOfWeek = getWeekOfDate(dt);
        String date = "";
        switch (dayOfWeek) {
            case 0:
                date = "周日";
                break;
            case 1:
                date = "周一";
                break;
            case 2:
                date = "周二";
                break;
            case 3:
                date = "周三";
                break;
            case 4:
                date = "周四";
                break;
            case 5:
                date = "周五";
                break;
            case 6:
                date = "周六";
                break;
            default:
                break;
        }
        return date;
    }

    public static String getWeekStr(long timestamp) {
        Date date = new Date(timestamp * 1000);
        return getWeekStr(date);
    }

    /**
     * @param mills 为毫秒
     * @return
     */
    public static String millsToWeekStr(long mills) {
        return getWeekStr(new Date(mills));
    }

    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     * yyyy-MM-dd-HH-mm-ss(时间戳的格式)
     *
     * @param time
     * @return
     */
    public static String dateStrToSeconds(String time, String timePattern) {
        SimpleDateFormat sdr = new SimpleDateFormat(timePattern,
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }

    public static String getTimeToCurrentStr(long timeMillis, long currentTime) {
        long time = currentTime - timeMillis;
        float min = time / 60;
        if (min < 1) {
            return "刚刚";
        } else if (min > 10) {
            return millisToStr(timeMillis, "HH:mm");
        } else {
            return (int) Math.floor(min) + "分钟前";
        }
    }

    /**
     * 将秒数转换为日时分秒，
     *
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long days = second / 86400;            //转换天数
        second = second % 86400;            //剩余秒数
        long hours = second / 3600;            //转换小时
        second = second % 3600;                //剩余秒数
        long minutes = second / 60;            //转换分钟
        second = second % 60;                //剩余秒数
        if (days > 0) {
            return days + "天" + hours + "小时" + minutes + "分" + second + "秒";
        } else {
            return hours + "小时" + minutes + "分" + second + "秒";
        }
    }
}
