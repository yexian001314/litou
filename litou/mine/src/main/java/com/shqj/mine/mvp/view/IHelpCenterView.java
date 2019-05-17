package com.shqj.mine.mvp.view;

import com.sleep.uulib.mvp.view.BaseView;

/**
 * Created by SleepYM09 on 2018/3/30.
 * <p>
 * blog: www.sleepym09.com
 */

public interface IHelpCenterView<T> extends BaseView {
    void loadData(T data);
}
