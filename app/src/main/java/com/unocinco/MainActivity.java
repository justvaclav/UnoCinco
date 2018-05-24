package com.unocinco;

import android.app.Fragment;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    long when = 1500000000000L;
    final String THEME = "80085";
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        Button btnRecords = (Button) findViewById(R.id.btnRecords);
        Fragment fragment = new Fragment();

        btnStart.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
            startActivity(intent);
        }});
        btnRecords.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }});

        ImageView imag = (ImageView) findViewById(R.id.imageView2);
        imag.setClickable(true);
        imag.setOnClickListener(this);
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        //ifContains();

        /*NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("UnoCinco")
                        .setContentText("А вы уже прошли вопрос дня? :D")
                        .setContentIntent(resultPendingIntent)
                        .setWhen(when)
                        .setShowWhen(true)
                        .addAction(R.mipmap.ic_launcher, "Открыть", resultPendingIntent);

        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (System.currentTimeMillis() > when) {
        notificationManager.notify(1, notification);}
        else {}*/
    }



    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.btnStart:
                i = new Intent(this, QuestActivity.class);
                startActivity(i);
                break;
            case R.id.btnRecords:
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;
            case R.id.btnStartChoice:
                i = new Intent(this, ChoiceActivityTva.class);
                startActivity(i);
                break;
            case R.id.imageView2:
                ImageView imag = (ImageView) findViewById(R.id.imageView2);
                imag.setClickable(true);
                mSettings = getSharedPreferences("myPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(THEME, String.valueOf(900));
                editor.apply();
                i = new Intent(this, QuestActivity.class);
                startActivity(i);
                Log.d("it works", "lol");
                MainActivity.super.finish();
        }
    }

    public void ifContains() {
        if(!mSettings.contains("myTheme")){
            SharedPreferences.Editor editor1 = mSettings.edit();
            editor1.putString("myTheme", String.valueOf(1));
            editor1.apply();
        }
        if(!mSettings.contains("myTime")){
            SharedPreferences.Editor editor1 = mSettings.edit();
            editor1.putString("myTime", String.valueOf(10));
            editor1.apply();
        }
    }

}
