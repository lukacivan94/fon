package com.example.ivan.fon.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ivan on 12/7/17.
 */

public class IspitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ISPITI.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY = "CREATE TABLE "
            + Ispit.IspitInfo.TABLE_NAME+"("
            + Ispit.IspitInfo.NAZIV+" TEXT,"
            + Ispit.IspitInfo.OCENA+" TEXT);";


    public IspitDbHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created/opened...");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created...");

    }

    public void addInformation(String naziv, String ocena, SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(Ispit.IspitInfo.NAZIV, naziv);
        contentValues.put(Ispit.IspitInfo.OCENA, ocena);
        db.insert(Ispit.IspitInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row inserted...");
    }

    public Cursor getInformation(SQLiteDatabase db){

        Cursor cursor;
        String[] projections = {Ispit.IspitInfo.NAZIV, Ispit.IspitInfo.OCENA};

        cursor = db.query(Ispit.IspitInfo.TABLE_NAME, projections, null,null,null,null,null);
        return cursor;
    }

    public Cursor getIspit(String naziv_ispita, SQLiteDatabase sqLiteDatabase){

        String[] projections = {Ispit.IspitInfo.OCENA};
        String selection = Ispit.IspitInfo.NAZIV+" LIKE ?";
        String[] selection_args = {naziv_ispita};
        Cursor cursor = sqLiteDatabase.query(Ispit.IspitInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    public void deleteInformation(String naziv_ispita, SQLiteDatabase sqLiteDatabase){

        String selection = Ispit.IspitInfo.NAZIV+" LIKE ?";
        String[] selection_args = {naziv_ispita};
        sqLiteDatabase.delete(Ispit.IspitInfo.TABLE_NAME, selection, selection_args);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
