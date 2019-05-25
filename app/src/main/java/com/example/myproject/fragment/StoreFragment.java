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
public class StoreFragment extends BaseFragment {
    private final String TAG = StoreFragment.class.getName();
    public static StoreFragment newInstance() {
        Bundle arg = new Bundle();
        StoreFragment fragment = new StoreFragment();
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
        return R.layout.fragment_store;
    }
}
