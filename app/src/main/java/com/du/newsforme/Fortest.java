package com.du.newsforme;


import com.du.newsforme.autotest.Mysqlhelper;
import com.du.newsforme.autotest.SqluserTest;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/1/29.
 */

public class Fortest {

    String s = SqluserModel.CREATE_TABLE;

    public void getwhat() {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(MyAdapterFactory.create())
                .create();
        Usertest usertest = gson.fromJson(s, Usertest.class);
    }

    public void getsql() {
        SqlBrite brite = SqlBrite.create();
//        SqlBrite.Builder builder = new SqlBrite.Builder();

        BriteDatabase briteDatabase = brite.wrapDatabaseHelper(Mysqlhelper.getInstance(),
                Schedulers.io());
        briteDatabase.update(SqluserTest.TABLE_NAME, SqluserTest.FACTORY.marshal()._id(111).age
                ((long) 24).login("yes").asContentValues(), null, null);
    }


}
