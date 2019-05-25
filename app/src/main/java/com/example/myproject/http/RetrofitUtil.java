package com.example.myproject.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: cong
 * @Date: 2019/5/25
 * @Description:
 */
public class RetrofitUtil {

    //服务路径
    private static final String Url = "http://hzqb.sftsdg.com/YMF_Webs/";
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    //获取Retrofit对象

    protected static Retrofit getRetrofit(){
        if (null == mRetrofit){
            if (null == mOkHttpClient){
                mOkHttpClient = OkHttpUtil.getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }

}
