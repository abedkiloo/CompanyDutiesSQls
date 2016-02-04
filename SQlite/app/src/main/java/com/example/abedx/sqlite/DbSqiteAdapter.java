package com.example.abedx.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abedx on 1/11/2016.
 */
public class DbSqiteAdapter {
    DbSQlite dbSQlite;

    public DbSqiteAdapter(Context context) {
        dbSQlite = new DbSQlite(context);
    }

    public long Insert(String Fname, String Sname) {
        SQLiteDatabase db = dbSQlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbSQlite.F_NAME, Fname);
        contentValues.put(DbSQlite.S_NAME, Sname);
        long id = db.insert(DbSQlite.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getAllData() {
        String[] columns = {DbSQlite.UID, DbSQlite.F_NAME, dbSQlite.S_NAME};
        SQLiteDatabase db = dbSQlite.getWritableDatabase();
        Cursor cursor = db.query(DbSQlite.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int indexUID = cursor.getColumnIndex(dbSQlite.UID);
            int indexFName = cursor.getColumnIndex(dbSQlite.F_NAME);
            int indexSName = cursor.getColumnIndex(dbSQlite.F_NAME);
            int gotID = cursor.getInt(indexUID);
            String gotFName = cursor.getString(indexFName);
            String gotSName = cursor.getString(indexSName);
            stringBuffer.append(gotID + " " + " " + gotFName + " " + gotSName + " " + "\n");
        }
        return stringBuffer.toString();

    }

    public String getSpecificData(String name) {
        //get an object of sqlite database
        SQLiteDatabase db = dbSQlite.getWritableDatabase();
        //specify the columns
        String[] columns = {dbSQlite.F_NAME};
        //specify a cursor object to get the specified data with its query
        Cursor cursor = db.query(dbSQlite.TABLE_NAME, columns, dbSQlite.F_NAME + "='" + name + "'", null, null, null, null);
        //create an istance of string object to help append the data
        StringBuffer stringBuffer = new StringBuffer();
        //move through the records searching a specified record
        while (cursor.moveToNext()) {
            //get postion of the column inside the DQLite database
            int indexName = cursor.getColumnIndex(dbSQlite.F_NAME);
            //put the value from the databasr into a string
            String gotName = cursor.getString(indexName);
            //append the value from the data base into a stringbuffer
            stringBuffer.append(" " + " " + gotName + "\n");
        }
        return stringBuffer.toString();

    }

    public String getSpecificDatamoreParams(String F_name, String password) {
        SQLiteDatabase db = dbSQlite.getWritableDatabase();
        String[] columns = {dbSQlite.UID};
        String select = "";//(dbSQlite.C_NAME + "=? AND " / .C_NAME += "=?");
        String[] selectionArgs = {F_name, password};
        Cursor cursor = db.query(dbSQlite.TABLE_NAME, columns, select, selectionArgs, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int indexName = cursor.getColumnIndex(dbSQlite.F_NAME);
            String gotName = cursor.getString(indexName);
            stringBuffer.append(" " + " " + gotName + "\n");
        }
        return stringBuffer.toString();

    }

    public int Update(String oldName, String newName) {
        SQLiteDatabase db = dbSQlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbSQlite.F_NAME, newName);
        String[] whereArgs = {oldName};
        //return the number of rows have been affected
        int count = db.update(dbSQlite.TABLE_NAME, contentValues, dbSQlite.F_NAME + "=?", whereArgs);
        return count;
    }

    public int Delete(String deleteWhat) {
        SQLiteDatabase db = dbSQlite.getWritableDatabase();
        String[] whereArgs = {deleteWhat};
        int count = db.delete(dbSQlite.TABLE_NAME, dbSQlite.F_NAME + "=?", whereArgs);
        return count;
    }

    static class DbSQlite extends SQLiteOpenHelper {
        private static final String Db_NAME = "OFFICE DATABASE";
        private static final int VERSION_NUMBER = 1;
        private static final String TABLE_NAME = "EMPLOYEES";
        private static final String UID = "_id";
        private static final String F_NAME = "F_NAME";
        private static final String S_NAME = "S_NAME";
        private static String C_PASSWORD = "PASSWORD";
        private static String C_ESTATE = "ESTATE";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + F_NAME + " VARCHAR(255)," +
                S_NAME + " VARCHAR(255)" + ");";
        private static final String ALTER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + " ";

        private Context context;

        public DbSQlite(Context context) {
            super(context, Db_NAME, null, VERSION_NUMBER);
            this.context = context;
            MessageClass.message(context, "DataBase Created");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
            MessageClass.message(context, "onCreate Called");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(ALTER_TABLE);
            onCreate(db);
        }
    }
}
