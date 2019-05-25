package com.example.myproject.mvp;

import com.example.myproject.activity.BaseActivity;

/**
 * @Author: cong
 * @Date: 2019/5/25
 * @Description:
 */
public abstract class BaseMvpActivity<P extends IPresenter> extends BaseActivity {


    protected P mPresenter;

    @Override
    protected void initView() {
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /**
     * 子类创建Presenter
     */
    protected abstract P getPresenter();
}
