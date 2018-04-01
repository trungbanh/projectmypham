package com.example.vuphu.app.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuphu.app.R;
import com.example.vuphu.app.object.users;
import com.example.vuphu.app.user.adapter.TabPagerAdapter;


public class ProfileFragment extends Fragment {


    private static users userData;

    public ProfileFragment() {

    }


    public static ProfileFragment newInstance(users user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        userData = user;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        viewPager.setAdapter(new TabPagerAdapter(getActivity().getSupportFragmentManager(),
                getActivity()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }


}
