package com.sleep.uulib.account

import android.app.Activity
import android.view.KeyEvent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.util.RxHelper
import com.sleep.commonlib.util.SPUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.R
import com.sleep.uulib.UUApplication
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.constant.Constant.GES_PSD_ERROR_COUNT
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.util.AnimationUtils
import com.sleep.uulib.uubase.UUBaseActivity
import com.sleep.uulib.widget.gestrue.ACache
import com.sleep.uulib.widget.gestrue.LockPatternUtil
import com.sleep.uulib.widget.gestrue.LockPatternView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_gestrue.*
import java.util.*


@Route(path = ArouterConstant.UUBASE_GESTRUE)
class GestrueActivity : UUBaseActivity() {
    override fun getLayoutResourse(): Int {
        return R.layout.activity_gestrue
    }

    private var mErrorClolr: Int = 0;
    private var mDrawColor: Int = 0;
    private var mSuccessColor: Int = 0;
    private var currentGestruePsdStatus: Int = SET_PASSWORD_FIRST;
    //true 密码设置  false密码验证
    private var currentHasPsd: Boolean = false

    //错误次数
    private var errorNumber: Int = 0

    companion object {
        //第一次设置密码
        const val SET_PASSWORD_FIRST: Int = 101;
        //第二次设置密码 密码验证
        const val SET_PASSWORD_SENCOD: Int = 102;
        //密码设置成功
        const val PASS_WORD_SET_SUCCESS: Int = 103;
        //手势密码允许错误次数
        const val CAN_ERROR_GESPSD_COUNT: Int = 3;
        //最少链接点数
        const val MIN_DRAW_POINT: Int = 4;
    }

    override fun initView() {

        mErrorClolr = resources.getColor(R.color.color_ff5353)
        mDrawColor = resources.getColor(R.color.color_676767)
        mSuccessColor = resources.getColor(R.color.color_42b689)
        hideBackView()
        currentHasPsd = SPUtil.getInstance().getBoolean(Constant.GES_PSD_HAS, false)
        if (!currentHasPsd) {
            setTitle("设置手势密码")
            currentGestruePsdStatus = SET_PASSWORD_FIRST
        } else {
            currentGestruePsdStatus = PASS_WORD_SET_SUCCESS
            ll_vertify_password.visibility = View.VISIBLE
            val cal = Calendar.getInstance()
            cal.time = Date()
            val month = cal.get(Calendar.MONTH) + 1
            tv_cotent_title.text = month.toString() + "月" + cal.get(Calendar.DAY_OF_MONTH).toString() + "日"
            errorNumber = SPUtil.getInstance().getInt(GES_PSD_ERROR_COUNT, 0)
            setTitle("手势密码验证")
            if (UUApplication.user!=null){
                successHandle("您好," + UUApplication.user?.getMobilePhone()?.replaceRange(3, 7, "****"), mDrawColor)
            }else{
                successHandle("您好!,请输入手势密码")
            }

        }
        registerListenter();

    }

