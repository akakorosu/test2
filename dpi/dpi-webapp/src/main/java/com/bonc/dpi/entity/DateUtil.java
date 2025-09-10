package com.bonc.dpi.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author yantao E-mail:kengsinia@126.com
 * @version
 * @create time : 2016-1-13 下午7:41:46
 */
public class DateUtil extends org.apache.commons.lang.time.DateUtils {
	public static final String DEFAULT_FORMAT_MONTH = "yyyy-MM";
	public static final String DEFAULT_FORMAT_MONTH_T = "yyyyMM";
	public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";
	public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss.SSS";
	public static final String DEFAULT_FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FORMAT_T =  "yyyyMMddHHmmss";
	public static final String DEFAULT_FORMAT_DATE_ONE = "yyyyMMdd";

	public static String formatDate(long time) {
		return formatDate(time, DEFAULT_FORMAT);
	}
	public static Date parseDate(String time) {
		return parseDate(time, DEFAULT_FORMAT);
	}
	public static Date parseDate(String time, String format) {
		return parseDate(time, format, Locale.getDefault());
	}
	public static Date parseDate(String time, String format, Locale locale) {
		if (format == null) {
			format = DEFAULT_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
		try {
			return sdf.parse(time);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	public static String formatDate(long time, String format) {
		return formatDate(time, format, Locale.getDefault());
	}
	public static String formatDate(long time, String format, Locale locale) {
		Date date = new Date(time);
		if (format == null) {
			format = DEFAULT_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
		return sdf.format(date);
	}
	/**
	 * <p>
	 * 获取当前年月日
	 * </p>
	 * @return [参数说明]
	 * @return String 返回类型
	 * @author lihongliang
	 * @date 2016年1月26日 下午1:31:14
	 */
	public static String getToday(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String period = df.format(new Date()).toString();
		return period;
	}
	/**
	 * <p>
	 * 获取昨天年月日
	 * </p>
	 * @return [参数说明]
	 * @return String 返回类型
	 * @author lihongliang
	 * @date 2016年1月26日 下午1:32:10
	 */
	public static String getYestoday(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		date = calendar.getTime();
		String period = df.format(date).toString();
		return period;
	}
	/**
	 * <p>
	 * 获取上个月年月日
	 * </p>
	 * @return [参数说明]
	 * @return String 返回类型
	 * @author lihongliang
	 * @date 2016年1月26日 下午1:32:27
	 */
	public static String getMonth(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		date = calendar.getTime();
		String period = df.format(date).toString();
		return period;
	}

	//-----------------------------------------------------------------------------------------

	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT_DATE);
		return sdf.format(date);
	}

	public static String formatDateTwo(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT_DATE_ONE);
		return sdf.format(date);
	}

	/**
	 * 获取上个月的今天
	 * @return
	 */
	public static Date getPrevTodayByMonth() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -1);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 获取上个月的第一天
	 * @return
	 */
	public static Date getPrevTodayByMonthFirst() {
		Calendar cal_1 = Calendar.getInstance();//获取当前日期
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		Date date = cal_1.getTime();
		return date;
	}

	/**
	 * 获取当月的第一天
	 * @return
	 */
	public static Date getTodayByMonthFirst() {
		Calendar cal_1 = Calendar.getInstance();//获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		Date date = cal_1.getTime();
		return date;
	}

	/**
	 * 获取昨天
	 * @return
	 */
	public static Date getPrevTodayByDay1() {
		Calendar cal_1 = Calendar.getInstance();
		cal_1.add(Calendar.DAY_OF_MONTH, -1);
		Date date = cal_1.getTime();
		return date;
	}

	/**
	 * 获取前天
	 * @return
	 */
	public static Date getPrevTodayByDay2() {
		Calendar cal_1 = Calendar.getInstance();
		cal_1.add(Calendar.DAY_OF_MONTH, -2);
		Date date = cal_1.getTime();
		return date;
	}

