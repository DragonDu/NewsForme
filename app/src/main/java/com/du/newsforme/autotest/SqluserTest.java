package com.du.newsforme.autotest;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.du.newsforme.SqluserModel;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.sqldelight.RowMapper;

import static android.R.attr.id;
import static android.R.attr.name;

/**
 * Created by Administrator on 2017/1/30.
 */
@AutoValue
public abstract class SqluserTest implements SqluserModel {

    public static final SqluserTest.Factory<SqluserTest> FACTORY =new Factory<>(new Creator<SqluserTest>() {

        @Override
        public SqluserTest create(long _id, @NonNull String login, @Nullable Long age,
                                  @Nullable String email, @Nullable String name) {
            return new AutoValue_SqluserTest(id,login,age,email,name);
        }


    });
    public static TypeAdapter<SqluserTest> typeAdapter(final Gson gson){
        return new AutoValue_SqluserTest.GsonTypeAdapter(gson);
    }

    public static final RowMapper<SqluserTest> MAPPER = FACTORY.select_by_nameMapper();

}
