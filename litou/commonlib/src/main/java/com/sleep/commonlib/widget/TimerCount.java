package com.sleep.commonlib.widget;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerCount extends CountDownTimer {

	private TextView bnt;

	public Boolean isRun = false;

    public TimerCount(long millisInFuture, long countDownInterval, TextView bnt) {
        super(millisInFuture, countDownInterval);
        this.bnt = bnt;
    }

    public TimerCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
        //rebuild 需要重构，修改颜色为自定义
        bnt.setTextColor(Color.parseColor("#ff721f"));
        bnt.setClickable(true);
        bnt.setText("获取验证码");
    }

    @Override
    public void onTick(long arg0) {
        isRun = true;
        bnt.setClickable(false);
        bnt.setText("重新获取（"+arg0/1000+"）S" );
    }

}
