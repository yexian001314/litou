package com.shqj.mine

import android.content.Intent
import com.alibaba.android.arouter.launcher.ARouter
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.uulib.UUApplication
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.fragment.MineFragment
import org.simple.eventbus.Subscriber

class MineActivityMainActivity : UUBaseActivity() {

    private val mineFragment = MineFragment()
    private var isFirstResume = true

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_main
    }

    override fun shouldReceiveOtherLogin(): Boolean {
        return false
    }

    override fun shouldShowToolBar(): Boolean {
        return false
    }

    override fun initView() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.content, mineFragment)
        transaction.commit()
    }

    override fun initData() {
    }

    override fun retryGetData() {
    }

    override fun onResume() {
        super.onResume()
        if (UUApplication.user == null) {
            ARouter.getInstance().build(ArouterConstant.UUBASE_LOGIN).navigation()
        }
        if(isFirstResume){
            mineFragment.onFirstUserVisible()
            isFirstResume = false
        }
    }

    @Subscriber
    fun receiveLogin(baseEvent: BaseEvent<Any>) {
        if (baseEvent.eventCode == EventCode.LOGIN) {
            val intent = Intent(this, MineActivityMainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}
