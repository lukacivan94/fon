package com.example.ivan.fon.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ivan on 12/9/17.
 */

public class KonsDBHelper extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "KONSULTACIJE.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY = "CREATE TABLE "
            + Konsultacija.KonsultacijaInfo.TABLE_NAME+"("
            + Konsultacija.KonsultacijaInfo.IME+" TEXT,"
            + Konsultacija.KonsultacijaInfo.TERMIN+" TEXT,"
            + Konsultacija.KonsultacijaInfo.KABINET+" TEXT);";


    public KonsDBHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created/opened...");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created...");

    }

    public void addInformation(String ime_prezime, String termin, String kabinet, SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(Konsultacija.KonsultacijaInfo.IME, ime_prezime);
        contentValues.put(Konsultacija.KonsultacijaInfo.TERMIN, termin);
        contentValues.put(Konsultacija.KonsultacijaInfo.KABINET, kabinet);
        db.insert(Konsultacija.KonsultacijaInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row inserted...");
    }

    public Cursor getInformation(SQLiteDatabase db){

        Cursor cursor;
        String[] projections = {Konsultacija.KonsultacijaInfo.IME, Konsultacija.KonsultacijaInfo.TERMIN, Konsultacija       .KonsultacijaInfo.KABINET};

        cursor = db.query(Konsultacija.KonsultacijaInfo.TABLE_NAME, projections, null,null,null,null,null);
        return cursor;
    }

    public Cursor getIspit(String ime_prezime, SQLiteDatabase sqLiteDatabase){

        String[] projections = {Konsultacija.KonsultacijaInfo.TERMIN, Konsultacija.KonsultacijaInfo.KABINET};
        String selection = Konsultacija.KonsultacijaInfo.IME+" LIKE ?";
        String[] selection_args = {ime_prezime};
        Cursor cursor = sqLiteDatabase.query(Konsultacija.KonsultacijaInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    public void deleteInformation(String ime_prezime, SQLiteDatabase sqLiteDatabase){

        String selection = Konsultacija.KonsultacijaInfo.IME+" LIKE ?";
        String[] selection_args = {ime_prezime};
        sqLiteDatabase.delete(Konsultacija.KonsultacijaInfo.TABLE_NAME, selection, selection_args);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
