package com.example.pomodothis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Statistics_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_layout);


        //updateTodayStats();
    }


    // Button to show all
    /*public void showmeall(View view) {
        ArrayList<String> stats = db.getAllStats();
        Toast.makeText(getApplicationContext(), stats.toString(), Toast.LENGTH_LONG).show();
    }*/


    // Update today's stats
    /*public void updateTodayStats() {
        // Getting the date and formatting it
        Format f = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String strDate = f.format(now);

        // Creating the sql update
        boolean isAdded = db.addToday(strDate, MainActivity.number_of_pomos, MainActivity.number_of_breaks); //(String today, int pomos, int breaks)
        //debug
        Toast.makeText(getApplicationContext(), strDate+ "is added ?" + isAdded, Toast.LENGTH_LONG).show();
    }*/
}