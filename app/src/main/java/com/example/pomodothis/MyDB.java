package com.example.pomodothis;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public boolean addToday(String today, int pomos, int breaks){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valeurs = new ContentValues();

        valeurs.put(STATS_CLN_DATE    , today);
        valeurs.put(STATS_CLN_POMO   , pomos);
        valeurs.put(STATS_CLN_BREAK     , breaks);

        long result=db.insert(STATS_TB_NAME,null,valeurs);
        if(result!=-1) return true;
        else return false;
    }

    public boolean modToday(String today, int pomos, int breaks){
        SQLiteDatabase db = this.getWritableDatabase();

        //db.execSQL("UPDATE stats SET titre= "+livre.getYear()+" WHERE id="+livre.getId());

        ContentValues valeurs =new ContentValues();

        valeurs.put(STATS_CLN_DATE    , today);
        valeurs.put(STATS_CLN_POMO   , pomos);
        valeurs.put(STATS_CLN_BREAK     , breaks);

        String[] args={today+""};

        int result = db.update(STATS_TB_NAME, valeurs,"date=?",args);
        if(result!=-1) return true;
        else return false;
    }


}
