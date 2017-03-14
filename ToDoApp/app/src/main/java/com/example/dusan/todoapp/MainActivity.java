package com.example.dusan.todoapp;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  private ListFragment mListFragment;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mListFragment = new ListFragment();
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.fragment_holder, mListFragment);
    fragmentTransaction.commit();

    scheduleNotification(buildNotification("Test"), 500);
  }

  // TEST CODE
  private void scheduleNotification(Notification notification, int delay)
  {
    Intent notificationIntent = new Intent(this, NotificationBroadcastReceiver.class);
    notificationIntent.putExtra(NotificationBroadcastReceiver.NOTIFICATION_ID, 1);
    notificationIntent.putExtra(NotificationBroadcastReceiver.NOTIFICATION, notification);
    PendingIntent pendingIntent =
        PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    long futureInMillis = SystemClock.elapsedRealtime() + delay;
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
  }

  private Notification buildNotification(String text)
  {
    Notification.Builder builder = new Builder(this);
    builder.setContentTitle("My notification");
    builder.setContentText(text);
    builder.setSmallIcon(R.mipmap.ic_launcher);

    Intent resultIntent = new Intent(this, MainActivity.class);
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    stackBuilder.addParentStack(MainActivity.class);
    stackBuilder.addNextIntent(resultIntent);

    PendingIntent resultPendingIntent =
        stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        );

    builder.setContentIntent(resultPendingIntent);

    return builder.build();
  }
}
