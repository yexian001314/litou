package com.shqj.mine.mvp.view

import com.sleep.uulib.bean.UpdataAddressBean
import com.sleep.uulib.mvp.view.BaseView

/**
 * Created by SleepYM09 on 2017/12/13.
 *
 * blog: www.sleepym09.com
 */
interface PersonalDataView: BaseView {
    fun saveAddressSuccess(data: UpdataAddressBean)
}