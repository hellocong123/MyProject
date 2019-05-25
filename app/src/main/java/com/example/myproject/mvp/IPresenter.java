package com.example.myproject.mvp;

import io.reactivex.disposables.Disposable;

/**
 * @Author: cong
 * @Date: 2019/5/25
 * @Description:
 */
public interface IPresenter<T extends BaseView> {

    void attachView(T view);                        // Presenter绑定View
    void detachView();                              // 解除View
    void subscribeEvent();                          // 订阅事件
    void addRxSubscribe(Disposable disposable);     // 管理Rx的订阅事件
    void unDisposable();                            // 注销所有请求


    // addRxSubscribe：将网络请求的每一个disposable添加进入CompositeDisposable，再退出时候一并注销
}
