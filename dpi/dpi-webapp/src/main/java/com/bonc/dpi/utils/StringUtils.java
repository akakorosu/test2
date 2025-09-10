package com.bonc.dpi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

    public final static String DEFAULT_STR_ZERO = "0";

    public final static String DEFAULT_STR_ONE = "1";

    public final static String DEFAULT_STR_SPACE = " ";

    private StringUtils() {
    }

    /**
     * null值转换
     * 
     * @param checkStr
     *            验证String对象
     * 
     * @return True ：空 False ：非空
     */
    public static boolean isNull(String checkStr) {
        boolean returnBoolean = false;
        if (checkStr == null || "".equals(checkStr.trim()) || "null".equals(checkStr)) {
            returnBoolean = true;
        }
        return returnBoolean;

    }

    /**
     * null值转换
     * 
     * @param convertStr
     *            格式变换String对象
     * @param defaultStr
     *            转换后默认值
     * 
     * @return
     */
    public static String convertNullObjectToString(Object convertObj, String... defaultStr) {

        String renturnStr = "";
        if (convertObj != null) {
            renturnStr = String.valueOf(convertObj);
        } else if (defaultStr != null && defaultStr.length > 0 && defaultStr[0] != null && !"".equals(defaultStr[0])) {
            renturnStr = defaultStr[0];
        }
        return renturnStr;
    }

    /**
     * 判断字符串中是否都为数字
     * 
     * @param objectStr
     *            要判断的字符串
     * 
     * @return
     */
    public static boolean isNumeric(String objectStr) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(objectStr);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为合法手机号码
     * 
     * @param objectStr
     *            要判断的字符串
     * 
     * @return
     */
    public static boolean isValidateTelephone(String objectStr) {
        Pattern pattern = Pattern
                .compile("^13[0-9]{9}|15[012356789][0-9]{8}|18[0-9]{9}|14[57][0-9]{8}|17[3678][0-9]{8}$");
        Matcher isNum = pattern.matcher(objectStr);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}