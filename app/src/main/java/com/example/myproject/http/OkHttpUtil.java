package com.example.myproject.http;

import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Author: cong
 * @Date: 2019/5/25
 * @Description:
 */
public class OkHttpUtil {

    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
    //    private static final File cacheDirectory = new File(MyApplication.getMyApplication().getCacheDir().getAbsolutePath(), "httpCache");
    //
    //    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);
    //
    //    //请求拦截
    //    private static RequestInterceptor requestInterceptor = new RequestInterceptor();
    //
    //    //响应拦截
    //    private static ResponseInterceptor responseInterceptor = new ResponseInterceptor();


    public static OkHttpClient getOkHttpClient() {
        if (null == mOkHttpClient) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .cookieJar(CookieJar.NO_COOKIES)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    //                    .addInterceptor(responseInterceptor)
                    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.i("http", message);
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                    //                    .cache(cache)
                    .build();
        }
        return mOkHttpClient;
    }


}
