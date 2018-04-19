package com.example.vuphu.app.user.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

import com.example.vuphu.app.user.catogries.HairCare;
import com.example.vuphu.app.user.catogries.Lipstick;
import com.example.vuphu.app.user.catogries.LotionFragment;
import com.example.vuphu.app.user.catogries.Perfume;
import com.example.vuphu.app.user.catogries.SkinCare;

public class CatoAdapter extends FragmentPagerAdapter{

    private String arr [] = {
            "lotion",
            "hair care",
            "skin care cosmetics",
            "perfume",
            "lipstick"};

    public CatoAdapter(Context context, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new LotionFragment();
            case 1 :
                return new HairCare();
            case 2 :
                return new SkinCare();
            case 3 :
                return new Perfume();
            case 4 :
                return new Lipstick();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arr[position] ;
    }
}
