package com.example.myproject.mvp;

/**
 * @Author: cong
 * @Date: 2019/5/25
 * @Description:
 */
public interface MainContract {

    interface View extends BaseView {
        void showVersionUpdateDialog(String versionDetail);     // 显示版本更新信息
        void downloadApk();                                     // 下载apk
        void setApkUrl(String apkUrl);                          // 设置新版本的下载地址
    }

    interface Presenter extends IPresenter<MainContract.View> {
        void checkVersion(String currentVersion);//检查版本更新
    }
}
