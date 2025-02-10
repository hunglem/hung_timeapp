package com.example.hung_timeapp;

import android.content.Intent;
import android.icu.text.MessageFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class StopWatch extends AppCompatActivity {
    TextView textView;
    MaterialButton reset, start, stop;
    int seconds, minutes, milliSecconds;
    long millisecond, startTime, timeBuff, upDateTime = 0L;
    Handler handler;
    public ImageButton clock1;
    public ImageButton clock2;
    public ImageButton clock4;

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecond = SystemClock.uptimeMillis() - startTime;
            upDateTime = timeBuff + millisecond;
            seconds = (int) (upDateTime / 1000);
            minutes = seconds /60;
            seconds = seconds % 60;
            milliSecconds =(int) (upDateTime % 1000);

            textView.setText(MessageFormat.format("{0}:{1}:{2}", minutes, String.format(Locale.getDefault(), "%02d", seconds), String.format(Locale.getDefault(), "%02d", milliSecconds)));
            handler.postDelayed(this, 0);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_watch);

        textView = findViewById(R.id.textView);
        reset = findViewById(R.id.resetButton);
        start = findViewById(R.id.startButton);
        stop = findViewById(R.id.stopButton);
        clock1 = findViewById(R.id.Clock1);
        clock2 = findViewById(R.id.Clock2);
        clock4 = findViewById(R.id.Clock4);


        handler = new Handler(Looper.getMainLooper());

        clock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(StopWatch.this, MainActivity.class);
                startActivity(intent);
            }});

        clock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(StopWatch.this, Clock.class);
                startActivity(intent);
            }});

        clock4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(StopWatch.this, CountDown.class);
                startActivity(intent);
            }});

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                reset.setEnabled(false);
                stop.setEnabled(true);
                start.setEnabled(false);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeBuff += millisecond;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
                stop.setEnabled(false);
                start.setEnabled(true);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                millisecond = 0L;
                startTime = 0L;
                timeBuff = 0L;
                upDateTime = 0L;
                minutes = 0;
                seconds = 0;
                milliSecconds = 0;
                textView.setText("00:00:00");
            }
        });
        textView.setText("00:00:00");
    }
}
