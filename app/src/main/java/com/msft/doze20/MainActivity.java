package com.msft.doze20;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        BroadcastReceiver screenReceiver = new ScreenReceiver();
        registerReceiver(screenReceiver, filter);
    }


    @Override
    protected void onPause() {
        if (ScreenReceiver.wasScreenOn) {
            System.out.println("SCREEN TURNED OFF Activity");
        }

        super.onPause();
    }

    @Override
    protected void onResume() {

        if (!ScreenReceiver.wasScreenOn)
            System.out.println("SCREEN TURNED ON Activity");
        super.onResume();
    }

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
}
this.registerReceiver(this.batteryInfoReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
private BroadcastReceiver batteryInfoReceiver=new BroadcastReceiver(){
@Override
public void onReceive(Context context,Intent intent){
        // this is where we deal with the data sent from the battery.
        }
        };