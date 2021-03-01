package com.example.pomodothis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    private TextView status;
    private Button startpomo, takebreak, reset;
    private ProgressBar mainprogbar;
    private CountDownTimer cdpromo, cdbreak;


    //int promoduration = 1500000;
    //int breakduration = 300000;

    int promoduration = 15000;
    int breakduration = 3000;
    private boolean work = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainprogbar=(ProgressBar) findViewById(R.id.loading_spinner);
        status = (TextView)findViewById(R.id.tomatostatus);
        startpomo = (Button)findViewById(R.id.startpomo);
        //takebreak = (Button)findViewById(R.id.takebreak);
        reset = (Button)findViewById(R.id.reset);

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

                } else {
                    cdpromo.cancel();
                    startpomo.setClickable(true);

                }

                mainprogbar.setProgress(0);
                status.setText("25:00");
            }
        });
    }
}