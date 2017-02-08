package com.du.newsforme.autotest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.du.newsforme.base.BaseApplication;

/**
 * Created by Administrator on 2017/1/30.
 */

public class Mysqlhelper extends SQLiteOpenHelper {
    private static final String TAG = Mysqlhelper.class.getSimpleName();
    private static final String DB_NAME = "GithubUserDB";//数据库名
    private static final int DATABASE_VERSION = 1;
    private static Mysqlhelper mGithubUserHepler;

    public Mysqlhelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    public static Mysqlhelper getInstance() {
        return GithubUserHeplerInstanceHolder.hepler;
    }

    public static class GithubUserHeplerInstanceHolder {
        private static Mysqlhelper hepler = new Mysqlhelper(BaseApplication.getInstance());
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqluserTest.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
