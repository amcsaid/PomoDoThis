package com.example.pomodothis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {
    private static final int VERSION_DB = 1;
    private static final String NAME_DB = "data";

    // Table for statistics
    private static final String STATS_TB_NAME = "stats";
    private static final String STATS_CLN_ID = "id";
    private static final String STATS_CLN_DATE = "date";
    private static final String STATS_CLN_POMO = "pomo";
    private static final String STATS_CLN_BREAK = "break";



    public MyDB(Context context){
        super(context,NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+STATS_TB_NAME+" ("+STATS_CLN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+ STATS_CLN_DATE+" TEXT," +
                " "+STATS_CLN_POMO+" INTEGER," +
                " "+STATS_CLN_BREAK+" INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+STATS_TB_NAME);
        onCreate(db);
    }

    public boolean addToday(Today today){
        String date = today.getDate();
        String pomos = String.valueOf(today.getPomos());
        String breaks = String.valueOf(today.getPomos());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valeurs = new ContentValues();

        valeurs.put(STATS_CLN_DATE    , date);
        valeurs.put(STATS_CLN_POMO   , pomos);
        valeurs.put(STATS_CLN_BREAK     , breaks);

        long result=db.insert(STATS_TB_NAME,null,valeurs);
        if(result!=-1) return true;
        else return false;
    }

    public boolean updateToday(Today today){
        SQLiteDatabase db = this.getWritableDatabase();

        //db.execSQL("UPDATE stats SET titre= "+livre.getYear()+" WHERE id="+livre.getId());

        ContentValues valeurs =new ContentValues();

        valeurs.put(STATS_CLN_POMO      , String.valueOf(today.getPomos()));
        valeurs.put(STATS_CLN_BREAK     , String.valueOf(today.getBreaks()));

        String[] args={today.getDate()+""};

        int result = db.update(STATS_TB_NAME, valeurs,"date=?",args);
        if(result!=-1) return true;
        else return false;
    }

    public ArrayList<Today> getAllStats() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Today> days = new ArrayList<>();
        Cursor mycursor = db.rawQuery("SELECT * FROM "+ STATS_TB_NAME, null);

        if(mycursor.moveToNext()) {
            do {
                days.add( new Today(mycursor.getString(1),
                                Integer.valueOf(mycursor.getString(2)),
                                Integer.valueOf(mycursor.getString(3))
                                )
                        );
            } while (mycursor.moveToNext());
        }
        return days;
    }

    public ArrayList<String> getAllStatsString() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> days = new ArrayList<>();
        Cursor mycursor = db.rawQuery("SELECT * FROM "+ STATS_TB_NAME, null);

        if(mycursor.moveToNext()) {
            do {
                days.add(
                        mycursor.getString(1) + ": "
                                + mycursor.getString(2) + ": "
                                + mycursor.getString(3) + ": "
                );
            } while (mycursor.moveToNext());
        }
        return days;
    }

    // Returns true if an entry for "Today" is already saved
    public boolean isToday(String date) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] args={date};
        Cursor mycursor = db.rawQuery("SELECT * FROM "+ STATS_TB_NAME + " WHERE "+STATS_CLN_DATE+"=?", args);

        if(mycursor.moveToNext()) {
            return true;
        }
        return false;
    }

    public Today getToday() {
        SQLiteDatabase db = this.getReadableDatabase();
        Today today = new Today();

        String[] args={today.getDate()};
        Cursor mycursor = db.rawQuery("SELECT * FROM "+ STATS_TB_NAME + " WHERE "+STATS_CLN_DATE+"=?", args);

        if(mycursor.moveToNext()) {
            do {
                today.setPomos(
                        Integer.valueOf(mycursor.getString(mycursor.getColumnIndex(STATS_CLN_POMO)))
                );
                today.setBreaks(
                        Integer.valueOf(mycursor.getString(mycursor.getColumnIndex(STATS_CLN_BREAK)))
                );
            } while (mycursor.moveToNext());
        }
        return today;
    }
}
