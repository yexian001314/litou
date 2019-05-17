package com.shqj.mine.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.sleep.commonlib.widget.wrappingpager.WrappingFragmentStatePagerAdapter

/**
 * Created by SleepYM09 on 2018/3/29.
 *
 * blog: www.sleepym09.com
 */
 class HelpCenterAdapter(private val fragments: List<Fragment>, fm: FragmentManager?) : WrappingFragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}