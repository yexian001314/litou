package com.shqj.information

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.bean.AdviceFeedbackBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.uubase.UUBaseActivity
import com.sleep.uulib.widget.FJEditTextCount
import com.shqj.information.mvp.presenter.FeedbackPresenter
import com.shqj.information.mvp.view.FeedbackView
import kotlinx.android.synthetic.main.information_activity_feed_back.*

@Route(path = ArouterConstant.INFORMATION_FEEDBACK)
class FeedBackActivity : UUBaseActivity(), View.OnClickListener, FeedbackView {

    private lateinit var mPresenter: FeedbackPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.information_activity_feed_back
    }

    override fun initView() {
        setTitle(getString(R.string.title_feedback))
        et_count.setEtHint(getString(R.string.please_fill_your_question_or_suggestion))
                .setEtMinHeight(180)
                .setLength(100)
                .setType(FJEditTextCount.PERCENTAGE)
                .show()
        tv_commit.setOnClickListener(this)
    }

    override fun initData() {
        mPresenter = FeedbackPresenter(this, this, mStateManager)
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_commit ->{
                //提交
                if (et_count.text.trim().isEmpty()) {
                    ToastUtil.showToast(R.string.please_input_your_good_advice)
                    return
                }
                mPresenter.submitFeedback(et_count.text.toString())
            }
        }
    }

    override fun submitFeedbackSuccess(data: AdviceFeedbackBean) {
        ToastUtil.showToast(R.string.commit_success)
        finish()
    }
}
