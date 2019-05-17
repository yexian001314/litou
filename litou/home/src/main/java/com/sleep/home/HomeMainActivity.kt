package com.sleep.home

import android.content.Intent
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.home.fragment.HomeFragment
import com.sleep.uulib.uubase.UUBaseActivity
import org.simple.eventbus.Subscriber

class HomeMainActivity : UUBaseActivity() {
    override fun retryGetData() {
    }

    override fun getLayoutResourse(): Int {
        return R.layout.home_activity_main
    }

    private val homeFragment = HomeFragment()

    override fun initView() {
        hideBackView()
        setTitle("首页")
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.content, homeFragment)
        transaction.commit()
    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        homeFragment.onFirstUserVisible()
    }

    @Subscriber
    fun receiveLogin(baseEvent: BaseEvent<Any>){
        if(baseEvent.eventCode == EventCode.LOGIN){
            val intent = Intent(this, HomeMainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}
