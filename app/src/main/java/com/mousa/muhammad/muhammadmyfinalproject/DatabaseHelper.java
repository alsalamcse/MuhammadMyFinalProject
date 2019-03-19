//package com.mousa.muhammad.muhammadmyfinalproject;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    public static final String DATABASE_NAME="Workers.db";
//    public static final String TABLE_NAME="Workers_Table";
//    public static final String COL_1="ID";
//    public static final String COL_2="FIRST_NAME";
//    public static final String COL_3="LAST_NAME";
//    public static final String COL_4="PASSWORD";
//
//
//    public DatabaseHelper( Context context) {
//
//        super(context, DATABASE_NAME, null, 1);
////        SQLiteDatabase db= this.getWritableDatabase();
//
//
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table"+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST TEXT,LAST TEXT,PASSWORD INTEGER )" );
//
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DropaTable"+ TABLE_NAME);
//        onCreate(db);
//
//    }
//
//    public boolean insertData(String ID ,String First,String Last,String password){
//        SQLiteDatabase db= this.getWritableDatabase();
//        ContentValues contentValues= new ContentValues();
//        contentValues.put(COL_1,ID);
//        contentValues.put(COL_2,First);
//        contentValues.put(COL_3,Last);
//        contentValues.put(COL_4,password);
//        long result =db.insert(TABLE_NAME,null,contentValues);
//        if(result==-1)
//            return false;
//        else
//            return true;
//
//
//        }
//
//
//
//
//}
