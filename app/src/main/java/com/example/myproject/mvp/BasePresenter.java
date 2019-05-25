package com.example.myproject.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author: cong
 * @Date: 2019/5/25
 * @Description:
 */
public class BasePresenter<V extends BaseView> implements IPresenter<V> {

    protected V mView;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }


    /**
     * 解除View 和 Rx订阅事件
     */
    @Override
    public void detachView() {
        this.mView = null;
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 管理Rx的订阅事件
     */
    @Override
    public void addRxSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    @Override
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


    @Override
    public void subscribeEvent() {

    }
}
