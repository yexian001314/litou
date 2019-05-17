package com.sleep.commonlib.util;

import android.view.View;

/**
 * 作者：alsoWell on 2017/5/15 17:43
 * 邮箱：1161882463@qq.com
 */
public class WidgetUtil {
    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }
}
