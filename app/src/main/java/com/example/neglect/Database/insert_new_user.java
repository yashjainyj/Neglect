package com.example.neglect.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.neglect.Utility.My_Utility;

public class insert_new_user extends SQLiteOpenHelper {
        public insert_new_user( Context context) {
        super(context,My_Utility.Database_Name,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ My_Utility.Table_User + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertdata(String name1,String email,String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME",name1);
        cv.put("EMAIL",email);
        cv.put("PASSWORD",password);
        //Log.i("HY","Hello-------------------->");
        long result = sqLiteDatabase.insert(My_Utility.Table_User,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getData()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ My_Utility.Table_User,null);
        return res;
    }

    public Cursor getDatabyid(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ My_Utility.Table_User+ " where ID=?",new String[] {id});
        return res;
    }
    public boolean update(String id,String name1,String email,String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME",name1);
        cv.put("EMAIL",email);
        cv.put("PASSWORD",password);
        //Log.i("HY","Hello-------------------->");
        long result = sqLiteDatabase.update(My_Utility.Table_User,cv,"ID=?",new String[] {id});
        if(result==-1)
            return false;
        else
            return true;
    }

}
