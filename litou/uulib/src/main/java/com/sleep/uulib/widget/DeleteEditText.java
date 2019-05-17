package com.sleep.uulib.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : Chen
 *     time   : 2017/06/28
 *     desc   : 带删除功能的EditText
 *     version: 1.0
 * </pre>
 */

public class DeleteEditText extends android.support.v7.widget.AppCompatEditText {



    private Drawable mRightDrawable;
    boolean isHasFocus;



    public DeleteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public DeleteEditText(Context context) {
        super(context);
        init();
    }
    public DeleteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        //获取控件上下左右四个方向插入的图片
        Drawable[] drawables = this.getCompoundDrawables();
        mRightDrawable = drawables[2];
        this.addTextChangedListener(new TextWatcherImpl());
        this.setOnFocusChangeListener(new OnFocusChangeImpl());
        setClearDrawableVisible(false);
    }
    //本方法控制右边的图片显示与否
    private void setClearDrawableVisible(boolean isNonull){
        Drawable rightDrawable;
        if(isNonull){
            rightDrawable = mRightDrawable;
        }else {
            rightDrawable = null;
        }
        //使用代码设置该控件left,top,right,bottom处的图标
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1],rightDrawable,
                getCompoundDrawables()[3]);
    }
    private class TextWatcherImpl implements TextWatcher {
        //下面三个是要复写的办法
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //内容输入前
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //内容输入中
        }
        @Override
        public void afterTextChanged(Editable s) {
            //内容输入后
            boolean isNoNull = getText().toString().length() >= 1;
            setClearDrawableVisible(isNoNull);
        }
    }
    //当焦点发生变化的时候,判断图片是否显示
    private class OnFocusChangeImpl implements OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            isHasFocus = hasFocus;
            if(isHasFocus){//如果获取焦点
                boolean isNonull = getText().toString().length() >= 1;
                setClearDrawableVisible(isNonull);
            }else {
                setClearDrawableVisible(false);
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //getPaddingRight():clean的图标右边缘至控件右边缘的距离
                //删除图片右侧到EditText控件最左侧距离
                int length1 = getWidth() - getPaddingRight();
                //getTotalPaddingRight():clean的图标左边缘至控件右边缘的距离
                //删除图片左侧到EditText控件最左侧距离
                int length2 = getWidth() - getTotalPaddingRight();
                boolean isClean = (event.getX() > length2)
                        && (event.getX() < length1);
                if (isClean) {
                    setText("");
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
