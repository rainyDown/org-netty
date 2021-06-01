package org.netty.other.util;



import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.netty.other.constants.Constants;
import org.netty.other.constants.SymbolConstants;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author luowx on 2018/8/14 0014.
 */
public class MyDateUtils extends DateUtils {
    public static final String TIME_OF_DAY_BEGIN = " 00:00:00";
    public static final String TIME_OF_DAY_END = " 23:59:59";
    public static final String TIME_OF_DAY_BEGIN_SHORT = " 00:00";
    public static final String TIME_OF_DAY_SHORT = " 23:59";
    private static String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS"};
    private static Date startTime = null;
    private static Date nextStartTime = null;
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    public MyDateUtils() {
    }

    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }

        return formatDate;
    }

    public static String getTime() {
        return formatDate(new Date(), new Object[]{"HH:mm:ss"});
    }

    public static String getDateTime() {
        return formatDate(new Date(), new Object[]{"yyyy-MM-dd HH:mm:ss"});
    }

    public static String getYear() {
        return formatDate(new Date(), new Object[]{"yyyy"});
    }

    public static int getMonth() {
        return getMonth(new Date());
    }

    public static String getDay() {
        return formatDate(new Date(), new Object[]{"dd"});
    }

    public static String getDay(Date date) {
        return formatDate(date, new Object[]{"dd"});
    }

    public static String getWeek() {
        return formatDate(new Date(), new Object[]{"E"});
    }

    public static Date parseDate(String str) {
        try {
            return parseDate(str, parsePatterns);
        } catch (ParseException var2) {
            return null;
        }
    }

    public static Date parseDate(String str, String pattern) {
        try {
            return parseDate(str, new String[]{pattern});
        } catch (ParseException var2) {
            return null;
        }
    }

    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / 86400000L;
    }

    public static long getTimeStampSeconds() {
        return System.currentTimeMillis() / 1000L;
    }

    public static long getTimeStampMills() {
        return System.currentTimeMillis();
    }

    public static String getCurrTimeStampString() {
        return String.valueOf(getTimeStampSeconds());
    }

    public static long getTimeStampSeconds(Date date) {
        return date.getTime() / 1000L;
    }

    public static Date getDate(long timeStamp) {
        Date date = new Date();
        date.setTime(timeStamp);
        return date;
    }

    public static Date getDate(long timeStamp, Object... pattern) {
        Date date = new Date();
        date.setTime(timeStamp);
        return date;
    }

    public static String getDateString(long timeStamp, Object... pattern) {
        return formatDate(getDate(timeStamp), pattern);
    }

    public static String getDateString(String timeStamp) {
        return timeStamp != null && !"".equals(timeStamp) ? getDateString(Long.valueOf(timeStamp).longValue(), new Object[]{parsePatterns[1]}) : "";
    }

    public static String getSimpleDateString(String timeStamp) {
        return timeStamp != null && !"".equals(timeStamp) ? getDateString(Long.valueOf(timeStamp).longValue(), new Object[]{parsePatterns[0]}) : "";
    }

    public static int getDateLong() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public static String addDays(String now, int days) {
        Date nowDate = getDate(Long.valueOf(now).longValue());
        Date newDate = addDays(nowDate, days);
        return String.valueOf(getTimeStampSeconds(newDate));
    }

    public static String addMonths(String now, int months) {
        Date nowDate = getDate(Long.valueOf(now).longValue());
        Date newDate = addMonths(nowDate, months);
        return String.valueOf(getTimeStampSeconds(newDate));
    }

    public static Date addTime(int type, Date nowDate, int amount) {
        Date retDate = nowDate;
        if (1 == type) {
            retDate = addDays(nowDate, amount);
        } else if (2 == type) {
            retDate = addMonths(nowDate, amount);
        } else if (3 == type) {
            retDate = addMonths(nowDate, amount * 3);
        } else if (4 == type) {
            retDate = addYears(nowDate, amount);
        }

        return retDate;
    }

    /**
     * start time of day
     */
    public static Date setDayZero(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * end time of day
     */
    public static Date setDayLast(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        return cal.getTime();
    }

    /**
     * 获取提交时间的小时
     *
     * @param time
     * @return
     * @throws NullPointerException when time format is not hh:mm
     */
    public static int getHours(String time) {
        return Integer.valueOf(time.split(SymbolConstants.COLON_SYMBOL)[0]);
    }

    public static int revisalHours(int hours) {
        return hours >= Constants.HOURS_OF_DAY ? hours - Constants.HOURS_OF_DAY : (hours < Constants.ZERO_LONG ? hours + Constants.HOURS_OF_DAY : hours);
    }

    public static String revisalMinutes(int minutes) {
        return minutes >= 10 ? minutes + "" : "0" + minutes;
    }

    /**
     * 获取提交时间的小时
     *
     * @param time
     * @return
     * @throws NullPointerException when time format is not hh:mm
     */
    public static int getMinutes(String time) {
        return Integer.valueOf(time.split(SymbolConstants.COLON_SYMBOL)[1]);
    }

    public static Timestamp getBeginTimeOfDate(String beginDate) {
        return Timestamp.valueOf(beginDate + " 00:00:00");
    }

    public static Timestamp getEndTimeOfDate(String endDate) {
        return Timestamp.valueOf(endDate + " 23:59:59");
    }

    public static Date getLastDayStartTime(Date date) {
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(date);
        lastDay.add(5, -1);
        lastDay.set(11, 0);
        lastDay.set(12, 0);
        lastDay.set(13, 0);
        lastDay.set(14, 0);
        startTime = lastDay.getTime();
        return startTime;
    }

    public static Date getDayStartTime(Date date) {
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        today.set(11, 0);
        today.set(12, 0);
        today.set(13, 0);
        today.set(14, 0);
        startTime = today.getTime();
        return startTime;
    }

    public static Date getNextDayStartTime(Date date) {
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        today.set(11, 0);
        today.set(12, 0);
        today.set(13, 0);
        today.set(14, 0);
        today.add(5, 1);
        nextStartTime = today.getTime();
        return nextStartTime;
    }

    public static Date getWeekStartTime(Date date) {
        Calendar weekStart = Calendar.getInstance();
        weekStart.setTime(date);
        weekStart.set(7, 2);
        weekStart.set(11, 0);
        weekStart.set(12, 0);
        weekStart.set(13, 0);
        weekStart.set(14, 0);
        Date startTime = weekStart.getTime();
        return startTime;
    }

    public static Date getNextWeekStartTime(Date date) {
        Calendar weekStart = Calendar.getInstance();
        weekStart.setTime(date);
        weekStart.set(7, 2);
        weekStart.set(11, 0);
        weekStart.set(12, 0);
        weekStart.set(13, 0);
        weekStart.set(14, 0);
        weekStart.add(4, 1);
        Date nextStartTime = weekStart.getTime();
        return nextStartTime;
    }

    public static Date getMonthStartTime(Date date) {
        Calendar monthStart = Calendar.getInstance();
        monthStart.setTime(date);
        monthStart.set(5, 1);
        monthStart.set(11, 0);
        monthStart.set(12, 0);
        monthStart.set(13, 0);
        monthStart.set(14, 0);
        startTime = monthStart.getTime();
        return startTime;
    }

    public static Date getNextMonthStartTime(Date date) {
        Calendar monthStart = Calendar.getInstance();
        monthStart.setTime(date);
        monthStart.set(5, 1);
        monthStart.set(11, 0);
        monthStart.set(12, 0);
        monthStart.set(13, 0);
        monthStart.set(14, 0);
        monthStart.add(2, 1);
        nextStartTime = monthStart.getTime();
        return nextStartTime;
    }

    public static Date getLastMonthStartTime(Date date) {
        Calendar monthStart = Calendar.getInstance();
        monthStart.setTime(date);
        monthStart.set(5, 1);
        monthStart.set(11, 0);
        monthStart.set(12, 0);
        monthStart.set(13, 0);
        monthStart.set(14, 0);
        monthStart.add(2, -1);
        nextStartTime = monthStart.getTime();
        return nextStartTime;
    }

    public static Date getYearStartTime(Date date) {
        Calendar yearStart = Calendar.getInstance();
        yearStart.setTime(date);
        yearStart.set(6, 1);
        yearStart.set(11, 0);
        yearStart.set(12, 0);
        yearStart.set(13, 0);
        yearStart.set(14, 0);
        startTime = yearStart.getTime();
        return startTime;
    }

    public static Date getNextYearStartTime(Date date) {
        Calendar yearStart = Calendar.getInstance();
        yearStart.setTime(date);
        yearStart.set(6, 1);
        yearStart.set(11, 0);
        yearStart.set(12, 0);
        yearStart.set(13, 0);
        yearStart.set(14, 0);
        yearStart.add(1, 1);
        nextStartTime = yearStart.getTime();
        return nextStartTime;
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static String getYear(Date date) {
        return formatDate(date, new Object[]{"yyyy"});
    }

    public static Date getMonthStartTime(Date date, int i) {
        Calendar monthStart = Calendar.getInstance();
        monthStart.setTime(date);
        monthStart.set(5, 1);
        monthStart.set(11, 0);
        monthStart.set(12, 0);
        monthStart.set(13, 0);
        monthStart.set(14, 0);
        monthStart.add(2, i);
        nextStartTime = monthStart.getTime();
        return nextStartTime;
    }

    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    public static int daysOfTwo(Date startDate, Date endDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(startDate);
        int day1 = aCalendar.get(6);
        aCalendar.setTime(endDate);
        int day2 = aCalendar.get(6);
        return Math.abs(day2 - day1);
    }

    public static Boolean getDateRange(String beginDate, String endDate) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long newDate = date.getTime();
        long begin = 0L;
        long end = 0L;

        try {
            c.setTime(sdf.parse(beginDate));
            begin = c.getTimeInMillis();
            c.setTime(sdf.parse(endDate));
            end = c.getTimeInMillis();
        } catch (ParseException var12) {
            var12.printStackTrace();
        }

        return newDate >= begin && newDate < end ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }

    public static Date getLastDay(Date date) {
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(date);
        lastDay.add(5, -1);
        return lastDay.getTime();
    }

    public static Date getMonthTime(String queryMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        if (queryMonth == null) {
            queryMonth = sdf.format(new Date());
        }

        try {
            Date monthTime = sdf.parse(queryMonth);
            return monthTime;
        } catch (ParseException var4) {
            //new BizException(OnpremiseErrorEnum.SYSTEM_ERROR.getErrorCode(), var4.getMessage());
        }
        return null;
    }

    public static int getDaysOfMonth(String queryMonth) {
        new SimpleDateFormat("yyyy年MM月");

        try {
            int days = daysBetween(getMonthStartTime(getMonthTime(queryMonth)), getNextMonthStartTime(getMonthTime(queryMonth)));
            return days;
        } catch (ParseException var4) {
            //new BizException(OnpremiseErrorEnum.SYSTEM_ERROR.getErrorCode(), var4.getMessage());
        }
        return 0;
    }

    /**
     * 校验格式YYYY-MM-DD
     *
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 校验时间格式HH:MM（精确）
     */
    public static boolean checkHHMMTime(String time) {
        if (checkHHMM(time)) {
            String[] temp = time.split(":");
            if ((temp[0].length() == 2 || temp[0].length() == 1) && temp[1].length() == 2) {
                int h, m;
                try {
                    h = Integer.parseInt(temp[0]);
                    m = Integer.parseInt(temp[1]);
                } catch (NumberFormatException e) {
                    return false;
                }
                if (h >= 0 && h <= 24 && m <= 60 && m >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkHHMM(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        try {
            dateFormat.parse(time);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static Integer formatIntegerDay(Long timeInMillis) {
        Date date = new Date(timeInMillis);
        return Integer.parseInt(format(FORMAT_YYYYMMDD, date));
    }

    public static Integer formatIntegerDay(Date date) {
        return Integer.parseInt(format(FORMAT_YYYYMMDD, date));
    }

    public static String format(String format, Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }

    public static void main(String[] args) {
//        System.out.println(getTimeStampMills());
//        System.out.println(getTimeStampMills() + 10 * 1000);
        System.out.println(parseDate("2020-04-04 12:12:12").getTime());
    }
}
