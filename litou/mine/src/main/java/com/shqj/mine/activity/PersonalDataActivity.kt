package com.shqj.mine.activity

import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.qqtheme.framework.entity.City
import cn.qqtheme.framework.entity.County
import cn.qqtheme.framework.entity.Province
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.sleep.commonlib.util.StringUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.UpdataAddressBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.task.AddressPickTask
import com.sleep.uulib.uubase.UUBaseActivity
import com.umeng.analytics.MobclickAgent
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.PersonalDataPersenter
import com.shqj.mine.mvp.view.PersonalDataView
import kotlinx.android.synthetic.main.mine_activity_personal_data.*
import java.util.*

/**
 * 个人资料
 */
@Route(path = ArouterConstant.MINE_PERSONAL_DATA)
class PersonalDataActivity : UUBaseActivity(), View.OnClickListener, AddressPickTask.Callback, PersonalDataView {

    private lateinit var mTask: AddressPickTask
    private lateinit var mPresenter: PersonalDataPersenter
    private var mProvinceData: ArrayList<Province>? = null
    private var mChooseProvince: Province? = null
    private var mChooseCity: City? = null
    private var mChooseCounty: County? = null

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_personal_data
    }

    override fun initView() {
        setToolBarColor(R.color.background_color)
        (getBarView(TitleView.TITLE_TV) as TextView).setTextColor(resources.getColor(R.color.black))
        (getBarView(TitleView.LEFT_IV) as ImageView).setImageResource(R.mipmap.icon_black_left_icon)
        setTitle(getString(R.string.title_personal_data))
        if (UUApplication.user != null) {
            //用户手机号
            tv_phone_num.text = StringUtil.getPhone(UUApplication.user?.getMobilePhone())
            //======================银行卡绑定设置=====================
            //银行卡绑定状态(1->若已经绑定,则显示绑卡信息,不可点击跳转  2->若未绑定,则显示去绑定,可点击跳转)
            if (UUApplication.user?.isIsBandCard()!!) {
                if (!TextUtils.isEmpty(UUApplication.user?.getBankInfo()?.cardCode)) {
                    tv_bank_name.text = UUApplication.user?.getBankInfo()?.bankName
                    tv_bank_card_num.text = StringUtil.dimStrings(UUApplication.user?.getBankInfo()?.cardCode, 4, 3)
                }
                tv_bank_name.setTextColor(ContextCompat.getColor(this, R.color.color_222222))
                tv_bank_card_num.setTextColor(ContextCompat.getColor(this, R.color.color_222222))
            } else {
                tv_bank_name.visibility = View.GONE
                tv_bank_card_num.text = "去绑定"
                tv_bank_card_num.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                ll_bank_bind.setOnClickListener(this)
            }

            //=======================实名认证设置======================
            //实名认证状态(1->若已经认证,则显示认证的信息,不可点击跳转  2->若未认证,则显示去认证,可点击跳转)
            if (UUApplication.user?.isIsIdentityVerified()!!) {
                if (!TextUtils.isEmpty(UUApplication.user?.getRealName())) {
                    tv_certification_statue.text = StringUtil.dimStrings(UUApplication.user?.getRealName(), 1, 0)
                }
                tv_certification_statue.setTextColor(ContextCompat.getColor(this, R.color.color_222222))
            } else {
                tv_certification_statue.text = "去认证"
                tv_certification_statue.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                ll_certification.setOnClickListener(this)
            }
        }
        ll_select_address.setOnClickListener(this)
        tv_save_address.setOnClickListener(this)
    }

    override fun initData() {
        mPresenter = PersonalDataPersenter(this, mStateManager)
        mTask = AddressPickTask(this)
        mTask.setHideProvince(false)
        mTask.setHideCounty(false)
        mTask.setCallback(this)
        mTask.execute()
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_bank_bind -> {
                //绑定银行卡 现在富有流程是实名认证过程必须绑定银行卡，所以银行卡未绑定的统一跳转实名认证
                ARouter.getInstance().build(ArouterConstant.UUBASE_IDENTIFICATION).navigation()
            }
            R.id.ll_certification -> {
                //实名认证
                MobclickAgent.onEvent(this, "wd_aqsz_smrz")
                ARouter.getInstance().build(ArouterConstant.UUBASE_IDENTIFICATION).navigation()
            }
            R.id.ll_select_address -> {
                //选择地址
                if (mProvinceData != null) {
                    mTask.showPicker(mProvinceData)
                } else {
                    ToastUtil.showToast("重新加载数据，请稍后")
                    mTask.execute()
                }
            }
            R.id.tv_save_address -> {
                //保存地址
                if (mChooseCity == null || mChooseCounty == null || mChooseProvince == null) {
                    ToastUtil.showToast("请选择地址")
                    return
                }
                if (StringUtil.isEmpty(et_input_street.text) || et_input_street.text.toString().length < 5) {
                    ToastUtil.showToast("详细地址少于5字")
                    return
                }
                if (et_input_street.text.toString().length > 32) {
                    ToastUtil.showToast("详细地址最多32字")
                    return
                }
                saveAddress()
            }
        }
    }

    override fun getDataFinish() {

    }

    override fun saveAddressSuccess(data: UpdataAddressBean) {
        ToastUtil.showToast("保存成功")
        finish()
    }

    override fun onAddressInitFailed() {
        ToastUtil.showToast("数据初始化失败")
    }

    override fun onAddressInitFinish(data: ArrayList<Province>) {
        mProvinceData = data
        //初始化地址
        setPersonalAddress()
    }

    override fun onAddressPicked(province: Province?, city: City?, county: County?) {
        tv_choose_address.text = (province?.areaName + " " + city?.areaName + " " + county?.areaName)
        mChooseProvince = province
        mChooseCity = city
        mChooseCounty = county
    }

    /**
     * 保存地址
     */
    private fun saveAddress() {
        mPresenter.saveAddress(this,
                mChooseProvince?.areaId!!,
                mChooseCity?.areaId!!,
                mChooseCounty?.areaId!!,
                et_input_street.text.toString()
        )
    }

    /**
     * 根据个人信息里的address设置个人地址
     */
    private fun setPersonalAddress() {
        if (UUApplication.user != null && !StringUtil.isEmpty(UUApplication.user?.getAddress())) {
            val address = UUApplication.user?.getAddress()!!.toString().split("|")
            if (address.size != 4) {
                return
            }
            for (province in mProvinceData!!) {
                if (province.areaName == address[0]) {
                    //匹配到当前用户选中的province
                    mChooseProvince = province
                    for (city in province.cities) {
                        if (city.areaName == address[1]) {
                            //匹配到当前用户选中的city
                            mChooseCity = city
                            city.counties
                                    .filter { it.areaName == address[2] }
                                    .forEach {
                                        //匹配到当前用户选中的county
                                        mChooseCounty = it
                                    }
                        }
                    }
                }
            }
            for (i in address.indices) {
                if (i == 0) {
                    tv_choose_address.text = address[0]
                } else if (i == 1 || i == 2) {
                    tv_choose_address.text = (tv_choose_address.text.toString() + " " + address[i])
                } else {
                    et_input_street.setText(address[3])
                }
            }
            iv_right_black_arrow.visibility = View.VISIBLE
        }
    }
}
