package com.example.restaurantguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;

import com.example.restaurantguide.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        onCreate(db);
    }

    //Insert
    public long insertInfo(String name, String address, String phone, String desc, String tag, String rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_NAME,name);
        values.put(Constants.C_ADDRESS,address);
        values.put(Constants.C_PHONE,phone);
        values.put(Constants.C_DESC,desc);
        values.put(Constants.C_TAG,tag);
        values.put(Constants.C_RATING,rating);
        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;
    }
    //Update
    public void updateInfo(String id, String name, String address, String phone, String desc, String tag, String rating){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.C_NAME,name);
        values.put(Constants.C_ADDRESS,address);
        values.put(Constants.C_PHONE,phone);
        values.put(Constants.C_DESC,desc);
        values.put(Constants.C_TAG,tag);
        values.put(Constants.C_RATING,rating);
        db.update(Constants.TABLE_NAME,values,Constants.C_ID+" = ? ",new String[]{id});
        db.close();
    }
    //Delete
    public void delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME,Constants.C_ID + " = ? ",new String[]{id});
        db.close();
    }

    //Map
    public List<String> getLocation (String id){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {Constants.C_ID};
        qb.setTables(Constants.TABLE_NAME);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null ,null,null);
        //Get restaurant address by ID
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String restaurantAddress =cursor.getString(cursor.getColumnIndex(Constants.C_ADDRESS));
                result.add(restaurantAddress);

            }while (cursor.moveToNext());
            db.close();
        }
        return result;
    }




    public List<Restaurant> getRestaurant(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {Constants.C_ID,Constants.C_NAME,Constants.C_ADDRESS,Constants.C_PHONE,Constants.C_DESC,Constants.C_TAG,Constants.C_RATING};
        qb.setTables(Constants.TABLE_NAME);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null ,null,null);
        //Get all restaurant object
        List<Restaurant> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Restaurant restaurant = new Restaurant();
                restaurant.setId(cursor.getInt(cursor.getColumnIndex(Constants.C_ID)));
                restaurant.setrName(cursor.getString(cursor.getColumnIndex(Constants.C_NAME)));
                restaurant.setrAddress(cursor.getString(cursor.getColumnIndex(Constants.C_ADDRESS)));
                restaurant.setrPhone(cursor.getString(cursor.getColumnIndex(Constants.C_PHONE)));
                restaurant.setrDesc(cursor.getString(cursor.getColumnIndex(Constants.C_DESC)));
                restaurant.setrTag(cursor.getString(cursor.getColumnIndex(Constants.C_TAG)));
                restaurant.setrRating(cursor.getString(cursor.getColumnIndex(Constants.C_RATING)));

                result.add(restaurant);
            }while (cursor.moveToNext());

        }
        return result;
    }

    public List<String> getRestaurantName(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {Constants.C_NAME};
        qb.setTables(Constants.TABLE_NAME);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null ,null,null);
        //Get all restaurant name
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String restaurantName =cursor.getString(cursor.getColumnIndex(Constants.C_NAME));

                result.add(restaurantName);
            }while (cursor.moveToNext());

        }
        return result;
    }

    public List<Restaurant> findRestaurantByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {Constants.C_NAME,Constants.C_ADDRESS,Constants.C_PHONE,Constants.C_DESC,Constants.C_TAG,Constants.C_RATING};
        qb.setTables(Constants.TABLE_NAME);
        Cursor cursor = qb.query(db,sqlSelect,Constants.C_NAME+" LIKE ?",new String[]{"%"+name+"%"},null ,null,null);
        //Get all restaurant object
        List<Restaurant> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Restaurant restaurant = new Restaurant();
                restaurant.setrName(cursor.getString(cursor.getColumnIndex(Constants.C_NAME)));
                restaurant.setrAddress(cursor.getString(cursor.getColumnIndex(Constants.C_ADDRESS)));
                restaurant.setrPhone(cursor.getString(cursor.getColumnIndex(Constants.C_PHONE)));
                restaurant.setrDesc(cursor.getString(cursor.getColumnIndex(Constants.C_DESC)));
                restaurant.setrTag(cursor.getString(cursor.getColumnIndex(Constants.C_TAG)));
                restaurant.setrRating(cursor.getString(cursor.getColumnIndex(Constants.C_RATING)));

                result.add(restaurant);
            }while (cursor.moveToNext());

        }

        Cursor cursor1 = qb.query(db,sqlSelect,Constants.C_TAG+" LIKE ?",new String[]{"%"+name+"%"},null ,null,null);

        if(cursor1.moveToFirst()){
            do{
                Restaurant restaurant = new Restaurant();
                restaurant.setrName(cursor1.getString(cursor.getColumnIndex(Constants.C_NAME)));
                restaurant.setrAddress(cursor1.getString(cursor.getColumnIndex(Constants.C_ADDRESS)));
                restaurant.setrPhone(cursor1.getString(cursor.getColumnIndex(Constants.C_PHONE)));
                restaurant.setrDesc(cursor1.getString(cursor.getColumnIndex(Constants.C_DESC)));
                restaurant.setrTag(cursor1.getString(cursor.getColumnIndex(Constants.C_TAG)));
                restaurant.setrRating(cursor1.getString(cursor.getColumnIndex(Constants.C_RATING)));

                result.add(restaurant);
            }while (cursor1.moveToNext());

        }


        return result;
    }

}
