package com.du.newsforme;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by Administrator on 2017/1/30.
 */
@GsonTypeAdapterFactory
public abstract class MyAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create(){
        return new AutoValueGson_MyAdapterFactory();
    }
}
