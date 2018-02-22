package com.example.administrator.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK =
            "create table Book(" +
                    "id integer primary " +
                    "key autoincrement, " +
                    "author text, " +
                    "price real, " +
                    "pages integer, " +
                    "name text);";


    public static final String CREATE_CATEGORY =
            "create table Category(" +
                    "id integer primary " +
                    "key autoincrement, " +
                    "category_name text, " +
                    "category_code integer);";

    private Context context;

    /**
     * 构造
     * @param context context
     * @param name 数据库名
     * @param factory 返回一个自定义的cursor，一般为null
     * @param version 数据库版本号，用于升级
     */
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context; //传入context
    }

    /**
     * 创建
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
        //Toast.makeText(this.context, "创建成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 升级
     * @param sqLiteDatabase 数据库
     * @param oldVersion 就版本号
     * @param newVersion 新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists Book");
        sqLiteDatabase.execSQL("drop table if exists Category");
        onCreate(sqLiteDatabase); //删除完再次执行onCreate
    }


}
