package com.tommywebdesigns.phonestore.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.tommywebdesigns.phonestore.model.data.Phone;
import com.tommywebdesigns.phonestore.model.data.PhoneBrands;

import java.util.ArrayList;
import java.util.List;

import static com.tommywebdesigns.phonestore.util.Logger.logDebug;

public class PhoneDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="phone.db";
    public static final String TABLE_NAME="Phone";
    public static final int DATABASE_VERSION=1;
    public static final String COLUMN_PHONE_ID="phone_id";
    public static final String COLUMN_PHONE_BRAND="phone_brand";
    public static final String COLUMN_PHONE_MODEL="phone_model";
    public static final String COLUMN_PRICE="price";

    public PhoneDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCommand="CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_PHONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_PHONE_BRAND+ " TEXT, "+
                COLUMN_PHONE_MODEL + " TEXT, " +
                COLUMN_PRICE+ " REAL) ";

        db.execSQL(createCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
      String delete= "DROP TABLE IF EXISTS " + TABLE_NAME;
      db.execSQL(delete);
      onCreate(db);

    }

    public void insertPhone(Phone phone){
     logDebug("A new phone object is inserting");
     //It's used to store set of values
     ContentValues contentValues= new ContentValues();
     //name() returns the name of the enum.
     contentValues.put(COLUMN_PHONE_BRAND, phone.getPhoneBrand().name());
     contentValues.put(COLUMN_PHONE_MODEL, phone.getModel());
     contentValues.put(COLUMN_PRICE, phone.getPrice());

      SQLiteDatabase db= getWritableDatabase();
      db.insert(TABLE_NAME, null, contentValues);
    }

    public List<Phone> getAllPhones(){
        logDebug("It's reading from the database.");

        List<Phone> phones= new ArrayList<>();
        String sql="SELECT * FROM " + TABLE_NAME;
        Cursor cursor= getReadableDatabase().rawQuery(sql, null);
        cursor.move(-1); //It's on -1, so it can start on 0.

        while(cursor.moveToNext()){
            Phone phone= new Phone(
            cursor.getInt(cursor.getColumnIndex(COLUMN_PHONE_ID)),
                    PhoneBrands.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_BRAND))),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_MODEL)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE))
            );
            phones.add(phone);
        }
        return phones;
    }

    public void deletePhone(Phone phone){
        String sql =TABLE_NAME + " WHERE " + COLUMN_PHONE_ID + " = " + phone.getPhoneId();
        getWritableDatabase().delete(sql,null, null);
    }



}
