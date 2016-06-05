package com.msft.doze20;

<<<<<<< HEAD
import android.app.DatePickerDialog;
||||||| merged common ancestors
=======
import android.app.AlarmManager;
import android.app.PendingIntent;
>>>>>>> a9728ffe47d08ec7dd99e8714984e54044526949
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
        screenReceiver = new ScreenReceiver();
        registerReceiver(screenReceiver, filter);

        IntentFilter filter2 = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        filter2.addAction(Intent.ACTION_BATTERY_OKAY);
        batteryReceiver = new BatteryReceiver();
        registerReceiver(batteryReceiver, filter2);

        Intent alarmIntent = new Intent(this, updateToServer.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();

        startAlarm();
    }

<<<<<<< HEAD

    @Override
    protected void onPause() {
        if (ScreenReceiver.wasScreenOn) {
            System.out.println("SCREEN TURNED OFF Activity");
        }
||||||| merged common ancestors
    @Override
    protected void onPause(){
        if (ScreenReceiver.wasScreenOn){
            System.out.println("SCREEN TURNED OFF Activity");
        }
=======
    public void startAlarm() {
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 1000;
>>>>>>> a9728ffe47d08ec7dd99e8714984e54044526949

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarm() {
        if (manager != null) {
            manager.cancel(pendingIntent);
            //Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
<<<<<<< HEAD
    protected void onResume() {

        if (!ScreenReceiver.wasScreenOn)
            System.out.println("SCREEN TURNED ON Activity");
||||||| merged common ancestors
    protected void onResume(){

        if (!ScreenReceiver.wasScreenOn)
            System.out.println("SCREEN TURNED ON Activity");
=======
    protected void onResume(){
>>>>>>> a9728ffe47d08ec7dd99e8714984e54044526949
        super.onResume();
    }
<<<<<<< HEAD

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        String time = "You picked the following time: "+hourOfDay+"h"+minute;
        timeTextView.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        dateTextView.setText(date);
    }
||||||| merged common ancestors
=======

    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.print("Destroyed");
        unregisterReceiver(screenReceiver);
        unregisterReceiver(batteryReceiver);
        cancelAlarm();
    }
>>>>>>> a9728ffe47d08ec7dd99e8714984e54044526949
}
this.registerReceiver(this.batteryInfoReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
private BroadcastReceiver batteryInfoReceiver=new BroadcastReceiver(){
@Override
public void onReceive(Context context,Intent intent){
        // this is where we deal with the data sent from the battery.
        }
        };