package com.autosigninwxq;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "apps.db";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context,String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //创建数据库表格
        db.execSQL("CREATE TABLE IF NOT EXISTS SupportApps(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " appname VARCHAR,packagename VARCHAR,versionname VARCHAR,versioncode VARCHAR)");
        db.execSQL("CREATE TABLE IF NOT EXISTS SignRecord(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " appname VARCHAR,time VARCHAR,endtime VARCHAR,ifsign BOOLEAN)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("ALTER TABLE SupportApps COLUMN other STRING");
        db.execSQL("ALTER TABLE SignRecord COLUMN other STRING");
    }
}
