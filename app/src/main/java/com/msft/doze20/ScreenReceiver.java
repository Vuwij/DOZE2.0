package com.msft.doze20;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ScreenReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        SQLiteOpenHelper helper = new SQLiteHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            System.out.println("SCREEN TURNED OFF");

            values.put(SQLiteHelper.COLUMN_TYPE, Constants.TYPE_SCREEN);
            values.put(SQLiteHelper.COLUMN_DATA, Constants.DATA_SCREEN_OFF);

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            System.out.println("SCREEN TURNED ON");

            values.put(SQLiteHelper.COLUMN_TYPE, Constants.TYPE_SCREEN);
            values.put(SQLiteHelper.COLUMN_DATA, Constants.DATA_SCREEN_ON);

        } else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            System.out.println("Power Connected");

            values.put(SQLiteHelper.COLUMN_TYPE, Constants.TYPE_CHARGING);
            values.put(SQLiteHelper.COLUMN_DATA, Constants.DATA_CHARGING_ON);

        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            System.out.println("Power Disconnected");

            values.put(SQLiteHelper.COLUMN_TYPE, Constants.TYPE_CHARGING);
            values.put(SQLiteHelper.COLUMN_DATA, Constants.DATA_CHARGING_OFF);
        }

        db.insert(SQLiteHelper.TABLE_NAME, null, values);
        db.close();
    }
}
