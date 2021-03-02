package com.example.pomodothis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Statistics_Activity extends AppCompatActivity {
    private TextView todayPomos, todayBreaks, totalPomos, totalBreaks;
    private MyDB db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_layout);

        // Find Views By ID
        todayPomos  =   (TextView) findViewById(R.id.todayPomos);
        todayBreaks =   (TextView) findViewById(R.id.todayBreaks);
        totalPomos  =   (TextView) findViewById(R.id.totalPomos);
        totalBreaks =   (TextView) findViewById(R.id.totalBreaks);

        // declating a new database
        db = new MyDB(this.getApplicationContext());

        this.updateTodayStats();
    }

    @Override
    protected void onRestart() {
        this.updateTodayStats();
        super.onRestart();
    }


    // Button to show all
    /*public void showmeall(View view) {
        ArrayList<String> stats = db.getAllStats();
        Toast.makeText(getApplicationContext(), stats.toString(), Toast.LENGTH_LONG).show();
    }*/


    // Update today's stats
    public void updateTodayStats() {
        // Getting the date and formatting it
        Today today = db.getToday();
        todayPomos.setText(String.valueOf(today.getPomos()));
        todayBreaks.setText(String.valueOf(today.getBreaks()));
    }
}