package com.example.vuphu.app.user.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vuphu.app.user.UserProfileTab.AddMoneyHistoryFragment;
import com.example.vuphu.app.user.UserProfileTab.OrderHistoryFragment;
import com.example.vuphu.app.user.UserProfileTab.UserInfoFragment;

/**
 * Created by vuphu on 4/1/2018.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Thông Tin", "Đơn hàng", "Lịch Sử" };
    Context context;

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
       this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:

                return UserInfoFragment.newInstance();
            case 1:

                return OrderHistoryFragment.newInstance();
            case 2:
                return  AddMoneyHistoryFragment.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
