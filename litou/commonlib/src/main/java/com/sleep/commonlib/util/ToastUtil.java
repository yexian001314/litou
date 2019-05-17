package com.sleep.commonlib.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sleep.commonlib.R;

import es.dmoral.toasty.Toasty;


/**
 * 作者：alsoWell on 2017/5/3 15:53
 * 邮箱：1161882463@qq.com
 */
public class ToastUtil {
    private static Context mContext;
    private static Toast mNormalToast;
    public static void init(Context context) {
        mContext = context;
        Toasty.Config.getInstance()
                .setErrorColor(Color.RED) // optional
                .setInfoColor(Color.BLUE) // optional
                .setSuccessColor(Color.GREEN) // optional
                .setWarningColor(Color.YELLOW) // optional
                .setTextColor(Color.WHITE) // optional
                .tintIcon(false) // optional (apply textColor also to the icon)
                .setToastTypeface(Typeface.DEFAULT) // optional
                .apply(); // required

    }
    public ToastUtil() {

    }
    /*****************************************显示正确的提示*****************************************/

    private static Toast mToast;
    /**
     * 显示单个吐司 不重复显示
     *
     * @param msg
     */
    public static void showToast(String msg) {

        if (mNormalToast == null) {
            mNormalToast = Toast.makeText(mContext, msg,
                    Toast.LENGTH_SHORT);
        }
        mNormalToast.setText(msg);
        mNormalToast.show();
    }

    /**
     * 显示单个吐司 根据文件资源
     * @param resID
     */
    public static void showToast(int resID) {
        String mS = mContext.getResources().getString(resID);
        if (mToast == null) {
            mToast = Toast.makeText(mContext,mS ,
                    Toast.LENGTH_SHORT);
        }
        mToast.setText(mS);
        mToast.show();
    }


    public static void showCorrectToast(String msg,boolean withIcon) {
        mToast=Toasty.success(mContext, msg, Toast.LENGTH_SHORT, withIcon);
        mToast.show();
    }
    public static void showCorrectToast(String msg) {
        showCorrectToast(msg,false);
    }

    public static void showCorrectToast(int rsID,boolean withIcon) {
        mToast=Toasty.success(mContext, mContext.getResources().getString(rsID), Toast.LENGTH_SHORT, withIcon);
        mToast.show();
    }
    public static void showCorrectToast(int rsID) {
        showCorrectToast(rsID,false);
    }
    /*****************************************显示错误的提示*****************************************/
    public static void showErrorToast(String msg,boolean withIcon) {
        mToast=Toasty.error(mContext, msg, Toast.LENGTH_SHORT, withIcon);
        mToast.show();
    }
    public static void showErrorToast(String msg) {
        showErrorToast(msg,false);
    }

    public static void showErrorToast(int rsID,boolean withIcon) {
        mToast=Toasty.error(mContext, mContext.getResources().getString(rsID), Toast.LENGTH_SHORT, withIcon);
        mToast.show();
    }
    public static void showErrorToast(int rsID) {
        showErrorToast(rsID,false);
    }
    /*****************************************显示警告的提示*****************************************/
    public static void showWarningToast(String msg,boolean withIcon) {
        mToast = Toasty.warning(mContext, msg, Toast.LENGTH_SHORT, withIcon);
        mToast.show();
    }
    public static void showWarningToast(String msg) {
        showWarningToast(msg,false);
    }

    public static void showWarningToast(int rsID,boolean withIcon) {
        mToast=Toasty.warning(mContext, mContext.getResources().getString(rsID), Toast.LENGTH_SHORT, withIcon);
        mToast.show();
    }
    public static void showWarningToast(int rsID) {
        showWarningToast(rsID,false);
    }
    /*****************************************显示info的提示*****************************************/
    public static void showInfoToast(String msg,boolean withIcon) {
       mToast= Toasty.info(mContext, msg, Toast.LENGTH_SHORT, withIcon);
       mToast.show();
    }
    public static void showInfoToast(String msg) {
        showInfoToast(msg,false);
    }

    public static void showInfoToast(int rsID,boolean withIcon) {
        mToast=Toasty.info(mContext, mContext.getResources().getString(rsID), Toast.LENGTH_SHORT, withIcon);
        mToast.show();
    }
    public static void showInfoToast(int rsID) {
        showInfoToast(rsID,false);
    }

    /*****************************************显示普通的提示*****************************************/
    public static void showNormalToast(String msg) {
        mToast = Toasty.custom(mContext, msg, null, Toast.LENGTH_SHORT, false);
        mToast.show();
    }
    public static void showNormalToast(int rsID) {
        mToast = Toasty.custom(mContext, mContext.getResources().getString(rsID), null, Toast.LENGTH_SHORT, false);
        mToast.show();
    }

    public static void showCenterNormalToast(String msg){
        mToast = Toasty.custom(mContext, msg, null, Toast.LENGTH_SHORT, false);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public static void showCenterNormalToast(Context context,String msg,int rsId){
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        //获取ImageView
        ImageView image = view.findViewById(R.id.toast_iv);
        //设置图片
        image.setImageResource(rsId);
        //获取TextView
        TextView title = view.findViewById(R.id.toast_tv);
        //设置显示的内容
        title.setText(msg);
        Toast toast = new Toast(context);
        //设置Toast要显示的位置，水平居中并在底部，X轴偏移0个单位，Y轴偏移70个单位，
        toast.setGravity(Gravity.CENTER, 0, 70);
        //设置显示时间
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setView(view);
        toast.show();
    }

    public static void showErrorCenterToast(Context context,String msg){
        showCenterNormalToast(context,msg, R.mipmap.icon_toast_error);
    }

    public static void showRightCenterToast(Context context,String msg){
        showCenterNormalToast(context,msg, R.mipmap.icon_toast_right);
    }

}
