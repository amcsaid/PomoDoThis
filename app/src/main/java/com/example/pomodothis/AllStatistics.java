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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_statistics);


        db = new MyDB(this.getApplicationContext());

        // Declaring the recycler
        myrecycler = findViewById(R.id.recycler);
        // Declaring a list of all days from DB
        ArrayList<Today> liste = db.getAllStats();;

        // creating my adapter
        MyAdapterForStats rv_adapter = new MyAdapterForStats(this.getApplicationContext(), liste);

        //defining my recycler view
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this.getApplicationContext());
        myrecycler.setHasFixedSize(true);
        myrecycler.setLayoutManager(lm);
        myrecycler.setAdapter(rv_adapter);
    }
}