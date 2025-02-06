package com.example.hung_timeapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.Clock;
import java.util.List;

public class SetAlarm extends AppCompatActivity {
    static final int ALARM_REQ_CODE = 0;
    private int jam, menit;
    private List<Clock> alarms;
    private AlarmAdapter alarmAdapter;
    private static final String PREFS_NAME = "AlarmPrefs";

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_set_alarm);

        FloatingActionButton bTimer = findViewById(R.id.timerButton);
        TimePicker tpAlarm = findViewById(R.id.tp);
        ListView listView = findViewById(R.id.lvAlarms);

        alarmAdapter = new AlarmAdapter(this,alarms);
        ListView.setAdapter(alarmAdapter);
        
        tpAlarm.setOnTimeChangedListener((View, hourOfDay , minute) -> {
            jam = hourOfDay;
            menit = minute;
        });
        bTimer.setOnClickListener(v -> {
            Clock alarm = null;
            alarms = new Clock(jam, menit, true);
            alarms.add(alarm);
            saveAlarms(alarms);
            alarmAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Alarm Set" + jam + " : " + menit, Toast.LENGTH_SHORT).show();
            setTimer();
            notification();
        });
    }

    private void saveAlarms(List<Clock> alarms) {
    }

    private void notification() {
    }

    private void setTimer() {
    }
}
