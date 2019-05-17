package com.sleep.commonlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.sleep.commonlib.R;

/**
 * Created by LLL on 2017/10/11.
 */

public class CountDownView extends AppCompatTextView {

    private int mStartTime; //倒数初始化时间
    private int mEachChangeTime;//每次数字跳动间隔时间,单位：ms
    private CountDownTimer mCountDownTimer; //计时器
    private SecondCallBack mCallBack;
    private Context mContext;


    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        mStartTime = typedArray.getInteger(R.styleable.CountDownView_start_time, 3);//默认从三秒开始倒数
        mEachChangeTime = typedArray.getInteger(R.styleable.CountDownView_each_change_time, 1000);//默认每次数字跳动间隔1秒：此处单位ms
        initView();
    }

    private void initView() {
        setNewTime(mStartTime, mEachChangeTime);
    }

    /**
     * 开始倒计时
     */
    public void start() {
        mCountDownTimer.start();

    }

    public void cancel() {
        mCountDownTimer.cancel();
    }

    public void restart() {
        mCountDownTimer.cancel(); //cancel之后，系统会重置初始时间。
        mCountDownTimer.start();
    }

    public void setNewTime(int startTime, int eachChangeTime) {
        mStartTime = startTime;
        mEachChangeTime = eachChangeTime;
        //mEachChangeTime-1 是因为第一次返回的millisUntilFinished <= mStartTime*1000,所以次数会少一次
        mCountDownTimer = new CountDownTimer(mStartTime * 1000+mEachChangeTime-1, mEachChangeTime) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mCallBack != null) {
                    mCallBack.nextSecond(mStartTime);
                    mStartTime--;
                }
            }
            @Override
            public void onFinish() {
                mCallBack.nextSecond(0);
            }
        };
    }

    public void setSecondCallBack(SecondCallBack callBack) {
        mCallBack = callBack;
    }

    public interface SecondCallBack {
        void nextSecond(int second);
    }
}
