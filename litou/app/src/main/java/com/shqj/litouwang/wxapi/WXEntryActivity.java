package com.shqj.litouwang.wxapi;

import android.os.Bundle;

import com.umeng.weixin.callback.WXCallbackActivity;
import com.shqj.litouwang.R;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
    }
}
