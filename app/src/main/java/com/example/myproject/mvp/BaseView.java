package com.example.myproject.mvp;

/**
 * @Author: cong
 * @Date: 2019/5/25
 * @Description:
 */
public interface BaseView {

    void setStatusBarColor();       // 设置状态栏颜色
    void showNormalView();          // 显示正常布局
    void showErrorView();           // 显示错误布局
    void reLoad();                  // 重新加载
    void showLoading();             // 显示加载布局

}
