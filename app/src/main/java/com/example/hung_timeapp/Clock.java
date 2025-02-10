package com.example.hung_timeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Clock extends AppCompatActivity {
    private TextView dateYear;
    private TextView digitalClock;
    private final Handler handler = new Handler();
    private Runnable runnable;
    public ImageButton clock1;
    public ImageButton clock3;
    public ImageButton clock4;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.clock);

        clock1 = findViewById(R.id.Clock1);
        clock3 = findViewById(R.id.Clock3);
        clock4 = findViewById(R.id.Clock4);
        dateYear = findViewById(R.id.dateYear);
        digitalClock = findViewById(R.id.digitalClock);

        clock1.setOnClickListener(view -> {
            Intent intent = new Intent(Clock.this, MainActivity.class);
            startActivity(intent);
        });

        clock3.setOnClickListener(view -> {
            Intent intent = new Intent(Clock.this, StopWatch.class);
            startActivity(intent);
        });

        clock4.setOnClickListener(view -> {
            Intent intent = new Intent(Clock.this, CountDown.class);
            startActivity(intent);
        });

        runnable = new Runnable() {
            @Override
            public void run() {
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                digitalClock.setText(currentTime);
            }
        };
        runnable = new Runnable() {
            @Override
            public void run() {
                String currentDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                dateYear.setText(currentDate);
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
