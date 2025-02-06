package com.example.hung_timeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.time.Clock;
import java.util.List;

public class AlarmAdapter extends ArrayAdapter<Clock> {
    public AlarmAdapter(Context context, List<Clock> alarms) {super(context, 0, alarms);}
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Clock alarm = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.switch_alarm ,parent ,false);
        }
        TextView timeTV = convertView.findViewById(R.id.tvTime);
        Switch switchButton = convertView.findViewById(R.id.onOffSwich);

        timeTV.setText(alarm.getHour() + " : " + alarm.getMinute());

        switchButton.setChecked(alarm.isEnabled());
        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Clock alarm1 = getItem(position);
           alarm1.setEnabled(isChecked);
        });
        return convertView;
    }
}
