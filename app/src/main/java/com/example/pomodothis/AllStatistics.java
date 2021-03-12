package com.example.pomodothis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class AllStatistics extends AppCompatActivity {
    private RecyclerView myrecycler;
    private MyDB db ;
    MyAdapterForStats rv_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_statistics);

        db = new MyDB(this.getApplicationContext());
        // Declaring a list of all days from DB
        ArrayList<Today> liste = db.getAllStats();;


        // Declaring the recycler
        myrecycler = findViewById(R.id.recycler);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));

        // creating my adapter
        rv_adapter = new MyAdapterForStats(this, liste);

        //defining my recycler view
        //myrecycler.setHasFixedSize(true);

        myrecycler.setAdapter(rv_adapter);
    }
}