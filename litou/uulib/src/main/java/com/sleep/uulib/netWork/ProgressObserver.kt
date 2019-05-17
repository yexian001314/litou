package com.sleep.uulib.netWork

import android.app.Activity
import android.app.Dialog
import android.content.Context
import com.sleep.uulib.widget.dialog.ProgressDialog
import io.reactivex.disposables.Disposable

/**
 * Created by SleepYM09 on 2017/10/25.
 *
 * blog: www.sleepym09.com
 */
class ProgressObserver<T>(val context: Context,canCancel:Boolean,listener: OnLoadDataListener<T>) : BaseObserver<T>(listener) {

    private var dialog: Dialog = ProgressDialog(canCancel,context)

    override fun onSubscribe(d: Disposable?) {
        super.onSubscribe(d)
        if (!(context as Activity).isFinishing && !dialog.isShowing){
            dialog.show()
        }
    }

    override fun onError(e: Throwable?) {
        super.onError(e)
        if(!(context as Activity).isFinishing && dialog.isShowing){
            dialog.dismiss()
        }
    }

    override fun onNext(value: T) {
        if(!(context as Activity).isFinishing && dialog.isShowing){
            dialog.dismiss()
        }
        super.onNext(value)
    }
}