package com.example.myproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.myproject.mvp.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: cong
 * @Date: 2019/5/25
 * @Description:
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private Unbinder mBinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract int getLayoutId();   // 初始化LayoutId
    protected abstract void initView();     // 初始化布局
    protected abstract void initData();     // 初始化数据

    @Override
    public void setStatusBarColor() {

    }

    @Override
    public void showNormalView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void reLoad() {

    }

    @Override
    public void showLoading() {

    }
}