    //设置监听
    private fun registerListenter() {

        tv_reset_password.setOnClickListener(View.OnClickListener {
            SPUtil.getInstance().put(Constant.GES_PSD_HAS, false)
            currentGestruePsdStatus = SET_PASSWORD_FIRST
            successHandle("请绘制解锁密码", mDrawColor)
            tv_reset_password.visibility = View.GONE
        })
        tv_forget_pwd.setOnClickListener(View.OnClickListener {
            UUApplication.user = null
            SPUtil.getInstance().put(Constant.GES_PSD_HAS, false)
            SPUtil.getInstance().put(Constant.GES_PSD_LOGIN_PHONE_NUMBER, "")
            LoginActivity.launch(1)
            AppRequest.INSTANCE.logout(TreeMap(), BaseObserver(object : OnLoadDataListener<Any> {
                override fun onFailure(e: Throwable?, errorCode: Int) {
                }

                override fun onSuccess(data: Any) {
                    UUApplication.user = null
                }
            }))
            finish()
        })

        tv_change_acount.setOnClickListener(View.OnClickListener {
            UUApplication.user = null
            LoginActivity.launch(1)
            AppRequest.INSTANCE.logout(TreeMap(), BaseObserver(object : OnLoadDataListener<Any> {
                override fun onFailure(e: Throwable?, errorCode: Int) {
                }

                override fun onSuccess(data: Any) {
                    UUApplication.user = null
                }
            }))
            finish()
        })

        lpv_gestrue.setOnPatternListener(object : LockPatternView.OnPatternListener {
            override fun onPatternStart() {
            }

            override fun onPatternComplete(cells: MutableList<LockPatternView.Cell>?) {
                val bytes = LockPatternUtil.patternToHash(cells)
                when (currentGestruePsdStatus) {
                    SET_PASSWORD_FIRST -> {
                        if (cells!!.size < MIN_DRAW_POINT) {
                            errorHandle("最少需要连接四个点,请重新绘制", mErrorClolr)
                            return
                        }
                        ACache.get(this@GestrueActivity).put(Constant.GES_PASSWORD, bytes)


                        currentGestruePsdStatus = SET_PASSWORD_SENCOD
                        successHandle("请再次绘制密码", mDrawColor)
                        lpv_gestrue.postClearPatternRunnable(1000L)
                        tv_reset_password.visibility = View.VISIBLE
                    }
                    SET_PASSWORD_SENCOD -> {
                        if (cells!!.size < MIN_DRAW_POINT) {
                            errorHandle("最少需要连接四个点,请重新绘制", mErrorClolr)
                            return
                        }
                        val asBinary = ACache.get(this@GestrueActivity).getAsBinary(Constant.GES_PASSWORD)
                        val isMatch = LockPatternUtil.checkPattern(cells, asBinary)
                        if (!isMatch) {
                            errorHandle("两次图案不同,请重新绘制", mErrorClolr)
                        } else {
                            successHandle("密码设置成功", mSuccessColor)
                            iv_gestrue_ok.visibility = View.VISIBLE
                            SPUtil.getInstance().put(Constant.GES_PSD_HAS, true)
                            SPUtil.getInstance().put(Constant.GES_PSD_LOGIN_PHONE_NUMBER, UUApplication.user!!.getMobilePhone())
                            currentGestruePsdStatus = PASS_WORD_SET_SUCCESS
                            lpv_gestrue.postClearPatternRunnable(2000L)
                            //密码设置成功后倒计时退出
                            RxHelper.rxCountDown(2).subscribe(object : Observer<Int?> {
                                override fun onError(e: Throwable?) {
                                    setResult(Activity.RESULT_OK)
                                    finish()
                                }

                                override fun onComplete() {
                                    setResult(Activity.RESULT_OK)
                                    finish()
                                }

                                override fun onSubscribe(d: Disposable?) {

                                }

                                override fun onNext(value: Int?) {

                                }
                            })

                        }
                    }
                    PASS_WORD_SET_SUCCESS -> {

                        val asBinary = ACache.get(this@GestrueActivity).getAsBinary(Constant.GES_PASSWORD)
                        val isMatch = LockPatternUtil.checkPattern(cells, asBinary)
                        if (asBinary==null){
                            ToastUtil.showToast("手势密码过期，请重新登录")
                            SPUtil.getInstance().put(Constant.GES_PSD_HAS, false)
                            LoginActivity.launch()
                            finish()
                            return
                        }
                        if (isMatch) {
                            errorNumber = 0
                            UUApplication.isGesTrueSuccess = true
                            SPUtil.getInstance().put(GES_PSD_ERROR_COUNT, 0)
                            finish()
                        } else {
                            if (++errorNumber >= CAN_ERROR_GESPSD_COUNT) {
                                SPUtil.getInstance().put(Constant.GES_PSD_HAS, false)
                                SPUtil.getInstance().put(Constant.GES_PSD_LOGIN_PHONE_NUMBER, "")
                                AppRequest.INSTANCE.logout(TreeMap(), BaseObserver(object : OnLoadDataListener<Any> {
                                    override fun onFailure(e: Throwable?, errorCode: Int) {
                                    }

                                    override fun onSuccess(data: Any) {
                                        UUApplication.user = null
                                    }
                                }))
                                DialogFactory.instance.getCommonDialog("提示", "密码输入错误次数过多，请重新登录", "去登陆", null, false,
                                        View.OnClickListener {
                                            LoginActivity.launch()
                                            finish()
                                        }).show(supportFragmentManager, "gestrue")
                                //如果次数达到3次失败，强制登出
                                return
                            }
                            errorHandle("手势密码有误,还有" + (CAN_ERROR_GESPSD_COUNT - errorNumber) + "次可试", mErrorClolr)
                            SPUtil.getInstance().put(GES_PSD_ERROR_COUNT, errorNumber)
                        }
                    }
                }

            }
        })
    }

    private fun successHandle(text: String = "", textColor: Int = mDrawColor) {
        tv_describ.setTextColor(textColor)
        tv_describ.text = text

    }

    private fun errorHandle(text: String, textColor: Int = mDrawColor) {
        tv_describ.text = text
        tv_describ.setTextColor(textColor)
        lpv_gestrue.setPattern(LockPatternView.DisplayMode.ERROR)
        AnimationUtils.shake(this, tv_describ)
        lpv_gestrue.postClearPatternRunnable(1000L)
    }

    fun swithCurrentStatus(status: Int) {
    }

    override fun initData() {

    }

    override fun retryGetData() {

    }
    private var firstTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                ToastUtil.showToast("再按一次退出程序")
                firstTime = System.currentTimeMillis()
            } else {
                UUApplication.sInstance.systemExit()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    override  fun receiveOtherLogin(baseEvent: BaseEvent<Any>) {
        if (baseEvent.eventCode == EventCode.OTHER_LOGIN && shouldReceiveOtherLogin()) {
            //判断是否重复显示
            if ((commonDialog == null || !commonDialog?.dialog?.isShowing!!) && isResume) {
                commonDialog = DialogFactory.instance.getCommonDialog("提示", "请重新登录", "确定", null, false,
                        View.OnClickListener {
                            SPUtil.getInstance().put(Constant.GES_PSD_HAS, false)
                            LoginActivity.launch()
                            finish()
                        })
                commonDialog?.show(supportFragmentManager, "outer_login")
            }
        }
    }
}
