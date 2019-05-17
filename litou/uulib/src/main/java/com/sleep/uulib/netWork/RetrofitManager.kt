package com.sleep.uulib.netWork

import android.util.Log
import com.google.gson.GsonBuilder
import com.sleep.uulib.constant.NetConstant
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 作者：alsoWell on 2017/7/24 09:32
 * 邮箱：1161882463@qq.com
 */
open class RetrofitManager {

    object single {
        val BASE_URL = NetConstant.BASE_URL
        val gson = GsonBuilder()
                .setLenient()
                .create()
    }

    /**
     * @param needJson  是否需要返回json
     */
    fun getRetrofit(): Retrofit {
        var mOkHttpClient = OkHttpUtils.getOkHttpClient()
        val retrofit = initOkhttpClient(mOkHttpClient)
        return retrofit!!
    }

    fun initOkhttpClient(client: OkHttpClient): Retrofit? {
        val builder = Retrofit.Builder()
        builder.
                addConverterFactory(GsonConverterFactory.create(single.gson)).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return builder.
                baseUrl(single.BASE_URL).
                client(client).
                build()
    }

    /**
     *
     */
    fun <T> setSubscribe(observable: Observable<T>, observer: BaseObserver<T>) {

        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer)
    }
}