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
    private Button startpomo, takebreak;
    private ProgressBar mainprogbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = (TextView)findViewById(R.id.tomatostatus);
        startpomo = (Button)findViewById(R.id.startpomo);
        mainprogbar=(ProgressBar) findViewById(R.id.loading_spinner);


        startpomo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View b) {
                int duration = 1500000;
                Format f = new SimpleDateFormat("mm:ss");

                new CountDownTimer(duration, 1000) {

                    public void onTick(long millisUntilFinished) {
                        status.setText(f.format(millisUntilFinished));
                        mainprogbar.setProgress(duration - (int) millisUntilFinished);
                        //here you can have your logic to set text to edittext
                    }

                    public void onFinish() {
                        status.setText("done!");
                    }

                }.start();

                //update_status();
            }
        });
    }

    private void update_status()
    {
        status.setText("WORKING");
        startpomo.setEnabled(false);
        takebreak.setVisibility(View.VISIBLE);
    }


}