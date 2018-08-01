package com.gmail_yudistirosaputro.kamusenglish.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gmail_yudistirosaputro.kamusenglish.Model.BahasaModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.gmail_yudistirosaputro.kamusenglish.Database.DatabaseContract.KamusColum.KATA;
import static com.gmail_yudistirosaputro.kamusenglish.Database.DatabaseContract.KamusColum.KETERANGAN;
import static com.gmail_yudistirosaputro.kamusenglish.Database.DatabaseContract.TABLE_ID;

public class KamusIdHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public KamusIdHelper(Context context) {
        this.context = context;
    }
    public KamusIdHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<BahasaModel> getDataID(String kata){

        Cursor cursor = database.query(TABLE_ID,null,KATA+" LIKE  ?",new String[]{"%"+kata+"%"},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        ArrayList<BahasaModel> arrayList = new ArrayList<>();
        BahasaModel model;
        if (cursor.getCount()>0) {
            do {
                model= new BahasaModel();
                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                model.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                model.setKeterangan(cursor.getString(cursor.getColumnIndex(KETERANGAN)));

                arrayList.add(model);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(BahasaModel model){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(KATA, model.getKata());
        initialValues.put(KETERANGAN, model.getKeterangan());
        return database.insert(TABLE_ID, null, initialValues);
    }
    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }
}
