package com.sleep.uulib.util;

import android.support.annotation.IntRange;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2017/07/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public  class NumberFormatUtils {


    /**
     * 转成整数
     * @param number    输入的数字
     * @return          处理后的字符串
     */
    public static String getFormatNumberWithNoDigital(double number){
        StringBuffer bf = new StringBuffer("########0");
        DecimalFormat df = new DecimalFormat(bf.toString());
        return df.format(number);
    }


    /**
     * 设置小数点保留的位数
     * @param number            输入的数字
     * @param decimalDigits     保留小数点后的位置
     * @return                  处理后的字符串
     */
    public static String getFormatNumber(double number,@IntRange(from = 1,to = 4) int decimalDigits){
        StringBuffer bf = new StringBuffer("########0.");
        for (int i = 0; i < decimalDigits; i++) {
            bf.append("0");
        }
        DecimalFormat df = new DecimalFormat(bf.toString());
        return df.format(number);
    }

    /**
     * 若小数点后有小数则显示,无则不显示
     * @param number                    输入的数字
     * @param decimalDigits             保留小数点后的位置
     * @return                          处理后的字符串
     */
    public static String getNumberWithDigital(double number,@IntRange(from = 1,to = 4) int decimalDigits){
        StringBuffer bf = new StringBuffer("########0.");
        for (int i = 0; i < decimalDigits; i++) {
            bf.append("0");
        }
        DecimalFormat df = new DecimalFormat(bf.toString());
        return df.format(number);
    }

    /**
     * 给数字整数位每隔三位添加一个逗号
     *
     * @param number
     * @return
     */
    public static String formatNumberWithComma(String number) {
        StringBuffer result = new StringBuffer();
        //获取到整数部分
        String[] splits = number.split("\\.");
        String originalFormatNum = splits[0];
        int firstSplit = originalFormatNum.length() % 3;
        int cursor = 0;
        int forNum = (originalFormatNum.length() - firstSplit) / 3;
        for (int i = 0; i <= forNum; i++) {
            if (i == forNum) {
                result.append(originalFormatNum.substring(cursor));
                if (splits.length == 2) {
                    result.append(".");
                    result.append(splits[1]);
                }
            }
            if(firstSplit != 0 && i != forNum){
                result.append(originalFormatNum.substring(cursor, firstSplit));
                result.append(",");
            }
            cursor = firstSplit;
            firstSplit += 3;
        }
        return result.toString();
    }

    /**
     * 将int转换为带万的数值
     *
     * 例：10000 -> 1万
     *     10001 -> 10001
     *
     * @param number
     * @return
     */
    public static String formatNumberWithTenThousand(int number){
        StringBuilder result = new StringBuilder();
        if(number % 10000 == 0){
            int tenThousand = number / 10000;
            result.append(tenThousand).append("万");
        }else {
            result.append(number);
        }
        return result.toString();
    }


    private static final int DEF_DIV_SCALE = 10;


    /**
     * 两个Double数相加
     * @param v1
     * @param v2
     * @return
     */
    public static Double add(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.add(b2).doubleValue();
    }

    /**
     * 两个Double数相减
     * @param v1
     * @param v2
     * @return
     */
    public static Double sub(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 两个Double数相乘
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double mul(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.multiply(b2).doubleValue();
    }



    /**
     * 两个Double数相除
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double div(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }



    /**
     * 两个Double数相除，并保留scale位小数
     * @param v1
     * @param v2
     * @param scale
     * @return Double
     */
    public static Double div(Double v1,Double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
