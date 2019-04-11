package com.example.neglect.Services;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.text.format.Time;
import android.util.Log;

import com.example.neglect.Utility.My_Utility;

import java.io.ByteArrayOutputStream;

public class NotificationService extends NotificationListenerService {  
  
    Context context;  
  
    @Override  
  
    public void onCreate() {  
  
        super.onCreate();  
        context = getApplicationContext();  
  
    }  
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if(My_Utility.isFinished)
        {
            String pack = sbn.getPackageName();
            if(pack.equalsIgnoreCase("com.example.pendingintent"))
            {
                String key = sbn.getKey();
                NotificationService.this.cancelNotification(key);
                String ticker = "";
                if (sbn.getNotification().tickerText != null) {
                    ticker = sbn.getNotification().tickerText.toString();
                }
                Bundle extras = sbn.getNotification().extras;
                String title = extras.getString("android.title");
                String text = extras.getCharSequence("android.text").toString();
                int id1 = extras.getInt(Notification.EXTRA_SMALL_ICON);
                Bitmap id = sbn.getNotification().largeIcon;
//                Log.i("Current Time " , "else--------current -time in minute------->" + time.minute);
//                Log.i("Current Time " , "else---------myum------->" + My_Utility.minute + " ---------systime-------->" + systemtimesecond);
//                Log.i("Package", pack);
//                Log.i("Ticker", ticker);
//                Log.i("Title", title);
//                Log.i("Text", text);
                Intent msgrcv = new Intent("Msg");
                msgrcv.putExtra("package", pack);
                msgrcv.putExtra("ticker", ticker);
                msgrcv.putExtra("title", title);
                msgrcv.putExtra("text", text);
                if (id != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    id.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    msgrcv.putExtra("icon", byteArray);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);

            }
        }

    }
    @Override  
  
    public void onNotificationRemoved(StatusBarNotification sbn) {  
        Log.i("Msg","Notification Removed");

  
    }  
}  