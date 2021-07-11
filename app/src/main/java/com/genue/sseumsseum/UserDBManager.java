package com.genue.sseumsseum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserDBManager extends SQLiteOpenHelper {
    static final String USER_DB = "user.db";
    static final String TABLE_USER_INFO = "UserTable";
    static final String TABLE_MONTHLY_EARN = "MonthlyEarnTable";
    static final String TABLE_MONTHLY_SAVE = "MonthlySaveTable";
    static final String TABLE_MONTHLY_SPEND = "MonthlySpendTable";
    static final int DB_VERSION = 1;

    private static UserDBManager dbManager = null;

    private SQLiteDatabase mDB = null;

    //싱글톤 구현
    public static UserDBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new UserDBManager(context);
        }
        return dbManager;
    }

    private UserDBManager(Context context) {
        super(context, USER_DB, null, DB_VERSION);
        mDB = this.getWritableDatabase();
//        resetTable(mDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table 생성

        createTable(db);
    }

    private void resetTable(SQLiteDatabase db){
        Log.d(">>>>", "resetTable");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTHLY_EARN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTHLY_SAVE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTHLY_SPEND);

        createTable(db);
    }

    private void createTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER_INFO +
                " (_id INTEGER PRIMARY KEY," +
                "startmoney INTEGER," +
                "grade TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_MONTHLY_EARN +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "money INTEGER," +
                "repeat INTEGER," +
                "exp TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_MONTHLY_SAVE +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "startmoney INTEGER," +
                "grade TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_MONTHLY_SPEND +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "startmoney INTEGER," +
                "grade TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_INFO);
        createTable(db);
    }

    //첫 시작시 사용함
    public boolean startData(int startMoney){
        ContentValues content = new ContentValues();
        content.put("_id", "1");
        content.put("startmoney", startMoney);
        if(mDB.insert(TABLE_USER_INFO, null, content) == -1){
            return false;
        }
        return true;
    }

    public boolean insertData(String table, ContentValues content){
        if(mDB.insert(table, null, content) == -1){
            return false;
        }
        return true;
    }

    public int getAccount(){
        String sql = "select * from " + TABLE_USER_INFO;
        Cursor result = mDB.rawQuery(sql, null);
        if(result.moveToFirst()){
            int money = result.getInt(1);
            result.close();
            return money;
        }
        //비어있어
        result.close();
        return -1;
    }

    public boolean isUserInfoEmpty(){
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

    public boolean isEmpty(String table){
        String sql = "select * from " + table;
        Cursor result = mDB.rawQuery(sql, null);
        if(result.moveToFirst()){
            result.close();
            return false;
        }
        //비어있어
        result.close();
        return true;
    }

    public boolean insertMonthlyEarn(int money, int repeat){
        ContentValues content = new ContentValues();
        content.put("money", money);
        content.put("repeat", repeat);
        if(mDB.insert(TABLE_MONTHLY_EARN, null, content) == -1){
            return false;
        }
        return true;
    }

    public void showMonthlyEarn(){
        String sql = "select * from " + TABLE_MONTHLY_EARN;
        Cursor result = mDB.rawQuery(sql, null);
        while(result.moveToNext()){
            Log.d(">>>>", "_id " + result.getInt(0) + " money " + result.getInt(1) + " repeat " + result.getInt(2));
        }
    }

    public ArrayList<MonthlyIOInfo> getEarnInfo(){
        ArrayList<MonthlyIOInfo> lists = new ArrayList<>();
        String sql = "select * from " + TABLE_MONTHLY_EARN;
        Cursor result = mDB.rawQuery(sql, null);
        while(result.moveToNext()){
            Log.d(">>>>", "_id " + result.getInt(0) + " money " + result.getInt(1) + " repeat " + result.getInt(2));
            MonthlyIOInfo info = new MonthlyIOInfo(result.getInt(0), result.getInt(1), result.getInt(2), 13);
            lists.add(info);
        }

        return lists;
    }
}
