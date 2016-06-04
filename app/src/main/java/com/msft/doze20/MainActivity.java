package com.msft.doze20;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    protected void onPause(){
        if (ScreenReceiver.wasScreenOn){
            System.out.println("SCREEN TURNED OFF Activity");
        }

        super.onPause();
    }

    @Override
    protected void onResume(){

        if (!ScreenReceiver.wasScreenOn)
            System.out.println("SCREEN TURNED ON Activity");
        super.onResume();
    }
}
