package com.example.vuphu.app.admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuphu.app.ItemOffsetDecoration;
import com.example.vuphu.app.R;
import com.example.vuphu.app.admin.adapter.AdminUserApdater;
import com.example.vuphu.app.object.users;

import java.util.ArrayList;


public class AdminUserFragment extends Fragment {

    private ArrayList<users> list = new ArrayList<>();
    private RecyclerView list_users;
    public AdminUserFragment() {
        // Required empty public constructor
    }

   
    public static AdminUserFragment newInstance() {
        AdminUserFragment fragment = new AdminUserFragment();
        Bundle args = new Bundle();
      
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
        View v = inflater.inflate(R.layout.fragment_admin_user, container, false);

        list.add(new users("Nguyễn Thị A","B"));
        list.add(new users("Nguyễn Thị A","B"));
        list.add(new users("Nguyễn Thị A","B"));
        list.add(new users("Nguyễn Thị A","B"));
        list.add(new users("Nguyễn Thị A","B"));
        list.add(new users("Nguyễn Thị A","B"));
        list.add(new users("Nguyễn Thị A","B"));
        list.add(new users("Nguyễn Thị A","B"));
        list_users = v.findViewById(R.id.list_admin_user);
        list_users.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        list_users.setLayoutManager(gridLayoutManager);


        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        list_users.addItemDecoration(itemDecoration);
        AdminUserApdater.userAdap adap = new AdminUserApdater.userAdap(list, getContext());
        list_users.setAdapter(adap);
        return v;
    }

}
