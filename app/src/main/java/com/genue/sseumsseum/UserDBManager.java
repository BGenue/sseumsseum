package com.genue.sseumsseum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class UserDBManager extends SQLiteOpenHelper {
    static final String USER_DB = "user.db";
    static final String TABLE_USER_INFO = "UserTable";
    static final int DB_VERSION = 1;

    private static UserDBManager dbManager = null;

    private SQLiteDatabase mDB = null;

    //싱글톤 구현
    public static UserDBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new UserDBManager(context);
        }
        Toast.makeText(context, "인스턴스", Toast.LENGTH_SHORT).show();
        return dbManager;
    }

    private UserDBManager(Context context) {
        super(context, USER_DB, null, DB_VERSION);
        mDB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table 생성
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER_INFO +
                " (_id INTEGER PRIMARY KEY," +
                "title TEXT," +
                "category TEXT," +
                "grade TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_INFO);
        onCreate(db);
    }

    public boolean insertData(int table, ContentValues content){
        if(mDB.insert(TABLE_USER_INFO, null, content) == -1){
            return false;
        }
        return true;
    }

    public boolean isEmpty(){
        String sql = "select * from " + TABLE_USER_INFO;
        Cursor result = mDB.rawQuery(sql, null);
        if(result.moveToFirst()){
            result.close();
            return false;
        }
        //비어있어
        result.close();
        return true;

    }
}
