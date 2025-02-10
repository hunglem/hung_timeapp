package com.example.hung_timeapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CountDown extends AppCompatActivity {

    private EditText countHour, countMinute, countSecond;
    private Button startButton, endtButton;
    private TextView tvTimer;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;
    private MediaPlayer mediaPlayer;
    public ImageButton clock1;
    public ImageButton clock2;
    public ImageButton clock3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countHour = findViewById(R.id.countHour);
        countMinute = findViewById(R.id.countMinute);
        countSecond = findViewById(R.id.countSecond);
        startButton = findViewById(R.id.startButton);
        endtButton = findViewById(R.id.endtButton);
        tvTimer = findViewById(R.id.tv_timer);
        clock1 = findViewById(R.id.Clock1);
        clock2 = findViewById(R.id.Clock2);
        clock3 = findViewById(R.id.Clock3);

        startButton.setOnClickListener(view -> startTimer());
        endtButton.setOnClickListener(view -> stopTimer());

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
    }

    private void startTimer() {
        if (isTimerRunning) {
            return;
        }

        int hours = getValueFromEditText(countHour);
        int minutes = getValueFromEditText(countMinute);
        int seconds = getValueFromEditText(countSecond);

        long timeInMillis = ((hours * 3600) + (minutes * 60) + seconds) * 1000L;

        if (timeInMillis == 0) {
            Toast.makeText(this, "Enter a valid time", Toast.LENGTH_SHORT).show();
            return;
        }

        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long totalSeconds = millisUntilFinished / 1000;
                long hoursLeft = totalSeconds / 3600;
                long minutesLeft = (totalSeconds % 3600) / 60;
                long secondsLeft = totalSeconds % 60;

                tvTimer.setText(String.format("%02d:%02d:%02d", hoursLeft, minutesLeft, secondsLeft));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("00:00:00");
                playAlarm();
                Toast.makeText(CountDown.this, "Time is up!", Toast.LENGTH_SHORT).show();
            }
        }.start();

        endtButton.setVisibility(View.VISIBLE);
        isTimerRunning = true;
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        tvTimer.setText("00:00:00");
        endtButton.setVisibility(View.GONE);
        isTimerRunning = false;
        stopAlarm();
    }

    private void playAlarm() {
        mediaPlayer = MediaPlayer.create(this, R.raw.victory);
        mediaPlayer.start();
    }

    private void stopAlarm() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private int getValueFromEditText(EditText editText) {
        String input = editText.getText().toString().trim();
        if (input.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(input);
    }
}
