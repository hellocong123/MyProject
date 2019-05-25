package com.example.myproject.fragment;

import android.os.Bundle;
import android.util.Log;

import com.example.myproject.BaseFragment;
import com.example.myproject.R;

/**
 * @Author: cong
 * @Date: 2019/5/24 17:28
 * @Description:
 */
public class HomeFragment extends BaseFragment {

    private final String TAG = HomeFragment.class.getName();

    public static HomeFragment newInstance() {
        Bundle arg = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        Log.i(TAG, "loadData: ");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
