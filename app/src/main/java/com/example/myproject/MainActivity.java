package com.example.myproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.myproject.fragment.HomeFragment;
import com.example.myproject.fragment.MeFragment;
import com.example.myproject.fragment.ProjectFragment;
import com.example.myproject.fragment.StoreFragment;
import com.example.myproject.mvp.BaseMvpActivity;
import com.example.myproject.mvp.IPresenter;
import com.example.myproject.mvp.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private List<BaseFragment> mFragments;
    private int mPreFragmentPosition = 0;//上一个被选中的Fragment位置
    private MainPresenter mPresenter;


    /**
     * 显示和隐藏Fragment
     */
    private void showAndHideFragment(BaseFragment show, BaseFragment hide) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show != hide) {
            transaction.show(show).hide(hide).commitAllowingStateLoss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigation.setOnNavigationItemSelectedListener(this);

        mFragments = new ArrayList<>();
        if (savedInstanceState == null) {

            // 初始化Fragment，把所有Fragment添加到集合
            mFragments.add(HomeFragment.newInstance());
            mFragments.add(ProjectFragment.newInstance());
            mFragments.add(StoreFragment.newInstance());
            mFragments.add(MeFragment.newInstance());
            loadAllFragment(mFragments);
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

        mPresenter.checkVersion("1");
    }

    /**
     * 把所有的Fragment添加到事务，并除了第一个其他的全隐藏
     */
    private void loadAllFragment(List<BaseFragment> mFragments) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            BaseFragment fragment = mFragments.get(i);
            transaction.add(R.id.frameContain, fragment, fragment.getClass().getName());
            if (i != 0) {
                transaction.hide(fragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    /***
     * 底部导航按钮点击事件
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                showAndHideFragment(mFragments.get(0), mFragments.get(mPreFragmentPosition));
                mPreFragmentPosition = 0;
                return true;
            case R.id.navigation_dashboard:
                showAndHideFragment(mFragments.get(1), mFragments.get(mPreFragmentPosition));
                mPreFragmentPosition = 1;
                return true;
            case R.id.navigation_notifications:
                showAndHideFragment(mFragments.get(2), mFragments.get(mPreFragmentPosition));
                mPreFragmentPosition = 2;
                return true;
            case R.id.navigation_me:
                showAndHideFragment(mFragments.get(3), mFragments.get(mPreFragmentPosition));
                mPreFragmentPosition = 3;
                return true;
        }
        return false;
    }

    @Override
    protected IPresenter getPresenter() {
        mPresenter = new MainPresenter();
        return mPresenter;
    }
}
