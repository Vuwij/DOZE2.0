package com.msft.doze20;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver screenReceiver;

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
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.print("Destroyed");
        unregisterReceiver(screenReceiver);
    }
}
