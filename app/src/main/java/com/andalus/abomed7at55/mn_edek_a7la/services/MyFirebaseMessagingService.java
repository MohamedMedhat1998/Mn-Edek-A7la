package com.andalus.abomed7at55.mn_edek_a7la.services;

import android.app.PendingIntent;
import android.content.Intent;

import com.andalus.abomed7at55.mn_edek_a7la.RecentActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getNotification() != null){
            sendNotification(remoteMessage.getNotification().getClickAction());
        }
    }

    private void sendNotification(String activityName){
        if(activityName.equals("RECENT_ACTIVITY")){
            Intent intent = new Intent(this, RecentActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,5,intent,PendingIntent.FLAG_ONE_SHOT);
        }

    }
}
