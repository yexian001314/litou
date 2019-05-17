package com.sleep.uulib.util;

import java.math.BigDecimal;

/**
 * <pre>
 *     author : Chen
 *     time   : 2017/08/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class NumberUtils {


    private static final int DEF_DIV_SCALE = 10;


    /**
     *
     * 向上取整指定位数
     *
     * @param v1 要取整的数字
     * @param newScale 精确位数
     * @return
     */
    public static Double getRoundUpNum(Double v1,int newScale){
        return new BigDecimal(v1).setScale(newScale,BigDecimal.ROUND_UP).doubleValue();
    }

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
     * 两个Double数相乘  精确到四位第五位向上取整
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double mulFourRoundUp(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.multiply(b2).setScale(2,BigDecimal.ROUND_UP).doubleValue();
    }



    /**
     * 两个Double数相除
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double div(Double v1,Double v2){
        if(v1 == 0 || v2 == 0) {
            return new Double(0);
        }
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
