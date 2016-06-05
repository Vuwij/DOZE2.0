package com.msft.doze20;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Telephony;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class updateToServer extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Updating", "to server");

        SQLiteOpenHelper helper = new SQLiteHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        final String[] projection = new String[]{
                SQLiteHelper.COLUMN_TIMESTAMP,
                SQLiteHelper.COLUMN_TYPE,
                SQLiteHelper.COLUMN_DATA
        };
        JSONObject jsonObject = new JSONObject();

        try {
            Cursor c = db.query(SQLiteHelper.TABLE_NAME, projection, null, null, null, null, null);
            c.moveToFirst();
            Log.d("fmlmfkew", "" + c.getCount());

            JSONArray array = new JSONArray();

            if (c.moveToFirst()){
                do {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("Timestamp", c.getString(0));
                    jsonObject1.put("type", c.getString(1));
                    jsonObject1.put("data", c.getString(2));
                    array.put(jsonObject1);
                } while (c.moveToNext());
            }
            c.close();
            jsonObject.put("data", array);
        } catch (Exception e) {
            e.printStackTrace();
        }

        helper.onUpgrade(db, 1, 2);

        Log.d("TAG",jsonObject.toString());
    }
}
