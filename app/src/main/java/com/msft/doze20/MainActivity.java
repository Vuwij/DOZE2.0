package com.msft.doze20;

import android.app.PendingIntent;

import android.app.DatePickerDialog;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver screenReceiver;
    BroadcastReceiver batteryReceiver;
    private PendingIntent pendingIntent;
    private AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);

        screenReceiver = new ScreenReceiver();
        registerReceiver(screenReceiver, filter);

        Intent alarmIntent = new Intent(this, updateToServer.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();

        startAlarm();
    }

    public void startAlarm() {
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000;
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

    public void cancelAlarm() {
        if (manager != null) {
            manager.cancel(pendingIntent);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.print("Destroyed");
        unregisterReceiver(screenReceiver);
        unregisterReceiver(batteryReceiver);
        cancelAlarm();
    }
}