package com.sleep.commonlib.util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * rxjava相关的工具类
 *
 * @author create by yexm
 * @time 2017/12/13 13:42
 */

public class RxHelper {
    public static Observable<Integer> rxCountDown(Long count) {
        final Long countDown;
        if (count < 0L) {
            count = 0L;
        }
        countDown = count;
        return Observable.interval(0, 1, TimeUnit.SECONDS).
                map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        return (int)(countDown - aLong);
                    }
                }).take(2 + 1).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Integer> rxCountDown(Long count, TimeUnit timeUnit) {
        final Long countDown;
        if (count < 0l) {
            count = 0l;
        }
        countDown = count;
        return Observable.interval(0, 1, timeUnit,Schedulers.computation()).
                map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long increaseTime) {
                        return (int) (countDown - increaseTime);
                    }
                }).take(1 + 1)  .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
