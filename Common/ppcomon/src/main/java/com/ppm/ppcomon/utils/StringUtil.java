package com.ppm.ppcomon.utils;

import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 */
public class StringUtil {
//    private static final String TAG = "StringUtil";

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }

    public static boolean isBlank(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }

    /**
     * 判断是否是邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || "".equals(email)) {
            return false;
        } else {
            Pattern p = Pattern
                    .compile("\\w+(\\.\\w+)*@\\w+(\\.\\w+)+");
            Matcher m = p.matcher(email);
            return m.matches();
        }
    }

    /**
     * 分解字符串，并且剔除空的元素。
     *
     * @param str
     * @param regex
     * @return
     */
    public static List<String> split2list(String str, String regex) {
        List<String> retList = new ArrayList<String>();
        if (str == null) {
            return retList;
        }
        String[] words = str.split(regex);
        for (int i = 0; i < words.length; i++) {
            if ((words[i] != null)
                    && (!words[i].trim().equals(""))) {
                retList.add(words[i]);
            }
        }
        return retList;
    }


    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isPinYin(String str) {
        Pattern pattern = Pattern.compile("[ a-zA-Z]*");
        return pattern.matcher(str).matches();
    }

    public static boolean containCn(String str) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        return pattern.matcher(str).find();
    }

    /**
     * 设置文本框文本，去处字符串为"null"
     *
     * @param view
     * @param str
     */
    public static void setTextOfView(TextView view, String str) {
        if (isEmpty(str) || "null".equals(str)) {
            view.setText("");
        } else {
            view.setText(str);
        }
    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        if (input == null) {
            return "";
        }
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static String formatAmount(float scale) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        return fnum.format(scale);
    }

    public static String formatDiscountAmount(float amount) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        return "-￥" + fnum.format(amount);
    }

    public static String formatDiscount(float scale) {
        DecimalFormat fnum = new DecimalFormat("##0.0");
        return fnum.format(scale);
    }

    public static long parseLong(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String appendSpecial(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    public static float parseStrAmount(String amount) {
        if (isEmpty(amount)) {
            return 0.00f;
        }
        try {
            return Float.parseFloat(amount);
        } catch (Exception e) {
            ExceptionUtil.showException(e);
            return 0.00f;
        }
    }

    public static String parseAmountToStr(String amountStr) {
        float amount = parseStrAmount(amountStr);
        return formatAmount(amount);
    }

    public static int parseInt(String str) {
        if (isEmpty(str)) {
            return 0;
        }

        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            ExceptionUtil.showException(e);
            return 0;
        }
    }

    public static float parseFloat(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            ExceptionUtil.showException(e);
            return 0;
        }
    }

    public static double parseDouble(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            ExceptionUtil.showException(e);
            return 0;
        }
    }
}
