package com.du.newsforme;


import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GsonTypeAdapter;


/**
 * Created by Administrator on 2017/1/18.
 */

@AutoValue
public abstract class Usertest implements Parcelable {
    @SerializedName("Id") public abstract int id();
    @SerializedName("Name") public abstract String name();

    public static Usertest create(int id, String name) {
        return builder()
                .id(id)
                .name(name)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_Usertest.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(int id);

        public abstract Builder name(String name);

        public abstract Usertest build();
    }

    public static TypeAdapter<Usertest> typeAdapter(Gson gson) {
        return new AutoValue_Usertest.GsonTypeAdapter(gson);
    }
}
