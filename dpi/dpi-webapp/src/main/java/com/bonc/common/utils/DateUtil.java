package com.bonc.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Fan
 * Created on 2018/6/27 上午11:43
 * Description : 日期工具类
 */
public class DateUtil {

    /**
     * 获取当前周周一的日期
     */
    public static List<String> getDayList() {
        SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");

        List<String> dateList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        //当前周周一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday1 = simdf.format(cal.getTime());

        //当前周周日
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 6);
        String weekday1 = simdf.format(cal.getTime());
        String date1 = monday1 + " -- " + weekday1;

        dateList.add(date1);

        int i = 0;
        //前8周
        while (i < 7) {
            //前一周周日
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
            String weekday2 = simdf.format(cal.getTime());

            //前一周周一
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 6);
            String monday2 = simdf.format(cal.getTime());
            String date = monday2 + " -- " + weekday2;
            dateList.add(date);

            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 6);
            i++;
        }
        return dateList;
    }

	public static String formatDate(long currentTimeMillis) {
		// TODO Auto-generated method stub
        SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
        String sd = simdf.format(new Date(Long.parseLong(String.valueOf(currentTimeMillis))));  
		return sd;
	}

	public static String formatTime(long ms) {
		// TODO Auto-generated method stub
        SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
        String sd = simdf.format(new Date(Long.parseLong(String.valueOf(ms))));  
		return sd;
	}

}