	/**
	 * 根据当前时间计算近多少天的日期
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public static Date getCurrentLatelyDays(Integer num) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, - num);
		return calendar.getTime();
	}
	//获取前三个月的日期
	public static Date getBeginThreeMonth() {
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -3);  //设置为前3月
		return calendar.getTime();
	}

	/**
	 * 获取指定日期日开始
	 *
	 * @param date 指定日期
	 * @return Date
	 * @author andy
	 * @date 2017年1月6日
	 */
	public static Date getDateBegin(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE);
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取指定日期的前一天
	 *
	 * @param date 指定日期
	 * @return Date
	 * @author andy
	 * @date 2017年1月6日
	 */
	public static Date getYesterday(Date date){
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(getDateBegin(date));
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期的上个月今天
	 *
	 * @param date 指定日期
	 * @return Date
	 * @author andy
	 * @date 2017年1月6日
	 */
	public static Date getLastMonthToday(Date date){
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(getDateBegin(date));
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期上个月开始
	 *
	 * @param date 指定日期
	 * @return Date
	 * @author andy
	 * @date 2017年1月6日
	 */
	public static Date getLastMonthBegin(Date date) {
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(getDateBegin(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期上个月结束
	 *
	 * @param date 指定日期
	 * @return Date
	 * @author andy
	 * @date 2017年1月6日
	 */
	public static Date getLastMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(getDateBegin(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期月开始
	 *
	 * @param date 指定日期
	 * @return Date
	 * @author andy
	 * @date 2017年1月6日
	 */
	public static Date getMonthBegin(Date date) {
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(getDateBegin(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期月结束
	 *
	 * @param date 指定日期
	 * @return Date
	 * @author andy
	 * @date 2017年1月6日
	 */
	public static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(getDateBegin(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期下个月开始
	 *
	 * @param date 指定日期
	 * @return Date
	 * @author andy
	 * @date 2017年1月6日
	 */
	public static Date getNextMonthBegin(Date date) {
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(getDateBegin(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	/***
     * 日期月份加减月
     *
     * @param datetime
     *            日期(2014-11)
     * @return 2017-01
     */
    public static String monthAddSubtract(String datetime,Integer num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, num);
        date = cl.getTime();
        return sdf.format(date);
    }

    /***
     * 本月最大日期
     *
     * @param datetime
     *            日期(2014-11)
     * @return 2015-01
     */
    public static String monthLastDay(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.set(Calendar.DATE, cl.getActualMaximum(Calendar.DATE));
        date = cl.getTime();
        return sdf.format(date);
    }

    /**
     * 判断当天是否为月初第一天
     * @param args
     */

    public static boolean judgeMonthFirst() {
    		boolean ismonthFirst = false;
    		String now = getToday(DEFAULT_FORMAT_DATE);
    		String monthFirst = formatDate(getTodayByMonthFirst());
    		if(now.equals(monthFirst)) {
    			ismonthFirst = true;
    		}
    		return ismonthFirst;
    }

	public static Integer compareDate(String start,String end){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//这个地方时间规格可根据自己的需求修改
		long result = 0;
		try {
			result = sdf.parse(start).getTime() - sdf.parse(end).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result < 0L?Integer.valueOf(-1):(result == 0L?Integer.valueOf(0):Integer.valueOf(1));
	}

	public static void main(String[] args) {

//		System.err.println(DateUtil.getToday(DEFAULT_FORMAT_DATE));

//		System.out.println("获取前三个月的日期:" + DateUtil.formatDate(getBeginThreeMonth()));
//
//		System.out.println("获取上个月的第一天:" + DateUtil.formatDate(getPrevTodayByMonthFirst()));
//
//		System.out.println("获取上个月的当天:" + DateUtil.formatDate(getPrevTodayByMonth()));
//
//		System.out.println("获取今天:" + DateUtil.formatDate(new Date()));
//
//		System.out.println("获取当月第一天:" + DateUtil.formatDate(getTodayByMonthFirst()));
//
//		System.out.println("获取昨天:" + DateUtil.formatDateTwo(getPrevTodayByDay1()));
//
//		System.out.println("获取前天:" + DateUtil.formatDate(getPrevTodayByDay2()));
//
//		System.out.println("获取近多少天:" + DateUtil.formatDate(getCurrentLatelyDays(5)));

		System.err.println(DateUtil.judgeMonthFirst());

		System.err.println(getMonth(DEFAULT_FORMAT_MONTH_T));


	}
}
