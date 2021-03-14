package com.example.restaurantguide;

public class Constants {
    //db name
    public static final String DB_NAME = "RESTAURANT_DB";
    //db version
    public static final int DB_VERSION = 1;
    //db table
    public static final String TABLE_NAME = "RESTAURANT_INFO_TABLE";
    //table columns;
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_ADDRESS = "ADDRESS";
    public static final String C_PHONE = "PHONE";
    public static final String C_DESC = "DESC";
    public static final String C_TAG = "TAG";
    public static final String C_RATING = "RATING";

    //query for creating table
    public static final String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME+"("
            +C_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +C_NAME + " TEXT, "
            +C_ADDRESS + " TEXT, "
            +C_PHONE + " TEXT, "
            +C_TAG + " TEXT, "
            +C_DESC + " TEXT, "
            +C_RATING + " TEXT"
            +");";
}
