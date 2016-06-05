package com.msft.doze20;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Victoria Cheng on 2016-06-04.
 */
public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SQLiteOpenHelper helper = new SQLiteHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            System.out.println("BATTERY LOW");
            values.put(SQLiteHelper.COLUMN_TYPE, Constants.TYPE_BATTERY);
            values.put(SQLiteHelper.COLUMN_DATA, Constants.DATA_BATTERY_LOW);

        } else if (intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)) {
            System.out.println("BATTERY OKAY");
            values.put(SQLiteHelper.COLUMN_TYPE, Constants.TYPE_BATTERY);
            values.put(SQLiteHelper.COLUMN_DATA, Constants.DATA_BATTERY_OKAY);

        }

        db.insert(SQLiteHelper.TABLE_NAME, null, values);

        db.close();
    }
}
