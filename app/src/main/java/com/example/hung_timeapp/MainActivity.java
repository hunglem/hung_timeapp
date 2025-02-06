package com.example.hung_timeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    TextView currenTime, currenDate;
    Handler handler;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currenTime = findViewById(R.id.timeTextView);
        currenDate = findViewById(R.id.dateTextView);
        fab = findViewById(R.id.allamButton);

        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat = new SimpleDateFormat("dd:MM:yyyy") ;

        currenDate.setText(dateFormat.format(new Date()));
        fab.setOnClickListener(view -> {
            Intent iAlam = new Intent(MainActivity.this, SetAlarm.class);
        });

        handler = new Handler();
        updateCurentTime();
    }

        private void updateCurentTime() {
            currenTime.setText(timeFormat.format(new Date()));
            handler.postDelayed(this::updateCurentTime,1000);
    }

        @Override
        protected void onDestroy(){
            super.onDestroy();
            handler.removeCallbacksAndMessages(null);
    }

}