package com.example.myproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * @Author: cong
 * @Date: 2019/5/25
 * @Description:
 */
public class LazyLoadFragment extends Fragment {

    private boolean isViewCreated = false;      // 布局是否被创建
    private boolean isLoadData = false;         // 数据是否加载
    private boolean isFirstVisible = true;      // 是否第一次可见
    private String TAG = "BaseLazy";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;
        Log.d(TAG, "onHiddenChanged名字: " + this.getClass().getSimpleName()
                + "isViewCreated：" + isViewCreated
        );
    }

    /**
     * 初始化数据加载
     * a.如果当前Fragment没有被隐藏并对用户可见时 && 已经添加到Activity
     * b.开始使用lazyLoad加载数据
     * c.然后设置tag，数据已经加载true、第一次加载过了，所以设置为false
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Fragment用户可见并已添加到Activity
        if (isFragmentVisible(this) && this.isAdded()) {
            // 这里有两个判断，一个是判断从当前的Fragment获取一个父控件，看得到的是Activity，还是Fragment
            // 如果getParentFragment==null,就证明它的父类是一个Activity，或者得到一个Fragment
            if (this.getParentFragment() == null || isFragmentVisible(this.getParentFragment())) {
                // 使用懒加载方式加载数据
                onLazyLoadData();
                // 设置这个数据已经加载
                isLoadData = true;
                // 设置这个数据第一次可见后，再把它设置为false
                if (isFirstVisible)
                    isFirstVisible = false;
            }
        }
    }

    /**
     * 当前Fragment对用户可见调用
     * 如果Fragment可见，并且没有加载过数据，那么就去使用lazyLoad加载数据
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //        Log.d(TAG, "Fragment名：" + this.getClass().getSimpleName()
        //                + " setUserVisibleHint(): "
        //                + " isVisibleToUser" + isVisibleToUser
        //                + " hide: " + this.isHidden()
        //                + " add :" + this.isAdded()
        //                + " visible: " + this.isVisible()
        //                + " resumed: " + this.isResumed());

        // 如果该Fragment用户可见，并且没有加载过数据，已经创建视图，已经添加到Activity
        if (isFragmentVisible(this) && !isLoadData && isViewCreated && this.isAdded()) {
            // 可以加载数据
            onLazyLoadData();
            isLoadData = true;
        }
    }


    /***
     * 如果片段现在被隐藏，则为True，否则为false。
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //        Log.d(TAG, "Fragment名：" + this.getClass().getSimpleName()
        //                + " 隐藏改变: " + hidden
        //                + " 是否隐藏: " + this.isHidden()
        //                + " 是否添加 :" + this.isAdded()
        //                + " 是否可见: " + this.isVisible()
        //                + " 就否Resumed状态: " + this.isResumed());


        //onHiddenChanged调用在Resumed之前，所以此时可能fragment被add, 但还没resumed
        if (!hidden && !this.isResumed())
            return;
        //使用hide和show时，fragment的所有生命周期方法都不会调用，除了onHiddenChanged（）
        if (!hidden && isFirstVisible && this.isAdded()) {
            onLazyLoadData();
            isFirstVisible = false;
        }
    }


    /**
     * Fragment被Destroy时，恢复所有状态
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        isLoadData = false;
        isFirstVisible = true;
    }

    /**
     * Fragment没有被隐藏 并且 用户可见
     */
    private boolean isFragmentVisible(Fragment fragment) {
        return !fragment.isHidden() && fragment.getUserVisibleHint();
    }

    /**
     * 使用懒加载loadData
     */
    protected void onLazyLoadData() {
    }
}
