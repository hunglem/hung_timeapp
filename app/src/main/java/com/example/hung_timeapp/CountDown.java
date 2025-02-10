package com.example.hung_timeapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CountDown extends AppCompatActivity {
    TextView countDownTimer;
    CountDownTimer timer;
    Button start;
    public ImageButton clock1;
    public ImageButton clock2;
    public ImageButton clock3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_down);

        countDownTimer = findViewById(R.id.textView2);
        start = findViewById(R.id.startButton);
        clock1 = findViewById(R.id.Clock1);
        clock2 = findViewById(R.id.Clock2);
        clock3 = findViewById(R.id.Clock3);

        clock1.setOnClickListener(view -> {
            Intent intent = new Intent(CountDown.this, MainActivity.class);
            startActivity(intent);
        });

        clock2.setOnClickListener(view -> {
            Intent intent = new Intent(CountDown.this, Clock.class);
            startActivity(intent);
        });

        clock3.setOnClickListener(view -> {
            Intent intent = new Intent(CountDown.this, StopWatch.class);
            startActivity(intent);
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime();
            }


        });
    }

    private void startTime() {
        timer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long hours = (millisUntilFinished/1000) / 3600;
                long minute = ((millisUntilFinished/1000) % 3600) / 60;
                long seconds = (millisUntilFinished/1000) % 60;
                String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours,minute,seconds);
                countDownTimer.setText(timeFormatted);
            }

            @Override
            public void onFinish() {
                countDownTimer.setText("00:00:00");
                Toast.makeText(CountDown.this, "", Toast.LENGTH_SHORT).show();
                MediaPlayer alarm = android.media.MediaPlayer.create(CountDown.this, R.raw.victory);
                alarm.start();
            }
        }.start();
    }
}
