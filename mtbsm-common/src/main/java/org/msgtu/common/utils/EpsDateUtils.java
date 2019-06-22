package org.msgtu.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpsDateUtils {
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    public static final String FORMAT_YYYYMM = "yyyyMM";

    public static final String FORMAT_HHMMSS = "HHmmss";

    public static final String FORMAT_YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static final String SEARCHMSG_DATEFORMAT = "MM/dd/yyyy";

    public static int getIntervalDays(String startDate, String endDate) {
        int intervalDays = 0;

        String startDateTemp = startDate.substring(0, 4)
                + startDate.substring(5, 7) + startDate.substring(8);

        String endDateTemp = endDate.substring(0, 4) + endDate.substring(5, 7)
                + endDate.substring(8);

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");

        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = formatDate.parse(startDateTemp);
            date2 = formatDate.parse(endDateTemp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            long len = date2.getTime() - date1.getTime();
            intervalDays = (int) (len / 86400000L);
        } catch (Exception len) {
            len.printStackTrace();
        }
        return intervalDays;
    }

    public static int getTodayBefore(String intervalDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, Integer.parseInt("-" + intervalDays));
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dayBefore = format.format(date);
        return Integer.parseInt(dayBefore);
    }

    public static int getTodayAfter(String intervalDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, Integer.parseInt(intervalDays));
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dayAfter = format.format(date);
        return Integer.parseInt(dayAfter);
    }

    public static String getTodayAfter(int intervalDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, intervalDays);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dayAfter = format.format(date);
        return dayAfter;
    }

    public static String getDayString(long time) {
        String timePattren = "yyyyMMdd";
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattren);
        return simpleDateFormat.format(date);
    }

    public static String getTimeString(long time) {

        return getLocalTimeString(time);
    }

    public static String getLocalTimeString(long time) {
        String timePattren = "yyyyMMddHHmmss";
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattren);
        return simpleDateFormat.format(date);
    }

    public static final String getCurrentTime(String format) {

        return getCurrentLocalTime(format);
    }

    public static final String getCurrentLocalTime(String timePattern) {
        SimpleDateFormat dfmt = new SimpleDateFormat(timePattern);
        return dfmt.format(new Date());
    }

    public static long getBetweenMillisTimes(String deleteAnonyUserTime) {
        Date currentTime = new Date();
        long currentMillisTimes = currentTime.getTime();
        SimpleDateFormat datformatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat minuteFormatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        String currentDate = datformatter.format(currentTime);
        currentDate = currentDate + " " + deleteAnonyUserTime;
        long betweenMinllisTime = -3274420716430163968L;
        try {
            Date lastTime = minuteFormatter.parse(currentDate);
            long lastMillisTimes = lastTime.getTime();
            betweenMinllisTime = lastMillisTimes - currentMillisTimes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (betweenMinllisTime <= -3274421334905454592L) {
            betweenMinllisTime = betweenMinllisTime + 86400000L;
        }
        return betweenMinllisTime;
    }

    public static String getNumericStrTime(String time) {
        if ((time == null) || ("".equals(time))) {
            return "";
        }
        String timeStr = time.replaceAll("[^0-9]", "");
        if (timeStr.length() < 14) {
            return timeStr;
        }

        return timeStr.substring(0, 14);
    }

    public static String getTodayAfter(String date, int intervalDays) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (date1 == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(5, intervalDays);
        Date date2 = calendar.getTime();
        String dayAfter = format.format(date2);
        return dayAfter;
    }

    public static String getRightFormatTime(String time, boolean removeZero) {
        Pattern pattern = Pattern
                .compile("((^\\d{2,4}-\\d{1,2}-\\d{1,2}( \\d{1,2}(:\\d{1,2}){1,2})?))");
        Matcher matcher = pattern.matcher(time);
        if (matcher.find()) {
            String newTime = matcher.group();
            if (removeZero) {
                newTime = newTime.replaceAll("-0", "-");
            }
            return newTime;
        }
        return "";
    }

    public static String getTodayCurrent() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 0);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd000000");
        return format.format(date);
    }

    public static String getWeekCurrent() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(4, 0);
        Date date = calendar.getTime();

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(7);
        int firstDayofWeek = now.get(5) + 2 - today;
        now.set(5, firstDayofWeek);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd000000");
        return format.format(now.getTime());
    }

    public static String getMonthCurrent() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, 0);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM01000000");
        return format.format(date);
    }

    public static String getTimeCurrentByPeriod(int period) {
        switch (period) {
            case 1:
                return getTodayCurrent();
            case 2:
                return getWeekCurrent();
            case 3:
        }

        return getMonthCurrent();
    }

    public static Date getStrFormatDate(String dateStr, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(dateStr);
        } catch (Exception localException) {
            date = null;
        }
        return date;
    }

    public static String getTimeString(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat1.parse(time);
            return simpleDateFormat.format(date);
        } catch (Throwable localThrowable) {
        }
        return "";
    }

    /**
     * 时间转成字符串
     *
     * @param date
     * @return
     */
    public static String getFormat(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS);
        String str = sdf.format(date);
        return str;
    }

    /**
     * 时间转成字符串
     *
     * @param date
     * @return
     */
    public static String getFormatTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS);
        String str = sdf.format(date);
        String tmp = str.substring(8);
        if (str != null && str.length() == 14 && tmp.equals("000000")) {
            str = str.substring(0, 8);
        }
        return str;
    }

    /**
     *
     * @param str
     * @return
     */
    public static Date strToDate(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        str = str.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
        if (str.length() != 8 && str.length() != 14) {
            return null;
        }
        if (str.length() == 8) {
            str = str + "000000";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS);
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String dateToStr(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(date);
        String tmp = str.substring(8);
        if (str != null && str.length() == 14 && tmp.equals("000000")) {
            str = str.substring(0, 8);
        }
        return str;
    }

    public static String getDataTimeFormat(String value) {
        if (value == null || value.length() != 14) {
            return value;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(value.substring(0, 4)).append("-")
                .append(value.substring(4, 6)).append("-")
                .append(value.substring(6, 8)).append(" ")
                .append(value.substring(8, 10)).append(":")
                .append(value.substring(10, 12)).append(":")
                .append(value.substring(12, 14));
        return sb.toString();
    }

    public static String getDataFormat(String value) {
        if (value == null || value.length() < 8) {
            return value;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(value.substring(0, 4)).append("-")
                .append(value.substring(4, 6)).append("-")
                .append(value.substring(6, 8));
        return sb.toString();
    }

    public static Date toDate(Object obj) {
        if(obj instanceof Date ){
            return (Date)obj;
        }else{
            if(obj==null){return null;}
            else{
                String s = obj.toString();
                if("".equals(s.trim())){return null;}
                else{
                    Date dt=GMTStrToDate(s);
                    if(dt==null){
                        dt = strToDate(s);
                    }

                    return dt;
                }
            }
        }
    }

    public static int getCurYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public static int getCurMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getCurDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }

    public static int getCurWeek() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static Date getCurDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }



    public static Date GMTStrToDate(String GMTDateStr){
       // if(if(GMTDateStr.indexOf("Tue Nov 22 2016 18:00:33 GMT+0800")))
        if(GMTDateStr.endsWith("GMT+0800")){
            try {
                return new Date(GMTDateStr);
            } catch (Exception e) {
                return null;
            }
        }else {
            String fmtstr = "d MMM yyyy HH:mm:ss 'GMT'";
            if (GMTDateStr.indexOf("UTC") > 0) {
                fmtstr = "d MMM yyyy HH:mm:ss 'UTC'";

            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.US);
            try {
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                return sdf.parse(GMTDateStr);
            } catch (Exception e) {
                return null;
            }
        }

    }

    public static String getCurDateStr(){
        return dateToStr(getCurDate(),"yyyy-MM-dd");
    }

    public static String getCurDateTimeStr(){
        return dateToStr(getCurDate(),FORMAT_YYYY_MM_DDHHMMSS);
    }


    public static  void main(String[] args){
        Date dt=toDate("12 Mar 2016 00:00:00 UTC");//"25 Nov 2015 01:29:16 GMT" Tue Nov 22 2016 18:00:33 GMT+0800
        System.out.println(new Date("Tue Nov 22 2016 18:00:33 GMT+0800"));
        System.out.println(dt instanceof Date);
        System.out.println(dt);
        System.out.println(dateToStr(dt, FORMAT_YYYY_MM_DDHHMMSS));
    }


}
