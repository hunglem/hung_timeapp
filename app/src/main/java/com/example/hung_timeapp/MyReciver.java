package com.example.hung_timeapp;

import static com.example.hung_timeapp.SetAlarm.ALARM_REQ_CODE;

import android.Manifest;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyReciver extends AppCompatActivity {
    static final int ALLARM_REQ_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onReceive(Context context, Intent intent){
        Vibrator vib = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        vib.vibrate(2000);

        Intent iBroadCast = new Intent(context, SetAlarm .class);
        iBroadCast.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getBroadcast(context, ALARM_REQ_CODE, iBroadCast, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Notify")
                .setContentTitle("alam clock")
                .setContentText("day di ong chau oi")
                .setSmallIcon(R.drawable.clock1)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi)
                .setAutoCancel(true);
        NotificationManagerCompat nnConpat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            nnConpat.notify(200, builder.build());
            Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/" + R.raw.alarm_tone):

            Ringtone ringtone = RingtoneManager.getRingtone(context, sound);
            ringtone.play();
        }
    }
}