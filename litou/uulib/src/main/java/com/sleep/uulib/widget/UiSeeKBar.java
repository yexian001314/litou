package com.sleep.uulib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.sleep.commonlib.util.SizeUtils;
import com.sleep.uulib.R;
import com.sleep.uulib.util.NumberFormatUtils;
import com.sleep.uulib.util.NumberUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by linlinlin on 2016/7/29.
 * 作者：TianNuo
 * 邮箱：1320917731@qq.com
 */
public class UiSeeKBar extends android.support.v7.widget.AppCompatSeekBar {

    /**
     * 进度条指示文字后缀
     */
    private String numTextFormat = "%";

    private String numText;

    /**
     * 进度条指示文字的背景
     */
    private int numBackground;
    private int numTextColor;
    /**
     * numBackground对应的bitmap
     */
    private Bitmap bm;
    /**
     * bitmap对应的宽高
     */
    private float bmpWidth, bmpHeight;
    /**
     * 构建画笔和文字
     */
    Paint bmPaint;

    /**
     * 测量seekBar的规格
     */
    private Rect rectSeek;

    /**
     * 目标progress
     */
    private int targetPorgress = 0;

    private Paint.FontMetrics fm;

    private double numScale = 0.16;

    private Context mContext ;


    public UiSeeKBar(Context context) {
        this(context, null);
    }

    public UiSeeKBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UiSeeKBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        //初始化属性
        init(context, attrs);
        //初始化bm
        initBm();
        //构建画笔
        initPaint();
        setPadding( SizeUtils.dp2px(context, 6),
                (int) Math.ceil(bmpHeight)+20,
                bm.getWidth()- SizeUtils.dp2px(context, 6),
                0);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            fm = bmPaint.getFontMetrics();
            numText = (NumberFormatUtils.getNumberWithDigital(NumberUtils.div((double) (getProgress()*100), (double) getMax()),1)) + numTextFormat;
            //seekBar的边框
            rectSeek = this.getProgressDrawable().getBounds();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                this.getThumb().getBounds();
            }

            //计算文字的中心位置在bitmap
            float text_x = (rectSeek.right - rectSeek.left) * getProgress() / getMax();
            bmPaint.setTextAlign(Paint.Align.CENTER);
            //根据文字所在位置，是否会超出屏幕来设置相应的alin位置
//            if (getSystemWith() * getProgress() / getMax() < getTextWidth(bmPaint, numText) / 2) {
//                //在左边，居中会超出边界，所以固定在最左邊
//                text_x = getTextWidth(bmPaint, numText) / 2;
//            } else if (getSystemWith() * (1 - ((double) getProgress() / getMax())) < getTextWidth(bmPaint, numText) / 2) {
//                //在最右边，居中会超出边界,所以固定在最右邊
//                text_x = getSystemWith() - getTextWidth(bmPaint, numText) / 2;
//            }
            canvas.drawBitmap(bm,text_x,rectSeek.top,bmPaint);
//            canvas.drawText(numText, text_x+bm.getWidth()/2, (float) (bmpHeight / 2 - (fm.descent - (fm.descent - fm.ascent) / 2) - (bmpHeight * numScale) / 2), bmPaint);
            canvas.drawText(numText, text_x+bm.getWidth()/2, (float) (bmpHeight / 2 - (bmpHeight * numScale) / 2 + SizeUtils.dp2px(mContext,5)), bmPaint);
        } catch (Exception e) {
            //为什么要try因为你的参数可能没有填
        }
    }

    /**
     * 获取文字宽度
     *
     * @param paint
     * @param str
     * @return
     */
    public int getTextWidth(Paint paint, String str) {
        int w = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                w += (int) Math.ceil(widths[j]);
            }
        }
        return w;
    }

    public int getSystemWith() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        return wm.getDefaultDisplay().getWidth();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        return true;
    }

    private void initPaint() {
        //抗锯齿
        bmPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bmPaint.setTypeface(Typeface.DEFAULT);
        bmPaint.setTextSize(SizeUtils.sp2px(getContext(),9));
        bmPaint.setColor(numTextColor);
    }

    private void initBm() {
        bm = BitmapFactory.decodeResource(getResources(), numBackground);
        //注意判断是否是null
        if (bm != null) {
            bmpWidth = bm.getWidth();
            bmpHeight = bm.getHeight();
        }
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomSeekBar);
        numTextFormat = array.getString(R.styleable.CustomSeekBar_numTextFormat);
        numBackground = array.getResourceId(R.styleable.CustomSeekBar_numbackground, R.mipmap.shows);
        numTextColor = array.getColor(R.styleable.CustomSeekBar_numTextColor, Color.WHITE);
        numScale = Double.parseDouble(array.getString(R.styleable.CustomSeekBar_numScale) == null ? numScale + "" : array.getString(R.styleable.CustomSeekBar_numScale));
        numTextFormat = numTextFormat == null ? "%" : numTextFormat;
        array.recycle();
    }

    @Override
    public synchronized void setProgress(final int progress) {
        if(progress == 0){return;}
        final int perMs = targetPorgress / 1000;
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0 ;i <= 50;i++){
                    try {
                        final int currentProgress = i*perMs*20;
                        Thread.sleep(20);
                        UiSeeKBar.this.post(new Runnable() {
                            @Override
                            public void run() {
                                UiSeeKBar.super.setProgress(currentProgress);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void setTragetProgress(int targetProgress){
        this.targetPorgress = targetProgress;
        setProgress(targetPorgress);
    }
}
