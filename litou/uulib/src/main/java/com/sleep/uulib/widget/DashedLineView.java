package com.sleep.uulib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.sleep.uulib.R;


/**
 * <pre>
 *     author : Chen
 *     time   : 2017/07/07
 *     desc   : 虚线
 *     version: 1.0
 * </pre>
 */

public class DashedLineView extends View {

    private Paint paint = null;
    private Path path = null;
    private PathEffect effects = null;

    private int orientation;
    private int lineColor;

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private static final int DEFAULT_COLOR_THEME = Color
            .parseColor("#dedede");

    public DashedLineView(Context context) {
        super(context, null);
        // TODO Auto-generated constructor stub
    }

    public DashedLineView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        // TODO Auto-generated constructor stub
        setCustomAttributes(attrs);
    }

    public DashedLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        setCustomAttributes(attrs);
    }

    private void setCustomAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.Dashedline);
        lineColor = a.getColor(R.styleable.Dashedline_lineColor,
                DEFAULT_COLOR_THEME);
        orientation = a.getInt(R.styleable.Dashedline_orientation, HORIZONTAL);
        paint = new Paint();
        path = new Path();

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(lineColor);
        paint.setAntiAlias(true);
        a.recycle();
    }

    @SuppressLint("DrawAllocation")
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(0, 0);
        paint.setStrokeWidth(getHeight());
        if (orientation == VERTICAL) {
            path.lineTo(0, this.getHeight());
        } else {
            path.lineTo(this.getWidth(), 0);
        }
        // PathEffect是用来控制绘制轮廓(线条)的方式
        // 代码中的float数组,必须是偶数长度,且>=2,指定了多少长度的实线之后再画多少长度的空白.如本代码中,绘制长度5的实线,再绘制长度5的空白,再绘制长度5的实线,再绘制长度5的空白,依次重复.1是偏移量,可以不用理会.

        effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);

    }
}
