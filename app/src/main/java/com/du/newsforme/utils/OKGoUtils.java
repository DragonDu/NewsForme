package com.du.newsforme.utils;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.util.Log;

import com.du.newsforme.commons.Contant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2016/10/26.
 */

public class OKGoUtils {


    public OKGoUtils() {
    }

    public static void get(String url, String tag, String type, AbsCallback callback) {
        HttpParams params = new HttpParams("key", Contant.NEWSAPPKEY);
        HttpParams leixing = new HttpParams("type", type);
        OkGo.get(url)
                .tag(tag)
                .cacheKey("News")
                .params(params)
                .params(leixing)
//                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    public static void search(String url, String type, AbsCallback callback) {
        HttpParams params = new HttpParams("key", Contant.NEWSSEARCH);
        HttpParams params1 = new HttpParams("q", type);

        OkGo.get(url)
                .cacheKey("search")
                .params(params)
                .params(params1)
                .execute(callback);
    }
}
