package com.example.MyBlogs;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class My_Services extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        cloudservices(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }
    public void cloudservices(String tittle,String message){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"mynotification")
                .setContentTitle(tittle)
                .setContentText(message)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_stat_name);
        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
        managerCompat.notify(111,builder.build());

    }
}
