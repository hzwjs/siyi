package siyi.game.utill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * description: 日期工具类 <br>
 * date: 2020/2/28 22:57 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public class DateUtil {
    static final DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final DateTimeFormatter timeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * description: 获取时间差 <br>
     * version: 1.0 <br>
     * date: 2020/2/28 23:05 <br>
     * author: zhengzhiqiang <br>
     *
     * @param startTime
     * @param endTime
     * @return java.lang.String
     */
    public static String getTimeDiff(Date startTime, Date endTime) {
       if (startTime != null && endTime != null) {
           long diff = endTime.getTime() - startTime.getTime();//这样得到的差值是微秒级别
           long hours = diff / (1000 * 60 * 60); //获取时
           long minutes = (diff -  hours * (1000 * 60 * 60)) / (1000 * 60);  //获取分钟
           long s = (diff / 1000 - hours * 60 * 60 - minutes * 60);//获取秒
           String CountTime =  hours + "小时" + minutes + "分" + s + "秒";
           return CountTime;
       }
           return "";
    }

    /**
     * description: date类型转Calendar <br>
     * version: 1.0 <br>
     * date: 2020/2/28 22:59 <br>
     * author: zhengzhiqiang <br>
     *
     * @param date
     * @return java.util.Calendar
     */
    public static Calendar dataToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取当前日期字符串
     * @return
     */
    public static String nowStringSimpleDate() {
        LocalDate now = LocalDate.now();
        String nowStr = now.format(dateFormatter);
        return nowStr;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static LocalDate nowSimpleDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前时间字符串
     * @return
     */
    public static String nowStringTime() {
        LocalDateTime now = LocalDateTime.now();
        String nowStr = now.format(timeFormatter);
        return nowStr;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static LocalDateTime nowTime() {
        return LocalDateTime.now();
    }


}
