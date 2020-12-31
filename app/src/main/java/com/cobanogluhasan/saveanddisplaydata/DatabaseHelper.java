package com.cobanogluhasan.saveanddisplaydata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String TABLE_NAME = "vocabularyList";
    private static final String COL1 = "ID";
    private static final String COL2 = "WORD";
    private static final String COL3 = "SYNONYM";
    private static final String COL4 = "DEFINITION";
    private static final String COL5 = "EXAMPLE";


    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " WORD TEXT, SYNONYM TEXT, DEFINITION TEXT, EXAMPLE TEXT)";



        database.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLE_NAME);

    }

    public boolean addData(String word,String synonym, String definition, String example) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, word);

        contentValues.put(COL3,synonym);
        contentValues.put(COL4,definition);
        contentValues.put(COL5, example);


        long result = database.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }




    public Cursor getData(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = database.rawQuery(query, null);
        return data;
    }


    public Cursor getItemID(String word,String synonym, String definition,String example){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + word + "' AND" + " WHERE " + COL3 + " = '" + synonym + "' AND" + " WHERE " + COL4 + " = '" + definition + "' AND" + " WHERE " + COL5 + " = '" + example + "'";


        Cursor data = database.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        db.execSQL(query);
    }






}
