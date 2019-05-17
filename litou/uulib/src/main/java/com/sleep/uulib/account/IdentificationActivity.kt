package com.sleep.uulib.account

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.commonlib.util.RegexUtils
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.R
import com.sleep.uulib.bean.CertificationBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.mvp.prestener.IdentificationPresenter
import com.sleep.uulib.mvp.view.IdentificationView
import com.sleep.uulib.uubase.HtmlContentActivity
import com.sleep.uulib.uubase.UUBaseActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_identification.*

/**
 * 身份认证
 */
@Route(path = ArouterConstant.UUBASE_IDENTIFICATION)
class IdentificationActivity : UUBaseActivity(), View.OnClickListener, IdentificationView, View.OnFocusChangeListener {

    private lateinit var mPresenter: IdentificationPresenter
    private var exitIsFinish = false

    override fun getLayoutResourse(): Int {
        return R.layout.activity_identification
    }

    override fun initView() {
        setTitle(getString(R.string.identification_title))
        tv_identify.setOnClickListener(this)
        tv_identify.isClickable = false
        et_name.addTextChangedListener(NameTextWatcher())
        et_id_num.addTextChangedListener(IdTextWatcher())
        et_name.onFocusChangeListener = this
        et_id_num.onFocusChangeListener = this
    }

    override fun initData() {
        mPresenter = IdentificationPresenter(this, this, mStateManager)
        exitIsFinish = intent.extras.getBoolean(ArouterParamConstant.EXIT_IS_FINISH)
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when (v?.id) {
            R.id.et_name -> {
                if (hasFocus) {
                    MobclickAgent.onEvent(this, "wd_smrz_xm")
                }
            }
            R.id.et_id_num -> {
                if (hasFocus) {
                    MobclickAgent.onEvent(this, "wd_smrz_sfz")
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_identify -> {
                MobclickAgent.onEvent(this, "wd_smrz_btn")
                val strName = et_name.text.toString().trim()
                val strIdNum = et_id_num.text.toString().trim()

                if (!RegexUtils.isIDCard15(strName) && !RegexUtils.isIDCard18(strIdNum)) {
                    ToastUtil.showToast(R.string.id_error_please_input_again)
                    return
                }
                mPresenter.getIdentificationData(strName, strIdNum)
            }

        }
    }

    override fun getIdentificationData(data: CertificationBean) {
        HtmlContentActivity.launch(data.date,exitIsFinish)
    }

    private inner class NameTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.toString().isNotEmpty() && et_id_num.text.toString().trim().isNotEmpty()) {
                tv_identify.setBackgroundResource(R.drawable.shape_accent_corner2)
                tv_identify.isClickable = true
            } else {
                tv_identify.setBackgroundResource(R.drawable.shape_gray_corner3)
                tv_identify.isClickable = false
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }

    private inner class IdTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.toString().isNotEmpty() && et_name.text.toString().trim().isNotEmpty()) {
                tv_identify.setBackgroundResource(R.drawable.shape_accent_corner2)
                tv_identify.isClickable = true
            } else {
                tv_identify.setBackgroundResource(R.drawable.shape_gray_corner3)
                tv_identify.isClickable = false
            }
        }

    }
}
