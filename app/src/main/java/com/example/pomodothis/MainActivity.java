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

    CountDownTimer cdpromo, cdbreak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainprogbar=(ProgressBar) findViewById(R.id.loading_spinner);
        status = (TextView)findViewById(R.id.tomatostatus);
        startpomo = (Button)findViewById(R.id.startpomo);
        takebreak = (Button)findViewById(R.id.takebreak);
        reset = (Button)findViewById(R.id.reset);

        startpomo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View b) {
                int duration = 1500000;
                mainprogbar.setMax(duration);
                Format f = new SimpleDateFormat("mm:ss");
                takebreak.setClickable(false);
                    cdpromo = new CountDownTimer(duration, 1000) {

                        public void onTick(long millisUntilFinished) {
                            status.setText(f.format(millisUntilFinished));
                            mainprogbar.setProgress(duration - (int) millisUntilFinished);
                            //here you can have your logic to set text to edittext
                        }

                        public void onFinish() {
                            status.setText("Done! Take your break now, or do some exercises!");
                            takebreak.setClickable(true);
                        }
                    };
                cdpromo.start();
            }
        });

        takebreak.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View b) {
                int duration = 300000;
                mainprogbar.setMax(duration);
                Format f = new SimpleDateFormat("mm:ss");
                startpomo.setClickable(false);
                    cdbreak = new CountDownTimer(duration, 1000) {

                        public void onTick(long millisUntilFinished) {
                            status.setText(f.format(millisUntilFinished));
                            mainprogbar.setProgress(duration - (int) millisUntilFinished);
                            //here you can have your logic to set text to edittext
                        }

                        public void onFinish() {
                            status.setText("Get back to work!");
                            startpomo.setClickable(true);
                        }

                    };
                cdbreak.start();
            }
        });

        reset.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View b) {

                if( takebreak.isClickable() && startpomo.isClickable()) {

                } else if (takebreak.isClickable() && !startpomo.isClickable()) {
                    cdbreak.cancel();
                    startpomo.setClickable(true);
                } else if (!takebreak.isClickable() && startpomo.isClickable()) {
                    cdpromo.cancel();
                    takebreak.setClickable(true);
                }

                mainprogbar.setProgress(0);
                status.setText("25:00");
            }
        });
    }
}