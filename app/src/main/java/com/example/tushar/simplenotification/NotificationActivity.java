package com.example.tushar.simplenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This class fires 3 types of notifications on decive
 */
public class NotificationActivity extends AppCompatActivity {

    private EditText notiTitle;
    private EditText notiDesc;
    private static int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notiTitle = (EditText) findViewById(R.id.noti_title_edittext);
        notiDesc = (EditText) findViewById(R.id.noti_desc_edittext);
        Button submitSmall = (Button) findViewById(R.id.submit_small_noti_button);
        Button submitBig = (Button) findViewById(R.id.submit_big_noti_button);
        Button submitGroup = (Button) findViewById(R.id.submit_group_noti_button);

        submitSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               fireSmallNotification();
            }
        });
        submitBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireBigNotification();
            }
        });

        submitGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireGroupNotification();
            }
        });

    }

    /**
     * In this method we fire small individual notifications
     * Custom actions can also be added here
     */
    public void fireSmallNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        builder.setAutoCancel(true);
        builder.setContentTitle(notiTitle.getText().toString());
        builder.setContentText(notiDesc.getText().toString());

        Intent notificationIntent = new Intent(getApplicationContext(), SuccessActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        //Add custome action here for notification
        //builder.addAction(R.mipmap.ic_launcher,"Custom Action",contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    /**
     * This method fires Big Style notifications
     */
    public void fireBigNotification(){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationActivity.this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        builder.setAutoCancel(true);
        builder.setContentTitle(notiTitle.getText().toString());
        builder.setContentText(notiDesc.getText().toString());

        Bitmap bitmap_image = BitmapFactory.decodeResource(NotificationActivity.this.getResources(), R.mipmap.ic_launcher);
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);

        s.setBigContentTitle(notiTitle.getText().toString());
        s.setSummaryText(notiDesc.getText().toString());
        builder.setStyle(s);

        Intent notificationIntent = new Intent(getApplicationContext(), SuccessActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
        manager.cancel(0);
    }

    /**
     * This method fires group notifications
     */
    public void  fireGroupNotification(){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationActivity.this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        builder.setAutoCancel(true);
        builder.setContentTitle(notiTitle.getText().toString());
        builder.setContentText(notiDesc.getText().toString());
        builder.setGroup("123");
        builder.setGroupSummary(true);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(notiTitle.getText().toString());
        for (int i = 0; i < 3; i++) {
            inboxStyle.addLine(notiDesc.getText().toString()+i);
        }
        builder.setStyle(inboxStyle);
        Intent notificationIntent = new Intent(getApplicationContext(), SuccessActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        value++;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(value, builder.build());
    }
}
