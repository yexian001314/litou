package com.sleep.commonlib.util.viewutils;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 点击事件拦截的hook
 *
 * @author create by yexm
 * @time 2017/12/22 15:30
 */

public class ViewHook {
    public void hookInit(View rootView) {
        LayoutTraverser.build(new LayoutTraverser.Processor() {
            @Override
            public void process(View view) {
                try {
                    Class viewClazz = Class.forName("android.view.View");
                    //事件监听器都是这个实例保存的
                    Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
                    if (!listenerInfoMethod.isAccessible()) {
                        listenerInfoMethod.setAccessible(true);
                    }
                    Object listenerInfoObj = listenerInfoMethod.invoke(view);

                    Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

                    Field onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");

                    if (!onClickListenerField.isAccessible()) {
                        onClickListenerField.setAccessible(true);
                    }
                    View.OnClickListener mOnClickListener = (View.OnClickListener) onClickListenerField.get(listenerInfoObj);
                    //自定义代理事件监听器
                    View.OnClickListener onClickListenerProxy = new HookedOnClickListener(mOnClickListener);
                    //更换
                    onClickListenerField.set(listenerInfoObj, onClickListenerProxy);

                    if (mOnClickListener == null) {
                        return;
                    }
                    //更换
                    onClickListenerField.set(listenerInfoObj, onClickListenerProxy);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).traverse((ViewGroup) rootView);
    }


    class HookedOnClickListener implements View.OnClickListener {
        private View.OnClickListener origin;
        private long proTime;

        HookedOnClickListener(View.OnClickListener origin) {

            this.origin = origin;
        }

        @Override
        public void onClick(final View v) {
            long currentTime = System.currentTimeMillis();
            Log.i("yexmi", this + "@this");
//            if (currentTime - proTime > 1000L) {
//                proTime = currentTime;
//                if (origin != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            }
            if (origin != null) {
                origin.onClick(v);
            }
//                    Log.i("yexmi", v.getContext().toString());
//                    v.setClickable(false);
//                    v.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            v.setClickable(true);
//                        }
//                    },1000);
//            ActivityManager activityManager = (ActivityManager) v.getContext().getSystemService(Context.ACTIVITY_SERVICE);
//            Log.e("yexmi", activityManager.getRunningTasks(10).toString());
//                }

//            }

        }
    }


    class ClickListenerInvocationHandler implements InvocationHandler {
        private Object defaultObject;

        public ClickListenerInvocationHandler(Object defaultObject) {
            this.defaultObject = defaultObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("onClick".equals(method.getName())) {
                method.invoke(defaultObject, args);
            }
            return null;
        }
    }
}
