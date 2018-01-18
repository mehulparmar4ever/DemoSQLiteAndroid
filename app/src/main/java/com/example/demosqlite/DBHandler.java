package com.example.demosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sooryen on 1/17/18.
 */

public class DBHandler extends SQLiteOpenHelper {

    //database version
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "product.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLOUM_ID = "_id";
    public static final String COLOUM_PRODUCTNAME = "productname";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //TO CREATE A NEW TABLE
    //Note:- space is necessory in between name
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + " (" + COLOUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLOUM_PRODUCTNAME + " TEXT" + ")";
        Log.i("Tag", query);

        db.execSQL(query);
    }

    //TO DELETE TABLE, CALL BELOW METHOD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("TAG", "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Create custom methods to add data into database
    public void addProducts(Products products) {
        ContentValues values = new ContentValues();
        values.put(COLOUM_PRODUCTNAME,products.get_productName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS,null,values);
        db.close();
    }

    //Custom method to delete products
    public void  deleteProduct(String productname) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLOUM_PRODUCTNAME + " =\"" + productname + "\";";
        Log.i("Tag", query);
        db.execSQL(query);
        db.close();
    }

    //get database
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS;
        Log.i("Tag", query);

        //cursor is point to the current row...
        //we move cursor, to change row
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {

            if(c.getString(c.getColumnIndex("productname")) != null) {
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }

            c.moveToNext();
        }

        db.close();
        return  dbString;
    }
}
