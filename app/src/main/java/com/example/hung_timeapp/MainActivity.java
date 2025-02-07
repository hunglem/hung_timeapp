package com.example.hung_timeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    TextView currenTime, currenDate;
    Handler handler;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dateFormat;
    public ImageButton imageButton;    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currenTime = findViewById(R.id.timeTextView);
        currenDate = findViewById(R.id.dateTextView);
        

        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat = new SimpleDateFormat("dd:MM:yyyy") ;

        currenDate.setText(dateFormat.format(new Date()));
        fab.setOnClickListener(view -> {
            Intent iAlam = new Intent(MainActivity.this, SetAlarm.class);
        });

        handler = new Handler();
        updateCurentTime();

        imageButton = (ImageButton) findViewById(R.id.imageView);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,SetAlarm.class);
                startActivity(intent);
            }
        });
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