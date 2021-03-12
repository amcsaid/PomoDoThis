package com.example.pomodothis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView status;
    private Button startpomo, reset;
    private ProgressBar mainprogbar;
    private CountDownTimer cdpromo, cdbreak;
    private View myView;
    private MyDB db;

    //int promoduration = 1500000;
    //int breakduration = 300000;

    int promoduration = 3500;
    int breakduration = 2500;
    private boolean work = true;

    // today's stats
    Today todayObject = new Today();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDB(this.getApplicationContext());



        mainprogbar=(ProgressBar) findViewById(R.id.loading_spinner);
        status = (TextView)findViewById(R.id.tomatostatus);
        startpomo = (Button)findViewById(R.id.startpomo);
        //takebreak = (Button)findViewById(R.id.takebreak);
        reset = (Button)findViewById(R.id.reset);
        myView = findViewById(R.id.main_activity_id);

        // always start with work


        startpomo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View b) {
                int duration = 0;
                if (work) {
                    duration = promoduration;
                    startpomo.setText("TAKE A BREAK");
                } else {
                    duration = breakduration;
                    startpomo.setText("WORK MORE");
                }



                mainprogbar.setMax(duration);
                startpomo.setClickable(false);

                Format f = new SimpleDateFormat("mm:ss");

                cdpromo = new CountDownTimer(duration, 1000) {

                        public void onTick(long millisUntilFinished) {
                            status.setText(f.format(millisUntilFinished));
                            mainprogbar.setProgress((int) millisUntilFinished);
                            //here you can have your logic to set text to edittext
                        }

                        public void onFinish() {
                            status.setText("DONE!");
                            mainprogbar.setProgress(0);
                            startpomo.setClickable(true);

                            if (work) {
                                todayObject.incrementPomos();
                            } else {
                                todayObject.incrementBreaks();
                            }
                            work = !work;
                        }
                };
                cdpromo.start();
            }
        });

        reset.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View b) {
                if (!work) {
                    cdpromo.cancel();
                    startpomo.setClickable(true);
                    status.setText("05:00");

                } else {
                    cdpromo.cancel();
                    startpomo.setClickable(true);
                    status.setText("25:00");

                }
                mainprogbar.setProgress(0);
            }
        });
    }

    @Override
    protected void onPause() {
        this.saveTodayObject();
        super.onPause();
    }

    @Override
    protected void onResume() {
        this.todayObject = new Today();
        super.onResume();
    }


    /*** Activity methodes decaltrations ***/



    public void saveTodayObject() {
        if(db.isToday(todayObject.getDate())) {
            Today savedToday = db.getToday();

            todayObject.setBreaks(todayObject.getBreaks() + savedToday.getBreaks());
            todayObject.setPomos(todayObject.getPomos() + savedToday.getPomos());

            db.updateToday(todayObject);

        } else {
            Toast.makeText(getApplicationContext(), "Creating new object", Toast.LENGTH_SHORT).show();
            db.addToday(todayObject);
        }
    }

    public void ShowStats(View view) {
        Intent myIntent = new Intent(this, Statistics_Activity.class);
        startActivity(myIntent);
    }
}