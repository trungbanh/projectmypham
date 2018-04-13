package com.example.vuphu.app.user.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.vuphu.app.user.UserProfileTab.AddMoneyHistoryFragment
import com.example.vuphu.app.user.UserProfileTab.OrderHistoryFragment
import com.example.vuphu.app.user.UserProfileTab.UserInfoFragment

/**
 * Created by vuphu on 4/1/2018.
 */

class TabPagerAdapter(fm: FragmentManager, internal var context: Context) : FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf("Thông Tin", "Đơn hàng", "Lịch Sử")

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 ->

                return UserInfoFragment.newInstance()
            1 ->

                return OrderHistoryFragment.newInstance()
            2 -> return AddMoneyHistoryFragment.newInstance()

            else -> return null
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return tabTitles[position]
    }
}
