package com.msft.doze20;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver{

    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // DO WHATEVER YOU NEED TO DO HERE
            wasScreenOn = false;
            System.out.println("SCREEN TURNED OFF");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // AND DO WHATEVER YOU NEED TO DO HERE
            wasScreenOn = true;
            System.out.println("SCREEN TURNED ON");
        } else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            System.out.println("Power Connected");
        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            System.out.println("Power Disconnected");
        }
    }
}
