package com.du.newsforme.base;

import android.app.Application;

import com.du.newsforme.commons.Contant;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

/**
 * Created by Administrator on 2016/10/24.
 */

public class BaseApplication extends Application {
    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        if (baseApplication == null) {
            baseApplication = this;
        }


        initOkGo();
        Fresco.initialize(baseApplication);


    }

    /**
     * 初始化网络框架
     */
    private void initOkGo() {

//        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("Key", Contant.NEWSAPPKEY);    //header不支持中文
//        headers.put("commonHeaderKey2", "commonHeaderValue2");
//        HttpParams params = new HttpParams();
//        params.put("key", Contant.NEWSAPPKEY);     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");
//        //-----------------------------------------------------------------------------------//
        //必须调用初始化
        OkGo.init(baseApplication);

        OkGo.getInstance()
//                .debug("OkGo");//debug 调试
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST);   //默认缓存模式

    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }
}
