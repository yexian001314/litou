package com.shqj.arrange.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.sleep.commonlib.widget.wrappingpager.WrappingFragmentStatePagerAdapter

/**
 * Created by SleepYM09 on 2017/12/18.
 *
 * blog: www.sleepym09.com
 */
class ProjectDetailPagerAdapter(private val fragments : List<Fragment>, fm: FragmentManager?): WrappingFragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}