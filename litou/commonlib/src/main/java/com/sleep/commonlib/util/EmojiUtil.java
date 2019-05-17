package com.sleep.commonlib.util;

/**
 * 作者：alsoWell on 2017/7/7 11:09
 * 邮箱：1161882463@qq.com
 */
public class EmojiUtil {
    /**
     * emoji表情替换
     *
     * @param source 原字符串
     * @param slipStr emoji表情替换成的字符串
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source,String slipStr) {
        if(!StringUtil.isEmpty(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        }else{
            return source;
        }
    }
}
