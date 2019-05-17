package com.sleep.uulib.widget

import com.bumptech.glide.integration.okhttp3.OkHttpStreamFetcher
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.sleep.uulib.netWork.OkHttpUtils
import okhttp3.Call
import java.io.InputStream

/**
 * Created by SleepYM09 on 2017/12/28.
 *
 * blog: www.sleepym09.com
 */
class OkHttpUrlLoader(var client: Call.Factory) : ModelLoader<GlideUrl, InputStream> {

    override fun buildLoadData(model: GlideUrl, width: Int, height: Int, options: com.bumptech.glide.load.Options?): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData(model, OkHttpStreamFetcher(client,model))
    }
    override fun handles(model: GlideUrl?)=true

    companion object{
        /**
         * 一个OkHttpUrlLoader的工厂
         */
        var factory=object : ModelLoaderFactory<GlideUrl, InputStream> {
            @Volatile private var internalClient: Call.Factory? = null
            private var client: Call.Factory? = null
            init {
                client=getInternalClient()
            }
            private fun getInternalClient(): Call.Factory? {
                if (internalClient == null) {
                    synchronized(this) {
                        if (internalClient == null) {
                            internalClient = OkHttpUtils.getOkHttpClient()
                        }
                    }
                }
                return internalClient
            }
            override fun build(multiFactory: MultiModelLoaderFactory?)
                    : ModelLoader<GlideUrl, InputStream> {
                return  OkHttpUrlLoader(client!!)
            }
            override fun teardown() {
            }
        }
    }
}