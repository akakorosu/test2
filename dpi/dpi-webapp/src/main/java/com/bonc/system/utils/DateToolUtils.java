package com.bonc.system.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateToolUtils {

    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYY_MM_DD_HH_MM_SS_SLASH = "yyyy/MM/dd HH:mm:ss";
    public final static String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public final static String yyyyMMdd = "yyyy/MM/dd";
    public final static String yyyy_MM_dd = "yyyy-MM-dd";
    public final static String yyMMdd = "yyyyMMdd";
    public static final String DATE_FORMAT_SSS = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String DATE_HOUR = "HH";
    public static final String DATE_DAY = "dd";
    
    public static final String DATE_STR_LINE = "-";
    
    private static final String[] WEEK_DAYS = { "7", "1", "2", "3", "4", "5", "6" };

    /**
     * 
     * <P>
     * Function: getCurrentDateTime
     * <P>
     * Description: //定义日期的格式
     * <P>
     * Others:
     * 
     * @author:
     * @CreateTime: 2012-9-26
     * @param formate
     *            转换为日期型的字符串
     * @return String
     */
    public static String getCurrentDateTime(String formate) {
        Date currentDate = getDBDate();
        SimpleDateFormat formatdater = getSimpleDateFormat(formate);
        return formatdater.format(currentDate);
    }
    
    /*** 
     * 日期月份加减月 
     *  
     * @param datetime 
     *            日期(2014-11) 
     * @return 2014-10 
     */  
    public static String monthAddSubtract(String datetime,Integer num) {  
        SimpleDateFormat sdf = new SimpleDateFormat(yyMMdd);  
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
    
    /**
     * 
     * <P>
     * Function: addDays
     * <P>
     * Description: //返回指定多少天之后的日期进行日期转换
     * <P>
     * Others:
     * 
     * @author:
     * @CreateTime: 2012-9-26
     * @param date
     *            //转换为日期型的字符串
     * @param offset
     *            //相隔多少天
     * @param formate
     *            //转换为日期型的字符串
     * @return String
     * @throws ParseException
     */
    public static String addDays(String date, int offset, String formate) throws ParseException {
        if (null == date) {
            return null;
        }
        SimpleDateFormat formatdater = getSimpleDateFormat(formate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatdater.parse(date));
        calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) + offset));
        return formatdater.format(calendar.getTime());
    }
    
    public static String addYears(String date, int offset, String formate) throws ParseException {
        if (null == date) {
            return null;
        }
        SimpleDateFormat formatdater = getSimpleDateFormat(formate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatdater.parse(date));
        calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) + offset));
        return formatdater.format(calendar.getTime());
    }
    
    /**
     * 获得数据库当前时间
     * <P>
     * Function: getDBDate
     * <P>
     * Description:返回数据库时间
     * <P>
     * Others:返回日期
     * 
     * @author:
     * @CreateTime: 2012-9-26
     * @return Date
     */
    public static Date getDBDate() {
        return new java.sql.Timestamp(new Date().getTime());
    }
    /**
     * <P>
     * Function: getSimpleDateFormat
     * <P>
     * Description:
     * <P>
     * Others:返回SimpleDateFormat
     * 
     * @author:
     * @CreateTime: 2012-9-26
     * @param formate
     * @return SimpleDateFormat
     */
    protected static SimpleDateFormat getSimpleDateFormat(String formate) {
        SimpleDateFormat formatdater = new SimpleDateFormat(formate);
        return formatdater;
    }
    /**
     * Date按照指定格式转换成String
     * 
     * @param convertObject
     *            格式变换Date对象
     * @param formatStr
     *            变换格式
     * @return
     */
    public static String convertDateFormat(Date convertObject, String formatStr) {
        try {
            SimpleDateFormat simpleDateFormatObject = new SimpleDateFormat(formatStr);
            return simpleDateFormatObject.format(convertObject);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * String格式转换成Date
     * 
     * @param convertStr
     *            String时间对象
     * @param formatStr
     *            变换格式
     * @return
     */
    public static Date convertDateFormat(String convertStr, String formatStr) {

        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(formatStr);
            return sdf.parse(convertStr);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 字符型日期比较
     * @param dateStr1
     * @param dateStr2
     * @param formatStr
     * @return
     */
    public static int compareTo(String dateStr1, String dateStr2, String formatStr) {
    	Date date1 = convertDateFormat(dateStr1, formatStr);
    	Date date2 = convertDateFormat(dateStr2, formatStr);
    	return date1.compareTo(date2);
    }
    
    /**
     * String格式转换成Date
     * @return
     */
    public static String getWeek(String week) {

        switch (week){
        case "0":
            return "星期一";
        case "1":
            return "星期二";
        case "2":
            return "星期三";
        case "3":
            return "星期四";
        case "4":
            return "星期五";
        case "5":
            return "星期六";
        case "6":
            return "星期日";
        default :
            return "";
        }
    }
    
    /**
     * String格式转换成Date
     *
     * @return
     */
    public static String getDayWeek(String week) {

        switch (week){
        case "星期一":
            return "0";
        case "星期二":
            return "1";
        case "星期三":
            return "2";
        case "星期四":
            return "3";
        case "星期五":
            return "4";
        case "星期六":
            return "5";
        case "星期日":
            return "6";
        default :
            return "";
        }
    }
    
	public static String getDayForWeek(String pTime, String formatStr) throws Throwable {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date tmpDate = format.parse(pTime);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(tmpDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
		if (w < 0) {			
			w = 0;
		}
		return WEEK_DAYS[w];
	}
    
    public static String getWeekLastDay(String date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
        Calendar cal = Calendar.getInstance();  
        Date time;
        try {
            time = sdf.parse(date);
            cal.setTime(time); 
            //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
            if(1 == dayWeek) {  
               cal.add(Calendar.DAY_OF_MONTH, -1);  
            }  
           cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一 
           int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
           cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
           cal.add(Calendar.DATE, 6);
           return sdf.format(cal.getTime());
        } catch (ParseException e) {
            return "";
        }
    }
    public static String getDate(String DataFormat, String dateType, int datenumber) {
        Calendar date = Calendar.getInstance();
        if (dateType.equals("year")) {
            date.add(Calendar.YEAR, datenumber);
        } else if (dateType.equals("month")) {
            date.add(Calendar.MONTH, datenumber);
        } else if (dateType.equals("day")) {
            date.add(Calendar.DATE, datenumber);
        } else if (dateType.equals("week")) {
            date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            date.add(Calendar.WEEK_OF_MONTH, datenumber);
        } else if (dateType.equals("weeks")) {
            date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else if (dateType.equals("firstday")) {
            date.set(Calendar.DATE, 1);
            date.add(Calendar.MONTH, datenumber);
        } else if (dateType.equals("defaultday")) {
            date.set(Calendar.DATE, 1);
            date.add(Calendar.MONTH, datenumber + 1);
            date.add(Calendar.DATE, -1);
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(DataFormat);
        return bartDateFormat.format(date.getTime());
    }
    
    /**
     * 获取日期（格式：MMDD）
     * @param date 格式类似YYYYMMDD
     * @return MMDD
     */
    public static String getDateOfMMDD(String date) {
    	return date.substring(4, 8);
    }
    
    /**
     * 获取年份（格式：YYYY）
     * @param date 格式类似YYYYMMDD
     * @return YYYY
     */
    public static String getYearOfYYYY(String date) {
    	return date.substring(0, 4);
    }
    
    /**
     * 获取月份（格式：MM）
     * @param date 格式类似YYYYMMDD
     * @return MM
     */
    public static String getMonthOfMM(String date) {
    	return date.substring(4, 6);
    }
    
    /**
     * 获取日期（格式：DD）
     * @param date 格式类似YYYYMMDD
     * @return DD
     */
    public static String getDayOfDD(String date) {
    	return date.substring(6, 8);
    }
    
    /**
     * 获取月份（格式：YYYYMM）
     * @param dateStr 格式类似YYYYMMDD
     * @return YYYYMM
     */
    public static String getMonthOfYYYYMM(String date) {
    	return date.substring(0, 6);
    }
    
    /**
     * 格式化日期字符串
     * @param dateStr 格式：YYYYMMDD
     * @return YYYY-MM-DD
     */
    public static String formatDateStrOf10(String dateStr) {
    	if(dateStr!=null && dateStr.length()==8) {
    		return getYearOfYYYY(dateStr) + DATE_STR_LINE + getMonthOfMM(dateStr) + DATE_STR_LINE + getDayOfDD(dateStr);
    	}else {
    		return dateStr;
    	}
    }
    
    /**
     * 格式化月份字符串
     * @param dateStr 格式：YYYYMM
     * @return YYYY-MM
     */
    public static String formatMonthStrOf7(String dateStr) {
    	if(dateStr!=null && dateStr.length()==6) {
    		return getYearOfYYYY(dateStr) + DATE_STR_LINE + getMonthOfMM(dateStr);
    	}else {
    		return dateStr;
    	}
    }
    
    /**
     * 格式化日期字符串，加入"-"
     * @param dateStr 格式类似YYYYMMDD或YYYYMM
     * @return
     */
    public static String formatDateStrWithHorizontal(String dateStr) {
		String horizontalDate = dateStr;
		if(dateStr != null) {
			if(dateStr.length()==8) {
				horizontalDate = DateToolUtils.formatDateStrOf10(dateStr);
			}else if(dateStr.length()==6) {
				horizontalDate = DateToolUtils.formatMonthStrOf7(dateStr);
			}
		}
		return horizontalDate;
    }
    /**
     * 获取当前时间前N个小时
     * @param args
     * @throws ParseException
     */
    
    public static String getBeforeByHourTime(int ihour) {
		String returnstr = ""; 
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour); 
		SimpleDateFormat df = new SimpleDateFormat("HH"); 
		returnstr = df.format(calendar.getTime()); 
		return returnstr; 		
		}
    /**
     * 获取前一天日期
     * @param args
     * @throws ParseException
     */
    public static String getYesterday() {
    		DateFormat dateFormat=new SimpleDateFormat(DateToolUtils.yyMMdd);
	    Calendar calendar=Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY,-24);
	    String yesterdayDate=dateFormat.format(calendar.getTime());
	    return yesterdayDate;
    }
	    

    
    public static void main(String[] args) throws ParseException {
    	System.out.println(DateToolUtils.getDate("yyyyMMdd", "day", -1));
    	System.out.println(DateToolUtils.getDate("yyyyMM", "month", -1));
	}

}