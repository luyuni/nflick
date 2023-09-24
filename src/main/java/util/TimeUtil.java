package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat sdfShiFen = new SimpleDateFormat("HH:mm");

    public static SimpleDateFormat sdfStr = new SimpleDateFormat("yyyyMMddHHmmss");

    public static SimpleDateFormat sdfStr2 = new SimpleDateFormat("yyyyMMdd");

    public static SimpleDateFormat sdfSimpleCh = new SimpleDateFormat("yyyy年MM月dd日");

    public static Date StrToDate1(String s) {
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Date strToDateSimple(String s) {
        try {
            return sdfSimple.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Date strToDateShiFen(String s) {
        try {
            return sdfShiFen.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转成字符串
     *
     * @param date
     * @return
     */
    public static String dateToStrSimple(Date date) {
        return sdfSimple.format(date);
    }

    /**
     * 日期转成字符串 fmt yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String DateToStr2(Date date) {
        return sdfStr.format(date);
    }

    /**
     * 日期转成字符串 fmt yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String DateToStr3(Date date) {
        return sdfStr2.format(date);
    }

    /**
     * 日期转成字符串
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return sdf.format(date);
    }

    /**
     * yyyy年MM月dd日
     *
     * @param date
     * @return
     */
    public static String DateToFormatChStr(Date date) {
        return sdfSimpleCh.format(date);
    }

    /**
     * 时间跳转到周一的同一时分秒
     *
     * @param date
     * @return
     */
    public static Date getWeekStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 时间跳转到本月一号的同一时分秒
     *
     * @param date
     * @return
     */
    public static Date getMonthStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 时间跳转到本月一号的同一时分秒
     *
     * @param date
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date getMonthEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.getActualMaximum(cal.DATE));
        return cal.getTime();
    }

    /**
     * 时间跳转到周六的同一时分秒
     *
     * @param date
     * @return
     */
    public static Date getWeekStaDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return cal.getTime();
    }

    /**
     * 时间跳转到传入日期的零点
     *
     * @param date
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * 时间跳转到传入日期的末点
     *
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * 指定时间(24小时)
     *
     * @param date
     * @return
     */
    public static Date getTime(Date date, Integer hour, Integer minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 999);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * 时间跳转到i天前或者i天后的同一时分秒
     *
     * @param i
     * @return
     */
    public static Date getNextDay(int i) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, i);
        return cal.getTime();
    }

    /**
     * 时间跳转到i天前或者i天后的同一时分秒
     *
     * @param i
     * @return
     */
    public static Date getNextDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, i);
        return cal.getTime();
    }

    /**
     * 获得下周一的同一时分秒
     *
     * @param date
     * @return
     */
    public static Date getNextStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_MONTH, 1);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        return cal.getTime();
    }

    public static Date getWeekDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (i == 7) {
            cal.add(Calendar.WEEK_OF_MONTH, 1);
            cal.set(Calendar.DAY_OF_WEEK, 1);
        } else {
            i = i + 1;
            cal.set(Calendar.DAY_OF_WEEK, i);
        }

        return cal.getTime();
    }

    public static boolean isEqualsDate(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        } else if (date1 != null && date2 != null) {
            return sdfSimple.format(date1).equals(sdfSimple.format(date2));
        } else {
            return false;
        }
    }

    public static int compare(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return 0;
        } else if (date1.getTime() == date2.getTime()) {
            return 1;
        } else {
            return 2;
        }
    }

    public static boolean isSW(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.AM_PM) == 0) {
            return true;
        } else {
            return false;
        }

    }

    public static Date beforeMinute(Date date, int m) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, m);
        return cal.getTime();
    }

    public static List<String> toDateStrArr(Date startDate, Date endDate) {
        if (endDate.getTime() < startDate.getTime()) {
            throw new RuntimeException("结束时间必须大于或等于开始时间！");
        }
        String dateStr = dateToStrSimple(startDate);
        String endStr = dateToStrSimple(endDate);
        List<String> list = new ArrayList<String>();
        int i = 1;
        while (true) {
            list.add(dateStr);
            if (dateStr.equals(endStr)) {
                break;
            }
            dateStr = dateToStrSimple(getNextDay(startDate, i));
            i++;
        }
        return list;
    }

    /**
     * 转化为yyyyMMss类型的日期数组
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> toDateStr3Arr(Date startDate, Date endDate) {
        if (endDate.getTime() < startDate.getTime()) {
            throw new RuntimeException("结束时间必须大于或等于开始时间！");
        }
        String dateStr = DateToStr3(startDate);
        String endStr = DateToStr3(endDate);
        List<String> list = new ArrayList<String>();
        int i = 1;
        while (true) {
            list.add(dateStr);
            if (dateStr.equals(endStr)) {
                break;
            }
            dateStr = DateToStr3(getNextDay(startDate, i));
            i++;
        }
        return list;
    }

    public static Date ExcelStrToDate(String str) {
        int i = Integer.valueOf(str) - 42712;
        return getNextDay(strToDateSimple("2016-12-08"), i);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取相差的秒数
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static long secondBetween(Date startDate, Date endDate) throws ParseException {
        return (endDate.getTime() - startDate.getTime()) / 1000;
    }

    /**
     * 判断是否失效
     */
    public static boolean isExpire(Date d1, int expireSecond) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        cal.add(13, expireSecond);
        Date d2 = cal.getTime();
        return (new Date()).compareTo(d2) > 0;
    }

    /**
     * 获取当前时间前n个小时的整点时间
     */
    public static String getTimeHourBefore(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,
                calendar.get(Calendar.HOUR_OF_DAY) - hour);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));
        return df.format(calendar.getTime());
    }

    /**
     * 获取相差的分钟数
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static long minsBetween(Date startDate, Date endDate) throws ParseException {
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60);
    }

    /**
     * 获取指定格式的上个小时的Date 00：00
     *
     * @param i > 0 i小时之后的数据，< 0 则是i之前时间
     * @return
     */
    public static Date getBeforeHourDate(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + i);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定格式的上个小时的Date 的59：59
     *
     * @param i > 0 i小时之后的数据，< 0 则是i之前时间
     */
    public static Date getBeforeHourEndDate(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + i);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return calendar.getTime();
    }

    /**
     * 获取上个小时的指定格式的字符串
     *
     * @return
     */
    public static String getBeforeHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
        String time = df.format(calendar.getTime());
        return time;
    }

    /**
     * 获取传入时间的前后多少天的00:00:00
     */
    public static Date getStartTimeDayBefore(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取传入时间的前后多少天的23:59:59
     */
    public static Date getEndTimeDayBefore(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取带T的时间格式(京东绑卡签约的时间)
     *
     * @return
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HHmmss");
        String dateString = formatter1.format(currentTime) + "T" + formatter2.format(currentTime);
        return dateString;
    }

    /**
     * 判断是否是今日
     *
     * @param date
     * @param pattern
     * @return
     */
    public static boolean isToday(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//		System.out.println(getEndTimeDayBefore(new Date(),2));
        System.out.println(strToDateSimple("2018-05-25"));
    }

}
